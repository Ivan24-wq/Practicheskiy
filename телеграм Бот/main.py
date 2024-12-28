import asyncio
from aiogram import Bot, Dispatcher, types
from aiogram.types import ReplyKeyboardMarkup, KeyboardButton
from aiogram.filters import Command
import pymongo
from pymongo import MongoClient
import datetime
import logging

# Логирование
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Токен Telegram API
API_TOKEN = "7221571794:AAHavgln73Hx4OfrCLwbAvczkptlTMpJVLI"

# Подключение к MongoDB
client = MongoClient("mongodb+srv://Darkivan:Python_aiogram_20@cluster0.jj6jc.mongodb.net/Groupproject?retryWrites=true&w=majority&appName=Cluster0")
db = client['Groupproject']
polls_collection = db['polls']

# Инициализация бота и диспетчера
bot = Bot(token=API_TOKEN)
dp = Dispatcher()

# Активные опросы
active_poll = {}

# Клавиатуры
start_keyboard = ReplyKeyboardMarkup(
    keyboard=[
        [KeyboardButton(text="📊 Пройти опрос")],
        [KeyboardButton(text="➕ Создать опрос")]
    ],
    resize_keyboard=True
)

finish_keyboard = ReplyKeyboardMarkup(
    keyboard=[[KeyboardButton(text="✅ Завершить создание опроса")]],
    resize_keyboard=True
)

# Команда /start
@dp.message(Command(commands=["start"]))
async def start_command(message: types.Message):
    await message.answer(
        "Добро пожаловать в бота для проведения розыгрышей!\nВыберите действие ниже:",
        reply_markup=start_keyboard
    )

# Начало создания опроса
@dp.message(lambda message: message.text == "➕ Создать опрос")
async def create_poll_start(message: types.Message):
    active_poll[message.from_user.id] = {"questions": [], "state": "waiting_for_question"}
    await message.answer("Введите вопрос для создания опроса:", reply_markup=finish_keyboard)

# Добавление вопроса в опрос
@dp.message(lambda message: message.from_user.id in active_poll and active_poll[message.from_user.id]["state"] == "waiting_for_question")
async def add_poll_question(message: types.Message):
    if message.text == "✅ Завершить создание опроса":
        await finish_poll_creation(message)
        return

    active_poll[message.from_user.id]["questions"].append({"question": message.text, "options": []})
    active_poll[message.from_user.id]["state"] = "waiting_for_options"
    await message.answer(f"Вопрос добавлен: {message.text}\nТеперь введите варианты ответа через запятую:")

# Добавление вариантов ответа
@dp.message(lambda message: message.from_user.id in active_poll and active_poll[message.from_user.id]["state"] == "waiting_for_options")
async def add_poll_options(message: types.Message):
    if message.text == "✅ Завершить создание опроса":
        await finish_poll_creation(message)
        return

    options = [opt.strip() for opt in message.text.split(",")]
    active_poll[message.from_user.id]["questions"][-1]["options"] = options
    active_poll[message.from_user.id]["state"] = "waiting_for_question"
    await message.answer(f"Варианты добавлены: {', '.join(options)}\nВведите следующий вопрос или завершите создание опроса.")

# Сохранение опроса в базу данных
async def save_poll_to_db(user_id, questions):
    poll_data = {
        "user_id": user_id,
        "questions": questions,
        "created_at": datetime.datetime.now()
    }
    result = polls_collection.insert_one(poll_data)
    return result.inserted_id

# Завершение создания опроса
async def finish_poll_creation(message: types.Message):
    user_id = message.from_user.id
    if user_id not in active_poll or not active_poll[user_id]["questions"]:
        await message.answer("Вы ещё не добавили ни одного вопроса!", reply_markup=start_keyboard)
        return

    try:
        # Сохранение опроса
        poll_id = await save_poll_to_db(user_id, active_poll[user_id]["questions"])
        
        # Создание сводки
        poll_summary = "\n".join(
            f"{i}. {question['question']}\nВарианты: {', '.join(question['options'])}"
            for i, question in enumerate(active_poll[user_id]["questions"], 1)
        )
        
        # Отправка результатов
        await message.answer(f"Опрос завершен и сохранен в базе данных! ID опроса: {poll_id}\nВот ваш опрос:\n{poll_summary}")
    except Exception as e:
        logger.error(f"Ошибка при сохранении опроса: {e}")
        await message.answer("Произошла ошибка при сохранении опроса. Попробуйте снова.")
    finally:
        # Очистка состояния
        del active_poll[user_id]
        await message.answer("Вы вышли из режима создания опроса.", reply_markup=start_keyboard)

# Пройти опрос
@dp.message(lambda message: message.text == "📊 Пройти опрос")
async def take_poll(message: types.Message):
    polls = list(polls_collection.find())
    if not polls:
        await message.answer("На данный момент нет доступных опросов.", reply_markup=start_keyboard)
        return

    polls_keyboard = ReplyKeyboardMarkup(
        resize_keyboard=True, 
        one_time_keyboard=True,
        keyboard=[[KeyboardButton(f"Опрос от {poll['user_id']}")] for poll in polls]
    )
    await message.answer("Выберите опрос, чтобы начать:", reply_markup=polls_keyboard)

# Основной запуск
async def main():
    await bot.delete_webhook(drop_pending_updates=True)
    logger.info("Бот запущен!")
    await dp.start_polling(bot)

if __name__ == "__main__":
    asyncio.run(main())

import asyncio
from aiogram import Bot, Dispatcher, types
from aiogram.filters import Command
from motor.motor_asyncio import AsyncIOMotorClient

# Конфигурация
BOT_TOKEN = "7984540566:AAGw0uBSlnhHyAaBWFUsLU9fGFaNsjpbb0s"
MONGO_URI = "mongodb+srv://Darkivan:Python_aiogram_20@cluster0.jj6jc.mongodb.net/Groupproject?retryWrites=true&w=majority&appName=Cluster0"
DATABASE_NAME = "Groupproject"
COLLECTION_NAME = "polls"

# Инициализация бота и базы данных
bot = Bot(token=BOT_TOKEN)
dp = Dispatcher()

# Настраиваем клиент MongoDB
mongo_client = AsyncIOMotorClient(MONGO_URI)
db = mongo_client[DATABASE_NAME]
collection = db[COLLECTION_NAME]

# Хендлер для команды /start
@dp.message(Command("start"))
async def start_command(message: types.Message):
    await message.reply("Привет! Я бот, который работает с MongoDB.\nНапишите /polls, чтобы получить опросы из базы данных.")

# Хендлер для вывода опросов
@dp.message(Command("polls"))
async def send_polls(message: types.Message):
    data = await collection.find().to_list(100)  # Получаем максимум 100 записей
    if not data:
        await message.reply("В базе данных нет опросов.")
        return

    # Формируем голосования
    for item in data:
        questions = item.get("questions", [])
        for question_data in questions:
            question = question_data.get("question", "Вопрос отсутствует")
            options = question_data.get("options", [])
            
            # Отправляем голосование
            if options:
                await bot.send_poll(
                    chat_id=message.chat.id,
                    question=question,
                    options=options,
                    is_anonymous=False  # Убираем анонимность
                )
            else:
                await message.reply(f"Опрос \"{question}\" не имеет вариантов ответа.")

# Хендлер для добавления примера данных в базу
@dp.message(Command("add_sample"))
async def add_sample_data(message: types.Message):
    try:
        # Пример данных для добавления
        new_data = {
            "user_id": 7641489602,
            "questions": [
                {
                    "question": "Какое сегодня число?",
                    "options": ["28 декабря", "29 декабря"],
                    "created_at": "2024-12-28T19:06:33.683+00:00"
                }
            ]
        }
        result = await collection.insert_one(new_data)
        await message.reply(f"Пример данных добавлен с ID: {result.inserted_id}")
    except Exception as e:
        await message.reply(f"Ошибка при добавлении данных: {e}")

# Запуск бота
async def main():
    try:
        await dp.start_polling(bot)
    finally:
        await bot.session.close()
        mongo_client.close()

if __name__ == "__main__":
    asyncio.run(main())

#include<iostream>
using namespace std;

//Функция сортировки методом вставки
void insertionSort(int arr[], int n, bool ascending) {   // параметр, определяющий порядок сортировки
	for (int i = 1; i < n; i++) {
		int key = arr[i];
		int j = i - 1;

		//Цикл для сравнения для вставки элемента на нужное место
		while (j >= 0 && (ascending ? arr[j] > key : arr[j] < key)) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = key;  // вставка элемента на правильное место

	}
}


int main() {
	setlocale(LC_ALL, "RU");
	int n;
	cout << "Введите кол-во элементов массива: ";
	cin >> n;
	if (n <= 0) {
		cout << "Размер массива должен быть положительным! ";
		return 0;
	}

	int* arr = new int[n]; // Динамическое выделение пвмяти для массива
	cout << "Введите элементы массива: ";
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	int order;
	cout << "Выберите порядок сортировки (1- по возрастанию; 2- по убыванию)";
	cin >> order;

	if (order == 1) {
		insertionSort(arr, n, true);  // Сортировка по возрастанию
		cout << "Отсортированный массив: ";

	}
	else if (order == 2) {
		insertionSort(arr, n, false);  // Сортировка по убыванию
		cout << "Отсортированный массив: ";
	}
	else {
		cout << "Неверный выбор порядка сортировки" << endl;
		delete[] arr;
		return 0;
	}

	for (int i = 0; i < n; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;

	delete[] arr; //Освобождаем память
	return 0;

}
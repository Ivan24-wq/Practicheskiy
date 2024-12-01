#include <iostream>
#include <vector>

using namespace std;

// Функция для циклической перестановки подквадратов
void cyclic_shift(vector<vector<int>>& matrix) {  //Примимаем ссылку на матрицу для ёё модификации
    int n = matrix.size();
    if (n % 2 != 0 || n == 0) {
        throw invalid_argument("Размер матрицы должен быть четным и больше нуля"); // проверка учловия( чтоьы матрица была чётная)
    }
    int half_n = n / 2;

    // Вспомогательный массив для временного хранения элементов
    vector<vector<int>> temp_matrix(n, vector<int>(n));

    // Перестановка подквадратов
    for (int i = 0; i < half_n; ++i) {
        for (int j = 0; j < half_n; ++j) {
            temp_matrix[i][j] = matrix[i + half_n][j + half_n];  // a4 -> a1
            temp_matrix[i + half_n][j] = matrix[i][j + half_n];    // a2 -> a3
            temp_matrix[i][j + half_n] = matrix[i + half_n][j];    // a3 -> a2
            temp_matrix[i + half_n][j + half_n] = matrix[i][j];    // a1 -> a4
        }
    }

    // Копирование временного массива в исходный
    matrix = temp_matrix;
}

int main() {
    setlocale(LC_ALL, "RU");
    int n;
    cout << "Введите размер матрицы (четное число): ";
    cin >> n;

    // Создание и заполнение матрицы (можно изменить на другой способ заполнения)
    vector<vector<int>> matrix(n, vector<int>(n));
    int count = 1;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            matrix[i][j] = count++;
        }
    }

    cout << "Исходная матрица:" << endl;
    for (const auto& row : matrix) {
        for (int val : row) {
            cout << val << " ";
        }
        cout << endl;
    }

    try {
        cyclic_shift(matrix);
        cout << "\nМатрица после циклической перестановки:" << endl;
        for (const auto& row : matrix) {
            for (int val : row) {
                cout << val << " ";
            }
            cout << endl;
        }
    }
    catch (const invalid_argument& e) {
        cerr << "Ошибка: " << e.what() << endl;
    }

    return 0;
}
// Код
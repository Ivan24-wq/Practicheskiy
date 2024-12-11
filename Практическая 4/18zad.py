import math

x = float(input('Введите x: '))
e = float(input('Введите точность e: '))

if not (-1 <= x < 1):
    print("Ошибка: x должен быть в диапазоне [-1, 1)")
else:
    result = 0
    n = 1
    num = -x

    while abs(num) >= e:
        result += num
        n += 1
        num = - (x**n) / n  

    exact_value = math.log(1 - x)    
    result_rounded = round(result, int(e)) 

    print(f'Приближенное значение суммы = {result_rounded:}')
    print(f'Точное значение функции ln(1 - x) = {exact_value:.3f}')
    print(f'Разница между приближенным и точным значением = {abs(result_rounded - exact_value):.3f}')
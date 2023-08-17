### Программа модуля

5. **Массивы и коллекции**  
    5.1. Создание массивов  
    5.2. Перебор элементов массивов  
    5.3. Обход массивов в обратном порядке  
    5.4. Массивы массивов  
    5.5. [Домашняя работа 5.1](#55-домашняя-работа-51)  
    5.6. Списки элементов  
    5.7. [Домашняя работа 5.2](#57-домашняя-работа-52)  
    5.8. Наборы уникальных элементов  
    5.9. [Домашняя работа 5.3](#59-домашняя-работа-53)  
    5.10. HashMap и TreeMap  
    5.11. [Домашняя работа 5.4](#511-домашняя-работа-54)  
    5.12. Поиск и сортировка  
    5.13. [Домашняя работа 5.5](#513-домашняя-работа-55)  
    
EXAMPLE_5_1: [LessonMap](./LessonMap) (5.10. HashMap и TreeMap)
    
TASK_5_1_1: [ReverseArray](./ReverseArray)  
TASK_5_1_2: [HospitalAverageTemperature](./HospitalAverageTemperature)  
TASK_5_1_3: [ArrayX](./ArrayX)  
TASK_5_2: [ToDoList](./ToDoList)  
TASK_5_3: [EmailList](./EmailList)  
TASK_5_4: [PhoneBook](./PhoneBook)  
TASK_5_5: [CoolCarNumber](./CoolCarNumber)  
    
---------------------------------------------

#### 5.5. Домашняя работа 5.1

**Цель задания**

Получить опыт работы с массивами.

**Задание №1**  
**Что нужно сделать**

**1.** Создайте массив из мнемонической фразы:

Каждый охотник желает знать, где сидит фазан.

**2.** Напишите код, который меняет порядок расположения элементов внутри массива на обратный.

**Пример**

Первоначальный массив: [“a”, “b”, “c”, “d”].

Массив с элементами в обратном порядке: [“d”, “c”, “b”, “a”].

**Решение**

TASK_5_1_1: [ReverseArray](./ReverseArray)

**Критерии оценки**

«Зачёт» — при прямом переборе и печати элементов массива в консоль элементы распечатаны в обратном порядке.  
«Незачёт» — задание не выполнено.

**Задание №2**  
**Что нужно сделать**

1.  Создайте массив типа **float** с температурами 30 пациентов (от 32 до 40 градусов).
2.  Напишите код, который выводит среднюю температуру по больнице и количество здоровых пациентов (с температурой от 36,2 до 36,9), а также температуры всех пациентов.

Вынесите в константы условия задания:

- количество пациентов,
- минимальная и максимальная температура пациентов,
- минимальная и максимальная температура диапазона здоровых пациентов.

**Пример**

    Температуры пациентов: 36.7 38.9 34.7  
    Средняя температура: 36.76  
    Количество здоровых: 1

**Решение**

TASK_5_1_2: [HospitalAverageTemperature](./HospitalAverageTemperature)

**Критерии оценки**

«Зачёт» — в консоль выводится правильная информация о пациентах.  
«Незачёт» — задание не выполнено.

**Дополнительное задание***

**Цель задания**

Закрепить навыки работы с массивами, используя двумерный массив.

**Что нужно сделать**

Создайте с помощью циклов двумерный массив строк. При его распечатке в консоли должен выводиться крестик из X:

    x     x
     x   x
      x x
       x
      x x
     x   x
    x     x

**Решение**

TASK_5_1_3: [ArrayX](./ArrayX)

**Критерии оценки**

«Зачёт» — программа выводит фигуру в консоль.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 5.7. Домашняя работа 5.2

**Цель задания**

Научиться работать со списком ArrayList.

**Что нужно сделать**

Разработайте список дел, который управляется командами в консоли. Команды: LIST, ADD, EDIT, DELETE.

Принцип работы команд:

- LIST — выводит дела с их порядковыми номерами;
- ADD — добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер;
- EDIT — заменяет дело с указанным номером;
- DELETE — удаляет.

**Примеры команд**

- `LIST`
- `ADD Какое-то дело`
- `ADD 4 Какое-то дело на четвёртом месте`
- `EDIT 3 Новое название дела`
- `DELETE 7`

Команды вводятся одной строкой пользователем в консоль.

**Решение**

TASK_5_2: [ToDoList](./ToDoList)

**Критерии оценки**

«Зачёт» — реализованы все команды для работы со списком дел.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 5.9. Домашняя работа 5.3

**Цель задания**

Научиться работать со множеством TreeSet.

**Что нужно сделать**

1.  Напишите программу, в которой будет храниться перечень адресов электронной почты. Адреса можно добавлять через консоль командой ADD и печатать весь список командой LIST.
2.  Программа должна проверять корректность вводимых email’ов и печатать сообщение об ошибке при необходимости.

**Принцип работы команд**

- LIST — выводит список электронных адресов.
- ADD — проверяет и, если формат адреса верный, добавляет в множество.

**Примеры команд**

- `LIST`
- `ADD hello@skillbox.ru`

Команды вводятся одной строкой пользователем в консоль.

**Решение**

TASK_5_3: [EmailList](./EmailList)

**Критерии оценки**

«Зачёт» — реализованы вывод списка и добавление электронного адреса с проверкой формата.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 5.11. Домашняя работа 5.4

**Цель задания**

Научиться работать с коллекцией Map.

**Что нужно сделать**

**1.** Напишите программу, которая будет работать как телефонная книга:

- Если пишем новое имя, программа просит ввести номер телефона и запоминает его. Если новый номер телефона — просит ввести имя и также запоминает.
- Если вводим существующее имя или номер телефона, программа выводит всю информацию о контакте.
- При вводе команды LIST программа печатает в консоль список всех абонентов в алфавитном порядке с номерами.

**2.** Определяйте имя и телефон с помощью регулярных выражений.

**3.** Подумайте, что выбрать в качестве ключа и значения для Map, выберите лучший вариант по вашему мнению. Опишите, какие минусы и плюсы видите в вашем выборе.

**Решение**

TASK_5_4: [PhoneBook](./PhoneBook)

**Критерии оценки**

«Зачёт» — телефонная книга работает согласно условиям.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 5.13. Домашняя работа 5.5

**Цель задания**

Научиться сортировать и искать элементы в коллекциях.

**Что нужно сделать**

**1.** Напишите генератор «красивых» автомобильных номеров и методы поиска элементов в коллекциях:

- прямым перебором по ArrayList,
- бинарным поиском по сортированному ArrayList,
- поиском в HashSet,
- поиском в TreeSet.

**2.** Измерьте и сравните длительность каждого метода поиска.

Формат вывода результатов поиска:

Поиск перебором: номер найден/не найден, поиск занял 34нс

Бинарный поиск: номер найден/не найден, поиск занял 34нс

Поиск в HashSet: номер найден/не найден, поиск занял 34нс

Поиск в TreeSet: номер найден/не найден, поиск занял 34нс

**3.** Напишите в форме ответа, какой поиск — самый быстрый, а какой — самый медленный.

В видео [Поиск и сортировка](https://go.skillbox.ru/course/java-s-nulya/0f096167-e333-4c25-bf93-fadd13393e38) неточно указана оценка результата работы метода бинарного поиска Collections.binarySearch(). Метод возвращает int, если возвращаемое значение больше или равно нулю — это означает, что элемент найден. Если возвращаемое значение int меньше нуля — элемент в коллекции не найден.

**Рекомендации**

- Сортировка не входит в учёт времени для бинарного поиска.
- Для детального сравнения методов поиска используйте время в наносекундах:

`System.nanoTime()`

- Используйте правила генерации номеров для получения более 2 млн номеров:

XYZ — различные буквы, N — цифры, R — регион (от 01 до 199);

XNNNYZR — _пример, A111BC197, Y777HC66_,

таким образом, количество номеров будет достаточно для оценки времени поиска даже в миллисекундах.

**Решение**

TASK_5_5: [CoolCarNumber](./CoolCarNumber)

**Критерии оценки**

«Зачёт» — при вводе в консоль автомобильного номера программа однозначно отвечает, найден ли номер в каждой из коллекций.  
«Незачёт» — задание не выполнено.
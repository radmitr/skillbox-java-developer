### Программа модуля

18. **Бонус-модуль. Алгоритмы**  
    18.1. Введение в алгоритмы  
    18.2. Зачем нужны алгоритмы  
    18.3. Рекурсивные алгоритмы  
    18.4. Временная сложность алгоритмов  
    18.5. Алгоритм бинарного поиска  
    18.6. Алгоритм сортировки пузырьком  
    18.7. Алгоритм сортировки *QuickSort*  
    18.8. Алгоритм сортировки *MergeSort*  
    18.9. Алгоритм поиска подстроки Рабина-Карпа  
    18.10. Алгоритм поиска подстроки Кнута-Морриса-Пратта  
    18.11. Алгоритм поиска подстроки Бойера-Мура  
    18.12. [Домашняя работа 18.1](./#1812-домашняя-работа-181)  
    
EXAMPLE_18_1: [Algorithms](./Algorithms)

TASK_18_1_1: [Algorithms/ArrayMaxValue.java](./Algorithms/src/main/java/array_max_value/ArrayMaxValue.java)  
TASK_18_1_3: [Algorithms/BinarySearch.java](./Algorithms/src/main/java/binary_search/BinarySearch.java)  
TASK_18_1_4: [Algorithms/BubbleSortTest.java](./Algorithms/src/test/java/bubble_sort/BubbleSortTest.java)  
TASK_18_1_5: [Algorithms/QuickSort.java](./Algorithms/src/main/java/quick_sort/)  
TASK_18_1_6: [Algorithms/MergeSort.java](./Algorithms/src/main/java/merge_sort/)  
TASK_18_1_7: [Algorithms/RabinKarpExtended.java](./Algorithms/src/main/java/rabin_karp/)  

----------------------------------------------------

#### 18.12 Домашняя работа 18.1

**Цель заданий**

Ознакомиться с алгоритмами на практике, доработав недостающие методы.

**Что нужно сделать**

**Задание 1**

В проекте **Algorithms** (**ArrayMaxValue.java**) в коде алгоритма поиска наибольшего числа в массиве не предусмотрена ситуация, которая может приводить к ошибке. Перепишите код метода таким образом, чтобы он учитывал эту ситуацию.

**Решение**

TASK_18_1_1: [Algorithms/ArrayMaxValue.java](./Algorithms/src/main/java/array_max_value/ArrayMaxValue.java)

**Задание 2**

Используя нотацию «O большое», напишите, какова будет временная сложность следующих алгоритмов:

- Поиск минимального значения в массиве чисел длиной *n*, который отсортирован по возрастанию.
- Расчёт среднего значения в массиве чисел длиной *n*.
- Получение длины массива размером *n*.
- Задан список из *n* объектов, каждый из которых представляет собой банковский счёт - `ArrayList<Bill>`. И есть класс и метод, с помощью которых можно получить общую сумму транзакций между первым и вторым счётом — `TransactionsCalculator.calculateTotalSum(Bill sourceBill, Bill destinationBill)`. Этим методом алгоритм должен посчитать общую сумму переводов между всеми счетами из списка. Какова будет временная сложность такого алгоритма?

**Задание 3**

Реализуйте алгоритм бинарного поиска в коде полностью, используя заготовку в проекте **Algorithms** (**BinarySearch.java**). В случае, если элемент в списке не найден, метод `search()` должен возвращать «-1».

**Решение**

TASK_18_1_3: [Algorithms/BinarySearch.java](./Algorithms/src/main/java/binary_search/BinarySearch.java)

**Задание 4**

Напишите Unit-тест для метода сортировки пузырьком проекта **Algorithms** (**BubbleSort.java**).

**Решение**

TASK_18_1_4: [Algorithms/BubbleSortTest.java](./Algorithms/src/test/java/bubble_sort/BubbleSortTest.java)

**Задание 5**

Реализуйте алгоритм сортировки *QuickSort* в проекте **Algorithms** (**QuickSort.java**). В проекте имеется заготовка. По сути, необходимо реализовать только метод `partition()`.

**Решение**

TASK_18_1_5: [Algorithms/QuickSort.java](./Algorithms/src/main/java/quick_sort/)

**Задание 6**

В заготовке алгоритма *MergeSort* в проекте **Algorithms** (**MergeSort.java**) реализуйте метод `merge()`.

**Решение**

TASK_18_1_6: [Algorithms/MergeSort.java](./Algorithms/src/main/java/merge_sort/)

**Задание 7**

Используя заготовку в проекте **Algorithms** (**RabinKarpExtended.java**), реализуйте алгоритм Рабина-Карпа. Также реализуйте в методах этого класса проверку размера алфавита. В проекте приведён усовершенствованный алгоритм Рабина-Карпа, в котором номера хранятся в TreeMap и тем самым облегчают поиск нужных позиций в строке без перебора всей строки.

**Решение**

TASK_18_1_7: [Algorithms/RabinKarpExtended.java](./Algorithms/src/main/java/rabin_karp/)
  
**Критерии оценки**

«Зачёт» — выполнены все домашние задания.  
«Незачёт» — задание не выполнено.

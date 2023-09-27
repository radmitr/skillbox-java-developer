### Программа модуля

6. **Наследование и полиморфизм**  
    6.1. Наследование классов Java  
    6.2. [Домашняя работа 6.1](#62-домашняя-работа-61)  
    6.3. Доступ к методам и переменным  
    6.4. [Домашняя работа 6.2](#64-домашняя-работа-62)  
    6.5. Абстрактные классы  
    6.6. [Домашняя работа 6.3](#66-домашняя-работа-63)  
    6.7. Интерфейсы  
    6.8. Полиморфизм  
    6.9. Интерфейс Comparable  
    6.10. Интерфейс Comparator  
    6.11. Краткая реализация интерфейсов  
    6.12. Интерфейсы Map и Set  
    6.13. [Домашняя работа 6.4](#613-домашняя-работа-64)  

TASK_6_1_1: [BankAccount](./BankAccount)  
TASK_6_1_2: [BankAccount](./BankAccount)  
TASK_6_2: [BankAccount](./BankAccount)  
TASK_6_3: [BankClient](./BankClient)  
TASK_6_4: [Company](./Company)  
 
---------------------------------------------

#### 6.2. Домашняя работа 6.1

**Цель задания**

Освоить на практике наследование классов.

**Что нужно сделать**

**1.** Создайте класс BankAccount, который представляет собой расчётный счёт в банке. У класса необходимо написать методы:

a. Снять со счёта сумму денег (без комиссии).
b. Вносить на счёт сумму денег (без комиссии).
c. Получить остаток на счёте.

**2.** Создайте два класса наследника, расширяющие работу с остатком на счёте:

- Депозитный расчётный счёт, с которого нельзя снимать деньги в течение месяца после последнего внесения.
- Карточный счёт, при снятии денег с которого будет взиматься комиссия 1%.

**Решение**

TASK_6_1_1: [BankAccount](./BankAccount)

**Критерии оценки**

«Зачёт» — написаны три класса: родитель и два наследника.  
«Незачёт» — задание не выполнено.

**Дополнительное задание***

**Цель задания**

Потренироваться в написании взаимодействия между классами.

**Что нужно сделать**

Напишите метод в классе BankAccount:

```java
boolean send(BankAccount receiver, double amount)
```

для отправки денег с одного счёта на другой. Метод должен вернуть true, если деньги успешно переведены.

**Примечание**

В методе для аргумента amount используйте тип данных, который применили в классе BankAccount. Тип double дан для примера.

**Решение**

TASK_6_1_2: [BankAccount](./BankAccount)

**Критерии оценки**

«Зачёт» — метод send() передаёт деньги между счетами с учётом комиссий и ограничений.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 6.4. Домашняя работа 6.2

**Цель задания**

Научиться исследовать код и выбирать подходящие модификаторы доступа.

**Что нужно сделать**

Изучите методы и переменные проекта задания 6.1 и установите подходящие модификаторы доступа у методов и переменных.

**Решение**

TASK_6_2: [BankAccount](./BankAccount)

**Критерии оценки**

«Зачёт» — для всех переменных и методов установлены подходящие модификаторы доступа.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 6.6. Домашняя работа 6.3

**Цель задания**

Освоить работу с наследованием, используя абстрактный класс.

**Что нужно сделать**

**1.** Создайте классы, представляющие клиентов банка: абстрактный класс Client, классы для физических лиц, юридических лиц и индивидуальных предпринимателей.

У каждого клиента есть сумма денег на счету (число). Деньги можно положить на счёт, снять и вернуть остаток на счёте. Каждый класс должен содержать метод, который выводит информацию в консоль о счёте: условие пополнения, условие снятия и баланс.

**2.** Реализуйте методы, при которых:

- У физических лиц пополнение и снятие происходит без комиссии.
- У юридических лиц — снятие с комиссией 1%.
- У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей. И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.

**Решение**

TASK_6_3: [BankClient](./BankClient)

**Критерии оценки**

«Зачёт» — написан абстрактный класс и три класса наследника.  
«Незачёт» — задание не выполнено.

---------------------------------------------

#### 6.13. Домашняя работа 6.4

**Цель задания**

Научиться работать с интерфейсами, абстрактными классами и взаимодействием классов.

**Что нужно сделать**

**1.** Создайте класс компании **Company**, содержащей сотрудников и реализующей методы:

- найм одного сотрудника — `hire()`,
- найм списка сотрудников – `hireAll()`,
- увольнение сотрудника – `fire()`,
- получение значения дохода компании – `getIncome()`.

Аргументы и возвращаемое значение методов выберите на основании логики работы вашего приложения.

**2.** Создайте два метода, возвращающие список указанной длины (count). Они должны содержать сотрудников, отсортированных по убыванию и возрастанию заработной платы:

- `List<Employee> getTopSalaryStaff(int count)`,
- `List<Employee> getLowestSalaryStaff(int count)`.

**3.** Создайте классы сотрудников с информацией о зарплатах и условиями начисления зарплаты:

- **Manager** — зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных для компании денег. Количество заработанных денег для компании генерируйте случайным образом от 115 000 до 140 000 рублей.
- **TopManager** — зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если доход компании более 10 млн рублей.
- **Operator** — зарплата складывается только из фиксированной части.

Каждый класс сотрудника должен имплементировать интерфейс Employee. В интерфейсе Employee должен быть объявлен метод, возвращающий зарплату сотрудника:

- `getMonthSalary()`

Аргументы и возвращаемое значение метода выберите в соответствии с логикой начисления зарплат.  

**Для демонстрации и тестирования работы ваших классов:**

1. Создайте и наймите в компанию: 180 операторов **Operator**, 80 менеджеров по продажам **Manager**, 10 топ-менеджеров **TopManager**.
2. Распечатайте список из 10–15 самых высоких зарплат в компании.
3. Распечатайте список из 30 самых низких зарплат в компании.
4. Увольте 50% сотрудников.
5. Распечатайте список из 10–15 самых высоких зарплат в компании.
6. Распечатайте список из 30 самых низких зарплат в компании.

**Примеры вывода списка зарплат**

Список из пяти зарплат по убыванию:

    - 230 000 руб.
    - 178 000 руб.
    - 165 870 руб.
    - 123 000 руб.
    - 117 900 руб.

**Рекомендации**

- Можно создавать разные экземпляры компании со своим списком сотрудников и доходом.
- Чтобы получить данные компании внутри класса сотрудника, настройте хранение ссылки на Company и передавайте объект Company с помощью конструктора или сеттера.
- Учтите, в методы получения списков зарплат могут передаваться значения count, превышающие количество сотрудников в компании, или отрицательные.

**Решение**

TASK_6_4: [Company](./Company) 

**Критерии оценки**

«Зачёт» — программа выполняет все требования.  
«Незачёт» — задание не выполнено.
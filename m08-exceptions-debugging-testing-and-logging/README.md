### Программа модуля

8. **Исключения, отладка, тестирование и логирование**  
    8.1. Возникновение исключений  
    8.2. Отлов исключений  
    8.3. Типы исключений  
    8.4. [Домашняя работа 8.1](#84-домашняя-работа-81)  
    8.5. Отладка приложений  
    8.6. Виды тестирования ПО  
    8.7. Модульное тестирование  
    8.8. [Домашняя работа 8.2](#88-домашняя-работа-82)   
    8.9. Логгирование в консоли  
    8.10. Логгирование с помощью log4j2  
    8.11. [Домашняя работа 8.3](#811-домашняя-работа-83)     

TASK_8_1: [ConsoleCustomerList](./ConsoleCustomerList)  
TASK_8_2: [SPBMetro](./SPBMetro)   
TASK_8_3: [SPBMetro](./SPBMetro)   

----------------------------------------------------

#### 8.4 Домашняя работа 8.1

**Цель задания**

Научиться использовать механизм исключений.

**Что нужно сделать**

Пропишите в проекте **ConsoleCustomerList** все возможные варианты защиты от некорректной работы и её преждевременного завершения.

Программа должна выбрасывать исключения (Exception) при неверном формате

- команды (количество элементов в команде),
- номера телефона,
- email.

**Решение**

TASK_8_1: [ConsoleCustomerList](./ConsoleCustomerList)

**Критерии оценки**

«Зачёт» — программа выводит информацию в консоль при неверном вводе, программа не завершается с ошибкой при вводе любого формата команд.  
«Незачёт» — задание не выполнено.

----------------------------------------------------

#### 8.8 Домашняя работа 8.2

**Цель задания**

Освоить написание тестов для классов, используя библиотеку JUnit.

**Что нужно сделать**

1. Напишите тесты на все методы класса **RouteCalculator** в проекте **SPBMetro**.
2. С помощью тестов и отладки исправьте ошибку, которую вы найдёте в проекте **SPBMetro** в классе **RouteCalculator**.

**Рекомендации**

- Сформируйте схему метро (StationIndex) в тестовом классе, в которой можно построить хотя бы один маршрут с двумя пересадками. Делайте небольшую схему, чтобы легче ориентироваться в ней.
- Чтобы протестировать приватные методы класса RouteCalculator, используйте различные аргументы метода getShortestRoute() для вызова методов без пересадок, с одной и двумя пересадками.
- Code Coverage — инструмент в IDEA, определяющий уровень покрытия тестами классов, методов и строк. Чтобы запустить тесты с проверкой покрытия, надо нажать правой кнопкой мыши по классу директории с тестами и выбрать Run All tests with Coverage. Желательно добиться 100%-ного покрытия методов класса RouteCalculator.

**Решение**

TASK_8_2: [SPBMetro](./SPBMetro)

**Критерии оценки**

«Зачёт» — исправлена ошибка в проекте **SPBMetro** , все тесты для проверки маршрутов и времени проезда выполняются без ошибок.  
«Незачёт» — задание не выполнено.

----------------------------------------------------

#### 8.11 Домашняя работа 8.3

**Цель задания**

Научиться писать конфигурацию для логирования событий при исполнении программы.

**Что нужно сделать**

Сделайте три отдельных лога в папке **logs** проектa **SPBMetro** с помощью **log4j2** :

- **logs/search.log** — заполнять информацией о станциях, которые ищут (существующие станции),
- **logs/input\_errors.log** — заполнять информацией об ошибочном вводе (несуществующие станции),
- **logs/exceptions.log** — вносить в лог информацию об исключениях (Exception).

**Решение**

TASK_8_3: [SPBMetro](./SPBMetro)

**Критерии оценки**

«Зачёт» — в каждый файл выводится только свой тип информации.  
«Незачёт» — задание не выполнено.
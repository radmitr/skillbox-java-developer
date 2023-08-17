package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("****************** Класс Date Java ******************\n");

        // 0 - String
        {
            System.out.println("0). -----------------------------------------");
            String date = "11 июня 2018 года";
            System.out.println(date);
            System.out.println();
        }

        // 1 - Date()
        {
            System.out.println("1). -----------------------------------------");
            Date date = new Date();
            System.out.println(date);
            System.out.println();
        }

        // 2 - Date(long date)
        {
            System.out.println("2). ------------------------------------------");
            Date date = new Date(1212121212121L);
            System.out.println(date);
            System.out.println();
        }

        // 3 - Date.getTime()
        {
            System.out.println("3). -----------------------------------------");
            Date date1 = new Date();
            Date date2 = new Date();

            System.out.println((date1.getTime() > date2.getTime()) ?
                    "date1 позже date2" : "date1 раньше date2");
            System.out.println();
        }

        // 4 - before()
        {
            System.out.println("4). ------------------------------------------");
            Date date1 = new Date();

            // приостановим работу программы на 2 секунды
            Thread.sleep(2000);
            Date date2 = new Date();

            System.out.println(date1.before(date2));
            System.out.println();
        }

        // 5 - equals()
        {
            System.out.println("5). ------------------------------------------");
            Date date1 = new Date();
            Date date2 = new Date();

            System.out.println(date1.getTime());
            System.out.println(date2.getTime());

            System.out.println(date1.equals(date2));
            System.out.println();
        }

        // 6 - @Deprecated
        {
            System.out.println("6). ------------------------------------------");
            Date date1 = new Date();
            System.out.println(date1.getHours());
            System.out.println();
        }

        System.out.println("****************** Класс Java Calendar ******************\n");

        // 7 - GregorianCalendar()
        {
            System.out.println("7). ------------------------------------------");
            Calendar calendar = new GregorianCalendar(2017, 0 , 25);
            System.out.println(calendar);
            System.out.println();
        }

        // 8 - Calendar.getTime()
        {
            System.out.println("8). ------------------------------------------");
            Calendar calendar = new GregorianCalendar(2017, 0 , 25);
            Date date = calendar.getTime();
            System.out.println(date);
            System.out.println();
        }

        // 9 - Calendar.JANUARY
        {
            System.out.println("9). ------------------------------------------");
            GregorianCalendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
            System.out.println();
        }

        // 10 - set()
        {
            System.out.println("10). ------------------------------------------");
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, 2017);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 25);
            calendar.set(Calendar.HOUR_OF_DAY, 19);
            calendar.set(Calendar.MINUTE, 42);
            calendar.set(Calendar.SECOND, 12);

            System.out.println(calendar.getTime());
            System.out.println();
        }

        // 11 - add()
        {
            System.out.println("11). ------------------------------------------");
            Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
            calendar.set(Calendar.HOUR, 19);
            calendar.set(Calendar.MINUTE, 42);
            calendar.set(Calendar.SECOND, 12);

            // чтобы отнять значение, в метод нужно передать отрицательное число
            calendar.add(Calendar.MONTH, -2);
            System.out.println(calendar.getTime());
            System.out.println();
        }

        // 12 - roll()
        {
            System.out.println("12). ------------------------------------------");
            Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
            calendar.set(Calendar.HOUR, 10);
            calendar.set(Calendar.MINUTE, 42);
            calendar.set(Calendar.SECOND, 12);

            calendar.roll(Calendar.MONTH, -2);
            System.out.println(calendar.getTime());
            System.out.println();
        }

        // 13 - get()
        {
            System.out.println("13). ------------------------------------------");
            GregorianCalendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
            calendar.set(Calendar.HOUR, 10);
            calendar.set(Calendar.MINUTE, 42);
            calendar.set(Calendar.SECOND, 12);

            System.out.println("Год: " + calendar.get(Calendar.YEAR));
            System.out.println("Месяц: " + calendar.get(Calendar.MONTH));
            // порядковый номер недели в месяце
            System.out.println("Порядковый номер недели в месяце: " + calendar.get(Calendar.WEEK_OF_MONTH));

            System.out.println("Число: " + calendar.get(Calendar.DAY_OF_MONTH));

            System.out.println("Часы: " + calendar.get(Calendar.HOUR));
            System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
            System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
            System.out.println("Миллисекунды: " + calendar.get(Calendar.MILLISECOND));
            System.out.println();
        }

        // 14 - Calendar.Era
        {
            System.out.println("14). ------------------------------------------");
            GregorianCalendar cannes = new GregorianCalendar(216, Calendar.AUGUST, 2);
            cannes.set(Calendar.ERA, GregorianCalendar.BC);

            DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
            System.out.println(df.format(cannes.getTime()));
            System.out.println();
        }

        System.out.println("****************** Перевод строки в Date ******************\n");

        // 15 - class SimpleDateFormat
        {
            System.out.println("15). ------------------------------------------");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(1212121212121L);

            System.out.println(formatter.format(date));
            System.out.println();
        }

        // 16 - java string to date
        {
            System.out.println("16). ------------------------------------------");
            String strDate = "Sat, April 4, 2020";
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d, yyyy", Locale.ENGLISH);

            try {
                Date date = formatter.parse(strDate);
                System.out.println(date);
                System.out.println(formatter.format(date));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        System.out.println("****************** SimpleDateFormat и Calendar ******************\n");

        // 17 -  DateFormat with Calendar
        {
            System.out.println("17). ------------------------------------------");
            GregorianCalendar cannes = new GregorianCalendar(216, Calendar.AUGUST, 2);
            cannes.set(Calendar.ERA, GregorianCalendar.BC);

            DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
            System.out.println(df.format(cannes.getTime()));
            System.out.println();
        }

        System.out.println("****************** Java Date Format ******************\n");

        // 18 - java date format
        {
            System.out.println("18). ------------------------------------------");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
            Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
            calendar.set(Calendar.HOUR, 10);
            calendar.set(Calendar.MINUTE, 42);
            calendar.set(Calendar.SECOND, 12);

            calendar.roll(Calendar.MONTH, -2);
            System.out.println(dateFormat.format(calendar.getTime()));
            System.out.println();
        }
    }
}

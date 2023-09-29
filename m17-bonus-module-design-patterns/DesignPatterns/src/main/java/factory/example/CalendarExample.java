package factory.example;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarExample {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
        Calendar calendar3 = Calendar.getInstance(Locale.FRANCE);
        Calendar calendar4 = Calendar.getInstance(TimeZone.getDefault(), Locale.FRANCE);
    }
}

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Task 4.6
public class Main {

    public static void main(String[] args) {
        int year = 1984;
        int month = Calendar.OCTOBER;
        int day = 10;

        Calendar myBirthday = Calendar.getInstance();
        myBirthday.set(year, month, day);

        Date today = Calendar.getInstance().getTime();

        for (int i = 0; myBirthday.getTime().before(today); i++) {
            System.out.println(i + " - " + new SimpleDateFormat("dd.MM.yyyy - E")
                    .format(myBirthday.getTime()));
            myBirthday.set(Calendar.YEAR, myBirthday.get(Calendar.YEAR) + 1);
        }
    }
}

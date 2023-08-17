import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatExample {

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();

        Date currentDate = new Date(currentTime);
        System.out.println(currentDate);

        String currentStringDate = new SimpleDateFormat("dd.MM.yyyy").format(currentTime);
        String currentStringDate2 = new SimpleDateFormat("dd.MM.yyyy").format(currentDate);

        System.out.println(currentStringDate);
        System.out.println(currentStringDate2);

        try {
            Date parsedDate = new SimpleDateFormat("dd.MM.yyyy").parse("16.04.2004");
            System.out.println(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

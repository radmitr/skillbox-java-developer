import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        boolean error = false;

        if (args.length == 0) {
            System.out.println(
                "Привет! Я - простое консольное Java-приложение.\n" +
                    "Запусти меня с параметром \"help\", и я расскажу " +
                    "тебе, что я умею :)"
            );
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                System.out.println(
                    "Я умею отвечать на простые вопросы.\n" +
                        "Чтобы увидеть мой ответ, напиши в кавычках \n" +
                        "любую фразу или вопрос из следующего списка: \n" +
                        "- \"привет!\"\n" +
                        "- \"как дела?\"\n" +
                        "- \"как настроение?\"\n" +
                        "- \"чем занимаешься?\"\n" +
                        "- \"почему не спишь?\"\n" +
                        "- \"что ты делаешь сегодня вечером?\"\n" +
                        "- \"пока!\"\n"
                );
            } else if (args[0].equals("привет!")) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour < 12) {
                    System.out.println("Доброе утро!");
                } else if (hour >= 12 && hour <= 18) {
                    System.out.println("Добрый день!");
                } else {
                    System.out.println("Добрый вечер!");
                }
            } else if (args[0].equals("как дела?")) {
                System.out.println("Хорошо, спасибо! :)");
            } else if (args[0].equals("как настроение?")) {
                System.out.println("Бодрое");
            } else if (args[0].equals("чем занимаешься?")) {
                System.out.println("Читаю пост на Хабр по Java");
            } else if (args[0].equals("почему не спишь?")) {
                System.out.println("Так обед же! Кто же спит в обед?");
            } else if (args[0].equals("что ты делаешь сегодня вечером?")) {
                System.out.println("Работаю :(");
            } else if (args[0].equals("пока!")) {
                System.out.println("Пока! Хорошего дня тебе! :)");
            } else {
                error = true;
            }
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Прости, я не поняла, что ты меня спрашиваешь :(\n" +
                "Спроси меня правильно. Вот примеры:\n" +
                "java -jar simple.jar help\n" +
                "java -jar simple.jar \"привет!\"\n" +
                "java -jar simple.jar \"как дела?\"\n");
        }
    }
}

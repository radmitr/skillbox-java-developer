// Task 5.1.2
public class Main {

    public static void main(String[] args) {
        final int AMOUNT_OF_PATIENTS = 30;
        final float MAX_TEMPERATURE = 40f;
        final float MIN_TEMPERATURE = 32f;
        final float MAX_TEMPERATURE_OF_HEALTHY_PATIENT = 36.9f;
        final float MIN_TEMPERATURE_OF_HEALTHY_PATIENT = 36.2f;
        float[] temperatures = new float[AMOUNT_OF_PATIENTS];

        int healthyPatientAmount = 0;

        float sum = 0;
        System.out.print("Температуры пациентов:");
        for (int i = 0; i < temperatures.length; i++) {
            temperatures[i] = MIN_TEMPERATURE
                    + (float) Math.round((int) (MAX_TEMPERATURE - MIN_TEMPERATURE) * 10 * (float) Math.random())
                    / 10;
            sum += temperatures[i];
            if (temperatures[i] >= MIN_TEMPERATURE_OF_HEALTHY_PATIENT
                    && (temperatures[i] <= MAX_TEMPERATURE_OF_HEALTHY_PATIENT)) {
                healthyPatientAmount++;
            }
            System.out.print("; " + temperatures[i]);
        }
        System.out.println();

        float averageTemperature = sum / AMOUNT_OF_PATIENTS;
        System.out.println("Средняя температура по больнице: " + averageTemperature);
        System.out.println("Количество здоровых пациентов: " + healthyPatientAmount);
    }
}

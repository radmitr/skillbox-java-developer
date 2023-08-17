package ru.radmitr;

import lombok.extern.log4j.Log4j2;

/**
 * Utility class. Serves to support bank statement conversion
 */
@Log4j2
public class Utility {

    private Utility() {
    }

    /**
     * Convert string to double
     */
    public static double getDouble(String sum) {
        try {
            return Double.parseDouble(sum);
        } catch (NumberFormatException nfe) {
            double ret = Double.parseDouble(sum.replaceAll("\"", "")
                    .replaceAll(",", "."));
            log.warn("Исправлен некорректный тип данных: \"{}\" -> {}", sum, ret);
            return ret;
        } catch (NullPointerException npe) {
            log.error("Не указана сумма для преобразования");
            return 0.0;
        }
    }

    /**
     * Extract recipient name
     */
    public static String extractRecipientName(String description) {
        try {
            String[] tmpArr = description.split("\\s{3,}");
            return tmpArr[1].lastIndexOf("\\") >= 0 ?
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("\\") + 1).trim().toUpperCase() :
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("/") + 1).trim().toUpperCase();
        } catch (NullPointerException npe) {
            log.error("Описание операции не найдено");
            return "Отсутствует имя получателя";
        }
    }
}

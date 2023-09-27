import java.sql.*;

// Task 10.1
public class Main1 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        final String sqlQuery = "SELECT pl.course_name, COUNT(*) purchases_count, " +
                "(MAX(MONTH(pl.subscription_date)) - MIN(MONTH(pl.subscription_date)) + 1) months_count, " +
                "COUNT(*) / (MAX(MONTH(pl.subscription_date)) - MIN(MONTH(pl.subscription_date)) + 1) " +
                "purchases_per_month FROM PurchaseList pl GROUP BY pl.course_name";

        // запрос выведет среднее количество покупок в месяц для каждого курса за 2018 год
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            System.out.printf("%-36s %-18s %-15s %-9s\n", "course_name", "purchases_count",
                    "months_count", "purchases_per_month");
            System.out.printf("%s\n", "-".repeat(91));

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int purchasesCount = resultSet.getInt("purchases_count");
                int monthsCount = resultSet.getInt("months_count");
                double purchasesPerMonth = resultSet.getDouble("purchases_per_month");

                System.out.printf("%-36s %-18d %-15d %-5.3f\n", courseName, purchasesCount,
                        monthsCount, purchasesPerMonth);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

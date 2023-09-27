import java.sql.*;

// Пример 1 из Task 10.1
public class Experiment2 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        // запрос выведет все покупки курса «Веб-разработчик c нуля до PRO»
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, pl.subscription_date\n" +
                     "FROM PurchaseList pl " +
                     "WHERE pl.course_name = \"Веб-разработчик c 0 до PRO\" " +
                     "ORDER BY pl.subscription_date")
        ) {
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                Date subscriptionDate = resultSet.getDate("subscription_date");
                System.out.println(courseName + " : " + subscriptionDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

import java.sql.*;

// 10.2 Подключение через JDBC
// 10.3 Запросы без ResultSet
public class Experiment1 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "root";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            // команда обновит строку с id = 1
            statement.execute("UPDATE courses SET name = 'Веб-разработчик c нуля до PRO' WHERE id = 1");

            // запрос выведет все курсы
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");

            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                System.out.println(courseName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

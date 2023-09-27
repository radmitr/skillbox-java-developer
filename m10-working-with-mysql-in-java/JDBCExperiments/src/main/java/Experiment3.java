import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

// Пример 2 из Task 10.1
public class Experiment3 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        // запрос выведет всех студентов, зарегистрированных в апреле
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM Students s WHERE MONTH(s.registration_date) = 4")
        ) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    String[] columnNames = new String[columnCount];

                    for (int column = 1; column <= columnCount; column++) {
                        String name = metaData.getColumnName(column);
                        columnNames[column - 1] = name;
                    }

                    if (columnCount == 4) {
                        System.out.printf("%-4s %-25s %-4s %-10s\n", columnNames[0], columnNames[1],
                                columnNames[2], columnNames[3]);
                        System.out.printf("%s\n", "-".repeat(55));
                    }
                }
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date date = resultSet.getDate("registration_date");

                System.out.printf("%-4d %-25s %-4d %-10s\n", id, name, age, df.format(date));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

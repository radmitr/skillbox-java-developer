package hibernate_experiments;

import hibernate_experiments.entities.Course;
import hibernate_experiments.entities.CourseType;

import java.sql.*;

// 10.5 Hibernate — подключение и настройка
public class Experiment1_WithoutHibernate {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "root";

        final String sqlQuery = "SELECT * FROM Courses;";

        // запрос выведет все курсы
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)
        ) {
            while (resultSet.next()) {
                Course course = new Course();

                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDuration(resultSet.getInt("duration"));
                course.setType(CourseType.valueOf(resultSet.getString("type")));
                course.setDescription(resultSet.getString("description"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setStudentsCount(resultSet.getInt("students_count"));
                course.setPrice(resultSet.getInt("price"));
                course.setPricePerHour(resultSet.getDouble("price_per_hour"));

                System.out.println(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

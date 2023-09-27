import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Task 10.1
public class Main2 {

    private static final Connection connection = DBConnector.connect();

    private static final String sqlQuery = "SELECT pl.course_name, " +
            "COUNT(*) / (MAX(MONTH(pl.subscription_date)) - MIN(MONTH(pl.subscription_date)) + 1) " +
            "purchases_per_month FROM PurchaseList pl GROUP BY pl.course_name";

    public static void main(String[] args) throws SQLException {
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    System.out.printf("%-36s %-5.3f\n", resultSet.getString("course_name"),
                            resultSet.getDouble("purchases_per_month"));
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null)
                connection.close();
        }
    }
}

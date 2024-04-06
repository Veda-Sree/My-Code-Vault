import java.sql.*;

public class CaseStudy {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "db6";
        String username = "root";
        String password = "vedasree1";

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            // Create the database if not exists
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database created successfully!");

            // Use the created database
            String useDatabaseQuery = "USE " + dbName;
            statement.executeUpdate(useDatabaseQuery);

            // Create a table if not exists
            String createTableQuery = "CREATE TABLE IF NOT EXISTS students (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "student_name VARCHAR(255)," +
                    "spin INT," +
                    "sage INT" +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully!");

            // Insert data for persons
            String insertDataQuery1 = "INSERT INTO students (student_name, spin, sage) VALUES ('Veda', 0737, 20)";
            statement.executeUpdate(insertDataQuery1);
            String insertDataQuery2 = "INSERT INTO students (student_name, spin, sage) VALUES ('Sriya', 0933, 20)";
            statement.executeUpdate(insertDataQuery2);
            String insertDataQuery3 = "INSERT INTO students (student_name, spin, sage) VALUES ('Harshi', 1155, 20)";
            statement.executeUpdate(insertDataQuery3);
            String insertDataQuery4 = "INSERT INTO students (student_name, spin, sage) VALUES ('shashi', 0730, 21)";
            statement.executeUpdate(insertDataQuery4);
            System.out.println("Data inserted successfully!");
            // print
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            System.out.println("Table content:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + "\t" +
                        resultSet.getString("student_name") + "\t" +
                        resultSet.getInt("spin") + "\t" +
                        resultSet.getInt("sage"));
            }
            resultSet.close();
            // Delete data for a person with name 'Veda'
            String deleteDataQuery = "DELETE FROM students WHERE student_name = 'Veda'";
            int rowsAffected = statement.executeUpdate(deleteDataQuery);
            if (rowsAffected > 0) {
                System.out.println("Data for 'Veda' deleted successfully!");
            } else {
                System.out.println("No data found for 'Veda'");
            }

            // Print table content
            resultSet = statement.executeQuery("SELECT * FROM students");
            System.out.println("Table content:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + "\t" +
                        resultSet.getString("student_name") + "\t" +
                        resultSet.getInt("spin") + "\t" +
                        resultSet.getInt("sage"));
            }
            resultSet.close(); // Close the ResultSet to release database resources

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }
}

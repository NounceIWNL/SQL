import java.sql.*;

public class MySQLJDBC {
    private static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "")) {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            readAll();
            create();

            update();
            System.out.println();
            readAll();
            System.out.println();
            delete();
            readAll();
        } catch (
                SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void readAll() throws SQLException {
        //Список всех служащих
        ResultSet rs = statement.executeQuery("select name from student");
        while (rs.next())
            System.out.printf("name: %s%n", rs.getString("name"));
    }

    public static void create() throws SQLException {
        String sql = "INSERT INTO student(surname, name) VALUES ('Иванов','Олег')";
        statement.executeUpdate(sql);
    }

    public static void update() throws SQLException {

        String sql = "UPDATE student SET univ_id = 2 WHERE name='Олег'";
        statement.executeUpdate(sql);
    }


    public static void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE name = 'Олег'";
        statement.executeUpdate(sql);
    }
} 
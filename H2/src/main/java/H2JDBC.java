import java.sql.*;

public class H2JDBC {
    private static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection("jdbc:h2:C:\\Users\\gk\\IdeaProjects\\Sqlite\\H2\\src\\main\\resources\\dbase.db", "sa", "")) {
            // set timeout to 30 sec.
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            readAll();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void readAll() throws SQLException {


        //Список всех служащих
        ResultSet rs = statement.executeQuery("select name from character");
        while (rs.next())
            System.out.printf("name: %s%n", rs.getString("name"));
    }
}
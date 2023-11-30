import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection a= SQLDriver.getSQLConnection();
        List<Student> b= SQLDriver.searchStudent(a,"19","Age");




        return;
    }
}
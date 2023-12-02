import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Student> studentList;
    public static Scanner scanner=new Scanner(System.in);
    //静态加载驱动链接数据库
    static {
        SQLDriver.getSQLConnection();
        System.out.println((SQLDriver.getSQLstatcode()==200?"连接成功  SQLurl: "+SQLDriver.getSQLurl():"连接失败，检查配置"));
    }

    public static void main(String[] args) throws SQLException {


        return;
    }

    public static void menu(){

    }
}
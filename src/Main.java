import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

/**
 * 增加 删除 修改 查询
 * 1. 查询有没有此学号的学生->没有就加入sql,有则返回让用户修改
 */
public class Main {

    public static List<Student> studentList;
    public static Scanner sc = new Scanner(System.in);

    //静态加载驱动链接数据库
    static {
        SQLDriver.getSQLConnection();
        System.out.println((SQLDriver.getSQLstatcode() == 200 ? "连接成功  SQLurl: " + SQLDriver.getSQLurl() : "连接失败，检查配置") + "\n");
    }

    public static void main(String[] args) throws SQLException {
//        menu();
//        List<Student> a = SQLDriver.searchStudent(SQLDriver.conn,"32","Studentbuilding");


        menu();


        return;

    }

    public static void menu() {
        System.out.println("<------------------------------->\n" +
                "1.增加    2.删除    3,修改    4.查询\n" +
                "<------------------------------->\n" +
                "请输入你的操作:(输入0可返回上级)\n");
        int temp = sc.nextInt();
        menu(temp);
    }

    public static void menu(int SwitchCase) {//增加 删除 修改 查询
        switch (SwitchCase) {
            case 1:
                while (true)
                    Add();
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    public static void Add() {
        System.out.println("请输入要增加的学生信息");
        Student stu = Student.newStudent(sc);
        List<Student> tempList;
        while (true) {
            try {
                tempList = SQLDriver.searchStudent(SQLDriver.conn, stu.getStudent_ID(), "Student_ID");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (tempList == null) {
                try {
                    SQLDriver.setStstement(stu);
                    System.out.println("添加成功");
                    break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("有相同学号学生如下");
                System.out.println(tempList.get(0).toString(tempList));
                System.out.println("请重新输入学号避免重复");
                stu.setStudent_ID(sc.next());
            }
        }
    }

    public static void Delete() {

    }

    public static void Change() {

    }

    public static void Find() {

    }
}
import javax.sound.midi.Soundbank;
import java.sql.SQLException;
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


        Delete();


        return;

    }

    public static void menu() {
        System.out.println("<------------------------------->\n" +
                "1.增加    2.删除    3,修改    4.查询\n" +
                "<------------------------------->\n" +
                "请输入你的操作:(输入0可返回上级)\n");
        int temp = sc.nextInt();
        sc.nextLine();
        menu(temp);
    }

    public static void menu(int SwitchCase) {//增加 删除 修改 查询
        switch (SwitchCase) {
            case 1:
                Add();
            case 2:
                Delete();
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
                System.out.println(Student.toString(tempList));
                System.out.println("请重新输入学号避免重复");
                stu.setStudent_ID(sc.next());
                sc.nextLine();
            }
        }
    }

    public static void Delete() {

        while (true) {
            System.out.println("请输入要删除的学生学号");
            String tempID = sc.next();

            sc.nextLine();

            List<Student> tempList;
            try {
                tempList = SQLDriver.searchStudent(SQLDriver.conn, tempID, "Student_ID");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (tempList == null) {
                System.out.println("未找到学生请重新输入学号");
            } else {
                System.out.println("找到对应学生，请确认");
                System.out.println(Student.toString(tempList));
                System.out.println("删除请输入1，按2删除其他学生信息，其他键退出删除");
                String string = sc.next();
                sc.nextLine();
                if (string.equals("1")) {
                    SQLDriver.DetStudent(tempID);
                    System.out.println("删除成功");
                    return;
                } else if (!string.equals("2")) {
                    return;
                }
            }
        }
    }

    public static void Change() {
        String userScanner;
        int IntOfuserScanner;
        System.out.println("请输入要更改的学生学号");
        String tempID = sc.next();

        sc.nextLine();

        List<Student> tempList;
        try {
            tempList = SQLDriver.searchStudent(SQLDriver.conn, tempID, "Student_ID");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (tempList == null) {
            System.out.println("更改学生不存在请重新输入学号查找");
        } else {
            System.out.println("找到学生信息");
            System.out.println(Student.toString(tempList));
            System.out.println("请输入要更改内容的序号");
            System.out.println("1.学号 2.姓名 3.性别 4.年龄 \n" +
                    "5.手机号1 6.手机号2 7.宿舍楼 8.宿舍号  0.退出更改");
            int i = 0;
            do {
                if (i != 0) System.out.println("请重新选择更改序号(更改输入0)");
                userScanner = sc.next();
                sc.nextLine();
                i++;
            } while (!SQLDriver.isNotNum(userScanner) &&
                    -1 < Integer.parseInt(userScanner) &&
                    9 > Integer.parseInt(userScanner));
            IntOfuserScanner =Integer.parseInt(userScanner);
            if (IntOfuserScanner==0){
                System.out.println("退出更改");
                return;
            }else {
                System.out.println("请输入更改的新的"+Student.getIndexChinese(IntOfuserScanner));
            }
        }
    }

    public static void Find() {

    }
}
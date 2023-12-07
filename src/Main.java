import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 增加 删除 修改 查询
 * 1. 查询有没有此学号的学生->没有就加入sql,有则返回让用户修改
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    //静态加载驱动链接数据库
    static {
        SQLDriver.getSQLConnection();
        System.out.println((SQLDriver.getSQLstatcode() == 200 ? "连接成功  SQLurl: " + SQLDriver.getSQLurl() : "连接失败，检查配置") + "\n");
    }

    public static void main(String[] args) {

        menu(menu());

    }

    public static int menu() {
        System.out.println("<------------------------------->\n" +
                "1.增加    2.删除    3,修改    4.查询\n" +
                "<------------------------------->\n" +
                "请输入你的操作:(输入0可退出)");
        int temp = sc.nextInt();
        sc.nextLine();
        return temp;
    }

    public static void menu(int SwitchCase) {//增加 删除 修改 查询
        while (true) {
            switch (SwitchCase) {
                case 1:
                    Add();
                    break;
                case 2:
                    Delete();
                    break;
                case 3:
                    Change();
                    break;
                case 4:
                    Find();
                    break;
                case 0:
                    System.out.println("退出成功");
                    return;
                default:
                    System.out.println("请输入正确的选项");
                    break;
            }
            SwitchCase = menu();
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
            System.out.println("请输入要删除的学生学号(输入exit可退出)");
            String tempID = sc.next();

            sc.nextLine();
            if (tempID.equals("exit")) {
                System.out.println("退出删除");
                return;
            }
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
        String userScanner1, userScanner2;
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
            System.out.println("1.姓名 2.性别 3.年龄 4.手机号1" +
                    "\n5.手机号2 6.宿舍楼 7.宿舍号  0.退出更改");
            int i = 0;
            do {
                if (i != 0) System.out.println("请重新选择更改序号(退出输入0)");//因为学号不应该被更改所以整体应该增加1
                userScanner1 = sc.next();
                sc.nextLine();
                i++;
            } while (SQLDriver.isNotNum(userScanner1) ||
                    -1 > Integer.parseInt(userScanner1) ||
                    8 < Integer.parseInt(userScanner1));
            IntOfuserScanner = Integer.parseInt(userScanner1) + 1;//此时IntOfuserScanner已经完成了偏移加1 范围是1-8
            if (IntOfuserScanner == 1) {
                System.out.println("退出更改");//会直接退出
            } else {
                i = 0;
                do {
                    if (i != 0)
                        System.out.println("输入的" + Student.getIndexChinese(IntOfuserScanner) + "有误(可能重复)");
                    System.out.println("请输入更改的新的" + Student.getIndexChinese(IntOfuserScanner));
                    System.out.println("IntOfuserScanner = " + IntOfuserScanner);
                    userScanner2 = sc.next();
                    sc.nextLine();
                    i++;
                } while (SQLDriver.isNotofIndex(IntOfuserScanner, userScanner2) ||  //输入值合格并且不与当前student重复
                        tempList.get(0).getThingOfIndex(IntOfuserScanner).equals(userScanner2));
                tempList.get(0).setThingOfIndex(IntOfuserScanner, userScanner2);
                SQLDriver.UpdateStudent(tempList.get(0), IntOfuserScanner);
            }
        }
    }

    public static void Find() {
        int i = 0;
        List<Student> tempList;
        System.out.println("请输入要更改查询的序号");
        System.out.println("1.学号 2.姓名 3.性别 4.年龄 5.手机号1" +
                "\n6.手机号2 7.宿舍楼 8.宿舍号 9.查询全部\n0.退出查询");
        String userScanner1, userScanner2;
        do {
            if (i != 0) System.out.println("请输入正确的序号(退出输入0)");
            userScanner1 = sc.next();
            sc.nextLine();
            i++;
        } while (SQLDriver.isNotNum(userScanner1) ||
                -1 > Integer.parseInt(userScanner1) ||
                10 < Integer.parseInt(userScanner1));
        int IntofuserScanner1 = Integer.parseInt(userScanner1);
        if (IntofuserScanner1 == 0) {
            System.out.println("退出查询");
//            return;
        } else {
            if (IntofuserScanner1 == 9) {
                try {
                    tempList = SQLDriver.searchStudent(SQLDriver.conn, null, "all");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("请输入要查询的" + Student.getIndexChinese(IntofuserScanner1));
                i = 0;
                do {
                    if (i != 0) System.out.println("请输入正确的" + Student.getIndexChinese(IntofuserScanner1));
                    userScanner2 = sc.next();
                    sc.nextLine();
                    i++;
                } while (SQLDriver.isNotofIndex(IntofuserScanner1, userScanner2));
                try {
                    if (IntofuserScanner1 == 3) userScanner2 = userScanner2.equals("男") ? "1" : "0";
                    tempList = SQLDriver.searchStudent(SQLDriver.conn, userScanner2, Student.getIndex(IntofuserScanner1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (tempList == null) {
                System.out.println("未找到目标学生或者数据库为空");
                return;
            }
            System.out.println(Student.toString(tempList));
            //TODO 选择快速排序
            System.out.println("请选择是否快速排序");
            System.out.println("0.退出查询 1.学号大小 2.名字长度");
            i = 0;
            do {
                if (i != 0) System.out.println();
                userScanner2 = sc.next();
                sc.nextLine();
                i++;
            } while (SQLDriver.isNotNum(userScanner2) ||
                    -1 > Integer.parseInt(userScanner2) ||
                    3 < Integer.parseInt(userScanner2));
            if (Integer.parseInt(userScanner2) == 0) {
                System.out.println("查询结束");
                return;
            } else {
                if (Integer.parseInt(userScanner2) == 1) {
                    System.out.println("排序如下");
                    System.out.println(Student.toString(Student.Qsort(tempList, "Student_ID")));
                } else {
                    System.out.println("排序如下");
                    System.out.println(Student.toString(Student.Qsort(tempList, "Name")));
                }
            }
            System.out.println("查询结束");
        }

    }
}
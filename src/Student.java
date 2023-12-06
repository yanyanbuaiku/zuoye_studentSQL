import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * 实现StudentList接口
 * 提供基础Student的方法支持
 * 重写Object.toString方法
 */
public class Student {
    private String Student_ID; //学号
    //bigint
    private String Name = null; //姓名
    //varchar 50位
    private boolean Sex = true; //性别
    //tinyint 1位(布尔类型) 1 true 男    0 false 女
    private int Age = 0; //年龄
    //int
    private String Phone_1; //手机号
    //bigint
    private String Phone_2; //手机号
    //bigint
    private String Studentbuilding = null;
    //char(20)
    private String StudentRoom = null; //宿舍
    //char 20位

    public Student() {
    }

    public Student(String student_ID, String name, Boolean sex, int age,
                   String phone_1, String phone_2, String studentbuilding, String studentRoom) {
        this.Student_ID = student_ID;
        this.Name = name;
        this.Sex = sex;
        this.Age = age;
        this.Phone_1 = phone_1;
        this.Phone_2 = phone_2;
        this.Studentbuilding = studentbuilding;
        this.StudentRoom = studentRoom;
    }

    public String getThingOfIndex(int x) {
        switch (x) {
            case 1:
                return Student_ID;
            case 2:
                return Name;
            case 3:
                return Sex ? "男" : "女";
            case 4:
                return Integer.toString(Age);
            case 5:
                return Phone_1;
            case 6:
                return Phone_2;
            case 7:
                return Studentbuilding;
            case 8:
                return StudentRoom;
            default:
                return null;
        }
    }

    public void setThingOfIndex(int x, String str) {
        switch (x) {
            case 1:
                Student_ID = str;
                break;
            case 2:
                Name = str;
                break;
            case 3:
                Sex = str.equals("男");
                break;
            case 4:
                Age = Integer.parseInt(str);
                break;
            case 5:
                Phone_1 = str;
                break;
            case 6:
                Phone_2 = str;
                break;
            case 7:
                Studentbuilding = str;
                break;
            case 8:
                StudentRoom = str;
                break;
            default:
                break;
        }
    }

    public static String getIndex(int x) {
        switch (x) {
            case 1:
                return "Student_ID";
            case 2:
                return "Name";
            case 3:
                return "Sex";
            case 4:
                return "Age";
            case 5:
                return "Phone_1";
            case 6:
                return "Phone_2";
            case 7:
                return "Studentbuilding";
            case 8:
                return "StudentRoom";
            default:
                return null;
        }
    }

    public static String getIndexChinese(int x) {
        switch (x) {
            case 1:
                return "学号";
            case 2:
                return "姓名";
            case 3:
                return "性别";
            case 4:
                return "年龄";
            case 5:
                return "手机号1";
            case 6:
                return "手机号2";
            case 7:
                return "宿舍楼";
            case 8:
                return "宿舍号";
            default:
                return null;
        }
    }

    public static String toString(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("学号                姓名           性别   年龄    手机号1           手机号2           宿舍楼   宿舍号    \n");
        for (Student student : students) {
            //TODO
            sb.append(stringOfLength(student.getStudent_ID(), 18))
                    .append(stringOfLength(student.getName(), 12))
                    .append(stringOfLength(student.getSex() ? "男" : "女", 4))
                    .append(stringOfLength(Objects.toString(student.getAge()), 6))
                    .append(stringOfLength(student.getPhone_1(), 16))
                    .append(stringOfLength(student.getPhone_2(), 16))
                    .append(stringOfLength(student.getStudentbuilding(), 6))
                    .append(stringOfLength(student.getStudentRoom(), 4)).append("\n");
        }
        return sb.toString();
    }


    public String Qsort(List<Student> students) {
        //TODO

        return null;
    }

    public String toString() {
        return Student_ID + Name + Sex + Age
                + Phone_1 + Phone_2 + Studentbuilding + StudentRoom;
    }

    /**
     * 用空格增加字符串长度
     *
     * @param str    传入一个非空的字符串
     * @param newlen 新长度应大于str.length()，否则函数对传入字符串无影响
     * @return 返回结尾被空格符填充的字符串
     */
    public static String stringOfLength(@NotNull String str, int newlen) {
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() <= newlen) {
            strBuilder.append(" ");
        }
        str = strBuilder.toString();
        return str;
    }


    public static Student newStudent(Scanner sc) {
        System.out.println("请依次输入  1.学号   2.姓名  3.性别(中文)  4.年龄   5.手机号1   6.手机号2   7.宿舍楼    8.宿舍号  \n");
        String Student_ID = sc.next(); //学号
        String Name = sc.next(); //姓名
        String Sex = sc.next(); //性别
        String Age = sc.next(); //年龄
        String Phone_1 = sc.next(); //手机号
        String Phone_2 = sc.next(); //手机号
        String Studentbuilding = sc.next();//宿舍楼
        String StudentRoom = sc.next(); //宿舍

        sc.nextLine();

        while (SQLDriver.isNotNum(Student_ID)) {
            System.out.println("学号输入有误请重新输入(数字)");
            Student_ID = sc.next();
        }
        while (!Sex.equals("男") && !Sex.equals("女")) {
            System.out.println("性别输入有误请重新输入(中文)");
            Sex = sc.next();
        }
        while (SQLDriver.isNotNum(Age)) {
            System.out.println("年龄输入有误请重新输入(数字)");
            Age = sc.next();
        }
        while (SQLDriver.isNotNum(Phone_1)) {
            System.out.println("手机号1输入有误请重新输入(数字)");
            Phone_1 = sc.next();
        }
        while (SQLDriver.isNotNum(Phone_2)) {
            System.out.println("手机号2输入有误请重新输入(数字)");
            Phone_2 = sc.next();
        }
        while (SQLDriver.isNotNum(Studentbuilding)) {
            System.out.println("宿舍楼输入有误请重新输入(数字)");
            Studentbuilding = sc.next();
        }
        while (SQLDriver.isNotNum(StudentRoom)) {
            System.out.println("宿舍号输入有误请重新输入(数字)");
            StudentRoom = sc.next();
        }

        sc.nextLine();

        return new Student(Student_ID, Name, Sex.equals("男"), Integer.parseInt(Age), Phone_1, Phone_2, Studentbuilding, StudentRoom);
    }

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String student_ID) {
        Student_ID = student_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getSex() {
        return Sex;
    }

    public void setSex(Boolean sex) {
        Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPhone_1() {
        return Phone_1;
    }

    public void setPhone_1(String phone_1) {
        Phone_1 = phone_1;
    }

    public String getPhone_2() {
        return Phone_2;
    }

    public void setPhone_2(String phone_2) {
        Phone_2 = phone_2;
    }

    public String getStudentRoom() {
        return StudentRoom;
    }

    public void setStudentRoom(String studentRoom) {
        StudentRoom = studentRoom;
    }

    public String getStudentbuilding() {
        return Studentbuilding;
    }

    public void setStudentbuilding(String studentbuilding) {
        Studentbuilding = studentbuilding;
    }

}

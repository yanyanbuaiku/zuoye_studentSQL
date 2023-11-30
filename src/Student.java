import java.io.Serializable;

/**
 * 支持序列化<p>
 * 提供get和set方法
 *
 */
public class Student implements Serializable {
    private String Student_ID; //学号
    //bigint
    private String Name = null; //姓名
    //varchar 50位
    private Boolean Sex = true; //性别
    //tinyint 1位(布尔类型) 1 true 男    0 false 女
    private int Age = 0; //年龄
    //int
    private String Phone_1; //手机号
    //bigint
    private String Phone_2; //手机号
    //bigint
    private String StudentRoom = null; //宿舍
    //char 20位

    public Student(){}
    public Student(String student_ID, String name, Boolean sex, int age,
                   String phone_1, String phone_2, String studentRoom) {
        this.Student_ID = student_ID;
        this.Name = name;
        this.Sex = sex;
        this.Age = age;
        this.Phone_1 = phone_1;
        this.Phone_2 = phone_2;
        this.StudentRoom = studentRoom;
    }


    public String getStuAll() {
        return Student_ID + Name + Sex + Age
                + Phone_1 + Phone_2 + StudentRoom;
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
}

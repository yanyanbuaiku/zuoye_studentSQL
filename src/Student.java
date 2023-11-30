import java.io.Serializable;

/**
 * 支持序列化<p>
 * 提供get和set方法
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


}

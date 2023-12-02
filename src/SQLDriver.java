import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLDriver {
    //数据库URL
//    private static String SQLurl = "jdbc:mysql://202.189.5.24:28685/zuoye_student?";//测试线上用


    private static String SQLurl = "jdbc:mysql://127.0.0.1:3306/zuoye_student?";

    //数据库参数
    private static final String SQLParam = "useSSL=false&serverTimezone=GMT%2B8";

    //账号
    private static final String SQLusername = "root";

    //密码
    private static final String SQLuserpassword = "Ww1231230.";

    //状态码，-1表示连接失败 200表示连接成功
    public static int SQLstatcode = 0;

    //创建连接对象
    public static Connection conn = null;

    public static void getSQLConnection() {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("无法找到JDBC驱动请检查 com.mysql.cj.jdbc.Driver 版本是否正确");
            throw new RuntimeException(e);
        }
        //连接数据库

        try {
            conn = DriverManager.getConnection(SQLurl + SQLParam, SQLusername, SQLuserpassword);
        } catch (SQLException e) {
            System.out.println("SQL连接失败失败，请检查连接");
            throw new RuntimeException(e);
        }
        //设置相应状态码
        if (conn == null) {
            SQLstatcode = -1;
        } else {
            SQLstatcode = 200;
        }
    }

    /**
     * @param conn        当前客户端的SQL连接
     * @param Searchthing 要查询的Mode字段内的内容
     * @param Mode        查询的字段
     * @return List<student> || null
     * @throws SQLException 默认抛出
     */
    public static List<Student> searchStudent(Connection conn, String Searchthing, String Mode) throws SQLException {
        if (conn == null) {
            System.out.println("error--searchStudent--conn is null");
            return null;
        }
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String param;
        param = "select * from zuoye_student.student where "+Mode+"=";
        ResultSet rs = null;
        if (!Mode.equals("all")&& st.execute(param + Searchthing)) {
            rs = st.executeQuery(param + Searchthing);
        }else if (Mode.equals("all")) rs =st.executeQuery("select * from zuoye_student.student");

        if (rs != null && rs.first()) {
            System.out.println("Use Database SUCCESS!");
            return getStudents(rs);
        } else {
            System.out.println("Use Database Not Find!");
            return null;
        }
    }

    public static int setStstement(Student student) throws SQLException {
        Student stu = new Student();
        Statement st = conn.createStatement();
        String param = "INSERT INTO " +
                "zuoye_student.student " +
                "(Student_ID, Name, Sex, Age,"
                + "Phone_1, Phone_2, StudentRoom) " +
                "VALUES (?,?,?,?,?,?,?);";
        PreparedStatement pst = conn.prepareStatement(param);

        pst.setString(1, student.getStudent_ID());
        pst.setString(2, student.getName());
        pst.setBoolean(3, student.getSex());
        pst.setInt(4, student.getAge());
        pst.setString(5, student.getPhone_1());
        pst.setString(6, student.getPhone_2());
        pst.setString(7, student.getStudentRoom());
        return pst.executeUpdate();
    }

    private static List<Student> getStudents(ResultSet rs) throws SQLException {
        List<Student> list;
        list = new LinkedList<>();
        if (rs.first()) {
            do {
                Student stu = new Student();
                stu.setStudent_ID(rs.getString("Student_ID"));
                stu.setName(rs.getString("Name"));
                stu.setSex(rs.getInt("Sex") != 0x00);
                stu.setAge(rs.getInt("Age"));
                stu.setPhone_1(rs.getString("Phone_1"));
                stu.setPhone_2(rs.getString("Phone_2"));
                stu.setStudentRoom(rs.getString("StudentRoom"));
                list.add(stu);
            } while (rs.next());
        }
        rs.close();
        return list;
    }

    public static String getSQLurl() {
        return SQLurl;
    }

    public static int getSQLstatcode() {
        return SQLstatcode;
    }
}

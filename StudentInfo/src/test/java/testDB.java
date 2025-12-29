import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testDB {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=Asia/Shanghai",
                "root",
                "041116"
        );
        System.out.println("数据库连接成功");
    }
}

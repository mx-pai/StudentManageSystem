package edu.student.studentinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "学生信息管理系统运行" + "<br>时间" + java.time.LocalTime.now();
    }

    @GetMapping("/test/db")
    public String testDatabase() {
        try {
            String result = jdbcTemplate.queryForObject(
                    "Select '数据库连接'", String.class);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/")
    public String home() {
        return """
                <!DOCTYPE html>
                            <html>
                            <head><title>学生信息管理系统</title></head>
                            <body>
                                <h1>学生信息管理系统</h1>
                                <p>应用已成功启动！</p>
                                <ul>
                                    <li><a href="/hello">测试接口</a></li>
                                    <li><a href="/test/db">测试数据库连接</a></li>
                                </ul>
                            </body>
                            </html>
                """;
    }
}

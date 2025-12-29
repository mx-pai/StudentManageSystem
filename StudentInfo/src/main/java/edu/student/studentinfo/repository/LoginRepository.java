package edu.student.studentinfo.repository;

import edu.student.studentinfo.entity.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginRepository {
    private final JdbcTemplate jdbcTemplate;

    public Login authenticate(String name, String password) {
        try {
            String sql = "select * from login where name = ? and password = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Login.class), name, password);
        } catch (Exception e) {
            return null;
        }
    }

    public int register(String name, String password) {
        String sql = "insert into login(name, password) values(?, ?)";
        return jdbcTemplate.update(sql, name, password);
    }

}

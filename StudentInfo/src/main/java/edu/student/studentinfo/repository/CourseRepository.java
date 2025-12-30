package edu.student.studentinfo.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.student.studentinfo.entity.Course;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    // 查询所有课程
    public List<Course> findAll() {
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Course.class));
    }

    // 根据cno查询
    public Course findByCno(String cno) {
        try {
            String sql = "select * from course where cno = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Course.class), cno);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }

    // save(Course course) - 新增课程
    public int save(Course course) {
        String sql = "insert into course(cno, cname, ccredit, cpno) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                course.getCno(),
                course.getCname(),
                course.getCcredit(),
                course.getCpno());
    }

    // update(Course course) - 更新课程
    public int update(Course course) {
        String sql = "update course set cname=?, ccredit=?, cpno=? where cno=?";
        return jdbcTemplate.update(sql,
                course.getCname(),
                course.getCcredit(),
                course.getCpno(),
                course.getCno());
    }

    // deleteByCno(String cno) - 删除课程
    public int deleteByCno(String cno) {
        String sql = "delete from course where cno=?";
        return jdbcTemplate.update(sql, cno);
    }
}

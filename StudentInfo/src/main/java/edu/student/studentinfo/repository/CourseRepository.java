package edu.student.studentinfo.repository;

import edu.student.studentinfo.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    //查询所有课程
    public List<Course> findAll(){
        String sql = "select * from course";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Course.class));
    }
    //根据cno查询
    public Course findByCno(Course cno) {
        String sql = "select * from course where cno = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Course.class), cno);
    }
}


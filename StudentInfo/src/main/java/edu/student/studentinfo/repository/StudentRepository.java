package edu.student.studentinfo.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.student.studentinfo.entity.Student;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    // public StudentRepository(JdbcTemplate repository) {
    // this.jdbcTemplate = repository;
    // }
    // 查询所有学生
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }

    // 模糊查询学生
    public List<Student> search(String keyword) {
        String sql = "SELECT * FROM student WHERE sno LIKE ? OR sname LIKE ? OR smajor LIKE ?";
        String param = "%" + keyword + "%";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class), param, param, param);
    }

    // 根据学号查询学生
    public Student findBySno(String sno) {
        try {
            String sql = "SELECT * FROM student WHERE sno = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), sno);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 添加学生
    public int save(Student student) {

        String sql = "INSERT INTO student(sno, sname, ssex, sbirthdate, smajor) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                student.getSno(),
                student.getSname(),
                student.getSsex(),
                student.getSbirthdate(),
                student.getSmajor());
    }

    // 删除学生
    public int deleteBySno(String sno) {
        String sql = "DELETE FROM student where sno = ?";
        return jdbcTemplate.update(sql, sno);
    }

    // 更新学生
    public int update(Student student) {
        String sql = "UPDATE student SET sname=?, ssex=?, sbirthdate=?, smajor=? WHERE sno=?";
        return jdbcTemplate.update(sql,
                student.getSname(),
                student.getSsex(),
                student.getSbirthdate(),
                student.getSmajor(),
                student.getSno());
    }

    // 总数
    public int count() {
        String sql = "SELECT COUNT(*) FROM student";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

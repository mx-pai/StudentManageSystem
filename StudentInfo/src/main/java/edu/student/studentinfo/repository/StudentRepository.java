package edu.student.studentinfo.repository;

import edu.student.studentinfo.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;

//    public StudentRepository(JdbcTemplate repository) {
//        this.jdbcTemplate = repository;
//    }
    //查询所有学生
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }
    //根据学号查询学生
    public Student findBySno(String sno) {
        String sql = "SELECT * FROM student WHERE sno = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), sno);
    }
    //添加学生
    public int save(Student student){

        String sql = "INSERT INTO student(sno, sname, ssex, sbirthdate, smajor) VALUE (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                student.getSno(),
                student.getSname(),
                student.getSsex(),
                student.getSbirthdate(),
                student.getSmajor());
    }
    //删除学生
    public int delete(String sno){
        String sql = "DELETE FROM student where sno = ?";
        return jdbcTemplate.update(sql);
    }
    //更新学生
    public int update(Student student){
        String sql = "UPDATE student SET sname=?, ssex=?, sbirthdate=?, smajor=? WHERE sno=?";
        return jdbcTemplate.update(sql,
                student.getSname(),
                student.getSsex(),
                student.getSbirthdate(),
                student.getSmajor(),
                student.getSno());
    }
    //总数
    public int count(){
        String sql = "SELECT COUNT(*) FROM student";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }



}

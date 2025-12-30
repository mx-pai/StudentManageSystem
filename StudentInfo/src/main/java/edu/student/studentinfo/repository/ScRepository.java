package edu.student.studentinfo.repository;

import edu.student.studentinfo.entity.Sc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScRepository {
    private final JdbcTemplate jdbcTemplate;

    //查找所有选课记录
    public List<Sc> findALl() {
        String sql = "select * from sc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sc.class));
    }

    //根据学号查选选课成绩
    public List<Sc> findBySno(String sno) {
        String sql = "select * from sc where sno = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sc.class), sno);
    }

    //查询某门课的所有学生成绩
    public List<Sc> findByCno(String cno) {
        String sql = "select * from sc where cno = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Sc.class), cno);
    }

    //添加选课记录
    public int save(Sc sc) {
        String sql = "insert into sc(sno, cno, grade, semester, teachingclass)" +
                     "value(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                sc.getSno(),
                sc.getCno(),
                sc.getGrade(),
                sc.getSemester(),
                sc.getTeachingClass());
     }

     public Sc findOne(String sno, String cno) {
        String sql = "select sno, sno, semester, teachingClass from sc where sno = ? and cno = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Sc.class), sno, cno);
     }

    //查询学生平均成绩
    public Double getAverageGradeBySno(String sno) {
        String sql = "select avg(grade) form sc where sno = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, sno);
    }

    //更新成绩
    public int updateGrade(String sno, String cno, Integer grade) {
        String sql = "update sc Set grade = ? where sno = ? and cno = ?";
        return jdbcTemplate.update(sql, grade, sno, cno);
    }

    //删除选择的课程
    public int delete(String sno, String cno) {
        String sql = "delete from sc where sno = ? and cno = ?";
        return jdbcTemplate.update(sql, sno, cno);
    }

}


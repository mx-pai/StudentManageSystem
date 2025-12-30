package edu.student.studentinfo.service;

import edu.student.studentinfo.common.BizException;
import edu.student.studentinfo.dto.request.ScGradeUpdateRequest;
import edu.student.studentinfo.dto.request.ScRequest;
import edu.student.studentinfo.dto.response.ScResponse;
import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.entity.Sc;
import edu.student.studentinfo.entity.Student;
import edu.student.studentinfo.repository.CourseRepository;
import edu.student.studentinfo.repository.ScRepository;
import edu.student.studentinfo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScService {
    private final ScRepository scRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private ScResponse toResponse(Sc sc) {
        return new ScResponse(
                sc.getSno(),
                sc.getCno(),
                sc.getGrade(),
                sc.getSemester(),
                sc.getTeachingClass()
        );
    }

    public List<ScResponse> listAll() {
        return scRepository.findALl()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ScResponse> listBySno(String sno) {
        return scRepository.findBySno(sno)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ScResponse> listByCno(String cno) {
        return scRepository.findByCno(cno)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void select(ScRequest req) {
        if (req.sno() == null || req.sno().isBlank())
            throw new BizException(400, "学号不能为空");
        if (req.cno() == null || req.cno().isBlank())
            throw new BizException(400, "课程号不能为空");

        Student stu = studentRepository.findBySno(req.sno());
        if (stu == null)
            throw new BizException(404, "学生不存在");
        Course course = courseRepository.findByCno(req.cno());
        if (course == null)
            throw new BizException(404, "课程不存在");

        if (scRepository.findOne(req.sno(), req.cno()) != null) {
            throw new BizException(409, "已选过该课程");
        }

        Sc sc = new Sc();
        sc.setSno(req.sno());
        sc.setCno(req.cno());
        sc.setGrade(null);
        sc.setSemester(req.semester());
        sc.setTeachingClass(req.teachingClass());

        int rows = scRepository.save(sc);
        if (rows < 0)
            throw new BizException(500, "选课失败");
    }

    public void drop(String sno, String cno) {
        if (scRepository.findOne(sno, cno) == null)
            throw new BizException(404, "选课记录不存在");
        int rows = scRepository.delete(sno, cno);
        if (rows < 0)
            throw new BizException(500, "退课失败");
    }

    public void updateGrade(String sno, String cno, ScGradeUpdateRequest req) {
        if (scRepository.findOne(sno, cno) == null)
            throw new BizException(404, "选课记录不存在");
        int rows = scRepository.updateGrade(sno, cno, req.grade());
        if (rows < 0)
            throw new BizException(500, "录入成绩失败");
    }


}

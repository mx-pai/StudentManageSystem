package edu.student.studentinfo.service;

import edu.student.studentinfo.common.BizException;
import edu.student.studentinfo.common.DtoMapper;
import edu.student.studentinfo.dto.request.StudentCreateRequest;
import edu.student.studentinfo.dto.request.StudentUpdateRequest;
import edu.student.studentinfo.dto.response.StudentResponse;
import edu.student.studentinfo.entity.Student;
import edu.student.studentinfo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private StudentResponse toResponse(Student s) {
        return new StudentResponse(s.getSno(), s.getSname(), s.getSsex(), s.getSbirthdate(), s.getSmajor());
    }

    public List<StudentResponse> listAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponse getBySno(String sno) {
        Student s = studentRepository.findBySno(sno);
        if (s == null) {
            throw new BizException(404, "学生不存在");
        }
        return toResponse(s);
    }

    public void create(StudentCreateRequest req) {
        if (req.sno() == null || req.sno().isBlank()) {
            throw new BizException(400, "学号不能为空");
        }
        Student exists = studentRepository.findBySno(req.sno());
        if (exists != null) {
            throw new BizException(409, "学号已存在");
        }
        Student s = DtoMapper.toStudentEntity(req);
        int rows = studentRepository.save(s);

        if (rows <= 0) {
            throw new BizException(500, "新增失败");
        }
    }

    public void update(String sno, StudentUpdateRequest req) {
        Student old = studentRepository.findBySno(sno);
        if (old == null) {
            throw new BizException(404, "学生不存在");
        }
        Student s = DtoMapper.toStudentEntity(sno, req);

        int rows = studentRepository.update(s);
        if (rows <= 0) {
            throw new BizException(500, "更新失败");
        }
    }

    public void delete(String sno) {
        Student old = studentRepository.findBySno(sno);
        if (old == null) {
            throw new BizException(404, "学生不存在");
        }
        int rows = studentRepository.deleteBySno(sno);
        if (rows <= 0) {
            throw new BizException(500, "删除失败");
        }
    }
    public int count() {
        int count = studentRepository.count();
        if (count == 0) {
            throw new BizException(500, "没有学生存在");
        }
        return count;
    }
}

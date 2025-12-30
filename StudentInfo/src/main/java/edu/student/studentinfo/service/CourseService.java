package edu.student.studentinfo.service;

import edu.student.studentinfo.common.BizException;
import edu.student.studentinfo.common.DtoMapper;
import edu.student.studentinfo.dto.request.CourseCreateRequest;
import edu.student.studentinfo.dto.response.CourseResponse;
import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private CourseResponse toReponse(Course c) {
        return new CourseResponse(
                c.getCno(),
                c.getCname(),
                c.getCcredit(),
                c.getCpno());
    }

    public List<CourseResponse> listALl() {
        return courseRepository.findAll()
                .stream()
                .map(this::toReponse)
                .toList();
    }

    public CourseResponse getByCno(String cno) {
        Course c = courseRepository.findByCno(cno);
        if (c == null) {
            throw new BizException(404, "课程不存在");
        }
        return toReponse(c);
    }

    public void create(CourseCreateRequest req) {
        if (req.cno() == null || req.cno().isBlank()) {
            throw new BizException(400, "课程号不能为空");
        }
        if (courseRepository.findByCno(req.cno()) != null) {
            throw new BizException(409, "课程号已存在");
        }
        Course c = DtoMapper.toCourseEntity(req);

//        int rows = courseRepository.
    }
}


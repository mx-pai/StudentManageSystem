package edu.student.studentinfo.controller;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.response.CourseResponse;
import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.repository.CourseRepository;
import edu.student.studentinfo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses(){
        List<CourseResponse> data = courseService.listALl();
        return ResponseEntity.ok(ApiResponse.ok("查找到所有课程", data));
    }
}

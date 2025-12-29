package edu.student.studentinfo.controller;

import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.repository.CourseRepository;
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

    @GetMapping
    public ResponseEntity<?> getAllCourse(){

        List<Course> courses =  courseRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "查找到所有课程");
        response.put("data", courses);
        return ResponseEntity.ok(response);

    }
}

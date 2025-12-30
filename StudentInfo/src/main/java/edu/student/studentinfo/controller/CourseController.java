package edu.student.studentinfo.controller;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.request.CourseCreateRequest;
import edu.student.studentinfo.dto.request.CourseUpdateRequest;
import edu.student.studentinfo.dto.response.CourseResponse;
import edu.student.studentinfo.entity.Course;
import edu.student.studentinfo.repository.CourseRepository;
import edu.student.studentinfo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses(){
        List<CourseResponse> data = courseService.listALl();
        return ResponseEntity.ok(ApiResponse.ok("查找到所有课程", data));
    }

    @GetMapping("/{cno}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourse(@PathVariable String cno) {
        return ResponseEntity.ok(ApiResponse.ok("查找到课程", courseService.getByCno(cno)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addCourse(@RequestBody CourseCreateRequest req) {
        courseService.create(req);
        return ResponseEntity.ok(ApiResponse.ok("添加课程成功", null));
    }

    @PutMapping("/{cno}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(@PathVariable String cno, @RequestBody CourseUpdateRequest req) {
        courseService.update(cno, req);
        return ResponseEntity.ok(ApiResponse.ok("更新课程成功", null));
    }

    @DeleteMapping("/{cno}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String cno) {
        courseService.delete(cno);
        return ResponseEntity.ok(ApiResponse.ok("删除课程成功", null));
    }

}

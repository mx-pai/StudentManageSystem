package edu.student.studentinfo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.request.CourseCreateRequest;
import edu.student.studentinfo.dto.request.CourseUpdateRequest;
import edu.student.studentinfo.dto.response.CourseResponse;
import edu.student.studentinfo.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses(
            @RequestParam(required = false) String keyword) {
        List<CourseResponse> data = courseService.listALl(keyword);
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
    public ResponseEntity<ApiResponse<Void>> updateCourse(@PathVariable String cno,
            @RequestBody CourseUpdateRequest req) {
        courseService.update(cno, req);
        return ResponseEntity.ok(ApiResponse.ok("更新课程成功", null));
    }

    @DeleteMapping("/{cno}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String cno) {
        courseService.delete(cno);
        return ResponseEntity.ok(ApiResponse.ok("删除课程成功", null));
    }

}

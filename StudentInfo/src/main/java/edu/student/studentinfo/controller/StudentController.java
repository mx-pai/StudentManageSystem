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
import edu.student.studentinfo.dto.request.StudentCreateRequest;
import edu.student.studentinfo.dto.request.StudentUpdateRequest;
import edu.student.studentinfo.dto.response.StudentResponse;
import edu.student.studentinfo.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents(
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.ok("OK", studentService.listAll(keyword)));
    }

    @GetMapping("/{sno}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentBySno(@PathVariable String sno) {
        return ResponseEntity.ok(ApiResponse.ok("OK", studentService.getBySno(sno)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addStudent(@RequestBody StudentCreateRequest req) {
        studentService.create(req);
        return ResponseEntity.ok(ApiResponse.ok("添加学生成功", null));
    }

    @DeleteMapping("/{sno}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String sno) {
        studentService.delete(sno);
        return ResponseEntity.ok(ApiResponse.ok("删除学生成功", null));
    }

    @PutMapping("/{sno}")
    public ResponseEntity<ApiResponse<Void>> updateStudent(@PathVariable String sno,
            @RequestBody StudentUpdateRequest req) {
        studentService.update(sno, req);
        return ResponseEntity.ok(ApiResponse.ok("更新学生信息成功", null));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> countStudents() {
        return ResponseEntity.ok(ApiResponse.ok("success", studentService.count()));
    }
}

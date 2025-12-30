package edu.student.studentinfo.controller;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.request.StudentCreateRequest;
import edu.student.studentinfo.dto.request.StudentUpdateRequest;
import edu.student.studentinfo.dto.response.StudentResponse;
import edu.student.studentinfo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents(){
        return ResponseEntity.ok(ApiResponse.ok("OK", studentService.listAll()));
    }

    @GetMapping("/{sno}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentBySno(@PathVariable String sno){
        return ResponseEntity.ok(ApiResponse.ok("OK", studentService.getBySno(sno)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addStudent(@RequestBody StudentCreateRequest req) {
        studentService.create(req);
        return ResponseEntity.ok(ApiResponse.ok("添加学生成功", null));
    }

    @DeleteMapping("/{sno}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String sno){
        studentService.delete(sno);
        return ResponseEntity.ok(ApiResponse.ok("删除学生成功", null));
    }

    @PutMapping("/{sno}")
    public ResponseEntity<ApiResponse<Void>> updateStudent(@PathVariable String sno, @RequestBody StudentUpdateRequest req) {
        studentService.update(sno, req);
        return ResponseEntity.ok(ApiResponse.ok("更新学生信息成功", null));
    }


    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> countStudents() {
        return ResponseEntity.ok(ApiResponse.ok("success",studentService.count()));
    }
}

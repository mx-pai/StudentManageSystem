package edu.student.studentinfo.controller;

import edu.student.studentinfo.entity.Student;
import edu.student.studentinfo.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", students);
        response.put("count", students.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{sno}")
    public ResponseEntity<?> getStudentBySno(@PathVariable String sno){
        try {
            Student student = studentRepository.findBySno(sno);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "success");
            response.put("data", student);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 404);
            response.put("message", "学生不存在");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        int result = studentRepository.save(student);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("code", 200);
            response.put("message", "添加学生成功");
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 500);
            response.put("message", "添加学生失败");
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/{sno}")
    public ResponseEntity<?> deleteStudent(@PathVariable String sno){
        int result = studentRepository.deleteBySno(sno);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("code", 200);
            response.put("message", "删除学生成功");
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("message", "学生不存在");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping("/{sno}")
    public ResponseEntity<?> updateStudent(@PathVariable String sno, @RequestBody Student student) {
        student.setSno(sno);
        int result = studentRepository.update(student);
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("code", 200);
            response.put("message", "更新学生信息成功");
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("message", "学生不存在");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> countStudents() {
        int count = studentRepository.count();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", count);
        return ResponseEntity.ok(response);
    }
}

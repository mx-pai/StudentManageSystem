package edu.student.studentinfo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.request.ScGradeUpdateRequest;
import edu.student.studentinfo.dto.request.ScRequest;
import edu.student.studentinfo.dto.response.ScResponse;
import edu.student.studentinfo.service.ScService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sc")
public class ScController {
    private final ScService scService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ScResponse>>> listAll(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(ApiResponse.ok("OK", scService.listAll(keyword)));
    }

    @GetMapping("/student/{sno}")
    public ResponseEntity<ApiResponse<List<ScResponse>>> listBySno(@PathVariable String sno) {
        return ResponseEntity.ok(ApiResponse.ok("OK", scService.listBySno(sno)));
    }

    @GetMapping("/{cno}")
    public ResponseEntity<ApiResponse<List<ScResponse>>> listByCno(@PathVariable String cno) {
        return ResponseEntity.ok(ApiResponse.ok("OK", scService.listByCno(cno)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> select(@RequestBody ScRequest req) {
        scService.select(req);
        return ResponseEntity.ok(ApiResponse.ok("选课成功", null));
    }

    @DeleteMapping("/{sno}/{cno}")
    public ResponseEntity<ApiResponse<Void>> drop(@PathVariable String sno, @PathVariable String cno) {
        scService.drop(sno, cno);
        return ResponseEntity.ok(ApiResponse.ok("退课成功", null));
    }

    @PostMapping("/{sno}/{cno}/grade")
    public ResponseEntity<ApiResponse<Void>> updateGrade(@PathVariable String sno,
            @PathVariable String cno,
            @RequestBody ScGradeUpdateRequest req) {
        scService.updateGrade(sno, cno, req);
        return ResponseEntity.ok(ApiResponse.ok("录入成绩成功", null));
    }

}

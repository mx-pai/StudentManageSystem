package edu.student.studentinfo.controller;

import edu.student.studentinfo.common.ApiResponse;
import edu.student.studentinfo.dto.request.LoginRequest;
import edu.student.studentinfo.dto.request.RegisterRequest;
import edu.student.studentinfo.dto.response.LoginResponse;
import edu.student.studentinfo.entity.Login;
import edu.student.studentinfo.repository.LoginRepository;
import edu.student.studentinfo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest req) {
        LoginResponse data = loginService.login(req.name(), req.password());
        return ResponseEntity.ok(ApiResponse.ok("登录成功", data));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest req) {
        loginService.register(req.name(), req.password());
        return ResponseEntity.ok(ApiResponse.ok("注册成功", null));
    }
}

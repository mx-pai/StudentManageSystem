package edu.student.studentinfo.controller;

import edu.student.studentinfo.entity.Login;
import edu.student.studentinfo.repository.LoginRepository;
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
    private final LoginRepository loginRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login loginRequest) {
        Login login = loginRepository.authenticate(loginRequest.getName(), loginRequest.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (login != null) {
            response.put("code", 200);
            response.put("message", "登录成功");
            response.put("user", login);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 401);
            response.put("message", "没有此用户");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Login registerRequest) {
        int result = loginRepository.register(registerRequest.getName(), registerRequest.getPassword());
        Map<String, Object> response = new HashMap<>();
        if (result > 0) {
            response.put("code", 200);
            response.put("message", "注册成功");
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 500);
            response.put("message", "注册失败，此用户可能已经存在");
            return ResponseEntity.status(500).body(response);
        }
    }
}

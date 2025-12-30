package edu.student.studentinfo.service;

import edu.student.studentinfo.common.BizException;
import edu.student.studentinfo.dto.response.LoginResponse;
import edu.student.studentinfo.entity.Login;
import edu.student.studentinfo.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginResponse login(String name, String password){
        if (name == null || name.isBlank() || password == null || password.isBlank()) {
            throw new BizException(400, "用户名或密码不能为空");
        }
        Login login = loginRepository.authenticate(name, password);
        if (login == null) {
            throw new BizException(401, "没有此用户或密码错误");
        }

        String role = name.startsWith("T") ? "teacher" : "student";
        return new LoginResponse(login.getName(), role);
    }

    public void register(String name, String password) {
        if (name == null || name.isBlank() || password == null || password.isBlank()) {
            throw new BizException(400, "用户名或密码不能为空");
        }
        if (loginRepository.existsByName(name)) {
            throw new BizException(409, "用户已存在");
        }
        int rows = loginRepository.register(name, password);
        if (rows <= 0) {
            throw new BizException(500, "注册失败");
        }
    }

}

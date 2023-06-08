package com.example.ch16.controller;

import com.example.ch16.dto.SignInResultDto;
import com.example.ch16.dto.SignUpResultDto;
import com.example.ch16.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-api")
public class SignController {
    private final SignService signService;

    @Autowired//자동주입
    public SignController(SignService signService) {
        this.signService = signService;
    }
    
    @PostMapping("/sign-in")
    public SignInResultDto signIn(@RequestParam String id, @RequestParam String password){
        SignInResultDto signInResultDto = signService.signIn(id, password);
        if(signInResultDto.getCode() == 0){
            System.out.println("[SignIn] 정상적으로 로그인 되었습니다.");
            System.out.println("token 토큰 : "+ signInResultDto.getToken());
        }
        return signInResultDto;
    }
    @PostMapping("/sign-up")
    public SignUpResultDto signUp(@RequestParam String id, @RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam String role){
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, email, role);

        return signUpResultDto;
    }
    @PostMapping("/exception")
    public void exception() throws RuntimeException{
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}

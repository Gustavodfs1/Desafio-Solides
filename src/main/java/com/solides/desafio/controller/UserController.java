package com.solides.desafio.controller;

import com.solides.desafio.domain.vo.request.LoginRequestVo;
import com.solides.desafio.domain.vo.request.SignUpRequestVo;
import com.solides.desafio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequestVo signUpRequestVo) {
        return new ResponseEntity<>(userService.signup(signUpRequestVo),
                                    HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestVo loginRequestVo){
        return new ResponseEntity<>(userService.login(loginRequestVo), HttpStatus.OK);

    }
}

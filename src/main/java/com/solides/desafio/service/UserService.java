package com.solides.desafio.service;

import com.solides.desafio.constants.UNIQUE_CONSTRAINTS;
import com.solides.desafio.domain.vo.request.LoginRequestVo;
import com.solides.desafio.domain.vo.request.SignUpRequestVo;
import com.solides.desafio.domain.User;
import com.solides.desafio.exception.UserAlreadyExistsException;
import com.solides.desafio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String signup(SignUpRequestVo signUpRequestVo) {
       User user = setUser(signUpRequestVo);
       try{
           userRepository.save(user);
       } catch (DataIntegrityViolationException e){
           var duplicatedFields = new ArrayList<String>();
           if(e.getMessage().contains((UNIQUE_CONSTRAINTS.EMAIL_UNIQUE))){
               duplicatedFields.add("Este e-mail já esta sendo utilizado");
           }
           if(e.getMessage().contains((UNIQUE_CONSTRAINTS.USERNAME_UNIQUE))){
               duplicatedFields.add("Este username já esta sendo utilizado");
           }
            throw new UserAlreadyExistsException("Usuário já existente", duplicatedFields);
       }
       return jwtService.generateToken(user.getUsername());
    }

    public String login(LoginRequestVo loginRequestVo) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestVo.username(), loginRequestVo.password()));

        return jwtService.generateToken(loginRequestVo.username());
    }

    private User setUser(SignUpRequestVo signUpRequestVo) {
       return User.builder()
                .email(signUpRequestVo.email())
                .password(passwordEncoder.encode(signUpRequestVo.password()))
                .username(signUpRequestVo.username()).build();
    }


}

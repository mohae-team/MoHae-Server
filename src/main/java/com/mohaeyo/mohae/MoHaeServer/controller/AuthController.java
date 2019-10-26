package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.AlreadyExistException;
import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.request.SignInModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.SignUpModel;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/mohae")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpModel signUpModel) {
        Optional<User> user = authService.getUserById(signUpModel.getId());
        if (user.isPresent()) {
            throw new AlreadyExistException("Account Already Exist");
        } else {

            User newUser = new User(
                    signUpModel.getId(),
                    signUpModel.getPassword(),
                    signUpModel.getUsername(),
                    Collections.emptyList(),
                    signUpModel.getAddress(),
                    ""
            );

            authService.saveUser(newUser);
            Token token = new Token(newUser);
            return ok(token.getTokenResponse());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInModel signInModel) {
        Optional<User> user = authService.getUserById(signInModel.getId());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(signInModel.getPassword())) {
                return ok(new Token(user.get()).getTokenResponse());
            } else {
                throw new NotFoundException("Password Not Found");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }
}

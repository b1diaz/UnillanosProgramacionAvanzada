package com.example.controllers;

import com.example.entities.user;
import com.example.repositories.userRepository;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userController {

    @Autowired
    private userRepository userRepository;

    @PostMapping("/Login")
    public user login(@RequestBody user user){
        com.example.entities.user result = new user();
        TokenUtils utils = new TokenUtils();

         user userLogin = this.get(user.getName());

        if (userLogin.getName().equals(user.getName()) && userLogin.getPassword().equals(user.getPassword())){
            result.setName(userLogin.getName());
            result.setPassword(utils.getToken(userLogin));
        }
        return result;
    }

    public user get(String name) {
        return this.userRepository.findByname(name);
    }

    @PostMapping("/user")
    public boolean create(@RequestBody user user){
        try {
            this.userRepository.save(user);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

}

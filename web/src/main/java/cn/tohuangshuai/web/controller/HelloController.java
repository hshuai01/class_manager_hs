package cn.tohuangshuai.web.controller;

import cn.tohuangshuai.pojo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

//    @Autowired
//    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot";
    }

//    @PostMapping("/test/{id}")
//    public User test(@PathVariable("id") Integer id){
//        return userService.findUser(id);
//    }
}

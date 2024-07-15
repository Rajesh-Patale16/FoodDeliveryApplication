package com.FoodDeliveryApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FoodDeliveryApplication.Entity.User;
import com.FoodDeliveryApplication.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginuser")
    public String loginUser(@RequestBody User user) throws Exception{
        User userobj = userService.fetchUserByEmailAndPassword(user.getEmail(), user.getPassword());

        if (user == null) {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }

        return "Login successful";
    }
}

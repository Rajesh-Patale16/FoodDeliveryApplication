package com.FoodDeliveryApplication.Controller;

import com.FoodDeliveryApplication.Entity.User;
import com.FoodDeliveryApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class UserController {


    @Autowired
    UserService userService;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @PostMapping("/registerUser")

    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{

        if (!isValidEmail(user.getEmail())) {
            return new ResponseEntity<>("enter valid userId", HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() != null || !"".equals(user.getEmail()) || user.getUsername() != null) {
            User userObj = userService.FindByUsername(user.getUsername());
            if (userObj != null) {
                throw new Exception("User with " + user.getUsername() + " already exists !!!");
            }
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/loginuser")
    public String loginUser(@RequestBody User user) throws Exception{
        User userobj = userService.fetchUserByEmailAndPassword(user.getEmail(), user.getPassword());

        if (user == null) {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }

        return "Login successful";
    }
}

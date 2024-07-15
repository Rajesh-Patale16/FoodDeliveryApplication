package com.FoodDeliveryApplication.Service;

import com.FoodDeliveryApplication.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

           public User saveUser(User user);
           public String LoginUser();


           public User FindByUsername(String username);

    public User fetchUserByEmailAndPassword(String email, String password) ;

}

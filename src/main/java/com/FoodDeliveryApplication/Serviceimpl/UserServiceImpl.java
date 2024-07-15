package com.FoodDeliveryApplication.Serviceimpl;


import com.FoodDeliveryApplication.Entity.User;
import com.FoodDeliveryApplication.Repository.UserRepository;
import com.FoodDeliveryApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user){ return userRepository.save(user);}

    public User FindByUsername(String username) {return userRepository.findByUsername(username);}

    @Override
    public User fetchUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public String LoginUser() {return null;}

}


package com.FoodDeliveryApplication.Repository;

import com.FoodDeliveryApplication.Entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends  JpaRepository<User,Id> {

       User findByUsername(String username);
       User findByEmailAndPassword(String email, String password);

}

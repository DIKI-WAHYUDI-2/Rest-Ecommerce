package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.User;
import com.ecommerce.RestEcommerce.requests.LoginUserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

//    User findByEmailAndPassword(LoginUserRequest request);
}

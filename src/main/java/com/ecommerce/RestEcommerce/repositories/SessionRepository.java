package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.Session;
import com.ecommerce.RestEcommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Integer> {
//    Session findByUser(User user);
    @Query("SELECT s FROM  Session s JOIN s.user u WHERE u.email = :email")
    List<Session> findSessionByUser(@Param("email") String email);
    Optional<Session> findByUser(User user);
    Optional<Session> findSessionByUserEmail(String email);
}

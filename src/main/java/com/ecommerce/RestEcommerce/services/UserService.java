package com.ecommerce.RestEcommerce.services;

import com.ecommerce.RestEcommerce.entities.Role;
import com.ecommerce.RestEcommerce.entities.Session;
import com.ecommerce.RestEcommerce.entities.User;
import com.ecommerce.RestEcommerce.repositories.RoleRepository;
import com.ecommerce.RestEcommerce.repositories.SessionRepository;
import com.ecommerce.RestEcommerce.repositories.UserRepository;
import com.ecommerce.RestEcommerce.requests.AddUserRequest;
import com.ecommerce.RestEcommerce.requests.LoginUserRequest;
import com.ecommerce.RestEcommerce.requests.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(AddUserRequest request){

        Role role = roleRepository.findById(2).orElse(null);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        repository.save(user);
        return user;
    }

    public User addImage(String email, MultipartFile file) throws IOException {
        User user = repository.findById(email).orElseThrow(()-> new RuntimeException("User not found"));
        user.setImage(file.getBytes());
        repository.save(user);
        return user;
    }

//    public User login(LoginUserRequest request){
//        User user = repository.findByEmailAndPassword(request);
//        return user;
//    }

    public List<User> getAll(){
        List<User> users = repository.findAll();
        return users;
    }

    public User get(String username){
        User user = repository.findById(username).orElseThrow(()-> new RuntimeException("User not found"));
        return user;
    }

    @Transactional
    public User update(String username, UpdateUserRequest request){
        User user = repository.findById(username).orElseThrow(()-> new RuntimeException("User not found"));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
        return user;
    }

    public void delete(String username){
        User user = repository.findById(username).orElseThrow(()-> new RuntimeException("User not found"));
        repository.delete(user);
    }

    public void addUserToSession(User user) {
        // Cek apakah ada sesi yang sudah ada untuk pengguna ini
        Optional<Session> existingSession = sessionRepository.findByUser(user);

        if (existingSession.isPresent()) {
            // Jika sesi sudah ada, lempar pengecualian atau kembalikan pesan yang sesuai
            throw new RuntimeException("Session already exists for user: " + user.getEmail());
        } else {
            // Jika sesi tidak ada, buat sesi baru
            Session session = new Session();
            session.setUser(user);
            sessionRepository.save(session);
        }
    }


    public void deleteUserFromSession(String email){

        Optional<Session> existingSession = sessionRepository.findSessionByUserEmail(email);

        if (existingSession.isPresent()) {
            sessionRepository.delete(existingSession.get());
        } else {
            throw new RuntimeException("Session does not exist for user: " + email);
        }
    }

    public List<Session> getSessionByUser(String email){
        List<Session> session = sessionRepository.findSessionByUser(email);
        return session;
    }
}

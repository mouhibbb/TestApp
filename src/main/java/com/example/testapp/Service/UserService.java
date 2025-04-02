package com.example.testapp.Service;

import com.example.testapp.Entity.User;
import com.example.testapp.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        System.out.println("mail"+userRepository.findByEmail(email));

        return userRepository.findByEmail(email);
    }

}

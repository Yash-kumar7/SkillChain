package com.backend.service.impl;

import com.backend.user.User;
import com.backend.exception.UserAlreadyExistsException;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        // Check if the email is already registered
        User existingUser = findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteByID(String id)
    {
        userRepository.deleteById(id);
    }


}


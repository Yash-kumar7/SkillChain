package com.backend.service;

import com.backend.user.User;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    User updateUser(User user);
    void  deleteByID(String id);

}

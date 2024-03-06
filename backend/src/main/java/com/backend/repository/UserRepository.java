package com.backend.repository;

import com.backend.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    void deleteById(String id);
}

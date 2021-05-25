package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getAllUsers();

    void update(User user);

    User getById(Long id);

    void delete(User user);

    User getByEmail(String email);
}

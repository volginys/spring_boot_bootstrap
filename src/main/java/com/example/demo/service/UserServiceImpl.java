package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}

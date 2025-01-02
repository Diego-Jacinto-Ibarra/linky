package com.diegojacinto.linky.linky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegojacinto.linky.linky.model.entity.User;
import com.diegojacinto.linky.linky.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) {

        return null;
    }

}

package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.model.User;
import com.andreyshlyahtovich.jwtrestexample.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(Long id);
    User save(User user);
    User replace(Long id, User newUser);
    void delete(Long id);

}

package com.andreyshlyahtovich.jwtrestexample.service;

import com.andreyshlyahtovich.jwtrestexample.exception.UserNotChangedException;
import com.andreyshlyahtovich.jwtrestexample.exception.UserNotCreatedException;
import com.andreyshlyahtovich.jwtrestexample.model.User;
import com.andreyshlyahtovich.jwtrestexample.exception.UserNotFoundException;
import com.andreyshlyahtovich.jwtrestexample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User newUser) {
        try {
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UserNotCreatedException(newUser);
        }
    }

    @Override
    public User replace(Long id, User newUser) {
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        user.setUsername(newUser.getUsername());
                        return userRepository.save(user);
                    })
                    .orElseGet(() -> {
                        newUser.setId(id);
                        return save(newUser);
                    });
        } catch (Exception e) {
            throw new UserNotChangedException(newUser);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }
}

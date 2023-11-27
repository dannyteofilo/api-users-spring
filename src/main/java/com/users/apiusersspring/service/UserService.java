package com.users.apiusersspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.users.apiusersspring.model.User;
import com.users.apiusersspring.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return this.userRepository.findById(id);
    }

    public User saveUser(User user) {
        Example<User> example = Example.of(user);
        Optional<User> existUser = userRepository.findOne(example);

        if (existUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya existe: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User updateUser(String id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(newUser.getName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setAddress(newUser.getAddress());

            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id);

    }
}

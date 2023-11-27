package com.users.apiusersspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.users.apiusersspring.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}

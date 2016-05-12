package com.fissionlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fissionlabs.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findUserByUsername(String username);
}

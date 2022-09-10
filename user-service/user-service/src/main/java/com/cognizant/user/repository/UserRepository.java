package com.cognizant.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

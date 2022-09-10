package com.cognizant.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.user.entity.User;

@Repository
public class UserDao {

	@Autowired
	private MongoOperations mongoOperation;

	public User validateUserDetail(String username, String password) {
		return mongoOperation.findOne(new Query(Criteria.where("email").is(username).and("password").is(password)),
				User.class);
	}

}

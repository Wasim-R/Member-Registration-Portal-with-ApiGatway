package com.cognizant.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.user.entity.Dependent;

@Repository
public interface DependentRepository extends MongoRepository<Dependent, String> {

	public List<Dependent> findByUserId(String userId);

}

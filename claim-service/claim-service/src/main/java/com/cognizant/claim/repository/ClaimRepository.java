package com.cognizant.claim.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.claim.entity.Claim;

@Repository
public interface ClaimRepository extends MongoRepository<Claim, Long> {

	Optional<Claim> findByMemberId(String memberId);

}

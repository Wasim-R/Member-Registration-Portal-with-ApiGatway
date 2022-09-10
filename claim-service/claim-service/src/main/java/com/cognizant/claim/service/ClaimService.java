package com.cognizant.claim.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.entity.MemberDetail;
import com.cognizant.claim.repository.ClaimRepository;

@Service
public class ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Claim save(Claim claimData) {
		claimData.setId((long) (Math.random() * 100000 + 9876543210l));
		return claimRepository.save(claimData);
	}

	public MemberDetail checkMemberClaim(String id) {
		return restTemplate.getForObject("http://localhost:8082/api/user/check/claim/" + id, MemberDetail.class);
	}

	public Optional<Claim> getClaimByMemberId(String memberId) throws Exception {
		try {
			return claimRepository.findByMemberId(memberId);
		} catch (Exception e) {
			throw new NoSuchElementException("No such element found");
		}
	}

}

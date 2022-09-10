package com.cognizant.claimservice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.cognizant.claim.controller.ClaimController;
import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.entity.MemberDetail;
import com.cognizant.claim.service.ClaimService;
import com.cognizant.claimservice.mock.ClaimMockData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClaimTestController {
	
	@InjectMocks
	ClaimController claimController;
	
	@Mock
	ClaimService claimService;

	ClaimMockData claimMockData;
	
	@BeforeEach
	public void setUp() throws Exception {
		claimMockData = new ClaimMockData();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	void testSubmitClaim(){
		
		Claim claimMockValue = claimMockData.getNewClaimMockData();
		
		Claim claim = new Claim();
		
		Mockito.when(claimService.save(claimMockValue)).thenReturn(claim);
		
		ResponseEntity<?> claimValue = claimController.submitClaim(claimMockValue);
		
		if(!Objects.isNull(claimValue)) {
			Assert.assertNotNull(claimValue);
		}
	}
	
	@Test
	void test1SubmitClaim(){
		
		Claim claimMockValue = null;
		
		Claim claim = null;
		
		Mockito.when(claimService.save(claimMockValue)).thenReturn(claim);
		
		ResponseEntity<?> claimValue = claimController.submitClaim(claimMockValue);
		
		if(Objects.isNull(claimValue)) {
			Assert.assertNull(claimValue);
		}
	}
	
	@Test
	void testVerifyClaimMember(){
		String id = "R-190";
		
		Optional<Claim> claim = Optional.ofNullable(new Claim());
		
		try {
			Mockito.when(claimService.getClaimByMemberId(id)).thenReturn(claim);
			
			if (claim.isPresent()) {
				Map<String, Object> response = new HashMap<>();
				response.put("error", true);
				response.put("message", "Claim Already submitted");
				ResponseEntity<?> claimValue = claimController.verifyClaimMember(id);
				Assert.assertNotNull(claimValue);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	void test1VerifyClaimMember(){
		
		String id = "D-111";
				
			MemberDetail memberDetail = new MemberDetail();

			Mockito.when(claimService.checkMemberClaim(id)).thenReturn(memberDetail);

			ResponseEntity<?> memberDetailValue = null;
			try {
				memberDetailValue = claimController.verifyClaimMember(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertNotNull(memberDetailValue);
	}


}

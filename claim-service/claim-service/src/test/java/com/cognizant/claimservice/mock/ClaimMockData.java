package com.cognizant.claimservice.mock;

import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.entity.MemberDetail;

public class ClaimMockData {
	
	public Claim getClaimMockData() {
		Claim claim = new Claim();
		
		claim.setId(9090909090l);
		claim.setName("Haritha");
		claim.setDateOfBirth("18-03-1997");
		claim.setDateOfAdmission("19-03-2021");
		claim.setDateOfDischarge("25-03-2021");
		claim.setTotalBillAmount(10000);
		claim.setMemberId("R-190");
		claim.setUserId("R-190");
		 
		return claim;
	}
	
	public Claim getNewClaimMockData() {
		Claim claim = new Claim();
		
		claim.setName("Haritha");
		claim.setDateOfBirth("18-03-1997");
		claim.setDateOfAdmission("19-03-2021");
		claim.setDateOfDischarge("25-03-2021");
		claim.setTotalBillAmount(10000);
		claim.setMemberId("D-111");
		claim.setUserId("R-190");
		 
		return claim;
	}
	
	public MemberDetail getMockMemberDetail() {
		MemberDetail memberDetail = new MemberDetail();
		
		memberDetail.setMemberId("D-111");
		memberDetail.setName("Latha");
		memberDetail.setDateOfBirth("18-02-1997");
		
		return memberDetail;
	}

}

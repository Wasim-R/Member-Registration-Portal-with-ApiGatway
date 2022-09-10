package com.cognizant.claim.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection="claim")
public class Claim {
	
	@Id
	private Long id;
	
	private String name;
	
	private String dateOfBirth;
	
	private String dateOfAdmission;
	
	private String dateOfDischarge;
	
	private int totalBillAmount;
	
	private String memberId;
	
	private String userId;
	
}

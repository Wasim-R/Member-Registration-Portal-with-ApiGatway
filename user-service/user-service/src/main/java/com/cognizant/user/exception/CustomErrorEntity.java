package com.cognizant.user.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorEntity {

	public CustomErrorEntity(Date timeStamp, String message, Integer statusCode, String status, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.statusCode = statusCode;
		this.status = status;
		this.details = details;
	}

	private Date timeStamp;
	private String message;
	private Integer statusCode;
	private String status;
	private String details;

}

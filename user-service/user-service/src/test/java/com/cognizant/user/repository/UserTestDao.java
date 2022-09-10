package com.cognizant.user.repository;

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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cognizant.user.entity.User;
import com.cognizant.user.mock.UserMockData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserTestDao {
	
	@InjectMocks
	UserDao userDao;
	
	@Mock
	MongoOperations mongoOperation;

	UserMockData userMockData;
	
	@BeforeEach
	public void setUp() throws Exception {
		userMockData = new UserMockData();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	void testValidateUserDetail() {
		User user = new User();
		
		Mockito.when(mongoOperation.findOne(new Query(Criteria.where("email").is("haritha@gmail.com").and("password").is("Haritha@123")),
				User.class)).thenReturn(user);
		
		User userValue = userDao.validateUserDetail("haritha@gmail.com", "Haritha@123");
		
		Assert.assertNotNull(userValue);
	}
	
}

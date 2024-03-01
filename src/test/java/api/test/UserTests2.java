package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User2;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User2 userPayload2;
	public Logger logger;
	int id ;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload2 = new User2();
		
		userPayload2.setId(String.valueOf(faker.number().numberBetween(30, 1000)));
		userPayload2.setEmpname(faker.name().firstName());	
		userPayload2.setEmpsal(faker.number().randomDigit());
		userPayload2.setAge(faker.number().numberBetween(20, 60));
		userPayload2.setProfile(faker.name().lastName());
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*********** Creating User *********");
		Response response = UserEndPoints2.createUser(userPayload2);
		id = response.jsonPath().getInt("id");		
		Assert.assertEquals(response.getStatusCode(), 201);
		logger.info("*********** User is Created *********");
	}
	
	@Test(priority=2, dependsOnMethods = {"testPostUser"})
	public void testGetUser()
	{
		logger.info("*********** Reading User Info *********");
		Response response2 = UserEndPoints2.readUser(String.valueOf(id));
		response2.then().log().all();
		Assert.assertEquals(response2.getStatusCode(), 200);
		logger.info("*********** User Info is displayed *********");
	}
	
	
	@Test(priority=3, dependsOnMethods = {"testPostUser"})
	public void testUpdateUser()
	{
		logger.info("*********** Updating User *********");
		userPayload2.setEmpname(faker.name().firstName());	
		userPayload2.setEmpsal(faker.number().randomDigit());
		userPayload2.setAge(faker.number().numberBetween(20, 60));
		userPayload2.setProfile(faker.name().lastName());
		
		Response response = UserEndPoints2.updateUser(String.valueOf(id), userPayload2);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User updated *********");
		
		//Checking after update
		Response responseAfterUpdate = UserEndPoints2.readUser(String.valueOf(id));
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
				
	}
	
	@Test(priority=4, dependsOnMethods = {"testPostUser"})
	public void testDeleteUserByName()
	{
		logger.info("*********** Deleting User *********");
		Response response = UserEndPoints2.deleteUser(String.valueOf(id));
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** User Deleted *********");
	} 

}

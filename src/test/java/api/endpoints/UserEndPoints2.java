package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;


import api.payload.User2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
// Created for perform Create, Read, Update, Delete requests the user API

public class UserEndPoints2 {
	
	//Method created for getting URL from properties file
	public static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); // load the properties file
		return routes;
	}

	public static Response createUser(User2 payload)
	{
		String post_url = getURL().getString("post_url");
		
		Response res = given()
		    .contentType(ContentType.JSON)
		    .body(payload)
		
		.when()
		    .post(post_url);
		
		return res;
		
	}
	
	public static Response readUser(String userId)
	{
		String get_url = getURL().getString("get_url");
		
		
		
		Response res = given()
				      .pathParam("userid", userId)
				          
	               	.when()
	               	     .get(get_url);
		
		return res;
		
	}
	
	public static Response updateUser(String userId, User2 payload)
	{
		String update_url = getURL().getString("update_url");
		
		Response res = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("userid", userId)
		    .body(payload)
		    
		
		.when()
		    .put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userId)
	{
		String delete_url = getURL().getString("delete_url");
		
		Response res = given()
				          .pathParam("userid", userId)
		    
	               	.when()
	               	     .delete(delete_url);
		return res;
		
	}

}

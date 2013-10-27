package com.callectiv.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasXPath;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.callectiv.api.resources.AuthResource;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.mapper.ObjectMapper;

public class AuthenticationTest {

    AuthResource auth;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	RestAssured.baseURI = System.getProperty("api.host");
	RestAssured.port = 80;
    }

    @Before
    public void setUp() throws Exception {
	auth = new AuthResource();
	auth.setApikey(System.getProperty("api.key"));
	auth.setSecret(System.getProperty("api.secret"));
    }

    @Test
    public void testAuthenticationSuccessXML() {
	given().body(auth, ObjectMapper.JAXB).contentType("application/xml").expect().statusCode(200)
		.body(hasXPath("/authorization/token")).body(hasXPath("/authorization/@expiryTime")).when()
		.post("/authentication");
    }

    @Test
    public void testAuthenticationSuccessJSON() {
	given().body(auth, ObjectMapper.JACKSON).contentType("application/json").expect().statusCode(200)
		.body(hasXPath("/authorization/token")).body(hasXPath("/authorization/@expiryTime")).when()
		.post("/authentication");
    }

    @Test
    public void testAuthenticationFailureXML() {
	auth.setApikey("somerandomcrap");
	auth.setSecret("morerandomcrap");

	given().body(auth, ObjectMapper.JAXB).contentType("application/xml").expect().statusCode(401).when()
		.post("/authentication");
    }

    @Test
    public void testAuthenticationFailureJSON() {

	auth.setApikey("anyrandomcrap");
	auth.setSecret("definitelyrandomcrap");

	given().body(auth, ObjectMapper.JACKSON).contentType("application/json").expect().statusCode(401).when()
		.post("/authentication");
    }

}

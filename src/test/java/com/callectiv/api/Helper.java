package com.callectiv.api;

import com.callectiv.api.resources.AuthResource;
import com.callectiv.api.resources.ContactResource;
import com.callectiv.api.resources.SubjectResource;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

import static com.jayway.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasXPath;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 28/10/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public static String authenticateWithXML(AuthResource auth) {
        RestAssured.baseURI = System.getProperty("api.host");
        RestAssured.port = 80;

        return given().body(auth, ObjectMapper.JAXB).contentType("application/xml").expect().statusCode(200)
            .when().post("/authentication").xmlPath().get("/authorization/token");
    }

    public static String autheticateWithJson(AuthResource auth) {
        RestAssured.baseURI = System.getProperty("api.host");
        RestAssured.port = 80;

        JsonPath jsonPath = given().body(auth, ObjectMapper.JACKSON).contentType("application/json").header("Accept", "application/json")
                .expect().statusCode(200)
                .when().post("/authentication").jsonPath();

        return jsonPath.getString("token");
    }

    public static AuthResource getAuth() {
        AuthResource auth = new AuthResource();
        auth.setApikey(System.getProperty("api.key"));
        auth.setSecret(System.getProperty("api.secret"));
        return auth;
    }

    public static SubjectResource getSubjectResource(String phone, String reference, String message) {
        ContactResource contactResource = new ContactResource();
        contactResource.setPhone(phone);

        SubjectResource subjectResource = new SubjectResource();
        subjectResource.setReference(reference);
        subjectResource.setContact(contactResource);
        subjectResource.setMessage(message);
        return subjectResource;
    }
}

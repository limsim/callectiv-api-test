package com.callectiv.api;

import com.callectiv.api.resources.ContactResource;
import com.jayway.restassured.mapper.ObjectMapper;
import org.junit.Test;

import static com.callectiv.api.Helper.autheticateWithJson;
import static com.callectiv.api.Helper.getAuth;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.callectiv.api.resources.SubjectResource;

import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 28/10/13
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class RegisterSubjectTest {

    @Test
    public void registerSubject() {
        String authToken = autheticateWithJson(getAuth());

        ContactResource contactResource = new ContactResource();
        contactResource.setPhone("447904181648");

        SubjectResource subjectResource = new SubjectResource();
        subjectResource.setReference("joblisting-4568903");
        subjectResource.setContact(contactResource);
        subjectResource.setMessage("Lim testing");


        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .body(subjectResource, ObjectMapper.GSON)
                .expect().statusCode(200)
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo("Lim testing"))
                .body("reference", equalTo("joblisting-4568903"))
                .when().post("/subject").print();
    }

}

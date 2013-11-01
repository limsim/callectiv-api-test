package com.callectiv.api;

import com.callectiv.api.resources.ContactResource;
import com.jayway.restassured.mapper.ObjectMapper;
import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.InRange;
import org.junit.Test;

import static com.callectiv.api.Helper.autheticateWithJson;
import static com.callectiv.api.Helper.getAuth;
import static com.jayway.restassured.RestAssured.given;
import com.callectiv.api.resources.SubjectResource;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 28/10/13
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Theories.class)
public class RegisterSubjectTest {

    @Theory
    public void registerSubject(@ForAll String reference, @ForAll String message, @ForAll long phone) {
        String authToken = autheticateWithJson(getAuth());

        ContactResource contactResource = new ContactResource();
        contactResource.setPhone(String.valueOf(phone));

        SubjectResource subjectResource = new SubjectResource();
        subjectResource.setReference(reference);
        subjectResource.setContact(contactResource);
        subjectResource.setMessage(message);


        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .body(subjectResource, ObjectMapper.GSON)
                .expect().statusCode(200)
                .when().post("/subject").print();
    }

    @Theory
    public void StringGeneration(@ForAll String testString, @ForAll String testString2) {
        System.out.println(testString + " -- ");
    }

}

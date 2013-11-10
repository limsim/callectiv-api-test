package com.callectiv.api;

import com.callectiv.api.resources.ContactResource;
import com.jayway.restassured.mapper.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static com.callectiv.api.Helper.autheticateWithJson;
import static com.callectiv.api.Helper.getAuth;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.callectiv.api.resources.SubjectResource;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 28/10/13
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class RegisterSubjectTest {

    private String authToken;

    @Before
    public void beforeMethod() {
        authToken = autheticateWithJson(getAuth());
    }

    @Test
    public void registerSubject() {
    	String reference = "item40_cust8945.2012" + Math.random();
        SubjectResource subjectResource = getSubjectResource("447904181648",reference , "Lim testing");

        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .body(subjectResource, ObjectMapper.GSON)
                .expect().statusCode(200)
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo("Lim testing"))
                .body("reference", equalTo(reference))
                .when().post("/subject").print();
    }



    @Test
    public void registerWithLongReferenceWillWork() throws Exception {
    	String reference = "joblisting-4568903" + Math.random();
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, "Lim testing");
        checkStatusCode(subjectResource, 200);
    }

    @Test
    public void registerWithNoMessageWillReturn400StatusCode() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", "joblisting-4568903", null);
        checkStatusCode(subjectResource, 400);
    }

    @Test
    public void registerSubjectWithInvalidPhoneNumberWillReturn400StatusCode() throws Exception {
        SubjectResource subjectResource = getSubjectResource("abc", "joblisting-4568903", "Lim testing");
        checkStatusCode(subjectResource, 400);
    }

    @Test
    public void doubleByteMessage() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", "joblisting-4568903", "你好");
        checkStatusCode(subjectResource, 200);
    }

    @Test
    public void doubleByteSubject() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", "你好", "Lim testing");
        checkStatusCode(subjectResource, 200);
    }

    // HELPER
    private void checkStatusCode(SubjectResource subjectResource, int expectedStatusCode) {
        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .body(subjectResource, ObjectMapper.GSON)
                .expect().statusCode(expectedStatusCode)
                .when().post("/subject").print();
    }

    private SubjectResource getSubjectResource(String phone, String reference, String message) {
        ContactResource contactResource = new ContactResource();
        contactResource.setPhone(phone);

        SubjectResource subjectResource = new SubjectResource();
        subjectResource.setReference(reference);
        subjectResource.setContact(contactResource);
        subjectResource.setMessage(message);
        return subjectResource;
    }
}

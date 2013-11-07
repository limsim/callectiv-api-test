package com.callectiv.api;

import com.jayway.restassured.mapper.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.callectiv.api.Helper.autheticateWithJson;
import static com.callectiv.api.Helper.getAuth;
import static com.callectiv.api.Helper.getSubjectResource;
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
        SubjectResource subjectResource = getSubjectResource("447904181648", "item40_cust8945.2012", "Lim testing");

        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .body(subjectResource, ObjectMapper.GSON)
                .expect().statusCode(200)
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo("Lim testing"))
                .body("reference", equalTo("item40_cust8945.2012"))
                .when().post("/subject").print();
    }

    @Test
    public void registerWithLongReferenceWillWork() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", "joblisting-" + UUID.randomUUID(), "Lim testing");
        checkStatusCode(subjectResource, 200);
    }

    @Test
    public void registerWithNoMessageWillReturn400StatusCode() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", "joblisting-4568903", null);
        checkStatusCode(subjectResource, 400);
    }

    @Test
    public void registerWithNoReferenceWillReturn400StatusCode() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", null, "asd lasdkjf3 \n");
        checkStatusCode(subjectResource, 400);
    }

    @Test
    public void registerWithNoPhoneNumberWillReturn400StatusCode() throws Exception {
        SubjectResource subjectResource = getSubjectResource(null, "joblisting-4568903", "asd lasdkjf3 \n");
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

    @Test
    public void referenceThatIs128CharLongIsOK() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", RandomStringUtils.randomAscii(128), "Lim testing");
        checkStatusCode(subjectResource, 200);
    }

    @Test
    public void referenceThatIsLongerThan128CharIsNotOK() throws Exception {
        SubjectResource subjectResource = getSubjectResource("447904181648", RandomStringUtils.randomAscii(129), "Lim testing");
        checkStatusCode(subjectResource, 400);
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

}

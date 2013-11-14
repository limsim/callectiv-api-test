package com.callectiv.api;

import com.callectiv.api.resources.SubjectResource;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static com.callectiv.api.Helper.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 07/11/13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class GetSubjectDetailsTest {
    @Test
    public void retrieveExistingSubject() throws Exception {
        String authToken = autheticateWithJson(getAuth());
        String reference = "joblisting-" + UUID.randomUUID();
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, "Lim testing");

        registerSubject(authToken, subjectResource);

        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .expect().statusCode(200)
                .body("contact.phone", equalTo("447904181648"))
                .body("creationDateTime", notNullValue())
                .body("message", equalTo("Lim testing"))
                .body("reference", equalTo(reference))
                .body("status", equalTo("enabled"))
                .when().get("/subject/" + reference).print();
    }

    @Test
    public void retrievingExistingSubjectTwiceWillReturnSameResponse() throws Exception {
        String authToken = autheticateWithJson(getAuth());
        String reference = "joblisting-" + UUID.randomUUID();
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, "Lim testing");

        registerSubject(authToken, subjectResource);

        for (int counter = 0; counter < 2; counter++) {
            given()
                    .header("Authorization", authToken).contentType("application/json")
                    .header("Accept", "application/json")
                    .expect().statusCode(200)
                    .body("contact.phone", equalTo("447904181648"))
                    .body("creationDateTime", notNullValue())
                    .body("message", equalTo("Lim testing"))
                    .body("reference", equalTo(reference))
                    .body("status", equalTo("enabled"))
                    .when().get("/subject/" + reference).print();
        }
    }

    @Test
    public void getSubjectWithoutReference() throws Exception {
        String authToken = autheticateWithJson(getAuth());
        String reference = "joblisting-" + UUID.randomUUID();
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, "Lim testing");

        List<Object> list = getAllSubjectList(authToken);

        int initialListSize = list.size();

        registerSubject(authToken, subjectResource);

        List<Object> secondList = getAllSubjectList(authToken);

        int secondSize = secondList.size();
        assertEquals(1, secondSize - initialListSize);
    }

    private List<Object> getAllSubjectList(String authToken) {
        return given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .expect().statusCode(200)
                .when().get("/subject/").jsonPath().getList("subject.contact");
    }
}

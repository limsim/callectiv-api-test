package com.callectiv.api;

import com.callectiv.api.resources.SubjectResource;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.UUID;

import static com.callectiv.api.Helper.*;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 12/11/13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class DeleteSubjectTest {
    @Test
    public void deleteExistingSubject() throws Exception {
        String authString = autheticateWithJson(getAuth());
        String reference = RandomStringUtils.randomAlphanumeric(20);
        String message = RandomStringUtils.randomAlphanumeric(140);
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, message);
        registerSubject(authString, subjectResource).print();

        given()
                .header("Authorization", authString)
                .header("Accept", "application/json")
        .expect().statusCode(200)
        .when().delete("/subject/" + reference).print();
    }

    @Test
    public void deleteWithNoReference() throws Exception {
        String authString = autheticateWithJson(getAuth());
        given()
                .header("Authorization", authString)
                .header("Accept", "application/json")
                .expect().statusCode(405)
                .when().delete("/subject").print();
    }

    @Test
    public void deleteWithReferenceThatDoesntExist() throws Exception {
        String authString = autheticateWithJson(getAuth());
        given()
                .header("Authorization", authString)
                .header("Accept", "application/json")
                .expect().statusCode(404)
                .when().delete("/subject/" + "lim" + UUID.randomUUID()).print();

    }
}

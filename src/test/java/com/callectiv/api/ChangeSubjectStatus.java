package com.callectiv.api;

import com.callectiv.api.resources.SubjectResource;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.callectiv.api.Helper.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created with IntelliJ IDEA.
 * User: The Monster
 * Date: 29/11/13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class ChangeSubjectStatus {

    private String authToken;
    private String reference;
    private String message;

    @Before
    public void setUp() throws Exception {
        authToken = autheticateWithJson(getAuth());
        reference = "joblisting-" + UUID.randomUUID();
        message = RandomStringUtils.randomAlphabetic(20);
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, message);

        registerSubject(authToken, subjectResource);

    }

    @Test
    public void changeSubjectStatusFromEnabledToDisabled() throws Exception {
        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
        .expect().statusCode(200)
                .body("reference", equalTo(reference))
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo(message))
                .body("status", equalTo("disabled"))
        .when().put("/subject/" + reference + "/status/disabled");
    }

    @Test
    public void changeSubjectStatusFromEnabledToArchived() throws Exception {
        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .expect().statusCode(200)
                .body("reference", equalTo(reference))
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo(message))
                .body("status", equalTo("archived"))
                .when().put("/subject/" + reference + "/status/archived");
    }

    @Test
    public void changeSubjectBackFromDisabledToEnabled() throws Exception {
        changeSubjectStatusFromEnabledToDisabled();

        given()
                .header("Authorization", authToken).contentType("application/json")
                .header("Accept", "application/json")
                .expect().statusCode(200)
                .body("reference", equalTo(reference))
                .body("contact.phone", equalTo("447904181648"))
                .body("message", equalTo(message))
                .body("status", equalTo("enabled"))
                .when().put("/subject/" + reference + "/status/enabled");
    }

    @Test
    public void usingDisabledSubjectWtillGive409Error() throws Exception {
        changeSubjectStatusFromEnabledToDisabled();

        throw new NotImplementedException();
    }
}

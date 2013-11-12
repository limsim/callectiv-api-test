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
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class GetConnectionsOnSubjectTest {
    @Test
    public void getConnectionRandomString() throws Exception {
        String authString = autheticateWithJson(getAuth());
        String reference = RandomStringUtils.randomAscii(10);
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, RandomStringUtils.randomAscii(50));
        registerSubject(authString, subjectResource).print();

        given()
                .header("Authorization", authString)
                .header("Accept", "application/json")
        .expect().statusCode(200)
        .when().get("/subject/" + reference + "/connections").print();
    }

    @Test
    public void getConnectionForValidSubject() throws Exception {
        String authString = autheticateWithJson(getAuth());
        String reference = RandomStringUtils.randomAlphanumeric(20);
        String message = RandomStringUtils.randomAlphanumeric(140);
        SubjectResource subjectResource = getSubjectResource("447904181648", reference, message);
        registerSubject(authString, subjectResource).print();

        given()
                .header("Authorization", authString)
                .header("Accept", "application/json")
                .expect().statusCode(200)
                .when().get("/subject/" + reference + "/connections").print();
    }

// status code = 400    "{"reference":"m\\\"-J89Di+","contact":{"phone":"447904181648"},"message":"/!:9Z\u0027%N-F$rB@5~F\u0026LVkHv5130\u0026TFymUzVMGIii^qM*9fYxz{"}"

// status code = 200 but null response   "{"reference":"kCc(S(tk9b","contact":{"phone":"447904181648"},"message":"5V\\BU#c}\u003c@o)S}%Nqu|F~F3/kx7n9bns2f)\u0026Ejv? #*J\"A\u003cI57"}"
}

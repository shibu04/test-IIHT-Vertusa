package com.danphe.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post(endPoint)
                .then().log().all()
                .extract().response();
    }

    public static Response performGet(String endPoint, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .get(endPoint)
                .then().log().all()
                .extract().response();
    }
}

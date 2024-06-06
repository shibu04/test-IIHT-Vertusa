package com.danphe.config;

import io.restassured.RestAssured;

public class RestConfig {

    public static final String AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXJyZW50VXNlciI6IntcIlVzZXJJZFwiOjEsXCJFbXBsb3llZUlkXCI6MSxcIlVzZXJOYW1lXCI6XCJhZG1pblwiLFwiUGFzc3dvcmRcIjpcIlwiLFwiRW1haWxcIjpcImFkbWluQG1uay5jb21cIixcIkNyZWF0ZWRCeVwiOjEsXCJDcmVhdGVkT25cIjpcIjIwMTctMDctMTNUMTU6NTE6NTIuNjU3XCIsXCJNb2RpZmllZEJ5XCI6MSxcIk1vZGlmaWVkT25cIjpcIjIwMTktMDgtMjlUMTY6MTY6MDEuODQzXCIsXCJSb2xlc1wiOltdLFwiSXNBY3RpdmVcIjp0cnVlLFwiTmVlZHNQYXNzd29yZFVwZGF0ZVwiOmZhbHNlLFwiRW1wbG95ZWVcIjpudWxsLFwiTGFuZGluZ1BhZ2VSb3V0ZUlkXCI6bnVsbH0iLCJleHAiOjE3MTc3MzMzMzMsImlzcyI6ImxvY2FsaG9zdCIsImF1ZCI6ImxvY2FsaG9zdCJ9.ubFHLRpJSR-vXAHaeGOGbxliMWimWkRD8mJoAKlIn1o";

    public static void init() {
        RestAssured.baseURI = "https://healthapp.yaksha.com";
    }
}

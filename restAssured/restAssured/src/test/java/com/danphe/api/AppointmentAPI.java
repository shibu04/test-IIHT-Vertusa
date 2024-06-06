package com.danphe.api;

import com.danphe.config.RestConfig;
import com.danphe.utils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class AppointmentAPI {

    @BeforeClass
    public void setup() {
        RestConfig.init();
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @Test
    public void createAppointment() {
        // Define the endpoint and request body for creating an appointment
        String endpoint = "/api/Appointment/AddAppointment";
        String requestBody = "{ \n" +
                "  \"FirstName\": \"ramesh\",\n" +
                "  \"MiddleName\": null,\n" +
                "  \"LastName\": \"Galande\",\n" +
                "  \"Gender\": \"Male\",\n" +
                "  \"Age\": \"26\",\n" +
                "  \"ContactNumber\": \"9890434556\",\n" +
                "  \"AppointmentDate\": \"2024-02-15\",\n" +
                "  \"AppointmentTime\": \"23:36:10\",\n" +
                "  \"PerformerName\": \"Dr. ALEX OKELLO ONYIEGO\",\n" +
                "  \"AppointmentType\": \"New\",\n" +
                "  \"PatientType\": \"outpatient\",\n" +
                "  \"ModifiedOn\": \"\",\n" +
                "  \"ModifiedBy\": null,\n" +
                "  \"CreatedOn\": \"2024-06-14T13:36:26\",\n" +
                "  \"CreatedBy\": null,\n" +
                "  \"CancelledOn\": \"\",\n" +
                "  \"IsDobVerified\": false,\n" +
                "  \"CancelledBy\": null,\n" +
                "  \"CancelledRemarks\": \"\",\n" +
                "  \"DepartmentName\": \"\",\n" +
                "  \"DoctorName\": null,\n" +
                "  \"Reason\": \"\"\n" +
                "}";

        // Headers including the Authorization header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", RestConfig.AUTH_TOKEN);

        // Perform POST request to create an appointment
        Response response = RestUtils.performPost(endpoint, requestBody, headers);
        response.then()
                .statusCode(200) // Expected status code
                .body("Results.AppointmentId", notNullValue())
                .body("Results.FirstName", equalTo("ramesh"))
                .body("Results.ContactNumber", equalTo("9890434556"));
    }

    @Test
    public void getApplicableDoctors() {
        // Define the endpoint for getting applicable doctors
        String endpoint = "/api/Visit/AppointmentApplicableDoctors";

        // Headers including the Authorization header
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", RestConfig.AUTH_TOKEN);

        // Perform GET request to get applicable doctors
        Response response = RestUtils.performGet(endpoint, headers);
        response.then()
                .statusCode(200) // Expected status code
                .body("Status", equalTo("OK"))
                .body("Results", not(empty()));
    }
}

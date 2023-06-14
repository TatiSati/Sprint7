package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;


public class BaseAPI {
    public RequestSpecification requestSpecification() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        return RestAssured.given()
        .header("Content-type", "application/json");
    }
}
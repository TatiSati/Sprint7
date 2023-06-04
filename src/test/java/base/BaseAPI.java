package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {
    public RequestSpecification RequestSpecification() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        return RestAssured.given();
    }
}
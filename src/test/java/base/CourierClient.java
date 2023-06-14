package base;

import constants.PathAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Courier;
import static io.restassured.RestAssured.given;


public class CourierClient extends BaseAPI {


    @Step("Метод создание курьера")
    @Description("POST на ручку /api/v1/courier")
    public Response createCourier(Courier courier) {
        return

                given(requestSpecification())
                        .and()
                        .body(courier)
                        .when()
                        .post(PathAPI.COURIER_BASE_URL);// отправляем POST-запрос с помощью метода post
    }

    @Step("Авторизация курьера в системе")
    @Description("POST на ручку /api/v1/courier/login")
    public Response loginCourier(Courier courier) {
        return
                given(requestSpecification())
                        .and()
                        .body(courier)
                        .when()
                        .post(PathAPI.COURIER_LOGIN);
    }
    @Step("Удалить курьера из системы")
    @Description("DELETE на ручку /api/v1/courier/:id")
    public void deleteCourier(Courier courier) {
        try {
            int id = loginCourier(courier).then().extract().path("id");


            given(requestSpecification()) // вызываем метод RequestSpecification(), где хранится Base url
                    .and()
                    .body(courier)
                    .when()
                    .delete(PathAPI.COURIER_BASE_URL + id);

        } catch (NullPointerException e) {
            System.out.println("Нечего удалять после теста");
        }
    }
}
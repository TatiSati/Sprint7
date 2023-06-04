package base;

import constants.PathAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Courier;
import static io.restassured.RestAssured.given;


public class CourierClient extends BaseAPI {

    public CourierClient()  {

    }

    @Step("Метод создание курьера")
    @Description("POST на ручку /api/v1/courier")
    public Response createCourier(Courier courier) {
        return

                given(RequestSpecification())
                        .header("Content-type", "application/json") // Строка указывает, что данные в теле запроса передаются в формате JSON
                        .and()
                        .body(courier)
                        .when()
                        .post(PathAPI.COURIER_BASE_URL);// отправляем POST-запрос с помощью метода post
    }

    @Step("Авторизация курьера в системе")
    @Description("POST на ручку /api/v1/courier/login")
    public Response loginCourier(Courier courier) {
        return
                given(RequestSpecification())
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post(PathAPI.COURIER_LOGIN);
    }

}
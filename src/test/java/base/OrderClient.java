package base;

import constants.PathAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.Order;
import static io.restassured.RestAssured.given;

public class OrderClient extends BaseAPI {

    public OrderClient() {
    }

    @Step("Метод  Создать заказ")
    @Description("POST на ручку api/v1/orders")

    public Response createOrder(Order order) {
        return given(requestSpecification())
                .and()
                .body(order)
                .when()
                .post(PathAPI.ORDER_BASE_URL);
    }

    @Step("Метод  Получить заказы")
    @Description("GET  на ручку api/v1/orders")

    public Response getOrder() {
        return given(requestSpecification()) //
                .get(PathAPI.ORDER_BASE_URL);
    }
}
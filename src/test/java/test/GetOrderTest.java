package test;


import base.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class GetOrderTest {
    OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Проверка позитивного сценария")
    @Description("Ожидаемый результат: возврат списка заказов")

    public void getOrderReturnAllOrders() {

        orderClient.getOrder()
                .then()
                .body("orders", notNullValue());
    }
}
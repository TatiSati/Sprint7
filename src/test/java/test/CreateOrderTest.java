package test;


import base.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.Order;
import pojo.RandomOrders;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final Order order; // создали поля тестового класса

    OrderClient orderClient = new OrderClient();

    public CreateOrderTest(Order order) { // создали конструктор тестового класса
        this.order = order;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getTestDataForColor() {
        return new Object[][]{
                // Самокат серого цвета
                {new RandomOrders(new String[]{"GREY"})},
                // Самокат черного цвета
                {new RandomOrders(new String[]{"BLACK"})},
                // Два цвета самоката
                {new RandomOrders(new String[]{"BLACK", "GREY"})},
                // Не выбран цвет
                {new RandomOrders(null)},
        };
    }

    @Test
    @DisplayName("Проверка кода в случае позитивного сценария")
    @Description("Ожидаемый результат: код 201")

    public void checkCreateValidOrder() {
        orderClient.createOrder(order)
                .then()
                .assertThat()
                .statusCode(201) // проверка статуса ответа
                .and()
                .body("track", notNullValue());
    }
}
package test;

import base.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pojo.Courier;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    String randomLogin = RandomStringUtils.random(10, true, false);
    String randomPassword = RandomStringUtils.random(10, true, true);
    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();

    Courier courier = new Courier(randomLogin, randomPassword, randomName);

    @Test
    @DisplayName("Успешное создание учетной записи")
    @Description("Ожидаемый результат: код 201")

    public void checkStatusForCreateSameCourier() {
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    @DisplayName("Запрос с повторяющимся логином")
    @Description("Ожидаемый результат: код ошибки 409.")

    public void checkStatusForDuplicationCreateCourier() {
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(409);
    }

    @Test
    @DisplayName("Проверка тела ответа")
    @Description("Ожидаемый результат: ok, true")

    public void checkBodyForCreateSameCourier() {
        courierClient.createCourier(courier)
                .then()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Запрос без логина")
    @Description("Ожидаемый результат: код ошибки 400")

    public void checkStatusForCreateCourierWithoutLogin() {
        courier = new Courier("", randomPassword, randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Запрос без пароля")
    @Description("Ожидаемый результат: код ошибки 400")

    public void checkStatusForCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin, "", randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверка запроса без логина")
    @Description("Ожидаемый результат: \"Недостаточно данных для создания учетной записи\"")

    public void checkCreateCourierWithoutLogin() {
        courier = new Courier("",randomPassword, randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Проверка запроса без пароля")
    @Description("Ожидаемый результат:  \"Недостаточно данных для создания учетной записи\"")

    public void checkCreateCourierWithoutPassword() {
        courier = new Courier(randomLogin,"", randomName);
        courierClient.createCourier(courier)
                .then()
                .assertThat().
                body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Проверка запроса с таким же логином")
    @Description("Ожидаемый результат: \"Этот логин уже используется. Попробуйте другой.\"")

    public void checkDuplicationCreateCourier() {
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @After
    public void deleteCourier() {
        courierClient.deleteCourier(courier);
    }
}
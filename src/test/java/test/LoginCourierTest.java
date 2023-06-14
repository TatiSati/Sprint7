package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pojo.Courier;
import base.CourierClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    String randomLogin = RandomStringUtils.random(10, true, false);
    String randomPassword = RandomStringUtils.random(10, true, true);
    String randomName = RandomStringUtils.random(10, true, false);

    CourierClient courierClient = new CourierClient();

    Courier courier = new Courier(randomLogin, randomPassword, randomName);

    @Test
    @DisplayName("Происходит проверка, что корректный запрос возвращает id курьера в системе + код 200")
    @Description("Ожидаемый результат: id курьера в случае успешного запроса")
    public void checkAuthorizationCourier() {
        courierClient.createCourier(courier);
        courierClient.loginCourier(courier)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id",notNullValue());// проверка статуса ответа
    }

    @Test
    @DisplayName("Проверяем обязательность поля Логин для авторизации курьера в системе")
    @Description("Ожидаемый результат: авторизация невозможна без Логина, запрос ведет к коду 400 и сообщению \"Недостаточно данных для входа\"")
    public void checkAuthorizationWithoutLogin() {
        courier = new Courier("", randomPassword, "");
        courierClient.loginCourier(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Проверяем обязательность поля Пароль для авторизации курьера в системе")
    @Description("Ожидаемый результат: авторизация невозможна без Пароля, запрос ведет к коду 400 и сообщению \"Недостаточно данных для входа\"")
    public void checkLoginWithoutPasswordMustBeRejected() {
        courier = new Courier(randomLogin, "", "");
        courierClient.createCourier(courier);
        courierClient.loginCourier(courier)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Проверяем безопасность авторизации: курьер должен быть зарегистрирован в системе для успешного входа")
    @Description("Ожидаемый результат: авторизация невозможна под незарегистрированными данными, запрос ведет к коду 404 и сообщению \"Учетная запись не найдена\"")
    public void checkAuthorizationWithoutExistingLoginAndPassword() {
        courier = new Courier("qwerty", "123123123", "");

        courierClient.loginCourier(courier)
                .then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @After
    public void deleteCourier() {
        courierClient.deleteCourier(courier);
    }

}


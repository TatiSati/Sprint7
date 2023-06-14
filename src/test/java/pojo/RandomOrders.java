package pojo;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrders extends Order {

    // Генераци рандомного имени
    private static final String RANDOM_FIRSTNAME = RandomStringUtils.random(10, true, false);

    // Генерация рандомной фамилии
    private static  final String RANDOM_LASTNAME = RandomStringUtils.random(10, true, false);

    // Генерация рандомного адреса
    private static final String RANDOM_ADDRESS = RandomStringUtils.random(10, true, false);

    // Генерация рандомной станции метро
    private static final int RANDOM_METRO_STATION =  ThreadLocalRandom.current().nextInt(1, 11);

    // Генерация рандомного номера телефона
    private static final String RANDOM_PHONE_NUMBER = RandomStringUtils.random(10, false, true);

    // Генерация рандомного времени аренды
    private static final int RANDOM_RENT_TIME= ThreadLocalRandom.current().nextInt(1, 11);

    // Генерация рандомной даты аренды
    private static final String RANDOM_DELIVERY_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    // Генерация рандомного комментария от заказчика, а так же выбор цвета самоката
    private static final String RANDOM_COMMENT = RandomStringUtils.random(10, true, false);
    private  String[] newColor;


    public RandomOrders(String[] newColour) {
        super(
                RANDOM_FIRSTNAME,
                RANDOM_LASTNAME,
                RANDOM_ADDRESS,
                RANDOM_METRO_STATION,
                RANDOM_PHONE_NUMBER,
                RANDOM_RENT_TIME,
                RANDOM_DELIVERY_DATE,
                RANDOM_COMMENT,
                newColour);
    }
}

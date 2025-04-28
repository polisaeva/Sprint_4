package ru.practicum.tests;

import PageObject.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderScooterFirefoxTest {
    public WebDriver driver;

    private final boolean isOrderUp;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String days;

    public OrderScooterFirefoxTest(boolean isOrderUp, String name, String surname,
                                  String address, String station, String phone,
                                  String days) {
        this.isOrderUp = isOrderUp;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.days = days;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {true, "Иван", "Иванов", "Москва", "Сокольники", "89997776655", "15"},
                {true, "Семён", "Семёнов", "Москва", "Красносельская", "88886665544", "15"},
                {false, "Иван", "Иванов", "Москва", "Сокольники", "89997776655", "15"},
                {false, "Семён", "Семёнов", "Москва", "Красносельская", "88886665544", "15"}
        };
    }

    @Before
    public void startUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void orderScooterTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderButtons objOrderButtons = new OrderButtons(driver);
        if (isOrderUp) {
            objOrderButtons.clickOrderUp();
        } else {
            objOrderButtons.clickOrderDown();
        }

        WhoIsTheScooterFor objwhoIsTheScooterFor = new WhoIsTheScooterFor(driver);
        objwhoIsTheScooterFor.whoIsTheScooterFor(
                name,
                surname,
                address,
                station,
                phone);

        AboutRent objAboutRent = new AboutRent(driver);
        objAboutRent.aboutRental(days);

        OrderConfirmation objOrderConfirmation = new OrderConfirmation(driver);
        objOrderConfirmation.clickYes();

        OrderPlaced objOrderPlaced = new OrderPlaced(driver);
        objOrderPlaced.orderPlacedOnDisplay();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
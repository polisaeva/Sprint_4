package ru.practicum.tests;

import PageObject.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    public WebDriver driver;

    private final boolean isOrderUp;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String days;
    private final String TEST_STAND = "https://qa-scooter.praktikum-services.ru/";

    public OrderScooterTest(boolean isOrderUp, String name, String surname,
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
                {false, "Семён", "Семёнов", "Москва", "Красносельская", "88886665544", "15"},
        };
    }

    //метод, принимающий параметр driver и описывающий весь путь тестового сценария
    private void performOrderTest(WebDriver driver) {
        driver.get(TEST_STAND);

        HomePage objHomePage = new HomePage(driver);

        if (isOrderUp) {
            objHomePage.clickOrderUp();
        } else {
            objHomePage.clickOrderDown();
        }

        OrderPage order = new OrderPage(driver);
        order.whoIsTheScooterFor(
                name,
                surname,
                address,
                station,
                phone);

        order.aboutRental(days);
        order.clickYes();
        order.orderPlacedOnDisplay();
    }

    //тестирование в браузере Chrome через локальную переменную driver
    @Test
    public void orderScooterChromeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        performOrderTest(driver);
    }

    //тестирование в браузере Firefox через локальную переменную driver
    @Test
    public void orderScooterFirefoxTest() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        performOrderTest(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
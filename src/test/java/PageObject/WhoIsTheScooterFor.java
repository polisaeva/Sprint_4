package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoIsTheScooterFor {
    private WebDriver driver;

    //локатор для поля "Имя" формы "Для кого самокат"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для поля "Фамилия" формы "Для кого самокат"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор для поля "Адрес" формы "Для кого самокат"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор для поля "Станция метро" формы "Для кого самокат"
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор для поля "Телефон" формы "Для кого самокат"
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор для кнопки "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");

    public WhoIsTheScooterFor(WebDriver driver) {
        this.driver = driver;
    }

    //метод для заполнения поля "Имя"
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод для заполнения поля "Фамилия"
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //метод для заполнения поля "Адрес"
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //метод для заполнения поля "Станция метро"
    public void setMetroStation(String station) {
        driver.findElement(metroStationField).click();
        WebElement option = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[contains(text(),'" + station + "')]")
                ));
        option.click();
    }

    //метод для заполнения поля "Телефон"
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //метод для клика по кнопке "Далее" формы "Для кого самокат"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //метод для заполнения формы "Для кого самокат?" и клик по кнопке "Далее"
    public void whoIsTheScooterFor(String name, String surname, String address, String station, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(station);
        setPhone(phone);
        clickNextButton();
    }
}

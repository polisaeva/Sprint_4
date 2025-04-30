package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    //локатор для кнопки "Заказать" вверху страницы
    private By orderUp = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");

    // локатор для кнопки "Заказать" внизу страницы
    private By orderDown = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для клика по кнопке "Заказать" вверху страницы
    public void clickOrderUp() {
        driver.findElement(orderUp).click();
    }

    //метод для клика по кнопке "Заказать" внизу страницы
    public void clickOrderDown() {
        //находим кнопку "Заказать" внизу страницы
        WebElement orderDownElement = driver.findElement(orderDown);
        //скроллим до кнопки и кликаем по ней
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderDownElement);
        orderDownElement.click();
    }
}
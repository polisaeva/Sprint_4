package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPlaced {
    private WebDriver driver;
    public OrderPlaced(WebDriver driver) {
        this.driver = driver;
    }

    //локатор для кнопки "Посмотреть статус"
    private By orderPlaced= By.xpath(".//button[text()='Посмотреть статус']");

    //метод, проверяющий наличие кнопки на экране
    public void orderPlacedOnDisplay() {
        driver.findElement(orderPlaced).isDisplayed();
    }

}

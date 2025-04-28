package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmation {
    private WebDriver driver;

    public OrderConfirmation(WebDriver driver) {
        this.driver = driver;
    }

    //локатор для кнопки "Да" окна подтверждения заказа
    private By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //клик по кнопке "Да" окна подтверждения заказа
    public void clickYes() {
        driver.findElement(buttonYes).click();
    }
}

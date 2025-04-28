package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRent {
    private WebDriver driver;

    public AboutRent(WebDriver driver) {
        this.driver = driver;
    }

    //локатор для поля "Когда привести самокат" формы "Про аренду"
    private By whenField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //локатор календаря для поля "Когда привести самокат"
    private By calendarWidget = By.xpath(".//div[@class='react-datepicker']");

    //локатор для поля "Срок аренды" формы "Про аренду"
    private By rentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");

    //локатор для выпадающего списка поля "Срок аренды"
    private By dropdownMenu = By.xpath(".//div[@class='Dropdown-menu']");

    //локатор для кнопки "Заказать" формы "Про аренду"
    private By orderAScooter = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //заполнение поля "Когда привести самокат" формы "Про аренду"
    public void selectDateInCalendar(String day) {
        driver.findElement(whenField).click();
        // ожидание появления календаря
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Клик по кнопке "следующий месяц"
        driver.findElement(By.xpath(".//button[@class='react-datepicker__navigation react-datepicker__navigation--next']")).click();
        // Выбираем день
        By dayLocator = By.xpath(String.format(".//div[@class='react-datepicker__day react-datepicker__day--0" + day + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
    }

    //заполнение поля "Срок аренды"
    public void setRentalPeriod() {
        driver.findElement(rentalPeriod).click();
        // Явное ожидание появления выпадающего списка и выбор опции
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//div[@class='Dropdown-option' and text()='двое суток']")));
        option.click();
    }

    //клик по кнопке "Заказать" формы "Про аренду"
    public void orderAScooterClick() {
        driver.findElement(orderAScooter).click();
    }

    //метод для заполнения формы "Про аренду"
    public void aboutRental(String day) {
        selectDateInCalendar(day);
        setRentalPeriod();
        orderAScooterClick();
    }
}
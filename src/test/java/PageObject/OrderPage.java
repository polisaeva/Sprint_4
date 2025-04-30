package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    //форма "Для кого самокат"
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

    public OrderPage(WebDriver driver) {
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


    //форма "Про аренду"
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


    //окно подтверждения заказа
    //локатор для кнопки "Да" окна подтверждения заказа
    private By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //клик по кнопке "Да" окна подтверждения заказа
    public void clickYes() {
        driver.findElement(buttonYes).click();
    }

    //окно "Заказ оформлен"
    //локатор для кнопки "Посмотреть статус"
    private By orderPlaced= By.xpath(".//button[text()='Посмотреть статус']");

    //метод, проверяющий наличие кнопки на экране
    public void orderPlacedOnDisplay() {
        driver.findElement(orderPlaced).isDisplayed();
    }
}

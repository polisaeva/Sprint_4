package ru.practicum.tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class DownListChromeTest {
    public WebDriver driver;
    private final int itemIndex;
    private final String expectedText;

    public DownListChromeTest(int itemIndex, String expectedText) {
        this.itemIndex = itemIndex;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] answersToQuestions() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void startUpChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void CheckingTheDropDownListTest() {
        //Открываем главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //локатор списка "Вопросы о важном"
        WebElement element = driver.findElement(By.xpath(
                ".//div[@class='Home_FourPart__1uthg']/div[@class='Home_SubHeader__zwi_E']"));

        //Скроллинг до выпадающего списка "Вопросы о важном"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        // Для последнего пункта сначала кликаем на кнопку куки
        if (itemIndex == 7) {
            driver.findElement(By.cssSelector(".App_CookieButton__3cvqF")).click();
        }

        // Клик на стрелку у выбранного пункта
        driver.findElement(By.cssSelector("[aria-controls='accordion__panel-" + itemIndex + "']")).click();

        // Проверка отображения соответствующего текста
        driver.findElement(By.xpath(".//p[text()='" + expectedText + "']")).isDisplayed();
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Cinema {
    public WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\OUT-Akopyan-SR\\driver\\ChromeDriver 72.0.3626.69.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://afisha.mail.ru/");
        driver.manage().window().maximize();


    }

    @Test
    public void Cinema () throws InterruptedException {
        WebElement Cino = driver.findElement(By.xpath("//span[text()='В кино']"));
        Cino.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input-group__item']//div[@class='dropdown__text js-dates__title' and text()='Сегодня']")));


        WebElement Tomorrow = driver.findElement(By.xpath("//div[@class='input-group__item']//div[@class='dropdown__text js-dates__title' and text()='Сегодня']"));
        Tomorrow.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-module='CustomScroll']//input[@data-title='Завтра']/..")));

        WebElement ChooseDay = driver.findElement(By.xpath("//div[@data-module='CustomScroll']//input[@data-title='Завтра']/.."));
        ChooseDay.click();

        WebElement Metro = driver.findElement(By.xpath("//div[@class='input-group__item']//input[@class='input__field js-suggest__input' and @placeholder=\"Станции метро\"]"));
        Metro.click();
        Metro.sendKeys("Курская");


        WebElement ChooseMetro = driver.findElement(By.xpath("//div[@data-id='68']"));
        ChooseMetro.click();

        WebElement ChooseJaners = driver.findElement(By.xpath("//form[@data-action='/ajax/kinoafisha/']//input[@class='input__field padding_right_40 js-select__filter']"));
        ChooseJaners.click();
        List<WebElement> listJaners = driver.findElements(By.xpath("//div[@class='suggest suggest_active dropdown__suggest']//div[@data-optidx]"));

        for (WebElement element : listJaners) {
            String text = element.findElement(By.xpath("./div/span")).getText();
            if(text.equals("драма") || text.equals("комедия")) {
                element.click();

            }

        }
        WebElement Seans = driver.findElement(By.xpath("//div[@class='checkbox checkbox_colored margin_right_20']"));
        Seans.click();

        WebElement Podborka = driver.findElement(By.xpath("//button[@class='button button_color_project']"));
        Podborka.click();

        WebElement headerIcon = driver.findElement(By.xpath("//h1[@class='title__title']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", headerIcon.isDisplayed());

        WebElement Proverka1 = driver.findElement(By.xpath("//div[@class='dropdown__text js-dates__title' and text()='Завтра']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", Proverka1.isDisplayed());

        WebElement Proverka2 = driver.findElement(By.xpath("//span[@class='tag__text' and text()='комедия']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", Proverka2.isDisplayed());

        WebElement Proverka3 = driver.findElement(By.xpath("//div[@class='checkbox checkbox_colored margin_right_20']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", Proverka3.isDisplayed());

    }




    @After
    public void tearDown() {
        driver.quit();
    }
}





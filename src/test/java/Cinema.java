import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\OUT-Akopyan-SR\\driver\\ChromeDriver 72.0.3626.69.exe");
        Log.log.debug("Создание WebDriver");
        driver = new ChromeDriver();
        Log.log.debug("Установка задержки implicitlyWait=10 секундам");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Log.log.info("Переход на страницу https://afisha.mail.ru/");
        driver.get("https://afisha.mail.ru/");
        Log.log.debug("Раскрываем окно браузера");
        driver.manage().window().maximize();
    }


    @Test
    public void Cinema () throws InterruptedException {
        WebElement Cino = findElement(By.xpath("//span[text()='В кино']"));
        Log.log.debug("Клик по элементу 'В кино'");
        Cino.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement Tomorrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input-group__item']//div[@class='dropdown__text js-dates__title' and text()='Сегодня']")));
        Tomorrow.click();

        sleep(1000);
        WebElement ChooseDay = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-module='CustomScroll']//input[@data-title='Завтра']/..")));
        ChooseDay.click();

        WebElement Metro = findElement(By.xpath("//div[@class='input-group__item']//input[@class='input__field js-suggest__input' and @placeholder=\"Станции метро\"]"));
        Metro.click();
        Metro.sendKeys("Курская");


        WebElement ChooseMetro = findElement(By.xpath("//div[@data-id='68']"));
        ChooseMetro.click();

        WebElement ChooseJaners = findElement(By.xpath("//form[@data-action='/ajax/kinoafisha/']//input[@class='input__field padding_right_40 js-select__filter']"));
        ChooseJaners.click();
        sleep(1000);
        List<WebElement> listJaners = driver.findElements(By.xpath("//div[@class='suggest suggest_active dropdown__suggest']//div[@data-optidx]"));

        clickJanr(listJaners, "драма");
        clickJanr(listJaners, "комеди");

        WebElement Seans = findElement(By.xpath("//div[@class='checkbox checkbox_colored margin_right_20']"));
        Seans.click();

        WebElement Podborka = findElement(By.xpath("//button[@class='button button_color_project']"));
        Podborka.click();

//      isDisplayed() - элемент виден на странице
//      isEnabled() - элемент активен
//      isSelected() - выбран ли элемент
        WebElement headerIcon = findElement(By.xpath("//h1[@class='title__title']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", headerIcon.isDisplayed());
        Assert.assertEquals("Киноафиша Москвы", headerIcon.getText());

        WebElement Proverka1 = findElement(By.xpath("//div[@class='dropdown__text js-dates__title' and text()='Завтра']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", Proverka1.isDisplayed());

        WebElement Proverka2 = driver.findElement(By.xpath("//span[@class='tag__text' and text()='комедия']"));
        Assert.assertTrue("Страница не загрузилась https://kino.mail.ru/", Proverka2.isDisplayed());
        Assert.assertEquals("Киноафиша Москвы", headerIcon.getText());

        WebElement Proverka3 = driver.findElement(By.xpath("//div[@class='checkbox checkbox_colored margin_right_20']//input"));
        Assert.assertTrue("Элемент 'Только сеансы в 2D' не выбран", Proverka3.isSelected());

    }




    @After
    public void tearDown() {
        driver.quit();
    }

    public void clickJanr(List<WebElement> list, String jant) {
        for (WebElement element : list) {
            String text = element.findElement(By.xpath("./div/span")).getText();
            if(text.equals(jant) ) {
                element.click();
                break;
            }
        }
    }

    public WebElement findElement(By by) {
        Log.log.debug("Поиск элемента '" + by.toString() + "'");
        return driver.findElement(by);
    }

}





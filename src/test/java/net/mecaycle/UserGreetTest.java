package net.mecaycle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserGreetTest {

    final By.ByClassName nameStringClass = new By.ByClassName("nameString");
    final By.ByClassName greetBtnClass = new By.ByClassName("greetBtn");
    final By.ById theCountId =  new By.ById("theCount");
    final By.ById theGreetId =  new By.ById("theGreet");

    By.ByCssSelector languageRadioButtonsSelector = new By.ByCssSelector("input[type='radio']");

    WebDriver driver;



    public String getCountAfterGreetingUser (String username) {

        WebElement nameElement = driver.findElement(nameStringClass);
        WebElement greetBtn = driver.findElement(greetBtnClass);
        WebElement theCount = driver.findElement(theCountId);
        WebElement theGreet = driver.findElement(theGreetId);

        nameElement.sendKeys(username);
        greetBtn.click();

        String counterText = theCount.getText();

        return counterText;
    }

    public String greetUserInLanguage (String username, String language) {

        WebElement nameElement = driver.findElement(nameStringClass);
        WebElement greetBtn = driver.findElement(greetBtnClass);
        WebElement theCount = driver.findElement(theCountId);
        WebElement theGreet = driver.findElement(theGreetId);
        List<WebElement> languageRadioButtons = driver.findElements(languageRadioButtonsSelector);

        nameElement.sendKeys(username);

        // click the appropriate radio button

        if ("spanish".equals(language)) {
            languageRadioButtons.get(1).click();
        } else if ("japanese".equals(language)) {
            languageRadioButtons.get(2).click();
        } else {
            languageRadioButtons.get(0).click();
        }

        greetBtn.click();

        String greetText = theGreet.getText();

        return greetText;
    }


    @BeforeAll
    public void doThisOnlyOnce() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("https://mecayleg.github.io/greetings/");
    }

    @BeforeEach
    public void doThisBeforeEachTest() {
        System.out.println("yo! About to run another test!");
    }


    @AfterAll
    public void afterAllTheTests() {
        driver.close();
    }


    @Test
    public void shouldIncreaseCounterIfMoreThanOneUserIsGreeted() {

        String theCounter = getCountAfterGreetingUser("Andre");
        assertEquals("1", theCounter);

        theCounter = getCountAfterGreetingUser("Mecayle");
        assertEquals("2", theCounter);

    }

    @Test
    public void shouldBeAbleToGreetUserInEnglish() {

        String theMessage = greetUserInLanguage("Andre", "english");
        assertEquals("Hello, andre", theMessage);

    }

    @Test
    public void shouldBeAbleToGreetUserInSpanish() {

        String theMessage = greetUserInLanguage( "Andre", "spanish");
        assertEquals("Hola, andre", theMessage);

    }

    @Test
    public void shouldBeAbleToGreetUserInJapanese() {

        String theMessage = greetUserInLanguage( "Andre", "japanese");
        assertEquals("Kon'nichiwa, andre", theMessage);

    }

}

package net.mecaycle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//class GreetUser {
//
//}


public class MyFirstSeleniumTask {




    public static void main(String[] args) {

//        WebDriverManager.chromedriver()
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://mecayleg.github.io/greetings/");

        System.out.println(driver.getTitle());

        By.ByClassName nameStringClass = new By.ByClassName("nameString");
        By.ByClassName greetBtnClass = new By.ByClassName("greetBtn");
        By.ById theCountId =  new By.ById("theCount");

        WebElement webElement = driver.findElement(nameStringClass);
        WebElement greetBtn = driver.findElement(greetBtnClass);
        WebElement theCount = driver.findElement(theCountId);

        webElement.sendKeys("Andre");
        greetBtn.click();

        System.out.println(theCount.getText());



    }


}

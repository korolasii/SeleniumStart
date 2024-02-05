package org.seleniumTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        WebElement elementByClassName = driver.findElement(By.className("selenium-button"));
        System.out.println("Element by Class Name: " + elementByClassName.getText());

        System.out.println("/////////////////////////////////////////////////////////////////////");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        elementByClassName.click();


        WebElement elementById = driver.findElement(By.id("Layer_1"));
        System.out.println("Element by ID: " + elementById.getText());

        System.out.println("/////////////////////////////////////////////////////////////////////");

        String pageSource = driver.getPageSource();
        System.out.println("Page Source: " + pageSource);

        System.out.println("/////////////////////////////////////////////////////////////////////");

        Document doc = Jsoup.parse(pageSource);

        Set<String> uniqueClasses = new HashSet<>();

        for (WebElement element : driver.findElements(By.cssSelector("*"))) {
            String elementClasses = element.getAttribute("class");
            if (elementClasses != null && !elementClasses.isEmpty()) {
                String[] classesArray = elementClasses.split("\\s+");
                for (String singleClass : classesArray) {
                    uniqueClasses.add(singleClass);
                }
            }
        }

        System.out.println("AllUnigueClassName: " + uniqueClasses);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.innerHTML = '';");


        String newElementScript = "var newElement = document.createElement('div');"
                + "newElement.innerHTML = 'Я взломал ваш сайт, но к сожалению только для себя!';"
                + "document.body.appendChild(newElement);";
        jsExecutor.executeScript(newElementScript);
    }
}

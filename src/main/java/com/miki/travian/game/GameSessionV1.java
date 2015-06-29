package com.miki.travian.game;

import com.miki.travian.Config;
import com.miki.travian.v2.model.AvailableResources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by Miki on 6/24/2015.
 */
public class GameSessionV1 {

    private static final String BUILD_URL = "http://tx3.travian.com/build.php?id=";
    WebDriver driver;


    public GameSessionV1() {
        System.setProperty("webdriver.chrome.driver", Config.get("web.driver.location"));
        driver = new ChromeDriver();
        driver.get(String.valueOf("http://tx3.travian.com"));
    }

    public void login(String user, String pwd) throws InterruptedException {
        WebElement emailInput = driver.findElement(By.name("name"));
        WebElement pwdInput = driver.findElement(By.name("password"));

        emailInput.sendKeys(user);
        pwdInput.sendKeys(pwd);
        emailInput.submit();
        Thread.sleep(3500L + Config.getRandomDelay());
    }

    public void setVillage(int village) throws InterruptedException {
        Thread.sleep(1000L);
        driver.get("http://tx3.travian.com/dorf1.php?newdid=" + village);
        Thread.sleep(1000L);
    }

    public void upgradeSequence(List<Integer> sequence) throws InterruptedException {
        for (int field : sequence) {
            if (upgradeThis(field)) {
                Thread.sleep(120000L + Config.getRandomDelay());
                break;
            }
        }
    }

    public Boolean recruit(int id, int value) throws InterruptedException {
        driver.get("http://tx3.travian.com/dorf2.php");
        Thread.sleep(1500 + Config.getRandomDelay());
        driver.get(BUILD_URL + id);
        Thread.sleep(Config.getRandomDelay());
        driver.get("http://tx3.travian.com/build.php?s=1&id=" + id);
        Thread.sleep(Config.getRandomDelay());

        AvailableResources resourcesAvailable = new AvailableResources(new AvailableResources.Resource()
                .wood(4600)
                .clay(4200)
                .iron(5800)
                .crop(4400)
                , driver
        );
        if (!resourcesAvailable.isAvailable()) {
            return false;
        }
        WebElement input = driver.findElement(By.name("t10"));
        input.click();
        input.clear();
        input.sendKeys(value + "");
        driver.findElement(By.id("s1")).click();
        Thread.sleep(1500 + Config.getRandomDelay());
        return true;
    }

    public Boolean upgradeThis(int id) throws InterruptedException {
        driver.get("http://tx3.travian.com/dorf1.php");
        Thread.sleep(1500 + Config.getRandomDelay());
        driver.get(BUILD_URL + id);
        Thread.sleep(2500L + Config.getRandomDelay());

        WebElement contractLink = driver.findElement(By.className("contractLink"));

        WebElement button = contractLink.findElement(By.tagName("button"));

        if (button.getAttribute("class").contains("gold")) {
            return false;
        }
        button.click();
        return true;
    }

    public void close() {
        driver.quit();
    }
}

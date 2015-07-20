package com.miki.travian.v2;

import com.miki.travian.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Miki on 6/28/2015.
 */
public class GameSession {

    private WebDriver driver;
    private final String SERVER;

    public GameSession(WebDriver driver, String server) {
        this.driver = driver;
        this.SERVER = server;
        driver.get(SERVER);
    }

    private String getBuildURL(int id) {
        return SERVER + "build.php?id=" + id;
    }

    public void login(String user, String pwd) throws InterruptedException {
        Thread.sleep(Config.getRandomDelay());
        WebElement emailInput = driver.findElement(By.name("name"));
        WebElement pwdInput = driver.findElement(By.name("password"));

        emailInput.sendKeys(user);
        pwdInput.sendKeys(pwd);
        emailInput.submit();
        Thread.sleep(2500L + Config.getRandomDelay());
    }

    public void close() {
        driver.quit();
    }

    public Boolean upgradeInside(int id) throws InterruptedException {
        driver.get("http://tx3.travian.com/dorf2.php");
        Thread.sleep(1500 + Config.getRandomDelay());
        return upgradeThis(id);
    }

    public Boolean upgradeOutside(int id) throws InterruptedException {
        driver.get("http://tx3.travian.com/dorf1.php");
        Thread.sleep(1500 + Config.getRandomDelay());
        try {
            return upgradeThis(id);
        } catch (Exception e) {
            return true;
        }

    }

    private Boolean upgradeThis(int id) throws InterruptedException {
        driver.get(getBuildURL(id));
        Thread.sleep(1500L + Config.getRandomDelay());

        WebElement contractLink = driver.findElement(By.className("contractLink"));
        WebElement button = null;


        button = contractLink.findElement(By.tagName("button"));


        if (button == null || button.getAttribute("class").contains("gold")) {
            return false;
        }
        button.click();
        return true;
    }

    public void setVillage(int village) throws InterruptedException {
        Thread.sleep(500L);
        driver.get(SERVER + "dorf1.php?newdid=" + village);
        Thread.sleep(1000L);
    }


}

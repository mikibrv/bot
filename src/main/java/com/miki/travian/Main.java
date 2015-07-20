package com.miki.travian;

import com.miki.travian.v2.ActionHandler;
import com.miki.travian.v2.GameSession;
import com.miki.travian.v2.config.GameConfig;
import com.miki.travian.v2.model.BuildingQueue;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miki on 6/24/2015.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        buildingQueueList = GameConfig.readQueue();
        while (true) {
            GameSession session = null;
            try {
                session = start();
                new ActionHandler(session, buildingQueueList).handle();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(System.currentTimeMillis());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            System.out.println("going to sleep");
            Thread.sleep(1000000L + Config.random.nextInt(60000));
        }
    }

    public static List<BuildingQueue> buildingQueueList = new ArrayList<BuildingQueue>();

    public static GameSession start() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", Config.get("web.driver.location"));
        GameSession session = new GameSession(new ChromeDriver(), Config.get("travian.server"));
        return session;
    }
}

package com.miki.travian.v2.strategy;

import com.miki.travian.v2.GameSession;
import org.openqa.selenium.WebDriver;

/**
 * Created by Miki on 6/28/2015.
 */
public interface BuildStrategy {

    public void execute(GameSession session, int village) throws InterruptedException;

}
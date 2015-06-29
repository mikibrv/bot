package com.miki.travian.v2.strategy;

import org.openqa.selenium.WebDriver;

/**
 * Created by Miki on 6/28/2015.
 */
public class QueueFinishedStrategy {

    public static enum Strategy {
        BuildResources

    }

    public static BuildStrategy getBuildStrategy(Object strategyStr) {
        if (strategyStr == null || strategyStr.toString().length() == 0) {
            return new BuildNothingStrategy();
        }
        Strategy strategy = Strategy.valueOf(strategyStr.toString());

        switch (strategy) {
            case BuildResources:
                return new BuildRandomResources();
            default:
                return new BuildRandomResources();
        }
    }

}

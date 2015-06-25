package com.miki.travian;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Miki on 6/24/2015.
 */
public class Config {
    private static Properties prop = new Properties();

    public static String get(String key) {
        return String.valueOf(prop.get(key));
    }

    static Random random = new Random();

    public static int getRandomDelay() {
        return random.nextInt(6000);
    }

    static {
        try {
            readConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readConfig() throws Exception {
        InputStream input = new FileInputStream("config.properties");
        // load a properties file
        prop.load(input);
    }
}

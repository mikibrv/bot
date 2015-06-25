package com.miki.travian;

import com.miki.travian.game.GameSession;
import com.miki.travian.game.config.GameConfig;

import java.util.Random;

/**
 * Created by Miki on 6/24/2015.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        while (true) {
            GameSession session = null;
            try {
                session = doMain();
            } catch (Exception e) {

            } finally {
                if (session != null) {
                    session.close();
                }
            }
            System.out.println("going to sleep");
            Thread.sleep(1800000L + Config.random.nextInt(30000));
        }
    }

    public static GameSession doMain() throws InterruptedException {
        GameSession session = new GameSession();
        session.login(Config.get("user"), Config.get("pwd"));
        session.upgradeSequence(GameConfig.clayFields);
        session.upgradeSequence(GameConfig.woodFields);
        session.upgradeSequence(GameConfig.ironFields);
        session.upgradeSequence(GameConfig.cornFields);
        return session;
    }
}

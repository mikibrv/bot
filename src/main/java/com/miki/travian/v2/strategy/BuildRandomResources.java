package com.miki.travian.v2.strategy;

import com.miki.travian.Config;
import com.miki.travian.v2.GameSession;
import com.miki.travian.v2.config.GameConfig;

import java.util.List;

/**
 * Created by Miki on 6/28/2015.
 */
public class BuildRandomResources implements BuildStrategy {
    @Override
    public void execute(GameSession session, int village) throws InterruptedException {
        this.upgradeSequence(GameConfig.clayFields, session, village);

        this.upgradeSequence(GameConfig.woodFields, session, village);
        this.upgradeSequence(GameConfig.ironFields, session, village);

        this.upgradeSequence(GameConfig.cornFields, session, village);
    }

    private void upgradeSequence(List<Integer> sequence, GameSession session, int village) throws InterruptedException {
        for (int field : sequence) {
            session.setVillage(village);
            if (session.upgradeOutside(field)) {
                Thread.sleep(10000L + Config.getRandomDelay());
                break;
            }
        }
    }
}

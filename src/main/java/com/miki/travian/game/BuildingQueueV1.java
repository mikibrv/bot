package com.miki.travian.game;

import com.miki.travian.v2.config.GameConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Miki on 6/26/2015.
 */
public class BuildingQueueV1 {

    private GameSessionV1 session;

    private static final List<Integer> villageList = new ArrayList<Integer>() {{
        add(6702);
        add(14828);
    }};

    private static final Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>() {{

    }};


    public BuildingQueueV1(GameSessionV1 session) {
        this.session = session;
    }


    private void recruit() throws InterruptedException {
        session.recruit(30, 1);
    }

    public void execute() throws InterruptedException {
        this.executeConstruction();
    }

    private void executeConstruction() throws InterruptedException {
        session.setVillage(villageList.get(0));
        Boolean canBeDone = Boolean.TRUE;

        while (queue.size() > 0 && canBeDone) {
            int toBuild = queue.peek();
            canBeDone = session.upgradeThis(toBuild);
            if (canBeDone) {
                queue.poll();
            }
        }
        if (queue.isEmpty()) {
            for (Integer village : villageList) {
                session.setVillage(village);
                executeOthers();
            }
        }
    }


    public void executeOthers() throws InterruptedException {
        session.upgradeSequence(GameConfig.ironFields);
        session.upgradeSequence(GameConfig.woodFields);
        session.upgradeSequence(GameConfig.clayFields);
        // session.upgradeSequence(GameConfig.cornFields);
    }

}

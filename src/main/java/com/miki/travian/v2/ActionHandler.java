package com.miki.travian.v2;

import com.miki.travian.Config;
import com.miki.travian.v2.model.Building;
import com.miki.travian.v2.model.BuildingQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miki on 6/28/2015.
 */
public class ActionHandler {

    GameSession session;
    public List<BuildingQueue> buildingQueueList = new ArrayList<BuildingQueue>();

    public ActionHandler(GameSession session, List<BuildingQueue> buildingQueueList) {
        this.session = session;
        this.buildingQueueList = buildingQueueList;
    }

    public void handle() throws InterruptedException {
        session.login(Config.get("user"), Config.get("pwd"));
        for (BuildingQueue buildingQueue : buildingQueueList) {
            session.setVillage(buildingQueue.village);
            if (buildingQueue.buildingQueue.isEmpty()) {
                buildingQueue.strategy.execute(session, buildingQueue.village);
            } else {
                Building toBuild = buildingQueue.buildingQueue.peek();
                Boolean canBeDone = session.upgradeOutside(toBuild.building);
                if (canBeDone) {
                    buildingQueue.buildingQueue.poll();
                }
            }
        }
    }


}

package com.miki.travian.v2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miki.travian.v2.model.Building;
import com.miki.travian.v2.model.BuildingQueue;
import com.miki.travian.v2.strategy.QueueFinishedStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Miki on 6/24/2015.
 */
public class GameConfig {

    public static List<Integer> woodFields = new ArrayList<Integer>() {{
        add(1);
        add(3);
        add(14);
        add(17);
    }};

    public static List<Integer> clayFields = new ArrayList<Integer>() {{
        add(5);
        add(6);
        add(16);
        add(18);
    }};

    public static List<Integer> ironFields = new ArrayList<Integer>() {{
        add(7);
        add(10);
        add(11);
        add(4);
    }};

    public static List<Integer> cornFields = new ArrayList<Integer>() {{
        add(2);
        add(8);
        add(9);
        add(12);
        add(13);
        add(15);

    }};

    public static List<BuildingQueue> readQueue() {
        List<BuildingQueue> buildingQueueList = new ArrayList<BuildingQueue>();
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

        try {
            Map<String, Map> queueData = mapper.readValue(new File("build.json"), Map.class);
            List<Map> queueMap = (List<Map>) queueData.get("queue");
            for (Map villageMap : queueMap) {
                BuildingQueue buildingQueue = new BuildingQueue();
                int village = (Integer) villageMap.get("village");
                buildingQueue.village = village;
                for (Integer toBuild : ((List<Integer>) (villageMap.get("queue")))) {
                    Building building = new Building();
                    building.village = village;
                    building.building = toBuild;
                    buildingQueue.buildingQueue.add(building);
                }
                buildingQueue.strategy = QueueFinishedStrategy.getBuildStrategy(villageMap.get("queueDone"));
                buildingQueueList.add(buildingQueue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buildingQueueList;
    }


}

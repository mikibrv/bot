package com.miki.travian.v2.model;

import com.miki.travian.v2.strategy.BuildStrategy;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Miki on 6/28/2015.
 */
public class BuildingQueue {

    public int village;

    public BuildStrategy strategy;
    public Queue<Building> buildingQueue = new ConcurrentLinkedQueue<Building>();




}

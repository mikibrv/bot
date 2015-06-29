package com.miki.travian.v2.model;

/**
 * Created by Miki on 6/28/2015.
 */
public class Building {

    public int village;
    public int building;


    public Building village(int village) {
        this.village = village;
        return this;
    }

    public Building building(int building) {
        this.building = building;
        return this;
    }


}

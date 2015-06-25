package com.miki.travian.game.config;

import java.util.ArrayList;
import java.util.List;

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


}

package com.miki.travian.v2.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Miki on 6/27/2015.
 */
public class AvailableResources {

    public static class Resource {

        public int crop;
        public int wood;
        public int iron;
        public int clay;

        public Resource crop(int crop) {
            this.crop = crop;
            return this;
        }

        public Resource clay(int clay) {
            this.clay = clay;
            return this;
        }

        public Resource iron(int iron) {
            this.iron = iron;
            return this;
        }

        public Resource wood(int wood) {
            this.wood = wood;
            return this;
        }

        public Boolean hasEnough(Resource other) {
            return this.clay < other.clay && this.wood < other.wood && this.iron < other.iron && this.crop < other.crop;
        }

    }

    Resource resource;
    WebDriver driver;

    public AvailableResources(Resource resource, WebDriver driver) {
        this.driver = driver;
        this.resource = resource;
    }

    public Boolean isAvailable() {
        Resource other = new Resource().wood(getValue(1)).clay(getValue(2)).iron(getValue(3)).crop(getValue(4));
        return resource.hasEnough(other);
    }

    public int getValue(int id) {
        return Integer.valueOf(driver.findElement(By.id("l" + id)).getText().replace(",", ""));
    }


}

package application.com.entities;

import java.util.Random;

public class Cat {
    private final String name;
    private final int age;
    private int satiety;
    private int happiness;
    private boolean sleeping;

    public Cat(String name) {
        this.name = name;
        this.age = 5;
        this.satiety = 50;
        this.happiness = 50;
        this.sleeping = false;
    }

    public void feed() {
        if (sleeping) {
            return;
        }
        satiety += 15;
        happiness += 5;
        if (satiety > 100) {
            happiness -= 30;
        }
    }

    public void play() {
        if (sleeping) {
            sleeping = false;
            happiness -= 5;
        } else {
            happiness += 15;
            satiety -= 10;

            if (new Random().nextInt(3) == 0) {
                happiness = 0;
            }
        }
    }

    public void sleep() {
        sleeping = true;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSatiety() {
        return Math.max(0, Math.min(satiety, 100));
    }

    public int getHappiness() {
        return Math.max(0, Math.min(happiness, 100));
    }

    public String getState() {
        return sleeping ? "Спит" : "Бодрствует";
    }

    public String getAvatar() {
        if (happiness <= 20) {
            return "/images/sad_cat.jpeg";
        } else if (happiness >= 70) {
            return "/images/happy_cat.jpeg";
        } else {
            return "/images/neutral_cat.jpeg";
        }
    }
}
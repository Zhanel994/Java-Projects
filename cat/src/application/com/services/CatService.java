package application.com.services;

import application.com.entities.Cat;

public class CatService {
    private static Cat cat;

    public static void createCat(String name) {
        cat = new Cat(name);
    }

    public static Cat getCat() {
        return cat;
    }

    public static void showAction(String action) {
        if (cat == null) return;

        switch (action) {
            case "feed":
                cat.feed();
            case "play":
                cat.play();
            case "sleep":
                cat.sleep();
        }
    }
}

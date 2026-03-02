package application.com.handlers.templates.impl;

import application.com.entities.Cat;
import application.com.handlers.templates.FTLHandler;
import application.com.services.CatService;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public class CatHandler extends FTLHandler {
    @Override
    protected Object process(HttpExchange exchange, String... errors) throws Exception {
        Cat cat = CatService.getCat();

        return Map.of(
                "name", cat.getName(),
                "age", cat.getAge(),
                "satiety", cat.getSatiety(),
                "happiness", cat.getHappiness(),
                "state", cat.getState(),
                "avatar", cat.getAvatar()
        );
    }
}

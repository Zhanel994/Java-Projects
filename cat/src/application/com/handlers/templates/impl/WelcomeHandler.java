package application.com.handlers.templates.impl;

import application.com.handlers.templates.FTLHandler;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public class WelcomeHandler extends FTLHandler {
    protected Object process(HttpExchange exchange, String... errors) {
        return Map.of("errors", errors);
    }
}

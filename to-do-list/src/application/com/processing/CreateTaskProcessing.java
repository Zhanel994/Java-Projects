package application.com.handlers.templates.impl;

import application.com.services.TaskService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class CreateTaskHandler implements HttpHandler {
    private final TaskService service = new TaskService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            String body
        }
    }
}

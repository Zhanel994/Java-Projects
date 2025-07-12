package application.com.handlers.templates.impl;

import application.com.handlers.common.RedirectHandler;
import application.com.services.TaskService;
import application.com.utils.IDResolver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class DeleteTaskHandler implements HttpHandler {
    private final TaskService service = new TaskService();

    @Override
    public void handle (HttpExchange exchange) throws IOException
    {
        Integer id = IDResolver.resolve(exchange);
        service.delete(id);
        RedirectHandler.handle(exchange, "/tasks");
    }
}

package application.com.handlers.templates.impl;

import application.com.handlers.templates.FTLHandler;
import application.com.services.TaskService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class TasksHandler extends FTLHandler
{
    private final TaskService service = new TaskService();

    @Override
    protected Object process(HttpExchange exchange, String... errors) throws IOException
    {
        return service.getAll();
    }
}

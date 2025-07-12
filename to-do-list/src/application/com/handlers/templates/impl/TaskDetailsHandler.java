package application.com.handlers.templates.impl;

import application.com.handlers.templates.FTLHandler;
import application.com.services.TaskService;
import application.com.utils.IDResolver;
import com.sun.net.httpserver.HttpExchange;

public class TaskDetailsHandler extends FTLHandler
{
    private final TaskService service = new TaskService();

    @Override
    protected Object process(HttpExchange exchange, String... errors) throws Exception
    {
        Integer id = IDResolver.resolve(exchange);
        return service.get(id);
    }
}

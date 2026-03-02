package application.com.processing;

import application.com.entities.Task;
import application.com.handlers.common.RedirectHandler;
import application.com.services.TaskService;
import application.com.utils.FormBodyParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;


public class CreateTaskProcessing implements HttpHandler {
    private final TaskService service = new TaskService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    RedirectHandler.handle(exchange, "/tasks");
                   return;
                }

        Map<String, String> body = FormBodyParser.parse(exchange);
        String title = body.get("title");
        String executor = body.get("executor");
        String description = body.get("description");

        if (title == null || executor == null || description == null) {
            RedirectHandler.handle(exchange, "/tasks");
            return;
        }

        Task task = new Task(service.getIncrementedId(), title, executor, description);
        service.save(task);
        RedirectHandler.handle(exchange, "/tasks");
    }
}

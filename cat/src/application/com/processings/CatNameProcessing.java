package application.com.processings;

import application.com.handlers.common.RedirectHandler;
import application.com.handlers.templates.impl.CatHandler;
import application.com.services.CatService;
import application.com.utils.FormBodyParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

import static application.com.constants.FTLTemplates.WELCOME_TEMPLATE;

public class CatNameProcessing {
    public void process(HttpExchange exchange) throws IOException {
        try {
            Map<String, String> body = FormBodyParser.parse(exchange);
            String name = body.get("name");

            if (name == null || name.isBlank()) {
                throw new Exception("Имя кота не может быть пустым!");
            }

            CatService.createCat(name);
            RedirectHandler.handle(exchange, "/cat");

        } catch (Exception exception) {
            new CatHandler().handle(exchange, WELCOME_TEMPLATE, exception.getMessage());
        }
    }
}

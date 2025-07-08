package application.com.processings;

import application.com.handlers.common.RedirectHandler;
import application.com.handlers.templates.impl.CatHandler;
import application.com.services.CatService;
import application.com.utils.FormBodyParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

import static application.com.constants.FTLTemplates.CAT_TEMPLATE;
import static application.com.constants.FTLTemplates.WELCOME_TEMPLATE;

public class CatActionProcessing {
    public void process(HttpExchange exchange) throws IOException {
        try {
            Map<String, String> body = FormBodyParser.parse(exchange);
            String action = body.get("action");

            if (action == null || action.isBlank()) {
                throw new Exception("Выберите действие для кота.");
            }

            CatService.showAction(action);
            RedirectHandler.handle(exchange, "/cat");

        } catch (Exception e) {
            new CatHandler().handle(exchange, CAT_TEMPLATE, e.getMessage());
        }
    }
}

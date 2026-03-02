package application.com.handlers;

import application.com.handlers.common.ErrorHandler;
import application.com.handlers.common.FileHandler;
import application.com.handlers.templates.impl.CatHandler;
import application.com.handlers.templates.impl.WelcomeHandler;
import application.com.processings.CatActionProcessing;
import application.com.processings.CatNameProcessing;
import application.com.utils.URIResolver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static application.com.constants.ContentTypes.*;
import static application.com.constants.FTLTemplates.*;
import static application.com.constants.StatusCodes.NOT_FOUND;

public class RouterHandler implements HttpHandler
{
    private final Map<String, HttpHandler> routes = new HashMap<>();

    public RouterHandler()
    {
        routes.put("GET /", it -> new WelcomeHandler().handle(it, WELCOME_TEMPLATE));

        routes.put("POST /cat", it -> new CatNameProcessing().process(it));
        routes.put("GET /cat", it -> new CatHandler().handle(it, CAT_TEMPLATE));

        routes.put("POST /cat/action", it -> new CatActionProcessing().process(it));

        routes.put("GET .css", it -> FileHandler.handle(it, CSS, CSS_DIR));
        routes.put("GET .jpeg", it -> FileHandler.handle(it, JPEG, IMAGES_DIR));
        routes.put("GET .png", it -> FileHandler.handle(it, PNG, IMAGES_DIR));
    }

    public void handle(HttpExchange exchange)
    {
        System.out.println("Info: Server (" + exchange.getLocalAddress() + "), got exchange on " + exchange.getRequestURI() + ":" + exchange.getRequestMethod() + ", from " + exchange.getRemoteAddress());

        try
        {
            String path = URIResolver.resolve(exchange);

            HttpHandler handler = handle(path);
            handler.handle(exchange);
        }
        catch (IOException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private HttpHandler handle(String path)
    {
        for (Map.Entry<String, HttpHandler> route : routes.entrySet())
        {
            if (path.matches(route.getKey()))
            {
                return route.getValue();
            }
        }

        return (it) -> ErrorHandler.handle(it, NOT_FOUND);
    }
}

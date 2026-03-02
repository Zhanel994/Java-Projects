package application.com.handlers.common;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

import static application.com.constants.Headers.LOCATION;
import static application.com.constants.StatusCodes.REDIRECT;

public class RedirectHandler
{
    public static void handle(HttpExchange exchange, String location)
    {
        try
        {
            exchange
                    .getResponseHeaders()
                    .add(LOCATION, location);
            exchange.sendResponseHeaders(REDIRECT, 0);
        }
        catch (IOException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}

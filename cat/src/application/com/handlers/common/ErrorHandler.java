package application.com.handlers.common;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public abstract class ErrorHandler implements HttpHandler
{
    public static void handle(HttpExchange request, Integer status)
    {
        try
        {
            request.sendResponseHeaders(status, 0);
            request.getResponseBody().close();
        }
        catch (IOException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}

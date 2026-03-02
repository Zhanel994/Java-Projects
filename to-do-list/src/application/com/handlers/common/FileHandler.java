package application.com.handlers.common;

import application.com.utils.IO.Sender;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static application.com.constants.StatusCodes.NOT_FOUND;

public class FileHandler
{
    public static void handle(HttpExchange exchange, String contentType, String dir)
    {
        try
        {
            Path path = getPath(exchange, dir);
            if (Files.notExists(path))
            {
                ErrorHandler.handle(exchange, NOT_FOUND);
            }
            else
            {
                byte[] data = Files.readAllBytes(path);
                Sender.sendResponse(exchange, contentType, data);
            }

        }
        catch (IOException exception)
        {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static Path getPath(HttpExchange exchange, String dir)
    {
        String path = exchange.getRequestURI().getPath();

        String[] elements = path.split("/");
        String file = elements[elements.length - 1];

        return Path.of("resources/templates/" + dir + "/" + file);
    }
}

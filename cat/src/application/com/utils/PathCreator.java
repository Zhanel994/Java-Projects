package application.com.utils;

import com.sun.net.httpserver.HttpExchange;

import java.nio.file.Path;

@Deprecated
public class PathCreator
{
    public static Path create(HttpExchange exchange)
    {
        return makeFilePath(exchange.getRequestURI().getPath());
    }

    private static Path makeFilePath(String dir, String... path)
    {
        return Path.of("resources/templates/" + dir, path);
    }
}

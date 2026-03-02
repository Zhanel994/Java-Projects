package application.com.utils;

import com.sun.net.httpserver.HttpExchange;

public class IDResolver
{
    public static Integer resolve(HttpExchange exchange)
    {
        String path = exchange.getRequestURI().getPath();
        String[] elements = path.split("/");

        String id = elements[elements.length - 1];
        return Integer.parseInt(id);
    }
}

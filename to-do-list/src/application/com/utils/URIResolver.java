package application.com.utils;

import com.sun.net.httpserver.HttpExchange;

public class URIResolver
{
    public static String resolve(HttpExchange exchange)
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        int index = path.lastIndexOf(".");
        String route = index != -1 ? path.substring(index).toLowerCase() : path;

        return getKey(method, route);
    }

    private static String getKey(String method, String route)
    {
        return String.format("%s %s", method.toUpperCase(), route);
    }
}

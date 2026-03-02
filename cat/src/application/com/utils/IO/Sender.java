package application.com.utils.IO;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

import static application.com.constants.Headers.CONTENT_TYPE;
import static application.com.constants.StatusCodes.OK;

public class Sender
{
    public static void sendResponse(HttpExchange exchange, String contentType, byte[] data) throws IOException
    {
        exchange.sendResponseHeaders(OK, 0);
        exchange
                .getResponseHeaders()
                .add(CONTENT_TYPE, contentType);

        exchange
                .getResponseBody()
                .write(data);
        exchange.getResponseBody().close();
    }
}

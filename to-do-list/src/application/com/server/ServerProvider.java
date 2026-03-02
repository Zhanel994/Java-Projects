package application.com.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerProvider
{
    protected static HttpServer provide() throws IOException
    {
        InetSocketAddress address = new InetSocketAddress("localhost", 8082);
        return HttpServer.create(address, 50);
    }
}

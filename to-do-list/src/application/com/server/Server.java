package application.com.server;

import application.com.handlers.RouterHandler;
import application.com.utils.IO.FileReader;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

public class Server
{
    private static Server instance = null;

    private final HttpServer server;

    private Server() throws IOException
    {
        this.server = ServerProvider.provide();
        server.createContext("/", it -> new RouterHandler().handle(it));
    }

    public static Server create() throws IOException
    {
        if (instance == null)
        {
            instance = new Server();
        }

        return instance;
    }

    public void start() throws IOException {
        server.start();

        String banner = FileReader.read("resources/banner.txt");
        System.out.println(banner);

        System.out.println("Server successfully started! \n\tServer address: http://" + server.getAddress() + "/\n");
    }
}

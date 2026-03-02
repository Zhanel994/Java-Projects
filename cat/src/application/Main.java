package application;

import application.com.server.Server;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Server server = Server.create();
            server.start();
        }
        catch (IOException exception)
        {
            System.out.println("FATAL Error: " + exception.getMessage());
        }

    }
}
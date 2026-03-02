package application.com.repositories;

import application.com.utils.IO.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

public class JSONRepository
{
    private static final JSONRepository INSTANCE = new JSONRepository();

    private final FileUtils utils = new FileUtils();
    private final Gson gson = new GsonBuilder()
                                                .setPrettyPrinting()
                                                .create();


    private JSONRepository() {}

    public static JSONRepository getInstance()
    {
        return INSTANCE;
    }


    public <T> T get(Type type, String path) throws IOException
    {
        String data = utils.read(path);
        return gson.fromJson(data, type);
    }

    public <T> void save(T data, String path) throws IOException
    {
        String json = gson.toJson(data);
        utils.write(path, json);
    }
}

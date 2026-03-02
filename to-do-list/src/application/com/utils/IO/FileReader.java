package application.com.utils.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader
{
    public static String read(String path) throws IOException
    {
        Path key = Path.of(path);
        byte[] bytes = Files.readAllBytes(key);

        return new String(bytes).intern();
    }
}

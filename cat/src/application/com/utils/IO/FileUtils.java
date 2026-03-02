package application.com.utils.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    public String read(String path) throws IOException {
        Path key = Path.of(path);
        return Files.readString(key);
    }

    public void write(String path, String data) throws IOException {
        Path key = Path.of(path);
        Files.write(key, data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}

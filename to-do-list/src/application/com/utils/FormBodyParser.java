package application.com.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FormBodyParser
{
    public static Map<String, String> parse(HttpExchange exchange) throws IOException
    {
        InputStream stream = exchange.getRequestBody();
        byte[] bytes = stream.readAllBytes();

        String body = new String(bytes);
        return parseBody(body, "&");
    }

    public static Map<String, String> parseBody(String body, String delimiter)
    {
        String[] pairs = body.split(delimiter);

        return Arrays.stream(pairs)
                                    .map(FormBodyParser::decode)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Optional<Map.Entry<String, String>> decode(String data)
    {
        if (!data.contains("="))
        {
            return Optional.empty();
        }

        String[] pair = data.split("=");
        if (pair.length != 2)
        {
            return Optional.empty();
        }

        Charset charset = StandardCharsets.UTF_8;

        String key = URLDecoder.decode(pair[0], charset);
        String value = URLDecoder.decode(pair[1], charset);

        return Optional.of(Map.entry(key, value));
    }
}

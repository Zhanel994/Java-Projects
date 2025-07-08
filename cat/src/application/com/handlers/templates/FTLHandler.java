package application.com.handlers.templates;

import application.com.configurations.FreemarkerConfiguration;
import application.com.handlers.common.ErrorHandler;
import application.com.utils.IO.Sender;
import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static application.com.constants.ContentTypes.HTML;
import static application.com.constants.StatusCodes.BAD_REQUEST;
import static application.com.constants.StatusCodes.SERVER_ERROR;

public abstract class FTLHandler
{
    public void handle(HttpExchange exchange, String templateName, String... errors)
    {
        try
        {
            Configuration configuration = FreemarkerConfiguration.init();

            Template template = configuration.getTemplate(templateName);

            Object model = process(exchange, errors);
            byte[] data = write(template, model, exchange.getRemoteAddress().getHostString());

            Sender.sendResponse(exchange, HTML, data);
        }
        catch (TemplateException | IOException exception)
        {
            System.out.println("Error: " + exception.getMessage());
            ErrorHandler.handle(exchange, SERVER_ERROR);
        }
        catch (Exception exception)
        {
            System.out.println("Error: " + exception.getMessage());
            ErrorHandler.handle(exchange, BAD_REQUEST);
        }
    }

    private byte[] write(Template template, Object data, String host) throws TemplateException, IOException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(stream);

        template.process(Map.of("data", data, "session", SessionService.getByHost(host)), writer);
        writer.flush();

        return stream.toByteArray();
    }

    protected abstract Object process(HttpExchange exchange, String... errors) throws Exception;
}

package application.com.configurations;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.File;

public class FreemarkerConfiguration
{
    public static Configuration init()
    {
        try
        {
            Version version = Configuration.VERSION_2_3_29;
            Configuration configuration = new Configuration(version);

            File templates = new File("resources/templates");
            configuration.setDirectoryForTemplateLoading(templates);

            configuration.setDefaultEncoding("UTF-8");

            TemplateExceptionHandler handler = TemplateExceptionHandler.RETHROW_HANDLER;
            configuration.setTemplateExceptionHandler(handler);

            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            configuration.setFallbackOnNullLoopVariable(false);

            return configuration;
        }
        catch (Exception exception)
        {
            throw new RuntimeException("Freemarker configuration is not valid!");
        }
    }
}

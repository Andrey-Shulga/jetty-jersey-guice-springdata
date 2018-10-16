package utility;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import config.AppConfig;
import config.DataBaseConfig;
import execution.guice.GuiceModule;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.guice.module.SpringModule;

import java.io.IOException;
import java.util.Properties;

public class Globals {

    private static Injector guiceInjector = null;

    public static Injector getGuiceInjector() {

        if (guiceInjector == null) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, DataBaseConfig.class);
            guiceInjector = Guice.createInjector(Stage.DEVELOPMENT, new SpringModule(context), new GuiceModule());
        }
        return guiceInjector;
    }

    public static Properties initializeConfig() {

        Properties prop = new Properties();

        try {
            prop.load(Globals.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}

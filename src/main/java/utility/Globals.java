package utility;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import execution.guice.GuiceModule;
import repo.ProductRepository;

import java.io.IOException;
import java.util.Properties;

public class Globals {

    private static Injector guiceInjector = null;
    private static final String TEST_PERSISTENCE_UNIT_NAME = "test";
    private static final String REPOSITORIES_BASE_PACKAGE_NAME = ProductRepository.class.getPackage().getName();

    public static Injector getGuiceInjector() {

        if (guiceInjector == null)
            guiceInjector = Guice.createInjector(Stage.DEVELOPMENT, new GuiceModule(TEST_PERSISTENCE_UNIT_NAME, REPOSITORIES_BASE_PACKAGE_NAME));

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

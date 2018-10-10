package execution.guice;

import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import utility.Globals;

public class GuiceModule extends AbstractModule {

    private String persistenceUnitName;
    private String repositoriesBasePackageName;

    public GuiceModule(String persistenceUnitName, String repositoriesBasePackageName) {
        this.persistenceUnitName = persistenceUnitName;
        this.repositoriesBasePackageName = repositoriesBasePackageName;
    }


    @Override
    protected void configure() {
        Names.bindProperties(binder(), Globals.initializeConfig());
        install(new ScanningJpaRepositoryModule(repositoriesBasePackageName, persistenceUnitName));
    }
}

package execution.guice;

import com.google.code.guice.repository.configuration.RepositoriesGroup;
import com.google.code.guice.repository.configuration.RepositoriesGroupBuilder;
import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import utility.Globals;

import java.util.Arrays;

public class GuiceModule extends AbstractModule {

    private String persistenceUnitName;
    private String repositoriesBasePackageName;

    private final String PAGING_AND_SORTING_REPOSITORY = "org.springframework.data.repository.PagingAndSortingRepository";
    private final String JPA_REPOSITORY = "org.springframework.data.jpa.repository.JpaRepository";
    private final String CRUD_REPOSITORY = "org.springframework.data.repository.CrudRepository";
    private final String BATCH_STORE_REPOSITORY = "com.google.code.guice.repository.BatchStoreJpaRepository";

    public GuiceModule(String persistenceUnitName, String repositoriesBasePackageName) {
        this.persistenceUnitName = persistenceUnitName;
        this.repositoriesBasePackageName = repositoriesBasePackageName;
    }


    @Override
    protected void configure() {

        Names.bindProperties(binder(), Globals.initializeConfig());

        RepositoriesGroupBuilder repositoriesGroupBuilder = RepositoriesGroupBuilder.forPackage(repositoriesBasePackageName);
        RepositoriesGroup group = buildRepoGroup(repositoriesGroupBuilder, persistenceUnitName);


        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class, DataBaseConfig.class);
        context.refresh();*/
        install(new ScanningJpaRepositoryModule(Arrays.asList(group)));


    }

    private RepositoriesGroup buildRepoGroup(RepositoriesGroupBuilder builder, String persistenceUnitName){

        String exclusionRepositoryPattern = assemblePattern();
        return builder.attachedTo(persistenceUnitName)
                //workaround for avoiding bug in guice-repository library in case of usage application in JAR package format
                .withExclusionPattern(exclusionRepositoryPattern)
                .build();
    }

    private String assemblePattern() {

        StringBuilder pattern = new StringBuilder();
        pattern.append("^(");
        pattern.append(CRUD_REPOSITORY).append("|");
        pattern.append(JPA_REPOSITORY).append("|");
        pattern.append(PAGING_AND_SORTING_REPOSITORY).append("|");
        pattern.append(BATCH_STORE_REPOSITORY);
        pattern.append(").*$");
        return pattern.toString();
    }
}

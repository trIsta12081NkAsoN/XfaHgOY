// 代码生成时间: 2025-09-18 01:56:30
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import liquibase.integration.spring.SpringBootIntegration;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

@Component
@ConditionalOnProperty(name = "db.migration.enabled", havingValue = "true")
public class DatabaseMigrationComponent {

    private final String changeLog = "classpath:db/changelog/db.changelog-master.xml"; // Changelog file location

    @Autowired
    private SpringBootIntegration springBootIntegration;

    /**
     * Migrates the database to the latest version.
     * 
     * @throws LiquibaseException if the migration fails.
     */
    public void migrate() throws LiquibaseException {
        try {
            ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();
            Liquibase liquibase = new Liquibase(changeLog, resourceAccessor, springBootIntegration.getDataSource());
            liquibase.update("latest");
        } catch (LiquibaseException e) {
            throw new LiquibaseException("Database migration failed", e);
        }
    }

    // Add any additional methods necessary for database migration logic
}

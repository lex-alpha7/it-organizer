package ru.akhitev.organizer.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.akhitev.organizer.entity.Project;
import ru.akhitev.organizer.repository.ProjectRepository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/** Database config. */
@Slf4j
@FieldDefaults(makeFinal=true, level = AccessLevel.PRIVATE)
@Configuration
@EnableJpaRepositories("ru.akhitev.organizer.repository")
@EnableTransactionManagement
@ComponentScan(basePackageClasses={DBSpringTestConfig.class, ProjectRepository.class, Project.class})
public class DBSpringTestConfig {
    static String DB_NAME = "classpath:organizer_db";
    static String DB_DDL = "classpath:db/init/db_ddl.sql";
    static String INIT_DATA_SQL = "classpath:db/main.sql";
    static String DIALECT = "org.hibernate.dialect.HSQLDialect";
    static String SHOW_SQL = "false";
    static String FORMAT_SQL = "false";

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .setName(DB_NAME).addScript(DB_DDL).addScript(INIT_DATA_SQL);
        return builder.build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(Project.class.getPackage().getName());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("db.hibernate.dialect", DIALECT);
        properties.put("db.hibernate.show_sql", SHOW_SQL);
        properties.put("db.hibernate.format_sql", FORMAT_SQL);
        return properties;
    }

}
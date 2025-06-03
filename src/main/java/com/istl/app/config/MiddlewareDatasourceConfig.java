package com.istl.app.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.istl.app.repository.middleware",
    entityManagerFactoryRef = "mweEntityManager",
    transactionManagerRef = "mweTransactionManager"
)
@EnableTransactionManagement
public class MiddlewareDatasourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "mweEntityManager")
    public LocalContainerEntityManagerFactoryBean mweEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mweDataSource());
        em.setPackagesToScan("com.istl.app.domain.middleware");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "mweDataSource")
    public DataSource mweDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("spring.datasource.mwedb.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mwedb.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mwedb.password"));
        return dataSource;
    }

    @Bean(name = "mweTransactionManager")
    public PlatformTransactionManager mweTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mweEntityManager().getObject());
        return transactionManager;
    }
}

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
    basePackages = "com.istl.app.repository.mobileapp",
    entityManagerFactoryRef = "mbaEntityManager",
    transactionManagerRef = "mbaTransactionManager"
)
@EnableTransactionManagement
public class MobileAppDatasourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "mbaEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean mbaEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mbaDataSource());
        em.setPackagesToScan(new String[] { "com.istl.app.domain.mobileapp" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name = "mbaDataSource")
    public DataSource mbaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("spring.datasource.mbadb.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mbadb.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mbadb.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "mbaTransactionManager")
    public PlatformTransactionManager mbaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mbaEntityManager().getObject());
        return transactionManager;
    }
}

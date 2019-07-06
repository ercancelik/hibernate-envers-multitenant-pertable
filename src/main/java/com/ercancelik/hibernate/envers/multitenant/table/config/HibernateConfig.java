/*

package com.ercancelik.hibernate.envers.multitenant.table.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class HibernateConfig {

    @Autowired
    private HibernateTenantInterceptor hibernateTenantInterceptor;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory, DataSource dataSource, JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.putAll(jpaProperties.getProperties());
        //properties.put("hibernate.ejb.interceptor", hibernateTenantInterceptor);
        return factory.dataSource(dataSource).packages("com.ercancelik.hibernate.envers.multitenant.table").properties(properties).build();
    }
}*/

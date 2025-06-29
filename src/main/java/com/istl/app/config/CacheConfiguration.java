package com.istl.app.config;

import com.istl.app.domain.middleware.CBSTransactions;
import com.istl.app.domain.middleware.SPSIncomingTransactions;
import com.istl.app.domain.middleware.SPSOutgoingTransactions;
import com.istl.app.domain.middleware.SPSParticipatingCodes;
import com.istl.app.domain.mobileapp.*;
import com.istl.app.repository.mobileapp.UserRepository;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, User.class.getName());
            createCache(cm, Authority.class.getName());
            createCache(cm, User.class.getName() + ".authorities");
            createCache(cm, CBSTransactions.class.getName());
            createCache(cm, SPSIncomingTransactions.class.getName());
            createCache(cm, SPSOutgoingTransactions.class.getName());
            createCache(cm, SPSParticipatingCodes.class.getName());
            createCache(cm, Billers.class.getName());
            createCache(cm, Branches.class.getName());
            createCache(cm, Channels.class.getName());
            createCache(cm, Charge.class.getName());
            createCache(cm, ChargeRange.class.getName());
            createCache(cm, ChargeRange.class.getName() + ".chargeids");
            createCache(cm, Customer.class.getName());
            createCache(cm, CustomerAccount.class.getName());
            createCache(cm, Limits.class.getName());
            createCache(cm, MessagesSms.class.getName());
            createCache(cm, MessageTemplate.class.getName());
            createCache(cm, PinResetHistory.class.getName());
            createCache(cm, Range.class.getName());
            createCache(cm, Range.class.getName() + ".chargeids");
            createCache(cm, ServiceManagement.class.getName());
            createCache(cm, Transactions.class.getName());
            createCache(cm, com.istl.app.domain.mobileapp.MobileAppTransactions.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}

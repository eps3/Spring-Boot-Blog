package cn.sheep3.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by sheep3 on 16-9-17.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {


    /*
    * 据shared与否的设置,
    * Spring分别通过CacheManager.create()
    * 或new CacheManager()方式来创建一个ehcache基地.
    *
    * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
    *
    */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("conf/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    /*
    * ehcache 主要的管理器
    * @param bean
    * @return
    */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(){
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }




}
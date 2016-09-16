package cn.sheep3.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by sheep3 on 16-9-16.
 */
@Configuration
@EnableJpaAuditing
/*@EnableJpaRepositories("cn.sheep3.repository")
@EntityScan("cn.sheep3.entity")
@EnableTransactionManagement*/
public class JpaConfig {

}

package cn.sheep3.config;

import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sheep3 on 16-9-18.
 */
@Slf4j
@Component
public class InItAdmin implements InitializingBean {

    @Autowired
    private UserService userSrv;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepo.count() == 0){
            log.error("------------初始化用户-------------");
            userSrv.addUser("admin","admin");
        }
    }
}

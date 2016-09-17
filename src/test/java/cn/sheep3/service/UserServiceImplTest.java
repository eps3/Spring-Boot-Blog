package cn.sheep3.service;

import cn.sheep3.BlogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sheep3 on 16-9-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userSrv;

    @Test
    public void findByUserLogin() throws Exception {
    }

    @Test
    public void addUser() throws Exception {
        userSrv.addUser("xuxin","123456");
        System.out.println(userSrv.findByUserLogin("xuxin"));
    }

}
package cn.sheep3.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by sheep3 on 16-9-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userSrv;

    @Test
    public void findByUserLogin() throws Exception {
    }

    @Test
    public void addUser() throws Exception {
        userSrv.addUser("xuxin", "123456");
        System.out.println(userSrv.findByUserLogin("xuxin"));
    }

}
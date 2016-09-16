package cn.sheep3.repository;

import cn.sheep3.BlogApplication;
import cn.sheep3.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sheep3 on 16-9-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){
        User user = new User();
        user.setUserPass("8008208820");
        user.setUserLogin("admin");
        user.setUserSalt("salt");
        userRepository.save(user);
        User xuxin = userRepository.findByUserLogin("admin");
        System.out.println(xuxin);
    }

}
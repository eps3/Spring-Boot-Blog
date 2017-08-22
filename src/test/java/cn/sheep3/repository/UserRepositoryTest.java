package cn.sheep3.repository;

import cn.sheep3.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sheep3 on 16-9-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
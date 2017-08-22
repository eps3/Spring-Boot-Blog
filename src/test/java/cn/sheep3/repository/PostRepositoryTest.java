package cn.sheep3.repository;

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
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepo;

    @Test
    public void testFindByPostTitle(){
        System.out.println(postRepo.findByPostTitle("Title"));
    }

    @Test
    public void testDelete(){
        postRepo.delete(postRepo.findOne(1L));
        System.out.println(postRepo.findOne(1L));
    }
}
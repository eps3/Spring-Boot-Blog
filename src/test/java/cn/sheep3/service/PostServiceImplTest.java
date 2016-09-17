package cn.sheep3.service;

import cn.sheep3.BlogApplication;
import cn.sheep3.entity.Post;
import cn.sheep3.model.status.PostStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sheep3 on 16-9-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
public class PostServiceImplTest {

    @Autowired
    private PostService postSrv;


    @Test
    public void editPost() throws Exception {
        Long id = 52L;
        String title = "题目吊";

        String html = "<h1 id=\"h1-love-u\"><a name=\"Love U\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Big Diao</h1><blockquote>\n" +
                "<p>我是羊三，食我大diao啦！</p>\n" +
                "</blockquote>\n";
        String markdown = "# Big diao\n" +
                "\n" +
                "> 我是羊三，食我大diao啦！";

        List<String> stringList = Arrays.asList("可怕","厉害","牛逼");
        Post post = postSrv.editPost(id,title, markdown, html, stringList);
        System.out.println(post);
    }

    @Test
    public void pushPost() throws Exception {
        String title = "题目";

        String html = "<h1 id=\"h1-love-u\"><a name=\"Love U\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Big Diao</h1><blockquote>\n" +
                "<p>我是羊三，食我大diao啦！</p>\n" +
                "</blockquote>\n";
        String markdown = "# Big diao\n" +
                "\n" +
                "> 我是羊三，食我大diao啦！";

        List<String> stringList = Arrays.asList("可怕","厉害","傻孩子");
        Post post = postSrv.pushPost(title, markdown, html, stringList, PostStatus.PUBLISHED);
        System.out.println(post);
        System.out.println(post.getTags());
    }

    @Test
    public void findByPostTitle() throws Exception {
        Post post = postSrv.findByPostTitle("题目吊");
        System.out.println(post);
    }

    @Test
    public void findPostByIndexAndSize() throws Exception {
        Page<Post> postPage = postSrv.findPostByIndexAndSize(0, 5);
        System.out.println(postPage.getContent());
    }

}
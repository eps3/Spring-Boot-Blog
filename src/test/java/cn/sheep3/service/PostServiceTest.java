package cn.sheep3.service;

import cn.sheep3.BlogApplication;
import cn.sheep3.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sheep3 on 16-9-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
public class PostServiceTest {

    @Autowired
    private PostService postSrv;

    @Test
    public void pushPost() throws Exception {
        for (int i =0 ; i < 50 ; i++){
            String title = "Title"+i;

            String html = "<h1 id=\"h1-love-u\"><a name=\"Love U\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Love U</h1><blockquote>\n" +
                    "<p>我是徐鑫，我只想好好的</p>\n" + i+
                    "</blockquote>\n";
            String markdown = "# Love U\n" +
                    "\n" +
                    "> 我是徐鑫，我只想好好的" + i;
            postSrv.pushPost(title,markdown,html);

        }
    }

    @Test
    public void findByPostTitle() throws Exception {

    }

    @Test
    public void findPostByIndexAndSize() throws Exception {
        Page<Post> page = postSrv.findPostByIndexAndSize(0, 5);
        System.out.println(page.getContent());
    }

}
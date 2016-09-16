package cn.sheep3.service;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import org.springframework.data.domain.Page;

/**
 * Created by sheep3 on 16-9-16.
 */
public interface PostService {
    Post pushPost(String title,String markdown,String html) throws Exception;

    Post findByPostTitle(String postTitle) throws PostInputException;

    Page<Post> findPostByIndexAndSize(int page, int size);
}

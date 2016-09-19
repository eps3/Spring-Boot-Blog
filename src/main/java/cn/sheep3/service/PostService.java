package cn.sheep3.service;

import cn.sheep3.entity.Post;
import cn.sheep3.model.status.PostStatus;
import cn.sheep3.exception.PostInputException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by sheep3 on 16-9-16.
 */
public interface PostService {
    Post pushPost(String title, String markdown, String html, List<String> tagStringList,PostStatus postStatus) throws Exception;

    Post editPost(Long id, String title, String markdown, String html, List<String> tagStringList) throws Exception;

    Post findByPostTitle(String postTitle) throws PostInputException;

    Page<Post> findPostByIndexAndSize(int page, int size);

    Page<Post> getHotPost();

    void removePage(String postTitle) throws PostInputException;

    List<Post> findAll();
}

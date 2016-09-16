package cn.sheep3.service;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by sheep3 on 16-9-16.
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserService userSrv;

    @Override
    public Post pushPost(String title, String markdown, String html) throws Exception {
        if (StringUtils.isBlank(title)){
            throw new PostInputException("标题不能为空!");
        } else if (findByPostTitle(title) != null){
            throw new PostInputException("标题不能重复!");
        }

        Post post = new Post();
        post.setUser(userSrv.findByUserLogin("admin"));
        post.setPostTitle(title);
        post.setPostContent(markdown);
        post.setPostShow(html);

        return postRepo.save(post);
    }

    @Override
    public Post findByPostTitle(String postTitle) throws PostInputException {
        if (StringUtils.isBlank(postTitle)){
            throw new PostInputException("标题不能为空!");
        }

        return postRepo.findByPostTitle(postTitle);
    }

    @Override
    public Page<Post> findPostByIndexAndSize(int page, int size) {
        if (size < 1 || page < 0 ){
            log.error("错误的分页数据:page="+page+",size="+size);
            return null;
        }


        //Sort sort = new Sort(Sort.Direction.DESC);
        Pageable pageable = new PageRequest(page, size);

        return postRepo.findAll(pageable);
    }
}

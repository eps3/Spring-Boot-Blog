package cn.sheep3.service;

import cn.sheep3.entity.Post;
import cn.sheep3.model.status.PostStatus;
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

import java.util.List;

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

    @Autowired
    private TagService tagSrv;


    @Override
    public Post editPost(Long id, String title, String markdown, String html, List<String> tagStringList) throws Exception {
        if ( id <0){
            throw new PostInputException("不存在的文章!");
        } else if (StringUtils.isBlank(title)){
            throw new PostInputException("标题不可为空!");
        }
        Post tmpPost = postRepo.findByPostTitle(title);
        if (tmpPost != null  && tmpPost.getId() != id){
            throw new PostInputException("标题不能重复!");
        }

        Post post = postRepo.findOne(id);
        post.setUser(userSrv.findByUserLogin("admin"));
        post.setPostTitle(title);
        post.setPostContent(markdown);
        post.setPostShow(html);
        post.setTags(tagSrv.strListToTagList(tagStringList));

        return postRepo.save(post);
    }

    @Override
    public Post pushPost(String title, String markdown, String html, List<String> tagStringList,PostStatus postStatus) throws Exception {
        if (StringUtils.isBlank(title)){
            throw new PostInputException("标题不能为空!");
        } else if (findByPostTitle(title) != null){
            throw new PostInputException("标题不能重复!");
        }

        Post post = new Post();
        post.setUser(userSrv.findByUserLogin("admin"));
        post.setPostStatus(postStatus);
        post.setPostTitle(title);
        post.setPostContent(markdown);
        post.setPostShow(html);
        post.setTags(tagSrv.strListToTagList(tagStringList));

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

        Sort sort = new Sort(Sort.Direction.DESC,"createdDate");
        Pageable pageable = new PageRequest(page, size,sort);

        return postRepo.findAll(pageable);
    }

    @Override
    public Page<Post> getHotPost() {
        Sort sort = new Sort(Sort.Direction.DESC,"lastModifiedDate");
        Pageable pageable = new PageRequest(0, 10,sort);
        return postRepo.findAll(pageable);
    }
}

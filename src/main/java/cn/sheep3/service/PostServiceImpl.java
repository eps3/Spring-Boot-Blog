package cn.sheep3.service;

import cn.sheep3.entity.Post;
import cn.sheep3.model.status.PostStatus;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.sheep3.config.Constant.PAGE_CACHE_NAME;
import static cn.sheep3.config.Constant.POST_CACHE_NAME;

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


    @CachePut(value = POST_CACHE_NAME, key = "'post_cache_key_'+#title")
    @CacheEvict(value = PAGE_CACHE_NAME, allEntries = true)
    @Override
    public Post editPost(Long id, String title, String markdown, String html, List<String> tagStringList) throws Exception {
        if (id < 0) {
            throw new PostInputException("不存在的文章!");
        } else if (StringUtils.isBlank(title)) {
            throw new PostInputException("标题不可为空!");
        }
        Post tmpPost = postRepo.findByPostTitle(title);
        if (tmpPost != null && tmpPost.getId() != id) {
            throw new PostInputException("标题不能重复!");
        }

        Post post = postRepo.findOne(id);
        post.setUser(userSrv.getUser());
        post.setPostTitle(title);
        post.setPostContent(markdown);
        post.setPostShow(html);
        post.setTags(tagSrv.strListToTagList(tagStringList));

        return postRepo.save(post);
    }

    @CachePut(value = POST_CACHE_NAME, key = "'post_cache_key_'+#title")
    @CacheEvict(value = PAGE_CACHE_NAME, allEntries = true)
    @Override
    public Post pushPost(String title, String markdown, String html, List<String> tagStringList, PostStatus postStatus) throws Exception {
        if (StringUtils.isBlank(title)) {
            throw new PostInputException("标题不能为空!");
        } else if (findByPostTitle(title) != null) {
            throw new PostInputException("标题不能重复!");
        }

        Post post = new Post();
        post.setUser(userSrv.getUser());
        post.setPostStatus(postStatus);
        post.setPostTitle(title);
        post.setPostContent(markdown);
        post.setPostShow(html);
        post.setTags(tagSrv.strListToTagList(tagStringList));

        return postRepo.save(post);
    }

    @Cacheable(value = POST_CACHE_NAME, key = "'post_cache_key_'+#postTitle")
    @Override
    public Post findByPostTitle(String postTitle) throws PostInputException {
        if (StringUtils.isBlank(postTitle)) {
            throw new PostInputException("标题不能为空!");
        }

        return postRepo.findByPostTitle(postTitle);
    }

    @Cacheable(value = PAGE_CACHE_NAME, key = "'page_cache_key_'+#page+'_'+#size")
    @Override
    public Page<Post> findPostByIndexAndSize(int page, int size) {
        if (size < 1 || page < 0) {
            log.error("错误的分页数据:page=" + page + ",size=" + size);
            return null;
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createdDate");
        Pageable pageable = new PageRequest(page, size, sort);

        return postRepo.findAll(pageable);
    }

    @Cacheable(value = PAGE_CACHE_NAME, key = "'hot_page_cache_key_'")
    @Override
    public Page<Post> getHotPost() {
        Sort sort = new Sort(Sort.Direction.DESC, "lastModifiedDate");
        Pageable pageable = new PageRequest(0, 10, sort);
        return postRepo.findAll(pageable);
    }

    @Caching(evict = {
            @CacheEvict(value = POST_CACHE_NAME, key = "'post_cache_key_'+#postTitle"),
            @CacheEvict(value = PAGE_CACHE_NAME, allEntries = true)}
    )
    @Override
    public void removePage(String postTitle) throws PostInputException {
        Post post = findByPostTitle(postTitle);
        if (post == null) {
            log.info("不存在的文章!");
            return;
        }
        post.setTags(null);
        postRepo.save(post);
        postRepo.delete(post.getId());
    }

    @Cacheable(value = PAGE_CACHE_NAME, key = "'all_page_cache_key_'")
    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }
}

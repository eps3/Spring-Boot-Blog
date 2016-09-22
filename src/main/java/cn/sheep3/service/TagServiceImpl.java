package cn.sheep3.service;

import cn.sheep3.entity.Tag;
import cn.sheep3.repository.TagRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.sheep3.config.Constant.TAG_CACHE_NAME;

/**
 * Created by sheep3 on 16-9-16.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepo;

    @Override
    public List<Tag> strListToTagList(List<String> stringList) {
        if (CollectionUtils.isEmpty(stringList)){
            return null;
        }
        List<Tag> tagList = new ArrayList<>();
        for (String name: stringList){
            if (StringUtils.isBlank(name)){
                continue;
            }
            Tag tag = findByName(name);
            if (tag == null){
                tag = new Tag(name);
                save(tag);
            }

            tagList.add(tag);
        }

        return tagList;
    }

    @Cacheable(value = TAG_CACHE_NAME,key = "'list_tag_cache_key_'")
    @Override
    public List<Tag> findAll() {
        return tagRepo.findAll();
    }

    @Cacheable(value = TAG_CACHE_NAME,key = "'tag_cache_key_'+#name")
    @Override
    public Tag findByName(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }

        return tagRepo.findByName(name);
    }

    @CachePut(value = TAG_CACHE_NAME,key = "'tag_cache_key_'+#tag.name")
    @CacheEvict(value = TAG_CACHE_NAME,key = "'list_tag_cache_key_'")
    public Tag save(Tag tag){
        tagRepo.save(tag);
        return tag;
    }
}

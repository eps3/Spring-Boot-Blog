package cn.sheep3.service;

import cn.sheep3.entity.Tag;
import cn.sheep3.repository.TagRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                tagRepo.save(tag);
            }

            tagList.add(tag);
        }

        return tagList;
    }



    @Override
    public List<Tag> findAll() {
        return tagRepo.findAll();
    }

    @Override
    public Tag findByName(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }

        return tagRepo.findByName(name);
    }
}

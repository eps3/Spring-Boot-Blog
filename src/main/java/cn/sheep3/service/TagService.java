package cn.sheep3.service;

import cn.sheep3.entity.Tag;

import java.util.List;

/**
 * Created by sheep3 on 16-9-16.
 */
public interface TagService {

    List<Tag> strListToTagList(List<String> stringList);


    List<Tag> findAll();

    Tag findByName(String name);


}

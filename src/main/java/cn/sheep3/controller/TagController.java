package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.entity.Tag;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sheep3 on 16-9-23.
 */
@Slf4j
@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagSrv;

    @RequestMapping(path = {"", "/"}, method = {RequestMethod.GET})
    public String list(Model model) {
        model.addAttribute("tags", tagSrv.findAll());
        return "/tag/list";
    }

    @RequestMapping(path = "/{name}", method = {RequestMethod.GET})
    public String getPost(Model model, @PathVariable("name") String name) {
        Tag tag = tagSrv.findByName(name);
        model.addAttribute("tag", tag);
        return "/tag/detail";
    }
}

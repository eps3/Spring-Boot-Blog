package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.exception.UserException;
import cn.sheep3.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by sheep3 on 16-9-15.
 */
@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postSrv;

    @RequestMapping(path = "/push",method = {RequestMethod.POST})
    public String pushPost(
            Model model,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){

        return "/admin/edit";
    }
    @RequestMapping(path = "/save",method = {RequestMethod.POST})
    public String savePost(
            Model model,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){

        return "/admin/edit";
    }
    @RequestMapping(path = "/update",method = {RequestMethod.POST})
    public String updatePost(
            Model model,
            @RequestParam("postId") int postId,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){
        
        return "/admin/edit";
    }

    @RequestMapping(path = "/{title}",method = {RequestMethod.GET})
    public String getPost(Model model, @PathVariable("title") String title){
        try {
            Post post = postSrv.findByPostTitle(title);
            model.addAttribute("post",post);
        } catch (PostInputException e) {
            e.printStackTrace();
        }
        return "/page";
    }
}

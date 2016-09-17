package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sheep3 on 16-9-14.
 */
@Controller
public class UserController {

    @Autowired
    private PostService postSrv;

    @RequestMapping("/admin")
    public String index() {
        return "/admin/list/0";
    }

    @RequestMapping("/admin/list/{index}")
    public String postList(
            Model model,
            @PathVariable("index") int index
    ){
        Page<Post> page = postSrv.findPostByIndexAndSize(index, 10);
        model.addAttribute("page",page);
        return "/admin/list";
    }

    @RequestMapping("/admin/edit")
    public String editPost() {
        return "/admin/edit";
    }

    @RequestMapping("/admin/edit/{title}")
    public String editPost(Model model,@PathVariable("title") String title) {
        try {
            Post post = postSrv.findByPostTitle(title);
            model.addAttribute("post",post);
        } catch (PostInputException e) {
            e.printStackTrace();
        }
        return "/admin/edit";
    }


    @RequestMapping(path = "/login",method = {RequestMethod.GET})
    public String login() {
        return "/admin/login";
    }

    @RequestMapping(path = "/login",method = {RequestMethod.POST})
    public String signIn(){
        return "/admin/list";
    }

}

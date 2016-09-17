package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.entity.User;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.exception.UserException;
import cn.sheep3.service.PostService;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sheep3 on 16-9-14.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private PostService postSrv;

    @Autowired
    private UserService userSrv;

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


    @RequestMapping(path = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model,
            HttpServletRequest request

    ) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        /*CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("_csrf",csrfToken);
        }*/
        return "/admin/login";
    }

    //TODO:需要去除GET方法
    @RequestMapping(path = "/admin/pass" ,method = {RequestMethod.GET,RequestMethod.POST})
    public String updateUserPass(@RequestParam() String password){
        try {
            User user = userSrv.updateUserPass(password, userSrv.getUser().getUserLogin());
            if (user == null){
                log.error("奇怪的用户密码修改!");
            }
        } catch (UserException e) {
            log.error(e.getMessage());
        }
        return "redirect:/logout";
    }

    @RequestMapping(path = "/admin/me" ,method = {RequestMethod.GET})
    @ResponseBody
    public User getUser(){
        User user= null;
        try {
            user = userSrv.getUser();
        } catch (UserException e) {
            log.error(e.getMessage());
        }
        return user;
    }
}

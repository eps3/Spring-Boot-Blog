package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sheep3 on 16-9-15.
 */
@Controller
public class HomeController {

    @Autowired
    private PostService postSrv;

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
    public String index(Model model){
        return "/page/0";
    }

    @RequestMapping(path = {"/page/{index}"},method = {RequestMethod.GET})
    public String page(
            Model model,
            @PathVariable("index") int index){
        if (index == 0){
            model.addAttribute("isIndex",true);
        }
        model.addAttribute("hotPage", postSrv.getHotPost());
        model.addAttribute("page", postSrv.findPostByIndexAndSize(index,2));
        return "/index";

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
        return "/admin/login";
    }

    @RequestMapping(path = {"/403","/404","/error"})
    public String forbidden(){
        return "/error/403";
    }


}

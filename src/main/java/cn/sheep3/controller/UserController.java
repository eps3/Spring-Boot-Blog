package cn.sheep3.controller;

import cn.sheep3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sheep3 on 16-9-14.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/admin")
    public String getByEmail() {
        return "/admin/index";
    }

    @RequestMapping("/admin/edit")
    public String editPost() {
        return "/admin/edit";
    }



    @RequestMapping(path = "/login",method = {RequestMethod.GET})
    public String login() {
        return "/admin/login";
    }

    @RequestMapping(path = "/login",method = {RequestMethod.POST})
    public String signIn(){
        return "/admin/index";
    }

}

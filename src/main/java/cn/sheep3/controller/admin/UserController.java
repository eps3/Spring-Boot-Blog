package cn.sheep3.controller.admin;

import cn.sheep3.entity.User;
import cn.sheep3.exception.UserException;
import cn.sheep3.service.PostService;
import cn.sheep3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sheep3 on 16-9-14.
 */
@Slf4j
@Controller("admin_user_controller")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private PostService postSrv;

    @Autowired
    private UserService userSrv;

    @RequestMapping(path = {"", "/","/post/list"})
    public String index() {
        return "/admin/post/list/0";
    }

    @RequestMapping(path = "/me", method = {RequestMethod.GET})
    @ResponseBody
    public User getUser() {
        User user = null;
        try {
            user = userSrv.getUser();
        } catch (UserException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    @RequestMapping(path = "/person/setting", method = {RequestMethod.GET})
    public String setUser(Model model) {
        try {
            model.addAttribute("user", userSrv.getUser());
        } catch (UserException e) {
            log.error(e.getMessage());
        }
        return "/admin/person-setting";
    }

    @RequestMapping(path = "/person/setting", method = {RequestMethod.POST})
    public String setUser(@RequestParam("userPass") String userPass,
                          @RequestParam("userLogin") String userLogin,
                          @RequestParam("userNiceName") String userNiceName) {
        try {
            User user = userSrv.updateUser(userPass, userLogin, userNiceName);
        } catch (UserException e) {
            log.error(e.getMessage());
        }

        return "redirect:/logout";
    }

}

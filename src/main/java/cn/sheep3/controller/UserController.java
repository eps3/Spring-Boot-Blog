package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.entity.User;
import cn.sheep3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sheep3 on 16-9-14.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/get-by-name")
    @ResponseBody
    public String getByEmail(@RequestParam("name") String name) {
        String userId;
        User user = userRepository.findByUserLogin(name);
        if (user != null) {
            userId = String.valueOf(user.getId());
            return "The user id is: " + userId;
        }
        return "user " + name + " is not exist.";
    }
}

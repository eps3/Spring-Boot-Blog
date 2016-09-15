package cn.sheep3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sheep3 on 16-9-15.
 */
@Controller
public class HomeController {

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
    public String index(){
        return "/index";
    }

}

package cn.sheep3.controller;

import cn.sheep3.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sheep3 on 16-9-15.
 */
@Controller
public class HomeController {

    @Autowired
    private PostService postSrv;

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
    public String index(Model model){
        model.addAttribute("page", postSrv.findPostByIndexAndSize(0,5));
        return "/index";
    }

    @RequestMapping(path = {"/page/{index}"},method = {RequestMethod.GET})
    public String page(
            Model model,
            @PathVariable("index") int index){

        model.addAttribute("page", postSrv.findPostByIndexAndSize(index,5));
        return "/index";

    }



}

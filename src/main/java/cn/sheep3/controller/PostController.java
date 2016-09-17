package cn.sheep3.controller;

import cn.sheep3.entity.Post;
import cn.sheep3.model.Message;
import cn.sheep3.model.status.CodeStatus;
import cn.sheep3.model.status.PostStatus;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sheep3 on 16-9-15.
 */
@Slf4j
@Controller
@RequestMapping("/admin/post")
public class PostController {

    @Autowired
    private PostService postSrv;

    /**
     * 发布新的文章
     */
    @RequestMapping(path = "/push",method = {RequestMethod.POST})
    @ResponseBody
    public Message pushPost(
            Model model,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){
        Message msg = new Message();
        try {
            Post post = postSrv.pushPost(titleString, markDownString, htmlString, tags, PostStatus.PUBLISHED);
            msg.setCodeStatus(CodeStatus.STATUS_OK).addItem("title",post.getPostTitle());
        } catch (PostInputException e){
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg(e.getMessage());
        } catch (Exception e) {
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg("系统错误!");
        }
        return msg;
    }

    /**
     * 保存文章
     */
    @RequestMapping(path = "/save",method = {RequestMethod.POST})
    @ResponseBody
    public Message savePost(
            Model model,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){
        Message msg = new Message();
        try {
            Post post = postSrv.pushPost(titleString, markDownString, htmlString, tags, PostStatus.DRAFT);
            msg.setCodeStatus(CodeStatus.STATUS_OK).addItem("title",post.getPostTitle());
        } catch (PostInputException e){
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg(e.getMessage());
        } catch (Exception e) {
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg("系统错误!");
        }
        return msg;
    }

    /**
     * 修改文章
     */
    @RequestMapping(path = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public Message updatePost(
            Model model,
            @RequestParam("postId") Long postId,
            @RequestParam("titleString") String titleString,
            @RequestParam("markDownString") String markDownString,
            @RequestParam("htmlString") String htmlString,
            @RequestParam("tags") List<String> tags
    ){
        Message msg = new Message();
        try {
            Post post = postSrv.editPost(postId,titleString, markDownString, htmlString, tags);
            msg.setCodeStatus(CodeStatus.STATUS_OK).addItem("title",post.getPostTitle());
        } catch (PostInputException e){
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg(e.getMessage());
        } catch (Exception e) {
            msg.setCodeStatus(CodeStatus.STATUS_ERROR).setMsg("系统错误!");
        }
        return msg;
    }

    @RequestMapping(path = "/remove/{postId}",method = {RequestMethod.GET})
    public String removePost(Model model, @PathVariable("postId") Long postId){
        postSrv.removePage(postId);
        return "redirect:/admin";
    }



}
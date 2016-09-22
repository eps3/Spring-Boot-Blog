package cn.sheep3.controller.admin;

import cn.sheep3.entity.Post;
import cn.sheep3.exception.PostInputException;
import cn.sheep3.model.Message;
import cn.sheep3.model.status.CodeStatus;
import cn.sheep3.model.status.PostStatus;
import cn.sheep3.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sheep3 on 16-9-22.
 */
@Slf4j
@Controller
@RequestMapping("/admin/post")
public class PostController {

    @Autowired
    private PostService postSrv;

    @RequestMapping("/list/{index}")
    public String postList(
            Model model,
            @PathVariable("index") int index
    ) {
        Page<Post> page = postSrv.findPostByIndexAndSize(index, 10);
        model.addAttribute("page", page);
        return "/list";
    }

    @RequestMapping("/new")
    public String editPost() {
        return "/admin/edit";
    }

    @RequestMapping("/edit/{title}")
    public String editPost(Model model, @PathVariable("title") String title) {
        try {
            Post post = postSrv.findByPostTitle(title);
            model.addAttribute("post", post);
        } catch (PostInputException e) {
            e.printStackTrace();
        }
        return "/admin/edit";
    }

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

    @RequestMapping(path = "/remove/{postTitle}",method = {RequestMethod.GET})
    public String removePost(Model model, @PathVariable("postTitle") String postTitle){
        try {
            postSrv.removePage(postTitle);
        } catch (PostInputException e) {
            log.error(e.getMessage());
        }
        return "redirect:/admin";
    }
}

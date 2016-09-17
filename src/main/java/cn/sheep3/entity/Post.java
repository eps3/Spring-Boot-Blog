package cn.sheep3.entity;

import cn.sheep3.model.status.PostLock;
import cn.sheep3.model.status.PostStatus;
import cn.sheep3.model.status.PostType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sheep3 on 16-9-14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_posts")
public class Post extends BaseEntity{

    /**
     * 文章作者
     */
    @ManyToOne(optional=false)
    @JoinColumn(name="c_user_id")//外键字段名
    @JsonIgnore
    private User user;

    /**
     * 文章的tag列表
     */
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "t_posts_tags",
            joinColumns = {@JoinColumn(name = "c_post_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "c_tag_id", nullable = false, updatable = false)}
    )
    private List<Tag> tags;

    /**
     * 文章标题
     */
    @Column(name = "c_post_title", nullable = false)
    private String postTitle;

    /**
     * 文章内容
     */
    @Column(name = "c_post_content")
    @Lob
    private String postContent;

    /**
     * 文章实际显示内容
     */
    @Column(name = "c_post_show")
    @Lob
    private String postShow;

    /**
     * 文章摘要
     */
    @Column(name = "c_post_excerpt")
    @Lob
    private String postExcerpt;

    /**
     * 文章密码
     */
    @Column(name = "c_post_password")
    private String postPassword;

    /**
     * 文章类型
     *  Page: 页面
     *  Post: 一篇博客
     */
    @Column(name = "c_post_type")
    @Enumerated(EnumType.STRING)
    private PostType postType = PostType.POST;

    /**
     * 文章状态
     * Draft: 草稿
     * Published: 发布
     */
    @Column(name = "c_post_status")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus = PostStatus.PUBLISHED;

    /**
     * 文章锁
     * Lock: 表示锁了需要解密
     * UN_Lock: 表示没锁
     */
    @Column(name = "c_post_lock")
    @Enumerated
    private PostLock postLock = PostLock.UN_LOCK;

    @Override
    public String toString() {
        return "Post{" +
                "tags=" + tags +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postShow='" + postShow + '\'' +
                ", postExcerpt='" + postExcerpt + '\'' +
                ", postPassword='" + postPassword + '\'' +
                ", postType=" + postType +
                ", postStatus=" + postStatus +
                ", postLock=" + postLock +
                '}';
    }
}

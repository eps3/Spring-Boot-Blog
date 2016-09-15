package cn.sheep3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheep3 on 16-9-14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_tags")
public class Tag extends BaseEntity{
    /**
     * tag name
     */
    @Column(name = "c_name",nullable = false)
    private String name;

    @ManyToMany(mappedBy="tags")
    private List<Post> posts = new ArrayList<>();
}

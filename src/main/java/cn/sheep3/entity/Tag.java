package cn.sheep3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}

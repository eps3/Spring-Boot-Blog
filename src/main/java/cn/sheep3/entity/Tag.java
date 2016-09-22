package cn.sheep3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheep3 on 16-9-14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "t_tags")
public class Tag extends BaseEntity{

    public Tag(String name){
        this.name = name;
    }


    /**
     * tag name
     */
    @Column(name = "c_name",nullable = false)
    private String name;

    @ManyToMany(mappedBy="tags",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}

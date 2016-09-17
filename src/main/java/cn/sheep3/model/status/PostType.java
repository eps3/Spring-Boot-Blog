package cn.sheep3.model.status;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheep3 on 16-9-15.
 */
public enum PostType{
    PAGE("Page"),
    POST("Post");

    @Getter@Setter
    private String type;

    PostType(String type) {
        this.type = type;
    }

}

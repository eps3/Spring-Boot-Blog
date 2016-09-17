package cn.sheep3.model.status;

/**
 * Created by sheep3 on 16-9-15.
 */
public enum PostStatus {
    DRAFT("Draft"),
    PUBLISHED("Published");

    private String status;

    PostStatus(String status) {
        this.status = status;
    }
}

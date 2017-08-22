package cn.sheep3.model.status;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheep3 on 16-9-15.
 */
public enum PostLock {
    LOCK(true),
    UN_LOCK(false);

    @Getter
    @Setter
    private boolean isLock;

    PostLock(boolean isLock) {
        this.isLock = isLock;
    }

}

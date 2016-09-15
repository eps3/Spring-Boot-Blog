package cn.sheep3.entity.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheep3 on 16-9-15.
 */
public enum PostLock {
    LOCK(true),
    UN_LOCK(false);

    @Getter@Setter
    private boolean isLock;

    PostLock(boolean isLock) {
        this.isLock = isLock;
    }

    public boolean isLock(){
        return this.isLock;
    }

}

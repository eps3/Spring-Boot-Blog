package cn.sheep3.model.status;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheep3 on 16-9-17.
 */
public enum  CodeStatus {
    STATUS_OK(0),STATUS_ERROR(1);

    @Getter@Setter
    private int value;

    CodeStatus(int value) {
        this.value = value;
    }
}

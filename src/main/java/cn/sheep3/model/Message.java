package cn.sheep3.model;

import cn.sheep3.model.status.CodeStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by sheep3 on 16-9-17.
 */
public class Message {
    @Getter
    private String msg;
    @Getter
    private CodeStatus codeStatus;
    @Getter
    private Map<String,Object> items;

    public Message setCodeStatus(CodeStatus codeStatus) {
        this.codeStatus = codeStatus;
        return this;
    }

    public Message setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public Message addItem(String key,Object value){
        if (items == null){
            items = new HashedMap();
        }

        items.put(key,value);
        return this;
    }

    public Message addAll(Map map){
        if (items == null){
            items = new HashedMap();
        }
        this.items.putAll(map);
        return this;
    }
}

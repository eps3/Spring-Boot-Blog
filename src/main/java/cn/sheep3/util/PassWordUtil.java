package cn.sheep3.util;

import cn.sheep3.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by sheep3 on 16-9-17.
 */
@Slf4j
public class PassWordUtil {
    public static String getMD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("生成MD5失败", e);
            return null;
        }
    }

    public static String getSalt(){
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public static User fuckUser(User user){
        user.setUserSalt(getSalt());
        user.setUserPass(getMD5(user.getUserPass()+user.getUserSalt()));
        return user;
    }
}

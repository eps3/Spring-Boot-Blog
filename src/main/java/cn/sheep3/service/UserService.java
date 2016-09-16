package cn.sheep3.service;

import cn.sheep3.entity.User;
import cn.sheep3.exception.UserException;

/**
 * Created by sheep3 on 16-9-16.
 */
public interface UserService {
    User findByUserLogin(String userLogin) throws UserException;
}

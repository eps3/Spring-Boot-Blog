package cn.sheep3.service;

import cn.sheep3.entity.User;
import cn.sheep3.exception.UserException;
import cn.sheep3.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sheep3 on 16-9-16.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public User findByUserLogin(String userLogin) throws UserException {
        if (StringUtils.isBlank(userLogin)){
            throw new UserException("不合法的用户名");
        }

        return userRepo.findByUserLogin(userLogin);
    }
}

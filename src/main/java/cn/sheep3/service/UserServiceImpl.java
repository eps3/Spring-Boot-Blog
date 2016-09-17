package cn.sheep3.service;

import cn.sheep3.entity.User;
import cn.sheep3.exception.UserException;
import cn.sheep3.repository.UserRepository;
import cn.sheep3.util.PassWordUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static cn.sheep3.config.Constant.USER_CACHE_NAME;

/**
 * Created by sheep3 on 16-9-16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Cacheable(value = USER_CACHE_NAME, key = "'user_cache_key'+#userLogin")
    @Override
    public User findByUserLogin(String userLogin) throws UserException {
        if (StringUtils.isBlank(userLogin)) {
            throw new UserException("不合法的用户名!");
        }
        return userRepo.findByUserLogin(userLogin);
    }

    @CachePut(value = USER_CACHE_NAME, key = "'user_cache_key'+#userLogin")
    @Override
    public User addUser(String userLogin, String userPass) throws UserException {
        if (StringUtils.isBlank(userLogin)) {
            throw new UserException("不合法的用户名!");
        } else if (StringUtils.isBlank(userPass)) {
            throw new UserException("不合法的密码!");
        }

        if (findByUserLogin(userLogin) != null) {
            throw new UserException("用户名已存在!");
        }

        User user = new User();
        user.setUserLogin(userLogin);
        user.setUserPass(userPass);
        user.setUserNiceName(userLogin);
        //TODO:此处待完善
        /**
         * 密码加盐并取MD5
         */
        PassWordUtil.fuckUser(user);
        return userRepo.save(user);
    }

    @Override
    public User getUser() throws UserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return null;
        }

        return (User) auth.getPrincipal();
    }

    @CachePut(value = USER_CACHE_NAME, key = "'user_cache_key'+#userLogin")
    @Override
    public User updateUserPass(String userPass, String userLogin) throws UserException {
        User user = findByUserLogin(userLogin);
        if (user == null){
            throw new UserException("不存在的用户");
        }
        user.setUserPass(userPass);
        PassWordUtil.fuckUser(user);
        User save = userRepo.save(user);
        return save;
    }



}

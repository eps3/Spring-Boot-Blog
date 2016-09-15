package cn.sheep3.repository;

import cn.sheep3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sheep3 on 16-9-14.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserLogin(String userLogin);
}

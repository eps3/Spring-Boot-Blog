package cn.sheep3.repository;

import cn.sheep3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sheep3 on 16-9-15.
 */
public interface PostRepository extends JpaRepository<Post,Long> {

}

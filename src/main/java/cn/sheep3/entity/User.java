package cn.sheep3.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sheep3 on 16-9-14.
 */
@Data
@Entity
@Table(name = "t_users")
@NoArgsConstructor
public class User extends BaseEntity{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    /**
     * user login name
     */
    @Column(name = "c_user_login", nullable = false)
    private String userLogin;

    /**
     * user password
     * this password= md5 (real_password + salt)
     */
    @Column(name = "c_user_pass", nullable = false)
    private String userPass;

    /**
     * user password salt
     */
    @Column(name = "c_user_salt", nullable = false)
    private String userSalt;

    /**
     * user nice name
     */
    @Column(name = "c_user_nice_name")
    private String userNiceName;

    /**
     * user email
     */
    @Column(name = "c_user_email")
    private String userEmail;

    @Column(name = "c_user_url")
    private String userUrl;
    @Column(name = "c_user_status")
    private int status;
}

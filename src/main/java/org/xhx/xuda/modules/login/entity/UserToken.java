package org.xhx.xuda.modules.login.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_token")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    //token
    private String token;

    //过期时间
    @Column(name = "expire_time")
    private Date expireTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
}

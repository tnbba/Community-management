package org.xhx.xuda.modules.login.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhx.xuda.common.bean.R;
import org.xhx.xuda.modules.login.dao.UserTokenRepository;
import org.xhx.xuda.modules.login.entity.UserToken;
import org.xhx.xuda.modules.login.oauth2.TokenGenerator;
import org.xhx.xuda.modules.login.service.UserTokenService;

import java.util.Date;


@Service("sysUserTokenService")
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private UserTokenRepository userTokenRepository;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public UserToken queryByUserId(Integer userId) {
        return userTokenRepository.findByUserId(userId);
    }

    @Override
    public UserToken queryByToken(String token) {
        return userTokenRepository.findByToken(token);
    }

    @Override
    public void save(UserToken token) {
        userTokenRepository.save(token);
    }

    @Override
    public void update(UserToken token) {
        userTokenRepository.save(token);
    }

    @Override
    public R createToken(Integer userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserToken tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new UserToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }
}

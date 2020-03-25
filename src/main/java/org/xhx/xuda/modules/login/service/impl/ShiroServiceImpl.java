package org.xhx.xuda.modules.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhx.xuda.modules.login.dao.UserRepository;
import org.xhx.xuda.modules.login.dao.UserTokenRepository;
import org.xhx.xuda.modules.login.entity.User;
import org.xhx.xuda.modules.login.entity.UserToken;
import org.xhx.xuda.modules.login.service.ShiroService;

import java.util.Optional;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;
    @Override
    public UserToken queryByToken(String token) {

        return userTokenRepository.findByToken(token);
    }

    @Override
    public User queryUser(Integer userId) {

        return userRepository.findById(userId).get();
    }
}

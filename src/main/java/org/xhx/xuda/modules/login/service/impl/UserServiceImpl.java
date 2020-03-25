package org.xhx.xuda.modules.login.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xhx.xuda.common.exception.RRException;
import org.xhx.xuda.modules.login.dao.UserRepository;
import org.xhx.xuda.modules.login.entity.User;
import org.xhx.xuda.modules.login.service.UserService;

import java.util.*;


/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User queryByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User queryObject(Integer userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void save(User user) {
        //sha256加密
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteBatch(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updatePassword(Integer userId, String password, String newPassword) {
        Optional<User> toolUser = userRepository.findById(userId);
        if (toolUser.isPresent()) {
            User user = toolUser.get();
            if (user.getPassword().equals(password)) {
                user.setPassword(newPassword);
                userRepository.save(user);
            } else {
                throw new RRException("原密码错误");
            }
        } else {
            throw new RRException("用户不存在");
        }
    }
}

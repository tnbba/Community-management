package org.xhx.xuda.modules.login.service;

import org.xhx.xuda.modules.login.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 根据用户名，查询系统用户
     */
    User queryByUserName(String username);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    User queryObject(Integer userId);

    /**
     * 保存用户
     */
    void save(User user);

    /**
     * 修改用户
     */
    void update(User user);

    /**
     * 删除用户
     */
    void deleteBatch(Integer userId);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    void updatePassword(Integer userId, String password, String newPassword);
}

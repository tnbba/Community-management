package org.xhx.xuda.modules.login.dao;

import org.xhx.xuda.modules.login.entity.UserToken;

public interface UserTokenRepository extends BaseRepository<UserToken, Integer> {

    UserToken findByToken(String token);

    UserToken findByUserId(Integer userId);
}

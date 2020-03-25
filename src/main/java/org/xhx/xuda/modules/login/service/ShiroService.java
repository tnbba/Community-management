package org.xhx.xuda.modules.login.service;

import org.xhx.xuda.modules.login.entity.User;
import org.xhx.xuda.modules.login.entity.UserToken;

public interface ShiroService {

    UserToken queryByToken(String token);

    User queryUser(Integer userId);
}

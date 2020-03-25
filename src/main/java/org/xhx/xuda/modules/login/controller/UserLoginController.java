package org.xhx.xuda.modules.login.controller;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xhx.xuda.common.bean.R;
import org.xhx.xuda.common.exception.RRException;
import org.xhx.xuda.modules.login.entity.User;
import org.xhx.xuda.modules.login.service.UserService;
import org.xhx.xuda.modules.login.service.UserTokenService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping(value = "/login")
    public Map<String, Object> login(String username, String password) throws IOException {

        //用户信息
        User user = userService.queryByUserName(username);

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(password).toHex())) {
            return R.error("账号或密码不正确");
        }

        //生成token，并保存到数据库
        R r = userTokenService.createToken(user.getId());
        return r;
    }

    @PostMapping(value = "/logon")
    public Map<String, Object> logon(String username, String password) throws IOException {

        User user = userService.queryByUserName(StringUtils.trimAllWhitespace(username));

        if (!ObjectUtils.isEmpty(user)) {
            throw new RRException("用户名已存在");
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);

        R r = userTokenService.createToken(user.getId());
        return r;
    }
}

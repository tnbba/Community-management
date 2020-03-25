package org.xhx.xuda.modules.login.service;


import org.xhx.xuda.common.bean.R;
import org.xhx.xuda.modules.login.entity.UserToken;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface UserTokenService {

	UserToken queryByUserId(Integer userId);

	UserToken queryByToken(String token);
	
	void save(UserToken token);
	
	void update(UserToken token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(Integer userId);

}

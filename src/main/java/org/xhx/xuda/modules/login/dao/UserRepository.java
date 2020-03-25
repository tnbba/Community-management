package org.xhx.xuda.modules.login.dao;

import org.xhx.xuda.modules.login.entity.User;

import java.util.Optional;


public interface UserRepository extends BaseRepository<User, Integer> {

    Optional<User> findById(Integer id);

    User findByUsername(String userName);

    void deleteById(Integer id);
}

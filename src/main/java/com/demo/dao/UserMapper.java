package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import com.demo.entity.UserRedoParam;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    void saveUser(UserRedoParam user);

    UserRedoParam loadUser();

    void stackDeleteById (Integer id);

    Integer selectMaxId();
}

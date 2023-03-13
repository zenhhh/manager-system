package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.entity.*;

import java.util.List;


public interface UserService {

    IPage<User> userQuery(Param param);

    void userAdd(User user);

    void userEdit(User user);

    void userDelete(List<Integer> ids);

    void loadUser();
}

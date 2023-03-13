package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.annotations.SaveOriginalData;
import com.demo.dao.UserMapper;
import com.demo.entity.*;
import com.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> userQuery(Param param){

        QueryWrapper<User> wrap = new QueryWrapper<>();
        Page<User> page = new Page<>();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());

        return userMapper.selectPage(page,wrap);
    };

    @Override
    @SaveOriginalData(actionType = "insert")
    public void userAdd(User user){
        userMapper.insert(new User()
                .setName(user.getName())
                .setAge(user.getAge())
                .setSex(user.getSex())
                .setPhone(user.getPhone())
                .setAddress(user.getAddress())
                .setArea(user.getArea())
        );
        
    };

    @Override
    @SaveOriginalData(actionType = "update")
    public void userEdit(User user){
        userMapper.updateById(new User()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .setSex(user.getSex())
                .setPhone(user.getPhone())
                .setAddress(user.getAddress())
                .setArea(user.getArea())
        );
    };

    @Override
    @SaveOriginalData(actionType = "delete")
    public void userDelete(List<Integer> ids){
        userMapper.deleteBatchIds(ids);
    };


    @Override
    public void loadUser(){
        UserRedoParam user = userMapper.loadUser();
        if(user.getActionType().equals("insert")){
            userMapper.deleteById(user.getId());
            userMapper.stackDeleteById(user.getId());
        }else if (user.getActionType().equals("delete")){
            userMapper.insert(new User()
                    .setName(user.getName())
                    .setAge(user.getAge())
                    .setSex(user.getSex())
                    .setPhone(user.getPhone())
                    .setAddress(user.getAddress())
                    .setArea(user.getArea())
            );
            userMapper.stackDeleteById(user.getId());
        }else if (user.getActionType().equals("update")){
            userMapper.updateById(new User()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setAge(user.getAge())
                    .setSex(user.getSex())
                    .setPhone(user.getPhone())
                    .setAddress(user.getAddress())
                    .setArea(user.getArea())
            );
            userMapper.stackDeleteById(user.getId());
        }
    }
}

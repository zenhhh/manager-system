package com.demo.utils;

import com.demo.annotations.SaveOriginalData;
import com.demo.dao.UserMapper;
import com.demo.entity.User;
import com.demo.entity.UserRedoParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Aspect
@Component
public class SaveOriginalDataAspect  {

    @Autowired
    private UserMapper userMapper;

    @Around("@annotation(saveOriginalData)")
    public Object around(ProceedingJoinPoint joinPoint, SaveOriginalData saveOriginalData) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            if(args[0] instanceof User){
                if(saveOriginalData.actionType().equals("update")){
                    User newUser = (User) args[0];
                    User oldUser = userMapper.selectById(newUser.getId());
                    UserRedoParam userRedoParam = new UserRedoParam();
                    userRedoParam.setActionType(saveOriginalData.actionType());
                    BeanUtils.copyProperties(oldUser,userRedoParam);
                    userMapper.saveUser(userRedoParam);
                }else if(saveOriginalData.actionType().equals("insert")){
                    User oldUser = (User) args[0];
                    Integer id = userMapper.selectMaxId() + 1;
                    UserRedoParam userRedoParam = new UserRedoParam();
                    userRedoParam.setActionType(saveOriginalData.actionType());
                    BeanUtils.copyProperties(oldUser,userRedoParam);
                    userRedoParam.setId(id);
                    userMapper.saveUser(userRedoParam);
                }
            } else if (saveOriginalData.actionType().equals("delete") && args[0] instanceof List){
                List ids = (List) args[0];
                ids.forEach((id) -> {
                    User user = userMapper.selectById((Serializable) id);
                    UserRedoParam userRedoParam = new UserRedoParam();
                    userRedoParam.setActionType(saveOriginalData.actionType());
                    BeanUtils.copyProperties(user,userRedoParam);
                    userMapper.saveUser(userRedoParam);
                });
            }
        }
        return joinPoint.proceed();
    }
}

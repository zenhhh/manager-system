package com.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.entity.*;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private UserService userService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "/query")
    public Result<IPage<User>> query(@RequestBody Param param) {
        return Result.success(userService.userQuery(param));
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.userAdd(user);
        return Result.success();
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/edit")
    public Result edit(@RequestBody User user) {
        userService.userEdit(user);
        return Result.success();
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids) {
        userService.userDelete(ids);
        return Result.success();
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/repeal")
    public Result repeal() {
        userService.loadUser();
        return Result.success();
    }
}

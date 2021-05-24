package com.example.portfolio.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.service.FileUploadService;
import org.springframework.web.bind.annotation.*;
import com.example.portfolio.entity.User;
import com.example.portfolio.service.UserService;
import org.springframework.validation.annotation.Validated;
import com.example.portfolio.service.FileUploadService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-04-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/detail")
    public Result getUserById(Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }
    @GetMapping("/username")
    public Result getUserByName(String name){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", name));
        Boolean res = false;
        if (user != null) res =true;
        return Result.success(res);
    }
    @PostMapping("/save")
    public Result saveUser(@Validated @RequestBody User user){
        Boolean result = false;
        if (user.getId() == null){
            result = userService.save(user);
        }else {
            User temp = new User();
            temp = userService.getById(user.getId());
            temp.setPassword(user.getPassword());
            temp.setUsername(user.getUsername());
            temp.setAvatar(user.getAvatar());
            temp.setDetail(user.getDetail());
            temp.setEmail(user.getEmail());
            temp.setStatus(user.getStatus());
            result = userService.saveOrUpdate(temp);
        }
        return Result.success(result);
    }
}

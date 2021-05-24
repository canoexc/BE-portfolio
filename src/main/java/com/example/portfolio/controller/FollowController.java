package com.example.portfolio.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.entity.Follow;
import com.example.portfolio.entity.User;
import com.example.portfolio.entity.UserFollow;
import com.example.portfolio.service.FollowService;
import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关注状态列表 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;

    @GetMapping("sub")
    public Result setFollow(Long oid, Long tid){
        Follow follow = new Follow();
        Follow follow1 = followService.getOne(new QueryWrapper<Follow>().eq("oid",oid).eq("tid",tid));
        Follow follow2 = followService.getOne(new QueryWrapper<Follow>().eq("oid",tid).eq("tid",oid));
        if (follow1 != null)
        {
            follow.setOid(oid);
            follow.setTid(tid);
            if (follow1.getStatus() == 0){
                follow.setStatus(1);
            }else if(follow1.getStatus() == 2) {
                follow.setStatus(3);
            }
        }else if (follow2 != null){
            follow.setOid(tid);
            follow.setTid(oid);
            if (follow2.getStatus() == 0){
                follow.setStatus(2);
            }else if(follow2.getStatus() == 1) {
                follow.setStatus(3);
            }
        }else {
            follow.setOid(oid);
            follow.setTid(tid);
            follow.setStatus(1);
        }
        Boolean res = followService.saveOrUpdate(follow);
        return Result.success(res);
    }

    @GetMapping("ing")
    public Result getFollowingByUid(Long id){
        List<UserFollow> userFollows = new ArrayList<>();
        List<Follow> follows = followService.list(new QueryWrapper<Follow>().or(i -> i.eq("oid",id).eq("status",1))
                .or(i -> i.eq("tid",id).eq("status",2))
                .or(i -> i.eq("oid",id).eq("status",3))
                .or(i -> i.eq("tid",id).eq("status",3))
        );
        for(Follow follow: follows){
            UserFollow userFollow = new UserFollow();
            User user = new User();
            Integer status = 0;
            if (follow.getStatus() == 1){
                user = userService.getById(follow.getTid());
                status = 1;
            }else if (follow.getStatus() == 2){
                user = userService.getById(follow.getOid());
                status = 2;
            }else if(follow.getStatus() == 3){
                if (follow.getOid().equals(id)){
                    user = userService.getById(follow.getTid());
                    status = 3;
                }else if (follow.getTid().equals(id)){
                    user = userService.getById(follow.getOid());
                    status = 4;
                }

            }
            userFollow.setUser(user);
            userFollow.setStatus(status);
            userFollows.add(userFollow);
        }
        return Result.success(userFollows);
    }

    @GetMapping("er")
    public Result getFollowerByUid(Long id){
        List<UserFollow> userFollows = new ArrayList<>();
        List<Follow> follows = followService.list(new QueryWrapper<Follow>().or(i -> i.eq("oid",id).eq("status",2))
                .or(i -> i.eq("tid",id).eq("status",1))
                .or(i -> i.eq("oid",id).eq("status",3))
                .or(i -> i.eq("tid",id).eq("status",3))
        );
        for(Follow follow: follows){
            UserFollow userFollow = new UserFollow();
            User user = new User();
            Integer status = 0;
            if (follow.getStatus() == 1){
                user = userService.getById(follow.getOid());
                status = 2;
            }else if (follow.getStatus() == 2){
                user = userService.getById(follow.getTid());
                status = 1;
            }else {
                if (follow.getOid().equals(id)){
                    user = userService.getById(follow.getTid());
                    status = 3;
                }else if (follow.getTid().equals(id)){
                    user = userService.getById(follow.getOid());
                    status = 4;
                }

            }
            userFollow.setUser(user);
            userFollow.setStatus(status);
            userFollows.add(userFollow);
        }
        return Result.success(userFollows);
    }

    @GetMapping("ingDelete")
    public Result deleteFollowing(Long oid, Long tid, Integer status){
        Follow follow = new Follow();
        if (status == 1){
            follow.setOid(oid);
            follow.setTid(tid);
            follow.setStatus(0);
        }else if (status == 2){
            follow.setOid(tid);
            follow.setTid(oid);
            follow.setStatus(0);
        }else if (status == 3){
            follow.setOid(oid);
            follow.setTid(tid);
            follow.setStatus(2);
        }else if (status == 4){
            follow.setOid(tid);
            follow.setTid(oid);
            follow.setStatus(1);
        }
        Boolean res = followService.saveOrUpdate(follow);
        return Result.success(res);
    }

    @GetMapping("erDelete")
    public Result deleteFollower(Long oid, Long tid, Integer status){
        Follow follow = new Follow();
        if (status == 1){
            follow.setOid(oid);
            follow.setTid(tid);
            follow.setStatus(0);
        }else if (status == 2){
            follow.setOid(tid);
            follow.setTid(oid);
            follow.setStatus(0);
        }else if (status == 3){
            follow.setOid(oid);
            follow.setTid(tid);
            follow.setStatus(1);
        }else if (status == 4){
            follow.setOid(tid);
            follow.setTid(oid);
            follow.setStatus(2);
        }
        Boolean res = followService.saveOrUpdate(follow);
        return Result.success(res);
    }

    @GetMapping("isIng")
    public Result isFollowing(Long oid, Long tid){
        Follow follow1 = followService.getOne(new QueryWrapper<Follow>().eq("oid",oid).eq("tid",tid));
        Follow follow2 = followService.getOne(new QueryWrapper<Follow>().eq("oid",tid).eq("tid",oid));
        Boolean res = false;
        if (follow1 != null && (follow1.getStatus() == 1 || follow1.getStatus() == 3))
        {
            res = true;
        }
        if (follow2 != null && (follow2.getStatus() == 2 || follow2.getStatus() == 3))
        {
            res = true;
        }
        return Result.success(res);
    }

    @GetMapping("isEr")
    public Result isFollower(Long oid, Long tid){
        Follow follow1 = followService.getOne(new QueryWrapper<Follow>().eq("oid",oid).eq("tid",tid));
        Follow follow2 = followService.getOne(new QueryWrapper<Follow>().eq("oid",tid).eq("tid",oid));
        Boolean res = false;
        if (follow1 != null && (follow1.getStatus() == 2 || follow1.getStatus() == 3))
        {
            res = true;
        }
        if (follow2 != null && (follow2.getStatus() == 1 || follow2.getStatus() == 3))
        {
            res = true;
        }
        return Result.success(res);
    }
}

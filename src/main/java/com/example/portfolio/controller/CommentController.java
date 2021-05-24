package com.example.portfolio.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.entity.Comment;
import com.example.portfolio.entity.CommentSub;
import com.example.portfolio.service.CommentService;
import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PostMapping("save")
    public Result savaComment(@RequestBody Comment comment){
        comment.setCreateDate(LocalDateTime.now());
        Boolean res = commentService.save(comment);
        return Result.success(res);
    }

    @GetMapping("file")
    public Result getCommentByFile(Long id, String type){
        List<Comment> comments = commentService.list(new QueryWrapper<Comment>().eq("cid",id)
                .eq("type",type)
                .isNull("fid") );
        List<CommentSub> commentSubs = new ArrayList<>();
        for(Comment comment: comments){
            CommentSub temp = new CommentSub();
            temp = temp.transComment(comment);
            temp.setCommentUser(userService.getById(comment.getCommentUser()));
            temp.setTargetUser(userService.getById(comment.getTargetUser()));
            List<Comment> children = commentService.list(new QueryWrapper<Comment>().eq("fid",temp.getId()) );
            List<CommentSub> childrenList = new ArrayList<>();
            for(Comment child: children){
                CommentSub commentSub = new CommentSub();
                commentSub = commentSub.transComment(child);
                commentSub.setCommentUser(userService.getById(child.getCommentUser()));
                commentSub.setTargetUser(userService.getById(child.getTargetUser()));
                childrenList.add(commentSub);
            }
            temp.setChildrenList(childrenList);
            commentSubs.add(temp);
        }
        return Result.success(commentSubs);
    }

    @GetMapping("total")
    public Result getCommentTotal(Long id, String type){
        List<Comment> comments = commentService.list(new QueryWrapper<Comment>().eq("cid",id)
                .eq("type",type));
        Integer total = comments.size();
        return Result.success(total);
    }

    @DeleteMapping("delete")
    public Result deleteCommentByFile(Long id){
        Boolean res = commentService.removeById(id);
        return Result.success(res);
    }
}

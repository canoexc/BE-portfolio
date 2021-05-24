package com.example.portfolio.service.impl;

import com.example.portfolio.entity.Comment;
import com.example.portfolio.mapper.CommentMapper;
import com.example.portfolio.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

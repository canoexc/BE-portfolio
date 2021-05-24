package com.example.portfolio.service.impl;

import com.example.portfolio.entity.User;
import com.example.portfolio.mapper.UserMapper;
import com.example.portfolio.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

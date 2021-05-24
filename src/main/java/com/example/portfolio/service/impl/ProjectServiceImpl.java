package com.example.portfolio.service.impl;

import com.example.portfolio.entity.Project;
import com.example.portfolio.mapper.ProjectMapper;
import com.example.portfolio.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目信息 服务实现类
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}

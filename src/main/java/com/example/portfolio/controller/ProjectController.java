package com.example.portfolio.controller;


import com.example.portfolio.entity.Project;
import com.example.portfolio.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.portfolio.common.lang.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 项目信息 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("upload")
    public Result uploadProject(@RequestBody Project project){
        Boolean result = false;
        if (project.getId() == null){
            project.setCreateTime(LocalDateTime.now());
            project.setUpdateTime(project.getCreateTime());
            result = projectService.save(project);
        }else {
            Project temp = new Project();
            temp = projectService.getById(project.getId());
            temp.setContent(project.getContent());
            temp.setUpdateTime(LocalDateTime.now());
            temp.setTitle(project.getTitle());
            temp.setCover(project.getCover());
            temp.setProgress(project.getProgress());
            result = projectService.saveOrUpdate(temp);
        }
        return Result.success(result);
    }

    @GetMapping("uidList")
    public Result getProjectByUid(Long uid, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 12);
        IPage pageData = projectService.page(page, new QueryWrapper<Project>().eq("uid",uid).orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @GetMapping("keyList")
    public Result getProjectByKey (String keyWord, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 12);
        IPage pageData = projectService.page(page, new QueryWrapper<Project>().like("title", keyWord).orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @GetMapping("detail")
    public Result detailProject(Long id){
        Project project = projectService.getById(id);
        Assert.notNull(project, "该项目已删除");
        return Result.success(project);
    }

    @DeleteMapping("delete")
    public Result deleteProject(Long id){
        Boolean result = projectService.removeById(id);
        return Result.success(result);
    }
}

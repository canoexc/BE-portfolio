package com.example.portfolio.controller;


import com.example.portfolio.entity.Download;
import com.example.portfolio.service.DownloadService;
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
 * 可下载资源信息 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    DownloadService downloadService;

    @PostMapping("upload")
    public Result uploadDownload(@RequestBody Download download){
        Boolean result = false;
        if(download.getId() == null){
            download.setCreateTime(LocalDateTime.now());
            result = downloadService.save(download);
        }else {
            Download temp = new Download();
            temp = downloadService.getById(download.getId());
            temp.setDetail(download.getDetail());
            temp.setFile(download.getFile());
            temp.setTitle(download.getTitle());
            result = downloadService.saveOrUpdate(temp);
        }
        return Result.success(result);
    }

    @GetMapping("uidList")
    public Result getDownloadByUid(Long uid, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = downloadService.page(page, new QueryWrapper<Download>().eq("uid",uid).orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @GetMapping("keyList")
    public Result getDownloadByKey (String keyWord, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = downloadService.page(page, new QueryWrapper<Download>()
                .like("title", keyWord)
                .or()
                .like("detail", keyWord)
                .orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @DeleteMapping("delete")
    public Result deleteDownload (Long id){
        Boolean result = downloadService.removeById(id);
        return Result.success(result);
    }

    @GetMapping("detail")
    public Result detailDownload(Long id) {
        Download download = downloadService.getById(id);
        Assert.notNull(download, "该资源已删除");
        return Result.success(download);
    }
}

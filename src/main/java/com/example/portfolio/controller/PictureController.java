package com.example.portfolio.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.entity.Picture;
import com.example.portfolio.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 图片信息 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    PictureService pictureService;

    @GetMapping("uidList")
//    /{uid}/{currentPage}
    public Result getPictureListByUid (Long uid, Integer currentPage){
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 3);
        IPage pageData = pictureService.page(page, new QueryWrapper<Picture>().eq("uid",uid).orderByDesc("createTime"));
        return Result.success(pageData);
    }
    @GetMapping("keyList")
    public Result getPictureListByKey (String keyWord, Integer currentPage){
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 6);
        IPage pageData = pictureService.page(page, new QueryWrapper<Picture>().like("detail",keyWord).orderByDesc("createTime"));
        return Result.success(pageData);
    }
    @GetMapping("detail")
    public Result detailPicture(Long id) {
        Picture picture = pictureService.getById(id);
        Assert.notNull(picture, "该图片已删除！");
        return Result.success(picture);
    }
    @PostMapping("upload")
    public Result uploadPictures(@Validated @RequestBody List<Picture> pics){
        for (Picture pic : pics){
            pic.setCreateTime(LocalDateTime.now());
            pictureService.save(pic);
        }
        return Result.success(null);
    }
    @DeleteMapping("delete")
    public Result deletePicture(Long id){
        boolean result = pictureService.removeById(id);
        return Result.success(result);
    }
}

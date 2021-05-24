package com.example.portfolio.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.portfolio.common.lang.Result;
import com.example.portfolio.entity.Article;
import com.example.portfolio.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 文章信息 前端控制器
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("upload")
    public Result uploadArticle(@RequestBody Article article){
        Boolean result = false;
        if (article.getId() == null){
            article.setCreateTime(LocalDateTime.now());
            article.setUpdateTime(article.getCreateTime());
            result = articleService.save(article);
        }else {
            Article temp = new Article();
            temp = articleService.getById(article.getId());
            temp.setContent(article.getContent());
            LocalDateTime localDateTime = LocalDateTime.now();
            temp.setUpdateTime(localDateTime);
            temp.setTitle(article.getTitle());
            temp.setDigest(article.getDigest());
            result = articleService.saveOrUpdate(temp);
        }
        return Result.success(result);
    }

    @GetMapping("uidList")
    public Result getArticleByUid (Long uid, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = articleService.page(page, new QueryWrapper<Article>().eq("uid",uid).orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @GetMapping("keyList")
    public Result getArticleByKey (String keyWord, Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = articleService.page(page, new QueryWrapper<Article>()
                .like("title",keyWord)
                .or()
                .like("digest",keyWord)
                .or()
                .like("content",keyWord)
                .orderByDesc("createTime"));
        return Result.success(pageData);
    }

    @GetMapping("detail")
    public Result detailArticle(Long id){
        Article article = articleService.getById(id);
        Assert.notNull(article, "该文章已删除");
        return Result.success(article);
    }

    @DeleteMapping("delete")
    public Result deleteArticle(Long id){
        Boolean result = articleService.removeById(id);
        return Result.success(result);
    }

}

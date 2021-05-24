package com.example.portfolio.service.impl;

import com.example.portfolio.entity.Article;
import com.example.portfolio.mapper.ArticleMapper;
import com.example.portfolio.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-01
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}

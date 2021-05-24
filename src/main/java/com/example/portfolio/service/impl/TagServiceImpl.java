package com.example.portfolio.service.impl;

import com.example.portfolio.entity.Tag;
import com.example.portfolio.mapper.TagMapper;
import com.example.portfolio.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

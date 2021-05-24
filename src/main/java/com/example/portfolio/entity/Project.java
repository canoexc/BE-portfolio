package com.example.portfolio.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目信息
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 进度
     */
    private Double progress;

    /**
     * 文件内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 最新修改时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 点击量
     */
    private Integer click;

    /**
     * 置顶等级
     */
    private Integer level;

    /**
     * 上传作者id
     */
    private Long uid;


}

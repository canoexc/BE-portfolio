package com.example.portfolio.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图片信息
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 描述
     */
    private String detail;

    /**
     * 文件地址
     */
    private String file;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

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

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
 * 可下载资源信息
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_download")
public class Download implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详细信息
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
     * 下载次数
     */
    private Integer times;

    /**
     * 置顶等级
     */
    private Integer level;

    /**
     * 上传作者id
     */
    private Long uid;


}

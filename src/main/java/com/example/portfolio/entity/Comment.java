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
 * 评论
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容id
     */
    private Long cid;

    /**
     * 内容类型
     */
    private String type;

    /**
     * 父评论id
     */
    private Long fid;

    /**
     * 评论用户id
     */
    @TableField("commentUser")
    private Long commentUser;

    /**
     * 目标用户id
     */
    @TableField("targetUser")
    private Long targetUser;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @TableField("createDate")
    private LocalDateTime createDate;


}

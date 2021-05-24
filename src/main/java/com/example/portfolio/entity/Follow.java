package com.example.portfolio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 关注状态列表
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_follow")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注人id
     */
    @TableId(value = "oid")
    private Long oid;

    /**
     * 被关注人id
     */
    @TableId(value = "tid")
    private Long tid;

    /**
     * 关注状态
     */
    private Integer status;


}

package com.example.portfolio.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容
     */
    private String content;


}

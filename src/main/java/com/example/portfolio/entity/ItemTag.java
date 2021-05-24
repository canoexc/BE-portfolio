package com.example.portfolio.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author Chenoa3104
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pfl_itemTag")
public class ItemTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    private Long tid;

    /**
     * 文章id
     */
    private Long aid;


}

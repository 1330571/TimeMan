package com.xys.timemgr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAvatar implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("URI")
    private String uri;


}

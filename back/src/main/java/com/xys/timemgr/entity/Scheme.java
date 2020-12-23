package com.xys.timemgr.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Xu Yusheng
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Scheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String schemeName;

    private String tagList;


}

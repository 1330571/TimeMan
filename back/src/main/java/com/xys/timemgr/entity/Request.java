package com.xys.timemgr.entity;

import java.io.Serializable;
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
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String requestInfo;

    private Integer requestType;


}

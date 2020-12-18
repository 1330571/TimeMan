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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userAccount;

    private String password;

    private Integer userType;

    private String tasks;

    private String userGroups;

    private String classes;

    private Integer requestId;

    private String userName;
}

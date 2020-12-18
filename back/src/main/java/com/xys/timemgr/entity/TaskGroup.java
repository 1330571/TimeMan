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
public class TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String classList;

    private String memeberList;

    private String taskList;

    private String groupName;

    private Integer creatorId;

    private Integer groupType;

    private Integer linkCode;


}

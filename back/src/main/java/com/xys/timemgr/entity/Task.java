package com.xys.timemgr.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userList;

    private String description;

    private String statesList;

    private LocalDateTime deadline;

    private String needFeedback;

    private String feedbackId;

    private String commentsId;

    private Integer schemeId;

    private String taskTitle;

    private String taskSender;
}

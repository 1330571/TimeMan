package com.xys.timemgr.entity;

import java.time.LocalDateTime;
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
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer senderId;

    private String status;

    private String reviewContent;

    private String feedbackContent;

    private LocalDateTime reviewTime;

    private LocalDateTime feedbackTime;


}

package com.team.assessment.entry;

import lombok.Data;

import java.util.Date;

@Data
public class SuperEntry {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者 ID
     */
    private Long createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者 ID
     */
    private Long updateUserId;
}

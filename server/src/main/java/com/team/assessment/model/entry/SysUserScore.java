package com.team.assessment.model.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.team.assessment.common.entry.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @TableName sys_user_score
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user_score")
public class SysUserScore extends BaseEntity implements Serializable {


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    private Integer year;


}
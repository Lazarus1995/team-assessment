package com.team.assessment.model.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName sys_law
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_law")
public class SysLaw extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 小立法 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long lawId;

    /**
     * 小立法分数
     */
    private Integer lawScore;

    /**
     * 小立法内容
     */
    private String lawContent;

    /**
     * 所属部门 ID
     */
    private Long departmentId;

    /**
     * 当月出现次数
     */
    private Integer lawMonthCount;


    /**
     * 小立法类型
     */
    private Integer lawType;
}
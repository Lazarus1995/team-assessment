package com.team.assessment.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @TableName sys_law
 */
@TableName(value ="sys_law")
@Data
public class SysLaw extends BaseEntity implements Serializable {
    /**
     * 小立法 ID
     */
    @TableId
    private Long id;

    /**
     * 小立法分数
     */
    private Integer lawScore;

    /**
     * 小立法内容
     */
    private String lawContent;


    private Long departmentId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
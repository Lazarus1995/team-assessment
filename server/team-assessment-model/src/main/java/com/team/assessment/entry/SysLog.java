package com.team.assessment.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName sys_log
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_log")
public class SysLog extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日志记录时间
     */
    private Date logTime;

    /**
     * 日志记录用户 ID
     */
    private Long logUserId;

    /**
     * 日志类型
     * @see com.team.assessment.enums.SysLogTypeEnum
     */
    private Integer logType;

}
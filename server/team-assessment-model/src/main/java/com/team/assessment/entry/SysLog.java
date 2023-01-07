package com.team.assessment.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName sys_log
 */
@TableName(value ="sys_log")
@Data
public class SysLog extends BaseEntity implements Serializable {
    /**
     * 主键 ID
     */
    @TableId
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
     * 日志类型:0打分日志，1 导出日志
     */
    private Integer logType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
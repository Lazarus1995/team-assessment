package com.team.assessment.model.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.team.assessment.common.entry.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
     * 日志类型
     *
     * @see com.team.assessment.enums.SysLogTypeEnum
     */
    private Integer logType;

    private String theme;

    /**
     * 补充说明
     */
    private String content;

    /**
     * 图片地址
     */
    private String picUrl;
}

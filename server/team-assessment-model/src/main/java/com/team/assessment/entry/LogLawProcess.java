package com.team.assessment.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "log_law_process")
public class LogLawProcess extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 小立法 ID
     */
    private Long lawId;

    /**
     * 被评分用户ID
     */
    private Long userId;

    /**
     * 小立法类型
     * @see com.team.assessment.enums.SysLawTypeEnum
     */
    private Integer lawType;

    /**
     * 补充说明
     */
    private String content;

    /**
     * 图片地址
     */
    private String picUrl;
}

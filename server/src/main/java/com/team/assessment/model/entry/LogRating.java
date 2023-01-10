package com.team.assessment.model.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "log_rating")
public class LogRating extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 评分人 id
     */
    private Long userId;


    /**
     * 打分状态
     * @see com.team.assessment.enums.LogRatingStatusEnum
     */
    private Integer ratingStatus;

    /**
     * 申诉内容
     */
    private String complaintContent;

    /**
     * 申诉状态
     * @see com.team.assessment.enums.LogComplaintStatusEnum
     */
    private Integer complaintStatus;
}

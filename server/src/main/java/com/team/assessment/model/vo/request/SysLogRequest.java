package com.team.assessment.model.vo.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 *
 */
@Data
public class SysLogRequest implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 日志记录用户 ID
     */
    private Long logUserId;

    /**
     * 日志类型
     *
     * @see com.team.assessment.enums.SysLogTypeEnum
     */
    private Integer logType;

    private MultipartFile file;
    /**
     * 补充说明
     */
    private String content;

    private String theme;


    private String createTime;
}

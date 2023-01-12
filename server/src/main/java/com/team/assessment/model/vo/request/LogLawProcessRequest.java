package com.team.assessment.model.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogLawProcessRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * @see com.team.assessment.common.enums.SysLawTypeEnum
     */
    private Integer lawType;

    private Integer lawScore;

    /**
     * 补充说明
     */
    private String content;

    /**
     * 创建者 ID
     */
    private Long createUserId;

    private MultipartFile file;
}

package com.team.assessment.model.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogLawProcessResponse implements Serializable {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 小立法 ID
     */
    private Long lawId;

    private String lawContent;

    /**
     * 被评分用户ID
     */
    private Long userId;

    private String userName;

    /**
     * 小立法类型
     *
     * @see com.team.assessment.common.enums.SysLawTypeEnum
     */
    private Integer lawType;

    private String lawTypeName;

    /**
     * 补充说明
     */
    private String content;

    /**
     * 图片地址
     */
    private String picUrl;


    private String createTime;
}

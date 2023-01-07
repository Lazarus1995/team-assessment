package com.team.assessment.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SysLawRequest {

    /**
     * 小立法分数
     */
    @NotNull(message = "小立法分数不能为空")
    private Integer lawScore;

    /**
     * 小立法内容
     */
    @NotBlank(message = "小立法内容不能为空")
    @Size(max = 1000, message = "小立法内容不能超过1000个字符")
    private String lawContent;


    /**
     * 所属部门 ID
     */
    @NotNull(message = "所属部门 ID 不能为空")
    private Long departmentId;


    /**
     * 新增或更新操作的用户
     */
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    /**
     * 是否根据分数排名
     */
    private Boolean orderByScore;

    /**
     * 分数排序：aes 正序，desc 倒序
     */
    private String scoreOrder;
}

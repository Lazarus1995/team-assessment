package com.team.assessment.model.vo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName sys_department
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDepartmentRequest implements Serializable {

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 上级部门 id
     */
    private Long parentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
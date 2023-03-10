package com.team.assessment.model.vo.response;

import com.team.assessment.model.entry.SysDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName sys_department
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDepartmentResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 上级部门 id
     */
    private Long parentId;

    /**
     * 子节点
     */
    private List<SysDepartmentResponse> children;

    /**
     * 是否为叶子结点
     */
    private Boolean leaf;

    public static SysDepartmentResponse convert(SysDepartment sysDepartment){
        SysDepartmentResponse sysDepartmentResponse = new SysDepartmentResponse();
        sysDepartmentResponse.setId(sysDepartment.getId());
        sysDepartmentResponse.setDepartmentName(sysDepartment.getDepartmentName());
        sysDepartmentResponse.setParentId(sysDepartment.getParentId());
        return sysDepartmentResponse;
    }
}
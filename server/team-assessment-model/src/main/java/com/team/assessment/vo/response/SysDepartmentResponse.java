package com.team.assessment.vo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.team.assessment.entry.BaseEntity;
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
public class SysDepartmentResponse implements Serializable {

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
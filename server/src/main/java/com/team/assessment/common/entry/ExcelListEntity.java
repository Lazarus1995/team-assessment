package com.team.assessment.common.entry;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelListEntity {

    /**
     * 判分人
     */
    @ExcelProperty(value = "姓名", index = 0)
    private String createUserName;


    /**
     * 判分时间
     */
    @ExcelProperty(value = "判分时间", index = 1)
    private String createTime;

    /**
     * 被判分人
     */
    @ExcelProperty(value = "被判分人", index = 2)
    private String userName;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型", index = 3)
    private String lawType;

    /**
     * 小立法
     */
    @ExcelProperty(value = "小立法", index = 4)
    private String lawContent;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 5)
    private String content;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片", index = 6)
    private File pic;
}

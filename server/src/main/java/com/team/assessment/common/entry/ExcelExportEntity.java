package com.team.assessment.common.entry;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(15) //列宽,最大值为255
@HeadRowHeight(16) //表头行高
@ContentRowHeight(16) //数据行高
public class ExcelExportEntity {

    /**
     * 月初日期
     */
    private String month;

    /**
     * 当前时间
     */
    private String now;

    /**
     * 判分人
     */
    private String createUserName;


    /**
     * 判分时间
     */
    private String creataTime;

    /**
     * 被判分人
     */
    private String userName;

    /**
     * 操作类型
     */
    private String lawType;

    /**
     * 小立法
     */
    private String lawContent;

    /**
     * 备注
     */
    private String content;

    private ExcelImageEntity pic;
}

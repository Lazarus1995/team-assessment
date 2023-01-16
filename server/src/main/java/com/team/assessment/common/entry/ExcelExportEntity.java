package com.team.assessment.common.entry;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelExportEntity {

    /**
     * 月初日期
     */
    @ExcelProperty(value = "月初时间", index = 0)
    private String month;

    /**
     * 当前时间
     */
    @ExcelProperty(value = "当前时间", index = 0)
    private String now;

    List<ExcelListEntity> excelListEntityList;
}

package com.ta.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ca.entry.SuperEntry;
import lombok.Data;

/**
 * 
 * @TableName sys_law
 */
@TableName(value ="sys_law")
@Data
public class SysLaw extends SuperEntry implements Serializable {
    /**
     * 小立法 ID
     */
    @TableId
    private Long lawId;

    /**
     * 小立法分数
     */
    private Integer lawScore;

    /**
     * 小立法内容
     */
    private String lawContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者 ID
     */
    private Long createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者 ID
     */
    private Long updateUserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysLaw other = (SysLaw) that;
        return (this.getLawId() == null ? other.getLawId() == null : this.getLawId().equals(other.getLawId()))
            && (this.getLawScore() == null ? other.getLawScore() == null : this.getLawScore().equals(other.getLawScore()))
            && (this.getLawContent() == null ? other.getLawContent() == null : this.getLawContent().equals(other.getLawContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLawId() == null) ? 0 : getLawId().hashCode());
        result = prime * result + ((getLawScore() == null) ? 0 : getLawScore().hashCode());
        result = prime * result + ((getLawContent() == null) ? 0 : getLawContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lawId=").append(lawId);
        sb.append(", lawScore=").append(lawScore);
        sb.append(", lawContent=").append(lawContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
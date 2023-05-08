package dcits.entery;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预检修对象 cus_prerepair
 * 
 * @author ruoyi
 * @date 2023-05-08
 */
public class CusPrerepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检修id */
    private Long overhaulId;

    /** 检修区域 */
    @Excel(name = "检修区域")
    private String inspectionArea;

    /** 检修内容 */
    @Excel(name = "检修内容")
    private String maintenanceContent;

    /** 检修状态 */
    @Excel(name = "检修状态")
    private String maintenanceCondition;

    /** 检修总天数 */
    @Excel(name = "检修总天数")
    private Integer totalDays;

    /** 上次检修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上次检修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastInspectionTime;

    /** 检修备注 */
    @Excel(name = "检修备注")
    private String inspectionRemarks;

    /** 检修人 */
    @Excel(name = "检修人")
    private String maintenanceMan;

    /** 检修问题未解决 */
    @Excel(name = "检修问题未解决")
    private String problem;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 检修周 */
    @Excel(name = "检修周")
    private Integer weeks;

    public void setOverhaulId(Long overhaulId) 
    {
        this.overhaulId = overhaulId;
    }

    public Long getOverhaulId() 
    {
        return overhaulId;
    }
    public void setInspectionArea(String inspectionArea) 
    {
        this.inspectionArea = inspectionArea;
    }

    public String getInspectionArea() 
    {
        return inspectionArea;
    }
    public void setMaintenanceContent(String maintenanceContent) 
    {
        this.maintenanceContent = maintenanceContent;
    }

    public String getMaintenanceContent() 
    {
        return maintenanceContent;
    }
    public void setMaintenanceCondition(String maintenanceCondition) 
    {
        this.maintenanceCondition = maintenanceCondition;
    }

    public String getMaintenanceCondition() 
    {
        return maintenanceCondition;
    }
    public void setTotalDays(Integer totalDays) 
    {
        this.totalDays = totalDays;
    }

    public Integer getTotalDays() 
    {
        return totalDays;
    }
    public void setLastInspectionTime(Date lastInspectionTime) 
    {
        this.lastInspectionTime = lastInspectionTime;
    }

    public Date getLastInspectionTime() 
    {
        return lastInspectionTime;
    }
    public void setInspectionRemarks(String inspectionRemarks) 
    {
        this.inspectionRemarks = inspectionRemarks;
    }

    public String getInspectionRemarks() 
    {
        return inspectionRemarks;
    }
    public void setMaintenanceMan(String maintenanceMan) 
    {
        this.maintenanceMan = maintenanceMan;
    }

    public String getMaintenanceMan() 
    {
        return maintenanceMan;
    }
    public void setProblem(String problem) 
    {
        this.problem = problem;
    }

    public String getProblem() 
    {
        return problem;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setWeeks(Integer weeks) 
    {
        this.weeks = weeks;
    }

    public Integer getWeeks() 
    {
        return weeks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("overhaulId", getOverhaulId())
            .append("inspectionArea", getInspectionArea())
            .append("maintenanceContent", getMaintenanceContent())
            .append("maintenanceCondition", getMaintenanceCondition())
            .append("totalDays", getTotalDays())
            .append("lastInspectionTime", getLastInspectionTime())
            .append("inspectionRemarks", getInspectionRemarks())
            .append("maintenanceMan", getMaintenanceMan())
            .append("problem", getProblem())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("weeks", getWeeks())
            .toString();
    }
}

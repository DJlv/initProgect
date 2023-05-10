package dcits.entery;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库房备件对象 cus_house
 * 
 * @author zr
 * @date 2023-05-10
 */
public class CusHouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 预警数量 */
    @Excel(name = "预警数量")
    private String warnings;

    /** 库位 */
    @Excel(name = "库位")
    private String storageLocation;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String materialNumber;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specificationModel;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 盘点数量 */
    @Excel(name = "盘点数量")
    private String countQuantity;

    /** 随机S/旧件J/新件X */
    @Excel(name = "随机S/旧件J/新件X")
    private String status;

    /** 库位说明 */
    @Excel(name = "库位说明")
    private String storageLocationDescription;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private String safetyStock;

    /** 货架 */
    @Excel(name = "货架")
    private String shelf;

    /** 货架计数 */
    @Excel(name = "货架计数")
    private String shelfCount;

    /** 是否导入 */
    @Excel(name = "是否导入")
    private String importNot;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarnings(String warnings) 
    {
        this.warnings = warnings;
    }

    public String getWarnings() 
    {
        return warnings;
    }
    public void setStorageLocation(String storageLocation) 
    {
        this.storageLocation = storageLocation;
    }

    public String getStorageLocation() 
    {
        return storageLocation;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialNumber(String materialNumber) 
    {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() 
    {
        return materialNumber;
    }
    public void setSpecificationModel(String specificationModel) 
    {
        this.specificationModel = specificationModel;
    }

    public String getSpecificationModel() 
    {
        return specificationModel;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setCountQuantity(String countQuantity) 
    {
        this.countQuantity = countQuantity;
    }

    public String getCountQuantity() 
    {
        return countQuantity;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setStorageLocationDescription(String storageLocationDescription) 
    {
        this.storageLocationDescription = storageLocationDescription;
    }

    public String getStorageLocationDescription() 
    {
        return storageLocationDescription;
    }
    public void setSafetyStock(String safetyStock) 
    {
        this.safetyStock = safetyStock;
    }

    public String getSafetyStock() 
    {
        return safetyStock;
    }
    public void setShelf(String shelf) 
    {
        this.shelf = shelf;
    }

    public String getShelf() 
    {
        return shelf;
    }
    public void setShelfCount(String shelfCount) 
    {
        this.shelfCount = shelfCount;
    }

    public String getShelfCount() 
    {
        return shelfCount;
    }
    public void setImportNot(String importNot) 
    {
        this.importNot = importNot;
    }

    public String getImportNot() 
    {
        return importNot;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warnings", getWarnings())
            .append("storageLocation", getStorageLocation())
            .append("materialName", getMaterialName())
            .append("materialNumber", getMaterialNumber())
            .append("specificationModel", getSpecificationModel())
            .append("brand", getBrand())
            .append("unit", getUnit())
            .append("countQuantity", getCountQuantity())
            .append("status", getStatus())
            .append("storageLocationDescription", getStorageLocationDescription())
            .append("safetyStock", getSafetyStock())
            .append("shelf", getShelf())
            .append("shelfCount", getShelfCount())
            .append("importNot", getImportNot())
            .append("price", getPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

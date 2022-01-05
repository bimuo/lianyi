package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 product
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 图号 */
    @Excel(name = "图号")
    private String drawNumber;

    /** 规格 */
    @Excel(name = "规格")
    private String specifications;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 总数 */
    @Excel(name = "总数")
    private Long total;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDrawNumber(String drawNumber) 
    {
        this.drawNumber = drawNumber;
    }

    public String getDrawNumber() 
    {
        return drawNumber;
    }
    public void setSpecifications(String specifications) 
    {
        this.specifications = specifications;
    }

    public String getSpecifications() 
    {
        return specifications;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("drawNumber", getDrawNumber())
            .append("specifications", getSpecifications())
            .append("unit", getUnit())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("total", getTotal())
            .toString();
    }
}

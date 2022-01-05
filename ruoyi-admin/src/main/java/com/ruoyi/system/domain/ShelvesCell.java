package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 货架格子对象 shelves_cell
 * 
 * @author ruoyi
 * @date 2022-01-04
 */
public class ShelvesCell extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 货架编号 */
    @Excel(name = "货架编号")
    private Long shelvesId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setShelvesId(Long shelvesId) 
    {
        this.shelvesId = shelvesId;
    }

    public Long getShelvesId() 
    {
        return shelvesId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("shelvesId", getShelvesId())
            .toString();
    }
}

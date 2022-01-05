package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品所在货柜的关联对象 product_shelves
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
public class ProductShelves extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 货架格子编号 */
    @Excel(name = "货架格子编号")
    private Long shelvesCellId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long productId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long count;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShelvesCellId(Long shelvesCellId) 
    {
        this.shelvesCellId = shelvesCellId;
    }

    public Long getShelvesCellId() 
    {
        return shelvesCellId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shelvesCellId", getShelvesCellId())
            .append("productId", getProductId())
            .append("count", getCount())
            .toString();
    }
}

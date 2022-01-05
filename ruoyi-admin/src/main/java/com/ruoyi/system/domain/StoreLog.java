package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出入库记录对象 store_log
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
public class StoreLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String  productName;
    
    private Long productId;

    /** 货架 */
    @Excel(name = "货架")
    private String  shelvesName;
    @Excel(name = "货架格子")
    private String  shelvesCellName;

    private Long shelvesCellId;
    
    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setShelvesCellId(Long shelvesCellId) 
    {
        this.shelvesCellId = shelvesCellId;
    }

    public Long getShelvesCellId() 
    {
        return shelvesCellId;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShelvesName() {
		return shelvesName;
	}

	public void setShelvesName(String shelvesName) {
		this.shelvesName = shelvesName;
	}

	public String getShelvesCellName() {
		return shelvesCellName;
	}

	public void setShelvesCellName(String shelvesCellName) {
		this.shelvesCellName = shelvesCellName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("productId", getProductId())
            .append("shelvesCellId", getShelvesCellId())
            .append("count", getCount())
            .append("createTime", getCreateTime())
            .toString();
    }
}

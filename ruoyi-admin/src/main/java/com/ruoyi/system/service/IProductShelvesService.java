package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ProductShelves;

/**
 * 商品所在货柜的关联Service接口
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
public interface IProductShelvesService 
{
    /**
     * 查询商品所在货柜的关联
     * 
     * @param id 商品所在货柜的关联主键
     * @return 商品所在货柜的关联
     */
    public ProductShelves selectProductShelvesById(Long id);

    /**
     * 查询商品所在货柜的关联列表
     * 
     * @param productShelves 商品所在货柜的关联
     * @return 商品所在货柜的关联集合
     */
    public List<ProductShelves> selectProductShelvesList(ProductShelves productShelves);

    /**
     * 新增商品所在货柜的关联
     * 
     * @param productShelves 商品所在货柜的关联
     * @return 结果
     */
    public int insertProductShelves(ProductShelves productShelves);

    /**
     * 修改商品所在货柜的关联
     * 
     * @param productShelves 商品所在货柜的关联
     * @return 结果
     */
    public int updateProductShelves(ProductShelves productShelves);

    /**
     * 批量删除商品所在货柜的关联
     * 
     * @param ids 需要删除的商品所在货柜的关联主键集合
     * @return 结果
     */
    public int deleteProductShelvesByIds(Long[] ids);

    /**
     * 删除商品所在货柜的关联信息
     * 
     * @param id 商品所在货柜的关联主键
     * @return 结果
     */
    public int deleteProductShelvesById(Long id);
}

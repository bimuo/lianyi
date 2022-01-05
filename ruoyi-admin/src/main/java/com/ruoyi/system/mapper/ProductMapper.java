package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Product;
import com.ruoyi.system.vo.ProductOuput;
import com.ruoyi.system.vo.ProductStore;

/**
 * 商品Mapper接口
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
public interface ProductMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Product selectProductById(Long id);
    /**
     * 出库
     * @param productShelves
     * @return
     */
    public int output(ProductOuput productOuput);

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品集合
     */
    public List<Product> selectProductList(Product product);

    public List<Product> selectrepeatProductList(Product product);
    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);
    /**
     * 获取指定产品的库存数据
     * @param id
     * @return
     */
    public List<ProductStore> getProductStoreById(Long id);
}

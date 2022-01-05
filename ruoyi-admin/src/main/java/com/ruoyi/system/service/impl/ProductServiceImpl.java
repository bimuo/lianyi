package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Product;
import com.ruoyi.system.domain.ProductShelves;
import com.ruoyi.system.domain.StoreLog;
import com.ruoyi.system.mapper.ProductMapper;
import com.ruoyi.system.mapper.ProductShelvesMapper;
import com.ruoyi.system.mapper.StoreLogMapper;
import com.ruoyi.system.service.IProductService;
import com.ruoyi.system.vo.ProductOuput;
import com.ruoyi.system.vo.ProductStore;

/**
 * 商品Service业务层处理
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
@Service
public class ProductServiceImpl implements IProductService 
{
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StoreLogMapper storeLogMapper;
    @Autowired
	private ProductShelvesMapper productShelvesMapper;
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param product 商品
     * @return 商品
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品
     * 
     * @param product 商品
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        product.setCreateTime(DateUtils.getNowDate());
        product.setDrawNumber(product.getDrawNumber()==null?"":product.getDrawNumber());
        product.setSpecifications(product.getSpecifications()==null?"":product.getSpecifications());
        Product findParams = new Product();
        findParams.setDrawNumber(product.getDrawNumber());
        findParams.setSpecifications(product.getSpecifications());
        List<Product> ps = productMapper.selectrepeatProductList(findParams);
        if(ps!=null && ps.size()>0) {
        	return 0;
        }
        
        return productMapper.insertProduct(product);
    }

    /**
     * 修改商品
     * 
     * @param product 商品
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        product.setUpdateTime(DateUtils.getNowDate());
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteProductByIds(Long[] ids)
    {
        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Long id)
    {
        return productMapper.deleteProductById(id);
    }

	@Override
	public List<ProductStore> getProductStoreById(long id) {
		return productMapper.getProductStoreById(id);
	}

	@Override
	public int output(List<ProductOuput> list) {
		int ret = 0;
		for(ProductOuput ps:list) {
			ProductShelves p =new ProductShelves();
			p.setShelvesCellId(ps.getShelvesId());
			List<ProductShelves> plist = productShelvesMapper.selectProductShelvesList(p);
			if(plist!=null && plist.size()>0) {
				if(ps.getOutputCount()>plist.get(0).getCount()) {
					throw new RuntimeException("出库数量不能大于库存");
				}else if(ps.getOutputCount()==plist.get(0).getCount()) {
					productShelvesMapper.deleteProductShelvesById(plist.get(0).getId());
				}else {
					ret+=productMapper.output(ps);
				}
			}
			
			StoreLog log=new StoreLog();
			log.setProductId(ps.getProductId());
			log.setType(0L);
			log.setShelvesCellId(ps.getShelvesId());
			log.setCount((long)ps.getOutputCount());
			log.setCreateTime(new Date());
			storeLogMapper.insertStoreLog(log);
		}
		return ret;
	}
}

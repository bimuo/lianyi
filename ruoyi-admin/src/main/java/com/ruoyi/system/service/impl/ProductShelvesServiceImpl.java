package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.ProductShelves;
import com.ruoyi.system.domain.StoreLog;
import com.ruoyi.system.mapper.ProductShelvesMapper;
import com.ruoyi.system.mapper.StoreLogMapper;
import com.ruoyi.system.service.IProductShelvesService;

/**
 * 商品所在货柜的关联Service业务层处理
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
@Service
public class ProductShelvesServiceImpl implements IProductShelvesService {
	@Autowired
	private ProductShelvesMapper productShelvesMapper;

	@Autowired
	private StoreLogMapper storeLogMapper;

	/**
	 * 查询商品所在货柜的关联
	 * 
	 * @param id 商品所在货柜的关联主键
	 * @return 商品所在货柜的关联
	 */
	@Override
	public ProductShelves selectProductShelvesById(Long id) {
		return productShelvesMapper.selectProductShelvesById(id);
	}

	/**
	 * 查询商品所在货柜的关联列表
	 * 
	 * @param productShelves 商品所在货柜的关联
	 * @return 商品所在货柜的关联
	 */
	@Override
	public List<ProductShelves> selectProductShelvesList(ProductShelves productShelves) {
		return productShelvesMapper.selectProductShelvesList(productShelves);
	}

	/**
	 * 新增商品所在货柜的关联
	 * 
	 * @param productShelves 商品所在货柜的关联
	 * @return 结果
	 */
	@Override
	public int insertProductShelves(ProductShelves productShelves) {
		ProductShelves findPs = new ProductShelves();
		findPs.setProductId(productShelves.getProductId());
		findPs.setShelvesCellId(productShelves.getShelvesCellId());
		List<ProductShelves> ps = productShelvesMapper.selectProductShelvesList(findPs);
		int ret = 0;
		if (ps != null && ps.size() > 0) {
			ps.get(0).setCount(ps.get(0).getCount() + productShelves.getCount());
			productShelves = ps.get(0);
			ret = productShelvesMapper.updateProductShelves(productShelves);
		}else {
			ret = productShelvesMapper.insertProductShelves(productShelves);
		}
		if (ret > 0 && productShelves.getCount() > 0) {
			StoreLog log = new StoreLog();
			log.setProductId(productShelves.getProductId());
			log.setType(1L);
			log.setShelvesCellId(productShelves.getShelvesCellId());
			log.setCount((long) productShelves.getCount());
			log.setCreateTime(new Date());
			storeLogMapper.insertStoreLog(log);
		}
		return ret;
	}

	/**
	 * 修改商品所在货柜的关联
	 * 
	 * @param productShelves 商品所在货柜的关联
	 * @return 结果
	 */
	@Override
	public int updateProductShelves(ProductShelves productShelves) {
		return productShelvesMapper.updateProductShelves(productShelves);
	}

	/**
	 * 批量删除商品所在货柜的关联
	 * 
	 * @param ids 需要删除的商品所在货柜的关联主键
	 * @return 结果
	 */
	@Override
	public int deleteProductShelvesByIds(Long[] ids) {
		return productShelvesMapper.deleteProductShelvesByIds(ids);
	}

	/**
	 * 删除商品所在货柜的关联信息
	 * 
	 * @param id 商品所在货柜的关联主键
	 * @return 结果
	 */
	@Override
	public int deleteProductShelvesById(Long id) {
		return productShelvesMapper.deleteProductShelvesById(id);
	}
}

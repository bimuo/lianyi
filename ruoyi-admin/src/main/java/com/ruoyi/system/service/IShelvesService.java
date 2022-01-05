package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Shelves;
import com.ruoyi.system.vo.SelectTreeValue;

/**
 * 货架Service接口
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
public interface IShelvesService 
{
    /**
     * 查询货架
     * 
     * @param id 货架主键
     * @return 货架
     */
    public Shelves selectShelvesById(Long id);

    /**
     * 查询货架列表
     * 
     * @param shelves 货架
     * @return 货架集合
     */
    public List<Shelves> selectShelvesList(Shelves shelves);
    
    /**
     * 查询货架列表
     * 
     * @param shelves 货架
     * @return 货架集合
     */
    public List<SelectTreeValue> getShelvesTree();
    
    public List<SelectTreeValue> getChildrenShelvesTree(Long id);

    /**
     * 新增货架
     * 
     * @param shelves 货架
     * @return 结果
     */
    public int insertShelves(Shelves shelves);

    /**
     * 修改货架
     * 
     * @param shelves 货架
     * @return 结果
     */
    public int updateShelves(Shelves shelves);

    /**
     * 批量删除货架
     * 
     * @param ids 需要删除的货架主键集合
     * @return 结果
     */
    public int deleteShelvesByIds(Long[] ids);

    /**
     * 删除货架信息
     * 
     * @param id 货架主键
     * @return 结果
     */
    public int deleteShelvesById(Long id);
}

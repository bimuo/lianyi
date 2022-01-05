package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ShelvesCell;

/**
 * 货架格子Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-04
 */
public interface ShelvesCellMapper 
{
    /**
     * 查询货架格子
     * 
     * @param id 货架格子主键
     * @return 货架格子
     */
    public ShelvesCell selectShelvesCellById(Long id);

    /**
     * 查询货架格子列表
     * 
     * @param shelvesCell 货架格子
     * @return 货架格子集合
     */
    public List<ShelvesCell> selectShelvesCellList(ShelvesCell shelvesCell);

    /**
     * 新增货架格子
     * 
     * @param shelvesCell 货架格子
     * @return 结果
     */
    public int insertShelvesCell(ShelvesCell shelvesCell);

    /**
     * 修改货架格子
     * 
     * @param shelvesCell 货架格子
     * @return 结果
     */
    public int updateShelvesCell(ShelvesCell shelvesCell);

    /**
     * 删除货架格子
     * 
     * @param id 货架格子主键
     * @return 结果
     */
    public int deleteShelvesCellById(Long id);

    /**
     * 批量删除货架格子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShelvesCellByIds(Long[] ids);
}

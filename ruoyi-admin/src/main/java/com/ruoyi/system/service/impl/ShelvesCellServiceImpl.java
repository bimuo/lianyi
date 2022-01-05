package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ShelvesCellMapper;
import com.ruoyi.system.domain.ShelvesCell;
import com.ruoyi.system.service.IShelvesCellService;

/**
 * 货架格子Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-04
 */
@Service
public class ShelvesCellServiceImpl implements IShelvesCellService 
{
    @Autowired
    private ShelvesCellMapper shelvesCellMapper;

    /**
     * 查询货架格子
     * 
     * @param id 货架格子主键
     * @return 货架格子
     */
    @Override
    public ShelvesCell selectShelvesCellById(Long id)
    {
        return shelvesCellMapper.selectShelvesCellById(id);
    }

    /**
     * 查询货架格子列表
     * 
     * @param shelvesCell 货架格子
     * @return 货架格子
     */
    @Override
    public List<ShelvesCell> selectShelvesCellList(ShelvesCell shelvesCell)
    {
        return shelvesCellMapper.selectShelvesCellList(shelvesCell);
    }

    /**
     * 新增货架格子
     * 
     * @param shelvesCell 货架格子
     * @return 结果
     */
    @Override
    public int insertShelvesCell(ShelvesCell shelvesCell)
    {
        return shelvesCellMapper.insertShelvesCell(shelvesCell);
    }

    /**
     * 修改货架格子
     * 
     * @param shelvesCell 货架格子
     * @return 结果
     */
    @Override
    public int updateShelvesCell(ShelvesCell shelvesCell)
    {
        return shelvesCellMapper.updateShelvesCell(shelvesCell);
    }

    /**
     * 批量删除货架格子
     * 
     * @param ids 需要删除的货架格子主键
     * @return 结果
     */
    @Override
    public int deleteShelvesCellByIds(Long[] ids)
    {
        return shelvesCellMapper.deleteShelvesCellByIds(ids);
    }

    /**
     * 删除货架格子信息
     * 
     * @param id 货架格子主键
     * @return 结果
     */
    @Override
    public int deleteShelvesCellById(Long id)
    {
        return shelvesCellMapper.deleteShelvesCellById(id);
    }
}

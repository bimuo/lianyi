package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.Shelves;
import com.ruoyi.system.domain.ShelvesCell;
import com.ruoyi.system.mapper.ShelvesCellMapper;
import com.ruoyi.system.mapper.ShelvesMapper;
import com.ruoyi.system.service.IShelvesService;
import com.ruoyi.system.vo.SelectTreeValue;

/**
 * 货架Service业务层处理
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
@Service
public class ShelvesServiceImpl implements IShelvesService 
{
    @Autowired
    private ShelvesMapper shelvesMapper;
    @Autowired
    private ShelvesCellMapper shelvesCellMapper;

    /**
     * 查询货架
     * 
     * @param id 货架主键
     * @return 货架
     */
    @Override
    public Shelves selectShelvesById(Long id)
    {
        return shelvesMapper.selectShelvesById(id);
    }

    /**
     * 查询货架列表
     * 
     * @param shelves 货架
     * @return 货架
     */
    @Override
    public List<Shelves> selectShelvesList(Shelves shelves)
    {
        return shelvesMapper.selectShelvesList(shelves);
    }

    /**
     * 新增货架
     * 
     * @param shelves 货架
     * @return 结果
     */
    @Override
    public int insertShelves(Shelves shelves)
    {
        return shelvesMapper.insertShelves(shelves);
    }

    /**
     * 修改货架
     * 
     * @param shelves 货架
     * @return 结果
     */
    @Override
    public int updateShelves(Shelves shelves)
    {
        return shelvesMapper.updateShelves(shelves);
    }

    /**
     * 批量删除货架
     * 
     * @param ids 需要删除的货架主键
     * @return 结果
     */
    @Override
    public int deleteShelvesByIds(Long[] ids)
    {
        return shelvesMapper.deleteShelvesByIds(ids);
    }

    /**
     * 删除货架信息
     * 
     * @param id 货架主键
     * @return 结果
     */
    @Override
    public int deleteShelvesById(Long id)
    {
        return shelvesMapper.deleteShelvesById(id);
    }

	@Override
	public List<SelectTreeValue> getShelvesTree() {
		List<SelectTreeValue> retsut = new ArrayList<>();
		
		Shelves shelves = new Shelves();
		List<Shelves> list = this.selectShelvesList(shelves);
		for(Shelves s : list) {
			ShelvesCell sc = new ShelvesCell();
			sc.setShelvesId(s.getId());
			
			SelectTreeValue stv = new SelectTreeValue();
			stv.setLabel(s.getName());
			stv.setValue(s.getId().toString());
			List<SelectTreeValue> stvClients = new ArrayList<>();
			stv.setChildren(stvClients);
			
			List<ShelvesCell> childs =  shelvesCellMapper.selectShelvesCellList(sc);
			for(ShelvesCell c:childs) {
				SelectTreeValue stvc = new SelectTreeValue();
				stvc.setLabel(c.getName());
				stvc.setValue(c.getId().toString()); 
				stvClients.add(stvc);
			}
			retsut.add(stv);
		}
		return retsut;
	}
}

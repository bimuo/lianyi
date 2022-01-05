package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StoreLogMapper;
import com.ruoyi.system.domain.StoreLog;
import com.ruoyi.system.service.IStoreLogService;

/**
 * 出入库记录Service业务层处理
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
@Service
public class StoreLogServiceImpl implements IStoreLogService 
{
    @Autowired
    private StoreLogMapper storeLogMapper;

    /**
     * 查询出入库记录
     * 
     * @param id 出入库记录主键
     * @return 出入库记录
     */
    @Override
    public StoreLog selectStoreLogById(Long id)
    {
        return storeLogMapper.selectStoreLogById(id);
    }

    /**
     * 查询出入库记录列表
     * 
     * @param storeLog 出入库记录
     * @return 出入库记录
     */
    @Override
    public List<StoreLog> selectStoreLogList(StoreLog storeLog)
    {
        return storeLogMapper.selectStoreLogList(storeLog);
    }

    /**
     * 新增出入库记录
     * 
     * @param storeLog 出入库记录
     * @return 结果
     */
    @Override
    public int insertStoreLog(StoreLog storeLog)
    {
        storeLog.setCreateTime(DateUtils.getNowDate());
        return storeLogMapper.insertStoreLog(storeLog);
    }

    /**
     * 修改出入库记录
     * 
     * @param storeLog 出入库记录
     * @return 结果
     */
    @Override
    public int updateStoreLog(StoreLog storeLog)
    {
        return storeLogMapper.updateStoreLog(storeLog);
    }

    /**
     * 批量删除出入库记录
     * 
     * @param ids 需要删除的出入库记录主键
     * @return 结果
     */
    @Override
    public int deleteStoreLogByIds(Long[] ids)
    {
        return storeLogMapper.deleteStoreLogByIds(ids);
    }

    /**
     * 删除出入库记录信息
     * 
     * @param id 出入库记录主键
     * @return 结果
     */
    @Override
    public int deleteStoreLogById(Long id)
    {
        return storeLogMapper.deleteStoreLogById(id);
    }
}

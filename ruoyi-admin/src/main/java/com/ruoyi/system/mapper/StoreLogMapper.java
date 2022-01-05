package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StoreLog;

/**
 * 出入库记录Mapper接口
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
public interface StoreLogMapper 
{
    /**
     * 查询出入库记录
     * 
     * @param id 出入库记录主键
     * @return 出入库记录
     */
    public StoreLog selectStoreLogById(Long id);

    /**
     * 查询出入库记录列表
     * 
     * @param storeLog 出入库记录
     * @return 出入库记录集合
     */
    public List<StoreLog> selectStoreLogList(StoreLog storeLog);

    /**
     * 新增出入库记录
     * 
     * @param storeLog 出入库记录
     * @return 结果
     */
    public int insertStoreLog(StoreLog storeLog);

    /**
     * 修改出入库记录
     * 
     * @param storeLog 出入库记录
     * @return 结果
     */
    public int updateStoreLog(StoreLog storeLog);

    /**
     * 删除出入库记录
     * 
     * @param id 出入库记录主键
     * @return 结果
     */
    public int deleteStoreLogById(Long id);

    /**
     * 批量删除出入库记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreLogByIds(Long[] ids);
}

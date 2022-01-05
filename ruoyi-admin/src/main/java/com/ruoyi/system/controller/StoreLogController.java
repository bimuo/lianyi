package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.StoreLog;
import com.ruoyi.system.service.IStoreLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出入库记录Controller
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
@RestController
@RequestMapping("/system/storelog")
public class StoreLogController extends BaseController
{
    @Autowired
    private IStoreLogService storeLogService;

    /**
     * 查询出入库记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreLog storeLog)
    {
        startPage();
        List<StoreLog> list = storeLogService.selectStoreLogList(storeLog);
        return getDataTable(list);
    }

    /**
     * 导出出入库记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:export')")
    @Log(title = "出入库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreLog storeLog)
    {
        List<StoreLog> list = storeLogService.selectStoreLogList(storeLog);
        ExcelUtil<StoreLog> util = new ExcelUtil<StoreLog>(StoreLog.class);
        util.exportExcel(response, list, "出入库记录数据");
    }

    /**
     * 获取出入库记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storeLogService.selectStoreLogById(id));
    }

    /**
     * 新增出入库记录
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:add')")
    @Log(title = "出入库记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreLog storeLog)
    {
        return toAjax(storeLogService.insertStoreLog(storeLog));
    }

    /**
     * 修改出入库记录
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:edit')")
    @Log(title = "出入库记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreLog storeLog)
    {
        return toAjax(storeLogService.updateStoreLog(storeLog));
    }

    /**
     * 删除出入库记录
     */
    @PreAuthorize("@ss.hasPermi('system:storelog:remove')")
    @Log(title = "出入库记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storeLogService.deleteStoreLogByIds(ids));
    }
}

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
import com.ruoyi.system.domain.ShelvesCell;
import com.ruoyi.system.service.IShelvesCellService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货架格子Controller
 * 
 * @author ruoyi
 * @date 2022-01-04
 */
@RestController
@RequestMapping("/system/cell")
public class ShelvesCellController extends BaseController
{
    @Autowired
    private IShelvesCellService shelvesCellService;

    /**
     * 查询货架格子列表
     */
    @PreAuthorize("@ss.hasPermi('system:cell:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShelvesCell shelvesCell)
    {
        startPage();
        List<ShelvesCell> list = shelvesCellService.selectShelvesCellList(shelvesCell);
        return getDataTable(list);
    }

    /**
     * 导出货架格子列表
     */
    @PreAuthorize("@ss.hasPermi('system:cell:export')")
    @Log(title = "货架格子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShelvesCell shelvesCell)
    {
        List<ShelvesCell> list = shelvesCellService.selectShelvesCellList(shelvesCell);
        ExcelUtil<ShelvesCell> util = new ExcelUtil<ShelvesCell>(ShelvesCell.class);
        util.exportExcel(response, list, "货架格子数据");
    }

    /**
     * 获取货架格子详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cell:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shelvesCellService.selectShelvesCellById(id));
    }

    /**
     * 新增货架格子
     */
    @PreAuthorize("@ss.hasPermi('system:cell:add')")
    @Log(title = "货架格子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShelvesCell shelvesCell)
    {
        return toAjax(shelvesCellService.insertShelvesCell(shelvesCell));
    }

    /**
     * 修改货架格子
     */
    @PreAuthorize("@ss.hasPermi('system:cell:edit')")
    @Log(title = "货架格子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShelvesCell shelvesCell)
    {
        return toAjax(shelvesCellService.updateShelvesCell(shelvesCell));
    }

    /**
     * 删除货架格子
     */
    @PreAuthorize("@ss.hasPermi('system:cell:remove')")
    @Log(title = "货架格子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shelvesCellService.deleteShelvesCellByIds(ids));
    }
}

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
import com.ruoyi.system.domain.Shelves;
import com.ruoyi.system.service.IShelvesService;
import com.ruoyi.system.vo.SelectTreeValue;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货架Controller
 * 
 * @author 韩磊
 * @date 2022-01-04
 */
@RestController
@RequestMapping("/system/shelves")
public class ShelvesController extends BaseController
{
    @Autowired
    private IShelvesService shelvesService;
    
    /**
     * 查询货架列表
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:list')")
    @GetMapping("/list")
    public TableDataInfo list(Shelves shelves)
    {
        startPage();
        List<Shelves> list = shelvesService.selectShelvesList(shelves);
        return getDataTable(list);
    }
    
    @GetMapping("/tree")
    public List<SelectTreeValue> tree()
    {
        return shelvesService.getShelvesTree();
    }

    /**
     * 导出货架列表
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:export')")
    @Log(title = "货架", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Shelves shelves)
    {
        List<Shelves> list = shelvesService.selectShelvesList(shelves);
        ExcelUtil<Shelves> util = new ExcelUtil<Shelves>(Shelves.class);
        util.exportExcel(response, list, "货架数据");
    }

    /**
     * 获取货架详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shelvesService.selectShelvesById(id));
    }

    /**
     * 新增货架
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:add')")
    @Log(title = "货架", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Shelves shelves)
    {
        return toAjax(shelvesService.insertShelves(shelves));
    }

    /**
     * 修改货架
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:edit')")
    @Log(title = "货架", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Shelves shelves)
    {
        return toAjax(shelvesService.updateShelves(shelves));
    }

    /**
     * 删除货架
     */
    @PreAuthorize("@ss.hasPermi('system:shelves:remove')")
    @Log(title = "货架", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shelvesService.deleteShelvesByIds(ids));
    }
}

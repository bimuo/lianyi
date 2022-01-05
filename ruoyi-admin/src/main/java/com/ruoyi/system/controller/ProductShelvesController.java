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
import com.ruoyi.system.domain.ProductShelves;
import com.ruoyi.system.service.IProductShelvesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品所在货柜的关联Controller
 * 
 * @author 韩磊
 * @date 2022-01-05
 */
@RestController
@RequestMapping("/system/productshelves")
public class ProductShelvesController extends BaseController
{
    @Autowired
    private IProductShelvesService productShelvesService;

    /**
     * 查询商品所在货柜的关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductShelves productShelves)
    {
        startPage();
        List<ProductShelves> list = productShelvesService.selectProductShelvesList(productShelves);
        return getDataTable(list);
    }

    /**
     * 导出商品所在货柜的关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:export')")
    @Log(title = "商品所在货柜的关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductShelves productShelves)
    {
        List<ProductShelves> list = productShelvesService.selectProductShelvesList(productShelves);
        ExcelUtil<ProductShelves> util = new ExcelUtil<ProductShelves>(ProductShelves.class);
        util.exportExcel(response, list, "商品所在货柜的关联数据");
    }

    /**
     * 获取商品所在货柜的关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productShelvesService.selectProductShelvesById(id));
    }

    /**
     * 新增商品所在货柜的关联
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:add')")
    @Log(title = "商品所在货柜的关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductShelves productShelves)
    {
        return toAjax(productShelvesService.insertProductShelves(productShelves));
    }

    /**
     * 修改商品所在货柜的关联
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:edit')")
    @Log(title = "商品所在货柜的关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductShelves productShelves)
    {
        return toAjax(productShelvesService.updateProductShelves(productShelves));
    }

    /**
     * 删除商品所在货柜的关联
     */
    @PreAuthorize("@ss.hasPermi('system:productshelves:remove')")
    @Log(title = "商品所在货柜的关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productShelvesService.deleteProductShelvesByIds(ids));
    }
}

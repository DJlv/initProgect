package dcits.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import dcits.entery.CusHouse;
import dcits.interfaces.ICusHouseService;
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

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库房备件Controller
 * 
 * @author zr
 * @date 2023-05-10
 */
@RestController
@RequestMapping("/mapper/house")
public class CusHouseController extends BaseController
{
    @Autowired
    private ICusHouseService cusHouseService;

    /**
     * 查询库房备件列表
     */
    @PreAuthorize("@ss.hasPermi('cus:house:list')")
    @GetMapping("/list")
    public TableDataInfo list(CusHouse cusHouse)
    {
        startPage();
        List<CusHouse> list = cusHouseService.selectCusHouseList(cusHouse);
        return getDataTable(list);
    }

    /**
     * 导出库房备件列表
     */
    @PreAuthorize("@ss.hasPermi('cus:house:export')")
    @Log(title = "库房备件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CusHouse cusHouse)
    {
        List<CusHouse> list = cusHouseService.selectCusHouseList(cusHouse);
        ExcelUtil<CusHouse> util = new ExcelUtil<CusHouse>(CusHouse.class);
        util.exportExcel(response, list, "库房备件数据");
    }

    /**
     * 获取库房备件详细信息
     */
    @PreAuthorize("@ss.hasPermi('cus:house:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cusHouseService.selectCusHouseById(id));
    }

    /**
     * 新增库房备件
     */
    @PreAuthorize("@ss.hasPermi('cus:house:add')")
    @Log(title = "库房备件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CusHouse cusHouse)
    {
        return toAjax(cusHouseService.insertCusHouse(cusHouse));
    }

    /**
     * 修改库房备件
     */
    @PreAuthorize("@ss.hasPermi('cus:house:edit')")
    @Log(title = "库房备件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CusHouse cusHouse)
    {
        return toAjax(cusHouseService.updateCusHouse(cusHouse));
    }

    /**
     * 删除库房备件
     */
    @PreAuthorize("@ss.hasPermi('cus:house:remove')")
    @Log(title = "库房备件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cusHouseService.deleteCusHouseByIds(ids));
    }
}

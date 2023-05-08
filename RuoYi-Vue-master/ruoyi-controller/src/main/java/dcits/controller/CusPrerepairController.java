package dcits.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import dcits.entery.CusPrerepair;
import dcits.interfaces.ICusPrerepairService;
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
 * 预检修Controller
 * 
 * @author ruoyi
 * @date 2023-05-08
 */
@RestController
@RequestMapping("/system/prerepair")
public class CusPrerepairController extends BaseController
{
    @Autowired
    private ICusPrerepairService cusPrerepairService;

    /**
     * 查询预检修列表
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:list')")
    @GetMapping("/list")
    public TableDataInfo list(CusPrerepair cusPrerepair)
    {
        startPage();
        List<CusPrerepair> list = cusPrerepairService.selectCusPrerepairList(cusPrerepair);
        return getDataTable(list);
    }

    /**
     * 导出预检修列表
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:export')")
    @Log(title = "预检修", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CusPrerepair cusPrerepair)
    {
        List<CusPrerepair> list = cusPrerepairService.selectCusPrerepairList(cusPrerepair);
        ExcelUtil<CusPrerepair> util = new ExcelUtil<CusPrerepair>(CusPrerepair.class);
        util.exportExcel(response, list, "预检修数据");
    }

    /**
     * 获取预检修详细信息
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:query')")
    @GetMapping(value = "/{overhaulId}")
    public AjaxResult getInfo(@PathVariable("overhaulId") Long overhaulId)
    {
        return success(cusPrerepairService.selectCusPrerepairByOverhaulId(overhaulId));
    }

    /**
     * 新增预检修
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:add')")
    @Log(title = "预检修", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CusPrerepair cusPrerepair)
    {
        return toAjax(cusPrerepairService.insertCusPrerepair(cusPrerepair));
    }

    /**
     * 修改预检修
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:edit')")
    @Log(title = "预检修", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CusPrerepair cusPrerepair)
    {
        return toAjax(cusPrerepairService.updateCusPrerepair(cusPrerepair));
    }

    /**
     * 删除预检修
     */
    @PreAuthorize("@ss.hasPermi('prerepair:prerepair:remove')")
    @Log(title = "预检修", businessType = BusinessType.DELETE)
	@DeleteMapping("/{overhaulIds}")
    public AjaxResult remove(@PathVariable Long[] overhaulIds)
    {
        return toAjax(cusPrerepairService.deleteCusPrerepairByOverhaulIds(overhaulIds));
    }
}

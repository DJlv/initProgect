package dcits.service;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import dcits.entery.CusPrerepair;
import dcits.interfaces.ICusPrerepairService;
import dcits.mapper.CusPrerepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 预检修Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-08
 */
@Service
public class CusPrerepairServiceImpl implements ICusPrerepairService
{
    @Autowired
    private CusPrerepairMapper cusPrerepairMapper;

    /**
     * 查询预检修
     * 
     * @param overhaulId 预检修主键
     * @return 预检修
     */
    @Override
    public CusPrerepair selectCusPrerepairByOverhaulId(Long overhaulId)
    {
        return cusPrerepairMapper.selectCusPrerepairByOverhaulId(overhaulId);
    }

    /**
     * 查询预检修列表
     * 
     * @param cusPrerepair 预检修
     * @return 预检修
     */
    @Override
    public List<CusPrerepair> selectCusPrerepairList(CusPrerepair cusPrerepair)
    {
        return cusPrerepairMapper.selectCusPrerepairList(cusPrerepair);
    }

    /**
     * 新增预检修
     * 
     * @param cusPrerepair 预检修
     * @return 结果
     */
    @Override
    public int insertCusPrerepair(CusPrerepair cusPrerepair)
    {
        cusPrerepair.setCreateTime(DateUtils.getNowDate());
        return cusPrerepairMapper.insertCusPrerepair(cusPrerepair);
    }

    /**
     * 修改预检修
     * 
     * @param cusPrerepair 预检修
     * @return 结果
     */
    @Override
    public int updateCusPrerepair(CusPrerepair cusPrerepair)
    {
        cusPrerepair.setUpdateTime(DateUtils.getNowDate());
        return cusPrerepairMapper.updateCusPrerepair(cusPrerepair);
    }

    /**
     * 批量删除预检修
     * 
     * @param overhaulIds 需要删除的预检修主键
     * @return 结果
     */
    @Override
    public int deleteCusPrerepairByOverhaulIds(Long[] overhaulIds)
    {
        return cusPrerepairMapper.deleteCusPrerepairByOverhaulIds(overhaulIds);
    }

    /**
     * 删除预检修信息
     * 
     * @param overhaulId 预检修主键
     * @return 结果
     */
    @Override
    public int deleteCusPrerepairByOverhaulId(Long overhaulId)
    {
        return cusPrerepairMapper.deleteCusPrerepairByOverhaulId(overhaulId);
    }
}

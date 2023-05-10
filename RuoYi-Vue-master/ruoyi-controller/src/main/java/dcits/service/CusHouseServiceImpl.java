package dcits.service;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import dcits.entery.CusHouse;
import dcits.interfaces.ICusHouseService;
import dcits.mapper.CusHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 库房备件Service业务层处理
 * 
 * @author zr
 * @date 2023-05-10
 */
@Service
public class CusHouseServiceImpl implements ICusHouseService
{
    @Autowired
    private CusHouseMapper cusHouseMapper;

    /**
     * 查询库房备件
     * 
     * @param id 库房备件主键
     * @return 库房备件
     */
    @Override
    public CusHouse selectCusHouseById(Long id)
    {
        return cusHouseMapper.selectCusHouseById(id);
    }

    /**
     * 查询库房备件列表
     * 
     * @param cusHouse 库房备件
     * @return 库房备件
     */
    @Override
    public List<CusHouse> selectCusHouseList(CusHouse cusHouse)
    {
        return cusHouseMapper.selectCusHouseList(cusHouse);
    }

    /**
     * 新增库房备件
     * 
     * @param cusHouse 库房备件
     * @return 结果
     */
    @Override
    public int insertCusHouse(CusHouse cusHouse)
    {
        cusHouse.setCreateTime(DateUtils.getNowDate());
        return cusHouseMapper.insertCusHouse(cusHouse);
    }

    /**
     * 修改库房备件
     * 
     * @param cusHouse 库房备件
     * @return 结果
     */
    @Override
    public int updateCusHouse(CusHouse cusHouse)
    {
        cusHouse.setUpdateTime(DateUtils.getNowDate());
        return cusHouseMapper.updateCusHouse(cusHouse);
    }

    /**
     * 批量删除库房备件
     * 
     * @param ids 需要删除的库房备件主键
     * @return 结果
     */
    @Override
    public int deleteCusHouseByIds(Long[] ids)
    {
        return cusHouseMapper.deleteCusHouseByIds(ids);
    }

    /**
     * 删除库房备件信息
     * 
     * @param id 库房备件主键
     * @return 结果
     */
    @Override
    public int deleteCusHouseById(Long id)
    {
        return cusHouseMapper.deleteCusHouseById(id);
    }
}

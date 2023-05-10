package dcits.interfaces;

import dcits.entery.CusHouse;

import java.util.List;

/**
 * 库房备件Service接口
 * 
 * @author zr
 * @date 2023-05-10
 */
public interface ICusHouseService 
{
    /**
     * 查询库房备件
     * 
     * @param id 库房备件主键
     * @return 库房备件
     */
    public CusHouse selectCusHouseById(Long id);

    /**
     * 查询库房备件列表
     * 
     * @param cusHouse 库房备件
     * @return 库房备件集合
     */
    public List<CusHouse> selectCusHouseList(CusHouse cusHouse);

    /**
     * 新增库房备件
     * 
     * @param cusHouse 库房备件
     * @return 结果
     */
    public int insertCusHouse(CusHouse cusHouse);

    /**
     * 修改库房备件
     * 
     * @param cusHouse 库房备件
     * @return 结果
     */
    public int updateCusHouse(CusHouse cusHouse);

    /**
     * 批量删除库房备件
     * 
     * @param ids 需要删除的库房备件主键集合
     * @return 结果
     */
    public int deleteCusHouseByIds(Long[] ids);

    /**
     * 删除库房备件信息
     * 
     * @param id 库房备件主键
     * @return 结果
     */
    public int deleteCusHouseById(Long id);
}

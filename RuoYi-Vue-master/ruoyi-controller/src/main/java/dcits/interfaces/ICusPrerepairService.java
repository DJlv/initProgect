package dcits.interfaces;


import dcits.entery.CusPrerepair;

import java.util.List;

/**
 * 预检修Service接口
 * 
 * @author ruoyi
 * @date 2023-05-08
 */
public interface ICusPrerepairService 
{
    /**
     * 查询预检修
     * 
     * @param overhaulId 预检修主键
     * @return 预检修
     */
    public CusPrerepair selectCusPrerepairByOverhaulId(Long overhaulId);

    /**
     * 查询预检修列表
     * 
     * @param cusPrerepair 预检修
     * @return 预检修集合
     */
    public List<CusPrerepair> selectCusPrerepairList(CusPrerepair cusPrerepair);

    /**
     * 新增预检修
     * 
     * @param cusPrerepair 预检修
     * @return 结果
     */
    public int insertCusPrerepair(CusPrerepair cusPrerepair);

    /**
     * 修改预检修
     * 
     * @param cusPrerepair 预检修
     * @return 结果
     */
    public int updateCusPrerepair(CusPrerepair cusPrerepair);

    /**
     * 批量删除预检修
     * 
     * @param overhaulIds 需要删除的预检修主键集合
     * @return 结果
     */
    public int deleteCusPrerepairByOverhaulIds(Long[] overhaulIds);

    /**
     * 删除预检修信息
     * 
     * @param overhaulId 预检修主键
     * @return 结果
     */
    public int deleteCusPrerepairByOverhaulId(Long overhaulId);
}

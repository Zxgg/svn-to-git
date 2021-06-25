package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseBuildingUnit;

/**
 * 楼栋单元 只做记录用，房屋数据直接使用单元号Service接口
 * 
 * @author yzp
 * @date 2020-11-30
 */
public interface IBaseBuildingUnitService 
{
    /**
     * 查询楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param id 楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    public BaseBuildingUnit selectBaseBuildingUnitById(String id);

    /**
     * 查询楼栋单元 只做记录用，房屋数据直接使用单元号列表
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 楼栋单元 只做记录用，房屋数据直接使用单元号集合
     */
    public List<BaseBuildingUnit> selectBaseBuildingUnitList(BaseBuildingUnit baseBuildingUnit);

    /**
     * 新增楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 结果
     */
    public int insertBaseBuildingUnit(BaseBuildingUnit baseBuildingUnit);

    /**
     * 修改楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 结果
     */
    public int updateBaseBuildingUnit(BaseBuildingUnit baseBuildingUnit);

    /**
     * 批量删除楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param ids 需要删除的楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    public int deleteBaseBuildingUnitByIds(String[] ids);
    /**
     * 批量软删除楼栋单元 只做记录用，房屋数据直接使用单元号
     *
     * @param ids 需要删除的楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    public int updateBaseBuildingUnitByIds(String[] ids);

    /**
     * 删除楼栋单元 只做记录用，房屋数据直接使用单元号信息
     * 
     * @param id 楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    public int deleteBaseBuildingUnitById(String id);
}

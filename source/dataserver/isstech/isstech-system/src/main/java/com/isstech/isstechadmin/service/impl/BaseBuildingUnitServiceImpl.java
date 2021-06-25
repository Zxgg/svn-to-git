package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseBuildingUnit;
import com.isstech.isstechadmin.mapper.BaseBuildingUnitMapper;
import com.isstech.isstechadmin.service.IBaseBuildingUnitService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼栋单元 只做记录用，房屋数据直接使用单元号Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Service
public class BaseBuildingUnitServiceImpl implements IBaseBuildingUnitService
{
    @Autowired
    private BaseBuildingUnitMapper baseBuildingUnitMapper;

    /**
     * 查询楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param id 楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @Override
    public BaseBuildingUnit selectBaseBuildingUnitById(String id)
    {
        return baseBuildingUnitMapper.selectBaseBuildingUnitById(id);
    }

    /**
     * 查询楼栋单元 只做记录用，房屋数据直接使用单元号列表
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @Override
    public List<BaseBuildingUnit> selectBaseBuildingUnitList(BaseBuildingUnit baseBuildingUnit)
    {
        return baseBuildingUnitMapper.selectBaseBuildingUnitList(baseBuildingUnit);
    }

    /**
     * 新增楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 结果
     */
    @Override
    public int insertBaseBuildingUnit(BaseBuildingUnit baseBuildingUnit)
    {
        baseBuildingUnit.setCreateTime(DateUtils.getNowDate());
        baseBuildingUnit.setCreateBy(SecurityUtils.getUsername());
        return baseBuildingUnitMapper.insertBaseBuildingUnit(baseBuildingUnit);
    }

    /**
     * 修改楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param baseBuildingUnit 楼栋单元 只做记录用，房屋数据直接使用单元号
     * @return 结果
     */
    @Override
    public int updateBaseBuildingUnit(BaseBuildingUnit baseBuildingUnit)
    {
        baseBuildingUnit.setUpdateTime(DateUtils.getNowDate());
        baseBuildingUnit.setUpdateBy(SecurityUtils.getUsername());
        return baseBuildingUnitMapper.updateBaseBuildingUnit(baseBuildingUnit);
    }

    /**
     * 批量删除楼栋单元 只做记录用，房屋数据直接使用单元号
     * 
     * @param ids 需要删除的楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    @Override
    public int deleteBaseBuildingUnitByIds(String[] ids)
    {
        return baseBuildingUnitMapper.deleteBaseBuildingUnitByIds(ids);
    }

    /**
     * 批量软删除楼栋单元 只做记录用，房屋数据直接使用单元号
     *
     * @param ids 需要删除的楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    @Override
    public int updateBaseBuildingUnitByIds(String[] ids)
    {
        return baseBuildingUnitMapper.updateBaseBuildingUnitByIds(ids);
    }

    /**
     * 删除楼栋单元 只做记录用，房屋数据直接使用单元号信息
     * 
     * @param id 楼栋单元 只做记录用，房屋数据直接使用单元号ID
     * @return 结果
     */
    @Override
    public int deleteBaseBuildingUnitById(String id)
    {
        return baseBuildingUnitMapper.deleteBaseBuildingUnitById(id);
    }
}

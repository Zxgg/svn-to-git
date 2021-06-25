package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseBuildingFloor;
import com.isstech.isstechadmin.mapper.BaseBuildingFloorMapper;
import com.isstech.isstechadmin.service.IBaseBuildingFloorService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼栋楼层 只做记录用，房屋数据直接使用楼层号Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Service
public class BaseBuildingFloorServiceImpl implements IBaseBuildingFloorService
{
    @Autowired
    private BaseBuildingFloorMapper baseBuildingFloorMapper;

    /**
     * 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param id 楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @Override
    public BaseBuildingFloor selectBaseBuildingFloorById(String id)
    {
        return baseBuildingFloorMapper.selectBaseBuildingFloorById(id);
    }

    /**
     * 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号列表
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @Override
    public List<BaseBuildingFloor> selectBaseBuildingFloorList(BaseBuildingFloor baseBuildingFloor)
    {
        return baseBuildingFloorMapper.selectBaseBuildingFloorList(baseBuildingFloor);
    }

    /**
     * 新增楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 结果
     */
    @Override
    public int insertBaseBuildingFloor(BaseBuildingFloor baseBuildingFloor)
    {
        baseBuildingFloor.setCreateTime(DateUtils.getNowDate());
        baseBuildingFloor.setCreateBy(SecurityUtils.getUsername());
        return baseBuildingFloorMapper.insertBaseBuildingFloor(baseBuildingFloor);
    }

    /**
     * 修改楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 结果
     */
    @Override
    public int updateBaseBuildingFloor(BaseBuildingFloor baseBuildingFloor)
    {
        baseBuildingFloor.setUpdateTime(DateUtils.getNowDate());
        baseBuildingFloor.setUpdateBy(SecurityUtils.getUsername());
        return baseBuildingFloorMapper.updateBaseBuildingFloor(baseBuildingFloor);
    }

    /**
     * 批量删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param ids 需要删除的楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 结果
     */
    @Override
    public int deleteBaseBuildingFloorByIds(String[] ids)
    {
        return baseBuildingFloorMapper.deleteBaseBuildingFloorByIds(ids);
    }

    /**
     * 批量软删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     *
     * @param ids 需要删除的楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 结果
     */
    @Override
    public int updateBaseBuildingFloorByIds(String[] ids)
    {
        return baseBuildingFloorMapper.updateBaseBuildingFloorByIds(ids);
    }

    /**
     * 删除楼栋楼层 只做记录用，房屋数据直接使用楼层号信息
     * 
     * @param id 楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 结果
     */
    @Override
    public int deleteBaseBuildingFloorById(String id)
    {
        return baseBuildingFloorMapper.deleteBaseBuildingFloorById(id);
    }
}

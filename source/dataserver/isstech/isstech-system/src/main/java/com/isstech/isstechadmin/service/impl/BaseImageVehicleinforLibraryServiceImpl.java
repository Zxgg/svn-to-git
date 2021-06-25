package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseImageVehicleinforLibraryMapper;
import com.isstech.isstechadmin.domain.BaseImageVehicleinforLibrary;
import com.isstech.isstechadmin.service.IBaseImageVehicleinforLibraryService;

/**
 * 车辆图像信息库Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Service
public class BaseImageVehicleinforLibraryServiceImpl implements IBaseImageVehicleinforLibraryService 
{
    @Autowired
    private BaseImageVehicleinforLibraryMapper baseImageVehicleinforLibraryMapper;

    /**
     * 查询车辆图像信息库
     * 
     * @param id 车辆图像信息库ID
     * @return 车辆图像信息库
     */
    @Override
    public BaseImageVehicleinforLibrary selectBaseImageVehicleinforLibraryById(String id)
    {
        return baseImageVehicleinforLibraryMapper.selectBaseImageVehicleinforLibraryById(id);
    }

    /**
     * 查询车辆图像信息库列表
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 车辆图像信息库
     */
    @Override
    public List<BaseImageVehicleinforLibrary> selectBaseImageVehicleinforLibraryList(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        return baseImageVehicleinforLibraryMapper.selectBaseImageVehicleinforLibraryList(baseImageVehicleinforLibrary);
    }

    /**
     * 新增车辆图像信息库
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 结果
     */
    @Override
    public int insertBaseImageVehicleinforLibrary(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        baseImageVehicleinforLibrary.setId(baseImageVehicleinforLibraryMapper.selectUuid());
        baseImageVehicleinforLibrary.setCreateBy(SecurityUtils.getUsername());
        baseImageVehicleinforLibrary.setCreateTime(DateUtils.getNowDate());
        baseImageVehicleinforLibrary.setDelFlag("0");
        return baseImageVehicleinforLibraryMapper.insertBaseImageVehicleinforLibrary(baseImageVehicleinforLibrary);
    }

    /**
     * 修改车辆图像信息库
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 结果
     */
    @Override
    public int updateBaseImageVehicleinforLibrary(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        baseImageVehicleinforLibrary.setUpdateBy(SecurityUtils.getUsername());
        baseImageVehicleinforLibrary.setUpdateTime(DateUtils.getNowDate());
        return baseImageVehicleinforLibraryMapper.updateBaseImageVehicleinforLibrary(baseImageVehicleinforLibrary);
    }

    /**
     * 批量删除车辆图像信息库
     * 
     * @param ids 需要删除的车辆图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageVehicleinforLibraryByIds(String[] ids)
    {
        return baseImageVehicleinforLibraryMapper.deleteBaseImageVehicleinforLibraryByIds(ids);
    }

    /**
     * 批量软删除车辆图像信息库
     *
     * @param ids 需要删除的车辆图像信息库ID
     * @return 结果
     */
    @Override
    public int updateBaseImageVehicleinforLibraryByIds(String[] ids)
    {
        return baseImageVehicleinforLibraryMapper.updateBaseImageVehicleinforLibraryByIds(ids);
    }

    /**
     * 删除车辆图像信息库信息
     * 
     * @param id 车辆图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageVehicleinforLibraryById(String id)
    {
        return baseImageVehicleinforLibraryMapper.deleteBaseImageVehicleinforLibraryById(id);
    }
}

package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseImageVehicleinforLibrary;

/**
 * 车辆图像信息库Service接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface IBaseImageVehicleinforLibraryService 
{
    /**
     * 查询车辆图像信息库
     * 
     * @param id 车辆图像信息库ID
     * @return 车辆图像信息库
     */
    public BaseImageVehicleinforLibrary selectBaseImageVehicleinforLibraryById(String id);

    /**
     * 查询车辆图像信息库列表
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 车辆图像信息库集合
     */
    public List<BaseImageVehicleinforLibrary> selectBaseImageVehicleinforLibraryList(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary);

    /**
     * 新增车辆图像信息库
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 结果
     */
    public int insertBaseImageVehicleinforLibrary(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary);

    /**
     * 修改车辆图像信息库
     * 
     * @param baseImageVehicleinforLibrary 车辆图像信息库
     * @return 结果
     */
    public int updateBaseImageVehicleinforLibrary(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary);

    /**
     * 批量删除车辆图像信息库
     * 
     * @param ids 需要删除的车辆图像信息库ID
     * @return 结果
     */
    public int deleteBaseImageVehicleinforLibraryByIds(String[] ids);

    /**
     * 删除车辆图像信息库信息
     * 
     * @param id 车辆图像信息库ID
     * @return 结果
     */
    public int deleteBaseImageVehicleinforLibraryById(String id);

    /**
     * 批量软删除车辆图像信息库
     *
     * @param ids 需要删除的车辆图像信息库ID
     * @return 结果
     */
    public int updateBaseImageVehicleinforLibraryByIds(String[] ids);
}

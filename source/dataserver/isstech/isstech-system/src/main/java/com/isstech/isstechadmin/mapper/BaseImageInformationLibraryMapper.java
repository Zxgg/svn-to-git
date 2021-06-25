package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseImageInformationLibrary;

/**
 * 图像信息库Mapper接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface BaseImageInformationLibraryMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询图像信息库
     * 
     * @param id 图像信息库ID
     * @return 图像信息库
     */
    public BaseImageInformationLibrary selectBaseImageInformationLibraryById(String id);

    /**
     * 查询图像信息库列表
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 图像信息库集合
     */
    public List<BaseImageInformationLibrary> selectBaseImageInformationLibraryList(BaseImageInformationLibrary baseImageInformationLibrary);

    /**
     * 新增图像信息库
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 结果
     */
    public int insertBaseImageInformationLibrary(BaseImageInformationLibrary baseImageInformationLibrary);

    /**
     * 修改图像信息库
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 结果
     */
    public int updateBaseImageInformationLibrary(BaseImageInformationLibrary baseImageInformationLibrary);

    /**
     * 删除图像信息库
     * 
     * @param id 图像信息库ID
     * @return 结果
     */
    public int deleteBaseImageInformationLibraryById(String id);

    /**
     * 批量删除图像信息库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseImageInformationLibraryByIds(String[] ids);

    /**
     * 批量软删除图像信息库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseImageInformationLibraryByIds(String[] ids);
}

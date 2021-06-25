package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseImagePerinforLibrary;

/**
 * 人员图像信息库Service接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface IBaseImagePerinforLibraryService 
{
    /**
     * 查询人员图像信息库
     * 
     * @param id 人员图像信息库ID
     * @return 人员图像信息库
     */
    public BaseImagePerinforLibrary selectBaseImagePerinforLibraryById(String id);

    /**
     * 查询人员图像信息库列表
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 人员图像信息库集合
     */
    public List<BaseImagePerinforLibrary> selectBaseImagePerinforLibraryList(BaseImagePerinforLibrary baseImagePerinforLibrary);

    /**
     * 新增人员图像信息库
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 结果
     */
    public int insertBaseImagePerinforLibrary(BaseImagePerinforLibrary baseImagePerinforLibrary);

    /**
     * 修改人员图像信息库
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 结果
     */
    public int updateBaseImagePerinforLibrary(BaseImagePerinforLibrary baseImagePerinforLibrary);

    /**
     * 批量删除人员图像信息库
     * 
     * @param ids 需要删除的人员图像信息库ID
     * @return 结果
     */
    public int deleteBaseImagePerinforLibraryByIds(String[] ids);

    /**
     * 删除人员图像信息库信息
     * 
     * @param id 人员图像信息库ID
     * @return 结果
     */
    public int deleteBaseImagePerinforLibraryById(String id);

    /**
     * 批量软删除人员图像信息库
     *
     * @param ids 需要删除的人员图像信息库ID
     * @return 结果
     */
    public int updateBaseImagePerinforLibraryByIds(String[] ids);
}

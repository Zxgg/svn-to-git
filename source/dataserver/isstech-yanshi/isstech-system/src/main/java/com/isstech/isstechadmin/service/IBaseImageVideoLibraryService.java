package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseImageVideoLibrary;

/**
 * 视频信息库Service接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface IBaseImageVideoLibraryService 
{
    /**
     * 查询视频信息库
     * 
     * @param id 视频信息库ID
     * @return 视频信息库
     */
    public BaseImageVideoLibrary selectBaseImageVideoLibraryById(String id);

    /**
     * 查询视频信息库列表
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 视频信息库集合
     */
    public List<BaseImageVideoLibrary> selectBaseImageVideoLibraryList(BaseImageVideoLibrary baseImageVideoLibrary);

    /**
     * 新增视频信息库
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 结果
     */
    public int insertBaseImageVideoLibrary(BaseImageVideoLibrary baseImageVideoLibrary);

    /**
     * 修改视频信息库
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 结果
     */
    public int updateBaseImageVideoLibrary(BaseImageVideoLibrary baseImageVideoLibrary);

    /**
     * 批量删除视频信息库
     * 
     * @param ids 需要删除的视频信息库ID
     * @return 结果
     */
    public int deleteBaseImageVideoLibraryByIds(String[] ids);

    /**
     * 删除视频信息库信息
     * 
     * @param id 视频信息库ID
     * @return 结果
     */
    public int deleteBaseImageVideoLibraryById(String id);

    /**
     * 批量软删除视频信息库
     *
     * @param ids 需要删除的视频信息库ID
     * @return 结果
     */
    public int updateBaseImageVideoLibraryByIds(String[] ids);
}

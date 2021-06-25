package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseImageVideoLibraryMapper;
import com.isstech.isstechadmin.domain.BaseImageVideoLibrary;
import com.isstech.isstechadmin.service.IBaseImageVideoLibraryService;

/**
 * 视频信息库Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Service
public class BaseImageVideoLibraryServiceImpl implements IBaseImageVideoLibraryService 
{
    @Autowired
    private BaseImageVideoLibraryMapper baseImageVideoLibraryMapper;

    /**
     * 查询视频信息库
     * 
     * @param id 视频信息库ID
     * @return 视频信息库
     */
    @Override
    public BaseImageVideoLibrary selectBaseImageVideoLibraryById(String id)
    {
        return baseImageVideoLibraryMapper.selectBaseImageVideoLibraryById(id);
    }

    /**
     * 查询视频信息库列表
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 视频信息库
     */
    @Override
    public List<BaseImageVideoLibrary> selectBaseImageVideoLibraryList(BaseImageVideoLibrary baseImageVideoLibrary)
    {
        return baseImageVideoLibraryMapper.selectBaseImageVideoLibraryList(baseImageVideoLibrary);
    }

    /**
     * 新增视频信息库
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 结果
     */
    @Override
    public int insertBaseImageVideoLibrary(BaseImageVideoLibrary baseImageVideoLibrary)
    {
        baseImageVideoLibrary.setId(baseImageVideoLibraryMapper.selectUuid());
        baseImageVideoLibrary.setCreateBy(SecurityUtils.getUsername());
        baseImageVideoLibrary.setCreateTime(DateUtils.getNowDate());
        baseImageVideoLibrary.setDelFlag("0");
        return baseImageVideoLibraryMapper.insertBaseImageVideoLibrary(baseImageVideoLibrary);
    }

    /**
     * 修改视频信息库
     * 
     * @param baseImageVideoLibrary 视频信息库
     * @return 结果
     */
    @Override
    public int updateBaseImageVideoLibrary(BaseImageVideoLibrary baseImageVideoLibrary)
    {
        baseImageVideoLibrary.setUpdateBy(SecurityUtils.getUsername());
        baseImageVideoLibrary.setUpdateTime(DateUtils.getNowDate());
        return baseImageVideoLibraryMapper.updateBaseImageVideoLibrary(baseImageVideoLibrary);
    }

    /**
     * 批量删除视频信息库
     * 
     * @param ids 需要删除的视频信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageVideoLibraryByIds(String[] ids)
    {
        return baseImageVideoLibraryMapper.deleteBaseImageVideoLibraryByIds(ids);
    }

    /**
     * 批量软删除视频信息库
     *
     * @param ids 需要删除的视频信息库ID
     * @return 结果
     */
    @Override
    public int updateBaseImageVideoLibraryByIds(String[] ids)
    {
        return baseImageVideoLibraryMapper.updateBaseImageVideoLibraryByIds(ids);
    }

    /**
     * 删除视频信息库信息
     * 
     * @param id 视频信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageVideoLibraryById(String id)
    {
        return baseImageVideoLibraryMapper.deleteBaseImageVideoLibraryById(id);
    }
}

package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseImageInformationLibraryMapper;
import com.isstech.isstechadmin.domain.BaseImageInformationLibrary;
import com.isstech.isstechadmin.service.IBaseImageInformationLibraryService;

/**
 * 图像信息库Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Service
public class BaseImageInformationLibraryServiceImpl implements IBaseImageInformationLibraryService 
{
    @Autowired
    private BaseImageInformationLibraryMapper baseImageInformationLibraryMapper;

    /**
     * 查询图像信息库
     * 
     * @param id 图像信息库ID
     * @return 图像信息库
     */
    @Override
    public BaseImageInformationLibrary selectBaseImageInformationLibraryById(String id)
    {
        return baseImageInformationLibraryMapper.selectBaseImageInformationLibraryById(id);
    }

    /**
     * 查询图像信息库列表
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 图像信息库
     */
    @Override
    public List<BaseImageInformationLibrary> selectBaseImageInformationLibraryList(BaseImageInformationLibrary baseImageInformationLibrary)
    {
        return baseImageInformationLibraryMapper.selectBaseImageInformationLibraryList(baseImageInformationLibrary);
    }

    /**
     * 新增图像信息库
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 结果
     */
    @Override
    public int insertBaseImageInformationLibrary(BaseImageInformationLibrary baseImageInformationLibrary)
    {
        baseImageInformationLibrary.setId(baseImageInformationLibraryMapper.selectUuid());
        baseImageInformationLibrary.setCreateBy(SecurityUtils.getUsername());
        baseImageInformationLibrary.setCreateTime(DateUtils.getNowDate());
        baseImageInformationLibrary.setDelFlag("0");
        return baseImageInformationLibraryMapper.insertBaseImageInformationLibrary(baseImageInformationLibrary);
    }

    /**
     * 修改图像信息库
     * 
     * @param baseImageInformationLibrary 图像信息库
     * @return 结果
     */
    @Override
    public int updateBaseImageInformationLibrary(BaseImageInformationLibrary baseImageInformationLibrary)
    {
        baseImageInformationLibrary.setUpdateBy(SecurityUtils.getUsername());
        baseImageInformationLibrary.setUpdateTime(DateUtils.getNowDate());
        return baseImageInformationLibraryMapper.updateBaseImageInformationLibrary(baseImageInformationLibrary);
    }

    /**
     * 批量删除图像信息库
     * 
     * @param ids 需要删除的图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageInformationLibraryByIds(String[] ids)
    {
        return baseImageInformationLibraryMapper.deleteBaseImageInformationLibraryByIds(ids);
    }

    /**
     * 批量软删除图像信息库
     *
     * @param ids 需要删除的图像信息库ID
     * @return 结果
     */
    @Override
    public int updateBaseImageInformationLibraryByIds(String[] ids)
    {
        return baseImageInformationLibraryMapper.updateBaseImageInformationLibraryByIds(ids);
    }

    /**
     * 删除图像信息库信息
     * 
     * @param id 图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImageInformationLibraryById(String id)
    {
        return baseImageInformationLibraryMapper.deleteBaseImageInformationLibraryById(id);
    }
}

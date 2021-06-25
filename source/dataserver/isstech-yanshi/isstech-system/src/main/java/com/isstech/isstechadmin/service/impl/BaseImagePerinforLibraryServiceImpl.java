package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseImagePerinforLibraryMapper;
import com.isstech.isstechadmin.domain.BaseImagePerinforLibrary;
import com.isstech.isstechadmin.service.IBaseImagePerinforLibraryService;

/**
 * 人员图像信息库Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Service
public class BaseImagePerinforLibraryServiceImpl implements IBaseImagePerinforLibraryService 
{
    @Autowired
    private BaseImagePerinforLibraryMapper baseImagePerinforLibraryMapper;

    /**
     * 查询人员图像信息库
     * 
     * @param id 人员图像信息库ID
     * @return 人员图像信息库
     */
    @Override
    public BaseImagePerinforLibrary selectBaseImagePerinforLibraryById(String id)
    {
        return baseImagePerinforLibraryMapper.selectBaseImagePerinforLibraryById(id);
    }

    /**
     * 查询人员图像信息库列表
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 人员图像信息库
     */
    @Override
    public List<BaseImagePerinforLibrary> selectBaseImagePerinforLibraryList(BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        return baseImagePerinforLibraryMapper.selectBaseImagePerinforLibraryList(baseImagePerinforLibrary);
    }

    /**
     * 新增人员图像信息库
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 结果
     */
    @Override
    public int insertBaseImagePerinforLibrary(BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        baseImagePerinforLibrary.setId(baseImagePerinforLibraryMapper.selectUuid());
        baseImagePerinforLibrary.setCreateBy(SecurityUtils.getUsername());
        baseImagePerinforLibrary.setCreateTime(DateUtils.getNowDate());
        baseImagePerinforLibrary.setDelFlag("0");
        return baseImagePerinforLibraryMapper.insertBaseImagePerinforLibrary(baseImagePerinforLibrary);
    }

    /**
     * 修改人员图像信息库
     * 
     * @param baseImagePerinforLibrary 人员图像信息库
     * @return 结果
     */
    @Override
    public int updateBaseImagePerinforLibrary(BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        baseImagePerinforLibrary.setUpdateBy(SecurityUtils.getUsername());
        baseImagePerinforLibrary.setUpdateTime(DateUtils.getNowDate());
        return baseImagePerinforLibraryMapper.updateBaseImagePerinforLibrary(baseImagePerinforLibrary);
    }

    /**
     * 批量删除人员图像信息库
     * 
     * @param ids 需要删除的人员图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImagePerinforLibraryByIds(String[] ids)
    {
        return baseImagePerinforLibraryMapper.deleteBaseImagePerinforLibraryByIds(ids);
    }

    /**
     * 批量软删除人员图像信息库
     *
     * @param ids 需要删除的人员图像信息库ID
     * @return 结果
     */
    @Override
    public int updateBaseImagePerinforLibraryByIds(String[] ids)
    {
        return baseImagePerinforLibraryMapper.updateBaseImagePerinforLibraryByIds(ids);
    }

    /**
     * 删除人员图像信息库信息
     * 
     * @param id 人员图像信息库ID
     * @return 结果
     */
    @Override
    public int deleteBaseImagePerinforLibraryById(String id)
    {
        return baseImagePerinforLibraryMapper.deleteBaseImagePerinforLibraryById(id);
    }
}

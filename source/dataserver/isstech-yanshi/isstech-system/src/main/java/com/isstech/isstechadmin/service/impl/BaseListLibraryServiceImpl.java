package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseListLibraryMapper;
import com.isstech.isstechadmin.domain.BaseListLibrary;
import com.isstech.isstechadmin.service.IBaseListLibraryService;

/**
 * 名单库Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-05
 */
@Service
public class BaseListLibraryServiceImpl implements IBaseListLibraryService 
{
    @Autowired
    private BaseListLibraryMapper baseListLibraryMapper;

    /**
     * 查询名单库
     * 
     * @param id 名单库ID
     * @return 名单库
     */
    @Override
    public BaseListLibrary selectBaseListLibraryById(String id)
    {
        return baseListLibraryMapper.selectBaseListLibraryById(id);
    }

    /**
     * 查询名单库列表
     * 
     * @param baseListLibrary 名单库
     * @return 名单库
     */
    @Override
    public List<BaseListLibrary> selectBaseListLibraryList(BaseListLibrary baseListLibrary)
    {
        return baseListLibraryMapper.selectBaseListLibraryList(baseListLibrary);
    }

    /**
     * 新增名单库
     * 
     * @param baseListLibrary 名单库
     * @return 结果
     */
    @Override
    public int insertBaseListLibrary(BaseListLibrary baseListLibrary)
    {
        baseListLibrary.setCreateTime(DateUtils.getNowDate());
        baseListLibrary.setCreateBy(SecurityUtils.getUsername());
        baseListLibrary.setDelFlag("0");
        return baseListLibraryMapper.insertBaseListLibrary(baseListLibrary);
    }

    /**
     * 修改名单库
     * 
     * @param baseListLibrary 名单库
     * @return 结果
     */
    @Override
    public int updateBaseListLibrary(BaseListLibrary baseListLibrary)
    {
        baseListLibrary.setUpdateTime(DateUtils.getNowDate());
        baseListLibrary.setUpdateBy(SecurityUtils.getUsername());
        return baseListLibraryMapper.updateBaseListLibrary(baseListLibrary);
    }

    /**
     * 批量删除名单库
     * 
     * @param ids 需要删除的名单库ID
     * @return 结果
     */
    @Override
    public int deleteBaseListLibraryByIds(String[] ids)
    {
        return baseListLibraryMapper.deleteBaseListLibraryByIds(ids);
    }

    /**
     * 批量软删除名单库
     *
     * @param ids 需要删除的名单库ID
     * @return 结果
     */
    @Override
    public int updateBaseListLibraryByIds(String[] ids)
    {
        return baseListLibraryMapper.updateBaseListLibraryByIds(ids);
    }

    /**
     * 删除名单库信息
     * 
     * @param id 名单库ID
     * @return 结果
     */
    @Override
    public int deleteBaseListLibraryById(String id)
    {
        return baseListLibraryMapper.deleteBaseListLibraryById(id);
    }
}

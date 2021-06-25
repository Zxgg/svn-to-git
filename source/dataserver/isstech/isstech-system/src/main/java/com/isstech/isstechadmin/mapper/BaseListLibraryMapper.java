package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseListLibrary;

/**
 * 名单库Mapper接口
 * 
 * @author yzp
 * @date 2021-01-05
 */
public interface BaseListLibraryMapper 
{
    /**
     * 查询名单库
     * 
     * @param id 名单库ID
     * @return 名单库
     */
    public BaseListLibrary selectBaseListLibraryById(String id);

    /**
     * 查询名单库列表
     * 
     * @param baseListLibrary 名单库
     * @return 名单库集合
     */
    public List<BaseListLibrary> selectBaseListLibraryList(BaseListLibrary baseListLibrary);

    /**
     * 新增名单库
     * 
     * @param baseListLibrary 名单库
     * @return 结果
     */
    public int insertBaseListLibrary(BaseListLibrary baseListLibrary);

    /**
     * 修改名单库
     * 
     * @param baseListLibrary 名单库
     * @return 结果
     */
    public int updateBaseListLibrary(BaseListLibrary baseListLibrary);

    /**
     * 删除名单库
     * 
     * @param id 名单库ID
     * @return 结果
     */
    public int deleteBaseListLibraryById(String id);

    /**
     * 批量删除名单库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseListLibraryByIds(String[] ids);

    /**
     * 批量软删除名单库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseListLibraryByIds(String[] ids);
}

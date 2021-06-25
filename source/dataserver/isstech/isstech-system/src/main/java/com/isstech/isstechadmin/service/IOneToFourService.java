package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.OneToFour;

import java.util.List;

/**
 * 1+4 记录 Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IOneToFourService 
{
    /**
     * 查询1+4 记录 
     * 
     * @param id 1+4 记录 ID
     * @return 1+4 记录 
     */
    public OneToFour selectOneToFourById(String id);

    /**
     * 查询1+4 记录 列表
     * 
     * @param oneToFour 1+4 记录
     * @return 1+4 记录 集合
     */
    public List<OneToFour> selectOneToFourList(OneToFour oneToFour);

    /**
     * 新增1+4 记录 
     * 
     * @param oneToFour 1+4 记录
     * @return 结果
     */
    public int insertOneToFour(OneToFour oneToFour);

    /**
     * 新增多条1+4 记录
     *
     * @param oneToFourList 1+4 记录
     * @return 结果
     */
    public int insertOneToFourList(List<OneToFour> oneToFourList);

    /**
     * 修改1+4 记录 
     * 
     * @param oneToFour 1+4 记录
     * @return 结果
     */
    public int updateOneToFour(OneToFour oneToFour);

    /**
     * 批量删除1+4 记录 
     * 
     * @param ids 需要删除的1+4 记录 ID
     * @return 结果
     */
    public int deleteOneToFourByIds(String[] ids);

    /**
     * 批量软删除1+4 记录
     *
     * @param ids 需要删除的1+4 记录 ID
     * @return 结果
     */
    public int updateOneToFourByIds(String[] ids);

    /**
     * 删除1+4 记录 信息
     * 
     * @param id 1+4 记录 ID
     * @return 结果
     */
    public int deleteOneToFourById(String id);


}

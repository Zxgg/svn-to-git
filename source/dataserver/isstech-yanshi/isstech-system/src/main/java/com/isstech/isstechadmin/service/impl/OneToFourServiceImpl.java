package com.isstech.isstechadmin.service.impl;

import com.isstech.isstechadmin.domain.OneToFour;
import com.isstech.isstechadmin.mapper.OneToFourMapper;
import com.isstech.isstechadmin.service.IOneToFourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1+4 记录 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class OneToFourServiceImpl implements IOneToFourService
{
    @Autowired
    private OneToFourMapper oneToFourMapper;

    /**
     * 查询1+4 记录 
     * 
     * @param id 1+4 记录 ID
     * @return 1+4 记录 
     */
    @Override
    public OneToFour selectOneToFourById(String id)
    {
        return oneToFourMapper.selectOneToFourById(id);
    }

    /**
     * 查询1+4 记录 列表
     * 
     * @param OneToFour 1+4 记录 
     * @return 1+4 记录 
     */
    @Override
    public List<OneToFour> selectOneToFourList(OneToFour OneToFour)
    {
        return oneToFourMapper.selectOneToFourList(OneToFour);
    }

    /**
     * 新增1+4 记录 
     * 
     * @param oneToFour 1+4 记录
     * @return 结果
     */
    @Override
    public int insertOneToFour(OneToFour oneToFour)
    {
        oneToFour.setDelFlag("0");
        return oneToFourMapper.insertOneToFour(oneToFour);
    }

    @Override
    public int insertOneToFourList(List<OneToFour> oneToFourList) {
        return oneToFourMapper.insertOneToFourList(oneToFourList);
    }

    /**
     * 修改1+4 记录 
     * 
     * @param oneToFour 1+4 记录
     * @return 结果
     */
    @Override
    public int updateOneToFour(OneToFour oneToFour)
    {
        return oneToFourMapper.updateOneToFour(oneToFour);
    }

    /**
     * 批量删除1+4 记录 
     * 
     * @param ids 需要删除的1+4 记录 ID
     * @return 结果
     */
    @Override
    public int deleteOneToFourByIds(String[] ids)
    {
        return oneToFourMapper.deleteOneToFourByIds(ids);
    }

    /**
     * 批量软删除1+4 记录
     *
     * @param ids 需要删除的1+4 记录 ID
     * @return 结果
     */
    @Override
    public int updateOneToFourByIds(String[] ids)
    {
        return oneToFourMapper.updateOneToFourByIds(ids);
    }

    /**
     * 删除1+4 记录 信息
     * 
     * @param id 1+4 记录 ID
     * @return 结果
     */
    @Override
    public int deleteOneToFourById(String id)
    {
        return oneToFourMapper.deleteOneToFourById(id);
    }

}

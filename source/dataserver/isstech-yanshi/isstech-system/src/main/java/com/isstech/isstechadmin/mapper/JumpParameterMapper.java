package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.JumpParameter;

/**
 * 轨迹跳转参数Mapper接口
 * 
 * @author yzp
 * @date 2021-01-19
 */
public interface JumpParameterMapper 
{
    /**
     * 查询轨迹跳转参数
     * 
     * @param id 轨迹跳转参数ID
     * @return 轨迹跳转参数
     */
    public JumpParameter selectJumpParameterById(String id);

    /**
     * 查询轨迹跳转参数列表
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 轨迹跳转参数集合
     */
    public List<JumpParameter> selectJumpParameterList(JumpParameter jumpParameter);

    /**
     * 新增轨迹跳转参数
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 结果
     */
    public int insertJumpParameter(JumpParameter jumpParameter);

    /**
     * 修改轨迹跳转参数
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 结果
     */
    public int updateJumpParameter(JumpParameter jumpParameter);

    /**
     * 删除轨迹跳转参数
     * 
     * @param id 轨迹跳转参数ID
     * @return 结果
     */
    public int deleteJumpParameterById(String id);

    /**
     * 批量删除轨迹跳转参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJumpParameterByIds(String[] ids);

}

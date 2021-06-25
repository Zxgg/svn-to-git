package com.isstech.isstechadmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.JumpParameterMapper;
import com.isstech.isstechadmin.domain.JumpParameter;
import com.isstech.isstechadmin.service.IJumpParameterService;

/**
 * 轨迹跳转参数Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-19
 */
@Service
public class JumpParameterServiceImpl implements IJumpParameterService 
{
    @Autowired
    private JumpParameterMapper jumpParameterMapper;

    /**
     * 查询轨迹跳转参数
     * 
     * @param id 轨迹跳转参数ID
     * @return 轨迹跳转参数
     */
    @Override
    public JumpParameter selectJumpParameterById(String id)
    {
        return jumpParameterMapper.selectJumpParameterById(id);
    }

    /**
     * 查询轨迹跳转参数列表
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 轨迹跳转参数
     */
    @Override
    public List<JumpParameter> selectJumpParameterList(JumpParameter jumpParameter)
    {
        return jumpParameterMapper.selectJumpParameterList(jumpParameter);
    }

    /**
     * 新增轨迹跳转参数
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 结果
     */
    @Override
    public int insertJumpParameter(JumpParameter jumpParameter)
    {
        return jumpParameterMapper.insertJumpParameter(jumpParameter);
    }

    /**
     * 修改轨迹跳转参数
     * 
     * @param jumpParameter 轨迹跳转参数
     * @return 结果
     */
    @Override
    public int updateJumpParameter(JumpParameter jumpParameter)
    {
        return jumpParameterMapper.updateJumpParameter(jumpParameter);
    }

    /**
     * 批量删除轨迹跳转参数
     * 
     * @param ids 需要删除的轨迹跳转参数ID
     * @return 结果
     */
    @Override
    public int deleteJumpParameterByIds(String[] ids)
    {
        return jumpParameterMapper.deleteJumpParameterByIds(ids);
    }

    /**
     * 删除轨迹跳转参数信息
     * 
     * @param id 轨迹跳转参数ID
     * @return 结果
     */
    @Override
    public int deleteJumpParameterById(String id)
    {
        return jumpParameterMapper.deleteJumpParameterById(id);
    }
}

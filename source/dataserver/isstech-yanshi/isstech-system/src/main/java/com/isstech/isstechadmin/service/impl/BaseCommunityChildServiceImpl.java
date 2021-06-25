package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseCommunityChild;
import com.isstech.isstechadmin.domain.BaseDeptCommunityChild;
import com.isstech.isstechadmin.mapper.BaseCommunityChildMapper;
import com.isstech.isstechadmin.service.IBaseCommunityChildService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import com.isstech.isstechadmin.utils.GetSqlUtils;
import com.isstech.isstechadmin.utils.mapper.DataSynchronizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小区信息  Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Service
public class BaseCommunityChildServiceImpl implements IBaseCommunityChildService
{
    @Autowired
    private BaseCommunityChildMapper baseCommunityChildMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;
    @Autowired
    private GetSqlUtils getSqlUtils;
    @Autowired
    private DataSynchronizationMapper dataSynchronizationMapper;

    /**
     * 查询小区信息  
     * 
     * @param id 小区信息  ID
     * @return 小区信息  
     */
    @Override
    public BaseCommunityChild selectBaseCommunityChildById(String id)
    {
        return baseCommunityChildMapper.selectBaseCommunityChildById(id);
    }

    /**
     * 查询小区信息  列表
     * 
     * @param baseCommunityChild 小区信息  
     * @return 小区信息  
     */
    @Override
    public List<BaseCommunityChild> selectBaseCommunityChildList(BaseCommunityChild baseCommunityChild)
    {
        return baseCommunityChildMapper.selectBaseCommunityChildList(baseCommunityChild);
    }

    /**
     * 新增小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    @Override
    public int insertBaseCommunityChild(BaseCommunityChild baseCommunityChild)
    {
        baseCommunityChild.setCreateTime(DateUtils.getNowDate());
        baseCommunityChild.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityChildMapper.selectUuid();
        baseCommunityChild.setId(uuid);
        int row = baseCommunityChildMapper.insertBaseCommunityChild(baseCommunityChild);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunityChild,"insertBaseCommunityChild"));
        checkExist(baseCommunityChild);
        return row;
    }

    private void checkExist(BaseCommunityChild baseCommunityChild){
        baseCommunityChildMapper.deleteBaseDeptCommunityChildById(baseCommunityChild.getId());
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunityChild.getId(),"deleteBaseDeptCommunityChildById"));
        String deptId = baseCommunityChild.getDeptId();
        if(StringUtils.isEmpty(deptId)||StringUtils.isEmpty(baseCommunityChild.getCommunityId())){
            return;
        }
        BaseDeptCommunityChild baseDeptCommunityChild = new BaseDeptCommunityChild();
        baseDeptCommunityChild.setCommunityChildId(baseCommunityChild.getId());
        baseDeptCommunityChild.setCommunityId(baseCommunityChild.getCommunityId());
        baseDeptCommunityChild.setDeptIp(deptId);
        baseCommunityChildMapper.insertBaseDeptCommunityChild(baseDeptCommunityChild);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseDeptCommunityChild,"insertBaseDeptCommunityChild"));
    }

    /**
     * 修改小区信息  
     * 
     * @param baseCommunityChild 小区信息  
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChild(BaseCommunityChild baseCommunityChild)
    {
        baseCommunityChild.setUpdateTime(DateUtils.getNowDate());
        baseCommunityChild.setUpdateBy(SecurityUtils.getUsername());
        int row = baseCommunityChildMapper.updateBaseCommunityChild(baseCommunityChild);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(baseCommunityChild,"updateBaseCommunityChild"));
        checkExist(baseCommunityChild);
        return row;
    }

    /**
     * 批量删除小区信息  
     * 
     * @param ids 需要删除的小区信息  ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildByIds(String[] ids)
    {
        int row = baseCommunityChildMapper.deleteBaseCommunityChildByIds(ids);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(ids,"deleteBaseCommunityChildByIds"));
        baseCommunityChildMapper.deleteBaseDeptCommunityChildByIds(ids);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(ids,"deleteBaseDeptCommunityChildByIds"));
        return row;
    }

    /**
     * 批量软删除小区信息
     *
     * @param ids 需要删除的小区信息  ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityChildByIds(String[] ids)
    {
        int row = baseCommunityChildMapper.updateBaseCommunityChildByIds(ids);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(ids,"updateBaseCommunityChildByIds"));
        return row;
    }

    /**
     * 删除小区信息  信息
     * 
     * @param id 小区信息  ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityChildById(String id)
    {
        int row = baseCommunityChildMapper.deleteBaseCommunityChildById(id);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(id,"deleteBaseCommunityChildById"));
        baseCommunityChildMapper.deleteBaseDeptCommunityChildById(id);
        dataSynchronizationUtils.dataSaveUp(dataSynchronizationMapper.getDeptId(),getSqlUtils.getSql(id,"deleteBaseDeptCommunityChildById"));
        return row;
    }
}

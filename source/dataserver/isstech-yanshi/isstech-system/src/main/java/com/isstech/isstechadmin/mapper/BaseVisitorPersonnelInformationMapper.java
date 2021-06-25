package com.isstech.isstechadmin.mapper;


import com.isstech.isstechadmin.domain.BaseVisitorPersonnelInformation;
import com.isstech.isstechadmin.domain.vo.Community;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 访客人员信息Mapper接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface BaseVisitorPersonnelInformationMapper 
{
    /**
     * 查询访客人员信息
     * 
     * @param id 访客人员信息ID
     * @return 访客人员信息
     */
    public BaseVisitorPersonnelInformation selectBaseVisitorPersonnelInformationById(String id);

    /**
     * 查询访客人员信息列表
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 访客人员信息集合
     */
    public List<BaseVisitorPersonnelInformation> selectBaseVisitorPersonnelInformationList(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation);

    /**
     * 新增访客人员信息
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 结果
     */
    public int insertBaseVisitorPersonnelInformation(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation);

    /**
     * 修改访客人员信息
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 结果
     */
    public int updateBaseVisitorPersonnelInformation(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation);

    /**
     * 删除访客人员信息
     * 
     * @param id 访客人员信息ID
     * @return 结果
     */
    public int deleteBaseVisitorPersonnelInformationById(String id);

    /**
     * 批量删除访客人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVisitorPersonnelInformationByIds(String[] ids);
    /**
     * 批量软删除访客人员信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVisitorPersonnelInformationByIds(String[] ids);

    /**
     * 根据身份证号查询访客人员id
     * @param credentialNumber
     * @return
     */
    @Select("SELECT id as personId,target_person_community_id communityId,target_person_community_child_id communityChildId FROM base_visitor_personnel_information WHERE identification_number=#{credentialNumber} order by create_time desc LIMIT 1  ")
    public Community selectPersonIdByIdentificationNumber(String credentialNumber);
}

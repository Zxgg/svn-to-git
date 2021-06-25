package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseTempVisitorPersonnelInformation;

/**
 * 临时访客人员信息Service接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface IBaseTempVisitorPersonnelInformationService 
{
    /**
     * 查询临时访客人员信息
     * 
     * @param id 临时访客人员信息ID
     * @return 临时访客人员信息
     */
    public BaseTempVisitorPersonnelInformation selectBaseTempVisitorPersonnelInformationById(String id);

    /**
     * 查询临时访客人员信息列表
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 临时访客人员信息集合
     */
    public List<BaseTempVisitorPersonnelInformation> selectBaseTempVisitorPersonnelInformationList(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation);

    /**
     * 新增临时访客人员信息
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 结果
     */
    public int insertBaseTempVisitorPersonnelInformation(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation);

    /**
     * 修改临时访客人员信息
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 结果
     */
    public int updateBaseTempVisitorPersonnelInformation(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation);

    /**
     * 批量删除临时访客人员信息
     * 
     * @param ids 需要删除的临时访客人员信息ID
     * @return 结果
     */
    public int deleteBaseTempVisitorPersonnelInformationByIds(String[] ids);
    /**
     * 批量软删除临时访客人员信息
     *
     * @param ids 需要删除的临时访客人员信息ID
     * @return 结果
     */
    public int updateBaseTempVisitorPersonnelInformationByIds(String[] ids);

    /**
     * 删除临时访客人员信息信息
     * 
     * @param id 临时访客人员信息ID
     * @return 结果
     */
    public int deleteBaseTempVisitorPersonnelInformationById(String id);

    /**
     * 根据身份证号查询临时访客人员id
     * @param credentialNumber
     * @return
     */
    public String selectPersonIdByIdentificationNumber(String credentialNumber);
}

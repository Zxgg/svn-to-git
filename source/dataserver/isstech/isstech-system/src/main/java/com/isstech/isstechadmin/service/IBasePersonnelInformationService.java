package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.vo.*;

import java.util.List;

/**
 * 人员信息Service接口
 * 
 * @author yzp
 * @date 2020-11-23
 */
public interface IBasePersonnelInformationService 
{
    /**
     * 查询人员信息
     * @param id 人员信息ID
     * @return 人员信息
     */
    public BasePersonnelInformation selectBasePersonnelInformationById(String id);

    /**
     * 根据（人员名称）查询人员信息和人员所在社区、楼层、房间
     * @param name 人员名称
     * @return 人员信息
     */
    public List<PersonHousesVo> selectBasePersonnelInformationByName(String name,String communityId,String communityChildId);

    /**
     * 查询人员信息列表
     * @param basePersonnelInformation 人员信息
     * @return 人员信息集合
     */
    public List<BasePersonnelInformation> selectBasePersonnelInformationList(BasePersonnelInformation basePersonnelInformation);


    /**
     * 新增人员信息
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    public String insertBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation);

    /**
     * 修改人员信息
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    public int updateBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation);

    /**
     * 批量删除人员信息
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    public int deleteBasePersonnelInformationByIds(String[] ids);
    /**
     * 批量软删除人员信息
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    public int updateBasePersonnelInformationByIds(String[] ids);

    /**
     * 删除人员信息信息
     * @param id 人员信息ID
     * @return 结果
     */
    public int deleteBasePersonnelInformationById(String id);

    /**
     * 根据房屋id查询人员信息
     * @param houseId
     * @return
     */
    public List<PersonHousesVo> selectPerInformByHousesId(String houseId);


    /**
     * 根据车辆信息查询关联的人员信息
     * @param information
     * @return 人员信息
     * @author qishuwei
     * @date 2020-12-22
     */
    public List<BasePersonnelInformation> getByVehicleInfo(VehicleInformationVo information);

    /**
     *  根据身份证号查询用户id
     * @param credentialNumber
     * @return id
     */
    public Community selectPersonIdByIdentificationNumber(String credentialNumber);

    public int removeSpecial(String[] ids);

    /**
     * 根据社区id和小区id获取人员总条数
     * @param queryVo
     * @return
     */
    public int queryCount(QueryVo queryVo);

    public List<BasePersonnelInformation> queryBasePerson(QueryVo queryVo);


    /**
     * 根据网格id汇总人员信息
     * @param gridId
     * @return 网格id
     */
    public PersonDetailVo sumByGrid(String gridId);

    /**
     * 演示5月10号,对象特异性转化
     * @param personDetailVo
     * @return
     */
    public PersonInfVo transform(PersonDetailVo personDetailVo);
}

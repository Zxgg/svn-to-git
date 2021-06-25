package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.vo.Community;
import com.isstech.isstechadmin.domain.vo.PersonHousesVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 人员信息Mapper接口
 * 
 * @author yzp
 * @date 2020-11-23
 */
public interface BasePersonnelInformationMapper 
{
    /**
     * 查询人员信息
     * 
     * @param id 人员信息ID
     * @return 人员信息
     */
    public BasePersonnelInformation selectBasePersonnelInformationById(String id);

    /**
     * 查询UUID
     *
     * @return uuid
     */
    public String selectUuid();

    /**
     * 根据（人员名称）查询人员信息和人员所在社区、楼层、房间
     *
     * @param name 人员名称
     * @return 人员信息
     */
    @Select("<script>"+
            "SELECT\n" +
            "        bpi.id ,bpi.name ,bpi.old_name oldName,bpi.sex,bpi.nationality,bpi.id_type idType,bpi.identification_number identificationNumber,bpi.idcard_start_date idcardStartDate,bpi.idcard_end_date idcardEndDate,\n" +
            "        bpi.nation,bpi.location,bpi.residence_detail residenceDetail,bpi.address,bpi.address_detail addressDetail,bpi.marital,bpi.political,bpi.degree,bpi.religion,bpi.profession,\n" +
            "        bch.id as houseId,bch.name as houseName, bch.community_id communityId,bch.community_child_id communityChildId,bch.community_building_id communityBuildingId,bch.serial_number serialNumber,bch.building_name buildingName,bch.unit,bch.floor,\n" +
            "        bch.house_number houseNumber,bch.phone,bch.house_structure houseStructure,bch.area,bch.construction_time constructionTime,bch.house_property_right housePropertyRight,bch.nature_of_use natureOfUse,bch.rent,\n" +
            "        bch.rental_purpose rentalPurpose,bch.hazard_type hazardType,bch.longitud,bch.latitude,bch.pics,bch.elevator\n" +
            "    FROM\n" +
            "        base_personnel_information bpi,\n" +
            "        base_community_houses bch,\n" +
            "        base_community_houses_personnel bchp \n" +
            "    where bchp.community_houses_id = bch.id\n" +
            "        AND bpi.id = bchp.personnel_id\n" +
            "        AND bpi.NAME LIKE concat('%', #{name}, '%')\n" +
            "        <if test='communityId !=null '>" +
            "           and bch.community_id = #{communityId}" +
            "        </if>" +
            "        <if test='communityChildId !=null '>" +
            "           and bch.community_child_id = #{communityChildId}" +
            "        </if>" +
            "</script>")
    public List<PersonHousesVo> selectBasePersonnelInformationByName(@Param("name") String name ,@Param("communityId") String communityId,@Param("communityChildId") String communityChildId);

    /**
     * 查询人员信息列表
     * 
     * @param basePersonnelInformation 人员信息
     * @return 人员信息集合
     */
    public List<BasePersonnelInformation> selectBasePersonnelInformationList(BasePersonnelInformation basePersonnelInformation);

    /**
     * 新增人员信息
     * 
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    public int insertBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation);

    /**
     * 修改人员信息
     * 
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    public int updateBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation);

    /**
     * 删除人员信息
     * 
     * @param id 人员信息ID
     * @return 结果
     */
    public int deleteBasePersonnelInformationById(String id);

    /**
     * 批量删除人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePersonnelInformationByIds(String[] ids);
    /**
     * 批量软删除人员信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePersonnelInformationByIds(String[] ids);


    /**
     *  根据房屋id查询人员信息
     * @param houseIds
     * @return
     */
    public List<PersonHousesVo> selectPerInformByHousesId(String[] houseIds);

    /**
     * 根据身份证号查询用户id
     * @param credentialNumber
     * @return
     */
    @Select("SELECT id as personId,community_id communityId,community_child_id communityChildId FROM isstech.base_personnel_information WHERE identification_number=#{credentialNumber} ")
    public Community selectPersonIdByIdentificationNumber(String credentialNumber);


    public List<BasePersonnelInformation> selectByIdList(@Param("list") List<String> idList);

    /**
     * 批量修改special=0
     * @param ids
     * @return 结果
     */
    public int updateSpecialByIds(String[] ids);


    public int queryCount(QueryVo queryVo);

    public List<BasePersonnelInformation> queryBasePerson(QueryVo queryVo);


    public int queryCount(BasePersonnelInformation basePersonnelInformation);

    /**
     * 根据网格id查询人员详情
     * @param gridId 网格id
     * @return 结果
    **/
    public List<BasePersonnelInformation> sumByGrid(String gridId);


    /**
     * 按年龄分组人员
     * @param personId 人员id集合
     * @return 结果
     **/
    @Select("select \n" +
            "sum(case when age<18 then 1 else 0 end) '<18',\n" +
            "sum(case when age between 18 and 29  then 1 else  0 end) '18-30',\n" +
            "sum(case when age between 30 and 39  then 1 else 0 end) '30-40',\n" +
            "sum(case when age between 40 and 49  then 1 else 0 end) '40-50',\n" +
            "sum(case when age between 50 and 59  then 1 else 0 end) '50-60',\n" +
            "sum(case when age between 60 and 79  then 1 else 0 end) '60-80',\n" +
            "sum(case when age >=80 then 1 else 0 end) '>80' from(\n" +
            "SELECT TIMESTAMPDIFF(YEAR,brith_date, CURDATE()) age from base_personnel_information " +
            "where id in (${personIds})) temp ")
    List<Map<String, BigDecimal>> groupByAgeScope(@Param("personIds") String personId);
}

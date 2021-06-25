package com.isstech.isstechadmin.service.impl;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.vo.*;
import com.isstech.isstechadmin.mapper.*;
import com.isstech.isstechadmin.service.IBasePersonnelInformationService;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 人员信息Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-23
 */
@Service
public class BasePersonnelInformationServiceImpl implements IBasePersonnelInformationService
{
    @Autowired
    private BasePersonnelInformationMapper basePersonnelInformationMapper;
    @Autowired
    private BaseVehicleInformationMapper baseVehicleInformationMapper;
    @Autowired
    private TSpecialReleasedPeopleMapper tSpecialReleasedPeopleMapper;
    @Autowired
    private TSpeciaCorrectMapper tSpeciaCorrectMapper;
    @Autowired
    private TSpeciaMentalMapper tSpeciaMentalMapper;
    @Autowired
    private TSpecialDrugAddictsMapper tSpecialDrugAddictsMapper;
    @Autowired
    private TSpeciaAidsMapper tSpeciaAidsMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询人员信息
     * 
     * @param id 人员信息ID
     * @return 人员信息
     */
    @Override
    public BasePersonnelInformation selectBasePersonnelInformationById(String id)
    {
        return basePersonnelInformationMapper.selectBasePersonnelInformationById(id);
    }

    /**
     * 根据（人员名称）查询人员信息和人员所在社区、楼层、房间
     *
     * @param name 人员名称
     * @return 人员信息
     */
    @Override
    public List<PersonHousesVo> selectBasePersonnelInformationByName(String name,String communityId,String communityChildId)
    {
        if(StringUtils.isEmpty(communityId)) communityId = null;
        if(StringUtils.isEmpty(communityChildId)) communityChildId = null;
        return basePersonnelInformationMapper.selectBasePersonnelInformationByName(name,communityId,communityChildId);
    }

    /**
     * 查询人员信息列表
     * 
     * @param basePersonnelInformation 人员信息
     * @return 人员信息
     */
    @Override
    public List<BasePersonnelInformation> selectBasePersonnelInformationList(BasePersonnelInformation basePersonnelInformation)
    {
        return basePersonnelInformationMapper.selectBasePersonnelInformationList(basePersonnelInformation);
    }

    /**
     * 新增人员信息
     * 
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    @Override
    public String insertBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation)
    {
        basePersonnelInformation.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isEmpty(basePersonnelInformation.getCreateBy())){
            basePersonnelInformation.setCreateBy(SecurityUtils.getUsername());
        }
        String uuid = basePersonnelInformationMapper.selectUuid();
        basePersonnelInformation.setId(uuid);
        int i = basePersonnelInformationMapper.insertBasePersonnelInformation(basePersonnelInformation);
        dataSynchronizationUtils.dataParsing(basePersonnelInformation,"insertBasePersonnelInformation");
        return uuid;
    }

    /**
     * 修改人员信息
     * 
     * @param basePersonnelInformation 人员信息
     * @return 结果
     */
    @Override
    public int updateBasePersonnelInformation(BasePersonnelInformation basePersonnelInformation)
    {
        basePersonnelInformation.setUpdateTime(DateUtils.getNowDate());
        basePersonnelInformation.setUpdateBy(SecurityUtils.getUsername());
        int row = basePersonnelInformationMapper.updateBasePersonnelInformation(basePersonnelInformation);
        dataSynchronizationUtils.dataParsing(basePersonnelInformation,"updateBasePersonnelInformation");
        return row;
    }

    /**
     * 批量删除人员信息
     * 
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBasePersonnelInformationByIds(String[] ids)
    {
        int row = basePersonnelInformationMapper.deleteBasePersonnelInformationByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBasePersonnelInformationByIds");
        return row;
    }
    /**
     * 批量软删除人员信息
     *
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    @Override
    public int updateBasePersonnelInformationByIds(String[] ids)
    {
        int row = basePersonnelInformationMapper.updateBasePersonnelInformationByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBasePersonnelInformationByIds");
        return row;
    }

    /**
     * 删除人员信息信息
     * 
     * @param id 人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBasePersonnelInformationById(String id)
    {
        int row = basePersonnelInformationMapper.deleteBasePersonnelInformationById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBasePersonnelInformationById");
        return row;
    }

    /**
     * 根据房屋id查询人员信息
     * @param houseId
     * @return
     */
    @Override
    public List<PersonHousesVo> selectPerInformByHousesId(String houseId) {
        String[] houseIds = houseId.split(",");
        return basePersonnelInformationMapper.selectPerInformByHousesId(houseIds);
    }

    @Override
    public List<BasePersonnelInformation> getByVehicleInfo(VehicleInformationVo information) {
        List<VehicleInformationVo> vehicleList = baseVehicleInformationMapper.selectVehicleInformationVoList(information);
        List<String> idList = vehicleList.stream().map(VehicleInformationVo::getPersonnelId).collect(Collectors.toList());
        return basePersonnelInformationMapper.selectByIdList(idList);
    }

    /**
     * 根据身份证号查询用户id
     * @param credentialNumber
     * @return
     */
    @Override
    public Community selectPersonIdByIdentificationNumber(String credentialNumber) {
        return basePersonnelInformationMapper.selectPersonIdByIdentificationNumber(credentialNumber);
    }

    @Override
    public int removeSpecial(String[] ids) {
        //修改base_personnel_information的special=0
        int count = basePersonnelInformationMapper.updateSpecialByIds(ids);
        //批量业务删除刑满释放人员 t_special_released_people
        tSpecialReleasedPeopleMapper.updateTSpecialReleasedPeopleByPersonIds(ids);
        //批量业务删除社区矫正人员 t_specia_correct
        tSpeciaCorrectMapper.updateTSpeciaCorrectByPersonIds(ids);
        //批量业务删除精神障碍患者 t_specia_mental
        tSpeciaMentalMapper.updateTSpeciaMentalByPersonIds(ids);
        //吸毒人员 t_special_drug_addicts
        tSpecialDrugAddictsMapper.updateTSpecialDrugAddictsByPersonIds(ids);
        //艾滋病人员 t_specia_aids
        tSpeciaAidsMapper.updateTSpeciaAidsByIds(ids);
        //批量同步
        dataSynchronizationUtils.dataParsing(ids,"updateSpecialByIds");
        dataSynchronizationUtils.dataParsing(ids,"updateTSpecialReleasedPeopleByPersonIds");
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaCorrectByPersonIds");
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaMentalByPersonIds");
        dataSynchronizationUtils.dataParsing(ids,"updateTSpecialDrugAddictsByPersonIds");
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaAidsByIds");
        return count;
    }

    @Override
    public int queryCount(QueryVo queryVo) {
        return basePersonnelInformationMapper.queryCount(queryVo);
    }

    @Override
    public List<BasePersonnelInformation> queryBasePerson(QueryVo queryVo) {
        return basePersonnelInformationMapper.queryBasePerson(queryVo);
    }


    /**
     * 根据网格id汇总人员信息
     * @param gridId 网格id
     * @return
     */
    @Override
    public PersonDetailVo sumByGrid(String gridId) {
        PersonDetailVo personDetailVo = new PersonDetailVo();
        List<BasePersonnelInformation> personList = basePersonnelInformationMapper.sumByGrid(gridId);

        //根据性别分组
        Map<String,Integer> sexGroupNum = personList.stream().collect(Collectors.groupingBy(
                BasePersonnelInformation::getSex, Collectors.summingInt(p -> 1)));
        //根据学位分组
        Map<String,Integer> degreeGroupNum = personList.stream().collect(Collectors.groupingBy(
                BasePersonnelInformation::getDegree, Collectors.summingInt(p -> 1)));
        //根据年龄段分组
        List<String> personIds = personList.stream().map(o -> o.getId()).collect(Collectors.toList());
        String personId = String.join("','",personIds);
        personId = "'"+personId+"'";
        //每次只计算查询结果的人员年龄，减少计算量
        List<Map<String,BigDecimal>> result = basePersonnelInformationMapper.groupByAgeScope(personId);
        HashMap<String,Integer> ageGroupNum = new HashMap<String,Integer>();
        result.get(0).entrySet().forEach(o -> {
            ageGroupNum.put(o.getKey(),Integer.parseInt(o.getValue().toString()));
        });
        //根据民族分组
        Map<String,Integer> nationGroupNum = personList.stream().collect(Collectors.groupingBy(
                BasePersonnelInformation::getNation, Collectors.summingInt(p -> 1)));
        //根据关爱人员分组
        //根据管控人员分组
        //根据特殊人群分组
        Map<String,Integer> specialGroupNum = personList.stream().collect(Collectors.groupingBy(
                BasePersonnelInformation::getSpecial, Collectors.summingInt(p -> 1)));
        //设置结果集
        personDetailVo.setTotalNum(personList.size());
        personDetailVo.setGrid_id(gridId);
        personDetailVo.setAgeGroupNum(ageGroupNum);
        personDetailVo.setSexGroupNum((HashMap<String, Integer>) sexGroupNum);
        personDetailVo.setDegreeGroupNum((HashMap<String, Integer>) degreeGroupNum);
        personDetailVo.setNationGroupNum((HashMap<String, Integer>) nationGroupNum);
        personDetailVo.setSpecialGroupNum((HashMap<String, Integer>) specialGroupNum);
        return personDetailVo;
    }

    /**
     * 演示5月10号,对象特异性转化
     * @param personDetailVo
     * @return
     */
    public PersonInfVo transform(PersonDetailVo personDetailVo){
        PersonInfVo personInfVo = new PersonInfVo();
        AgeGroupVo ageGroupVo = new AgeGroupVo();
        List<String> x = new ArrayList<>();
        x.add("<18");
        x.add("18-30");
        x.add("30-40");
        x.add("40-50");
        x.add("50-60");
        x.add("60-80");
        x.add(">80");
        List<Double> y = new ArrayList<>();
        Integer totalNum = personDetailVo.getTotalNum();
        //计算各年龄段比例
        x.forEach(o -> {
            Integer num= personDetailVo.getAgeGroupNum().get(o);
            double scale = new BigDecimal((float)num*100/totalNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            y.add(scale);
        });
        ageGroupVo.setX(x);
        ageGroupVo.setY(y);
        personInfVo.setAgeData(ageGroupVo);
        personInfVo.setSexData(mapToCommonMap(personDetailVo.getSexGroupNum()));
        personInfVo.setEduData(mapToCommonMap(personDetailVo.getDegreeGroupNum()));
        personInfVo.setNationXData(mapToCommonMap(personDetailVo.getNationGroupNum()));
        personInfVo.setGrid_id(personDetailVo.getGrid_id());
        personInfVo.setTotalNum(personDetailVo.getTotalNum());
        return personInfVo;
    }

    /**
     *  map转特定对象
     * @param map
     * @return
     */
    private List<CommonMap> mapToCommonMap(Map<String,Integer> map){
        List<CommonMap> list = new ArrayList<>();
        String name ="";
        String value="";
        for (Map.Entry<String, Integer> o : map.entrySet()) {
            name = o.getKey();
            value = o.getValue().toString();
            CommonMap commonMap = new CommonMap();
            commonMap.setName(name);
            commonMap.setValue(value);
            list.add(commonMap);
        }
        return list;
    }

}

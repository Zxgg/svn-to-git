package com.isstech.isstechadmin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.isstech.common.core.domain.entity.SysDictData;
import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.domain.vo.*;
import com.isstech.isstechadmin.mapper.ACountMapper;
import com.isstech.isstechadmin.mapper.TCaringEventMapper;
import com.isstech.isstechadmin.mapper.TPersonEpidemicStateMapper;
import com.isstech.isstechadmin.service.IACountService;
import com.isstech.isstechadmin.service.ISysDictTypeService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 楼栋楼层 只做记录用，房屋数据直接使用楼层号Service业务层处理
 *
 * @author yzp
 * @date 2020-11-30
 */
@Service
public class ACountServiceImpl implements IACountService {
    @Autowired
    private ACountMapper aCountMapper;
    @Autowired
    private TCaringEventMapper tCaringEventMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    @Autowired
    private TPersonEpidemicStateMapper tPersonEpidemicStateMapper;
    @Autowired
    private ISysDictTypeService dictTypeService;

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map countPerProportion(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        //外地人员占比
        String nonlocal = aCountMapper.countNonlocal(queryVo);
        //本地人员占比
        String local = aCountMapper.countLocal(queryVo);
        //访客量占比
        String visitors = aCountMapper.countVisitors(queryVo);
        Map map = new HashMap();
        map.put("nonlocal", nonlocal);
        map.put("local", local);
        map.put("visitors", visitors);
        return map;
    }

    @Override
    public List<GroupByVo> countOrganization() {
        return aCountMapper.countOrganization();
    }

    @Override
    public List<GroupByVo> countCaptureOnline() {
        return aCountMapper.countCaptureOnline();
    }

    @Override
    public List<GroupByVo> countCaringType(QueryVo queryVo) {
        BaseCaringPerson person = new BaseCaringPerson();
        person.setCommunityId(queryVo.getCommunityId());
        person.setCommunityChildId(queryVo.getCommunityChildId());
        person.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        person.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countCaringType(person);
    }

    @Override
    public Map<String,String> countHouse(QueryVo queryVo) {

        BaseCommunityHouses houses = new BaseCommunityHouses();
        houses.setCommunityId(queryVo.getCommunityId());
        houses.setCommunityChildId(queryVo.getCommunityChildId());
        houses.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        houses.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        int allHouseNum = aCountMapper.countHouseNum(houses);
        int houseLiveNum = aCountMapper.countHouseLiveNum(houses);
        DecimalFormat df = new DecimalFormat("0.00");
        String liveRate = "";
        if(allHouseNum==0){
            liveRate = "0";
        }else{
            liveRate = df.format((float)houseLiveNum/allHouseNum);
        }
        Map<String,String> map = new HashMap<>();
        map.put("houseNum",String.valueOf(allHouseNum));
        map.put("liveRate",liveRate);
        return map;
    }

    @Override
    public Map<String, List<GroupByVo>> countFloat(QueryVo queryVo) {

        Map<String,List<GroupByVo>> map = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate today = LocalDate.now();
        String sixMonth = today.plusMonths(-5).format(formatter);
        String fiveMonth = today.plusMonths(-4).format(formatter);
        String fourMonth = today.plusMonths(-3).format(formatter);
        String threeMonth = today.plusMonths(-2).format(formatter);
        String twoMonth = today.plusMonths(-1).format(formatter);
        String oneMonth = today.format(formatter);

        List<GroupByVo> floatIncreaseList = new ArrayList<>();
        floatIncreaseList.add(new GroupByVo(0,sixMonth));
        floatIncreaseList.add(new GroupByVo(0,fiveMonth));
        floatIncreaseList.add(new GroupByVo(0,fourMonth));
        floatIncreaseList.add(new GroupByVo(0,threeMonth));
        floatIncreaseList.add(new GroupByVo(0,twoMonth));
        floatIncreaseList.add(new GroupByVo(0,oneMonth));

        //新增流动人口
        //获取当前时间的前六个月的起始日期
        LocalDate sixDate = today.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(-6);
        String startDate = sixDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BaseCommunityHousesPersonnel personnel = new BaseCommunityHousesPersonnel();
        personnel.setCommunityId(queryVo.getCommunityId());
        personnel.setCommunityChildId(queryVo.getCommunityChildId());
        personnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        personnel.setStartTime(startDate);
        List<GroupByVo> countIncreaseFloatList
                = aCountMapper.countIncreaseFloating(personnel);

        for(GroupByVo vo1 : countIncreaseFloatList){
            String name1 = vo1.getName();
            for(GroupByVo vo : floatIncreaseList){
                String name = vo.getName();
                if(StringUtils.equals(name1,name)){
                    int num = vo1.getNum();
                    vo.setNum(num);
                }
            }
        }
        map.put("floatIncreaseList",floatIncreaseList);

        List<GroupByVo> floatList = new ArrayList<>();
        //查询截至6个月前的流动人口
        int count = aCountMapper.countSix(personnel);
        for (GroupByVo vo : floatIncreaseList){
            int increase = vo.getNum();
            count += increase;
            floatList.add(new GroupByVo(count,vo.getName()));
        }
        map.put("floatList",floatList);
        return map;
    }

    @Override
    public List<GroupByVo> countSpecialPerson(QueryVo queryVo) {
        BaseCommunityHousesPersonnel personnel = new BaseCommunityHousesPersonnel();
        personnel.setCommunityId(queryVo.getCommunityId());
        personnel.setCommunityChildId(queryVo.getCommunityChildId());
        personnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countSpecialPerson(personnel);
    }

    @Override
    public List<PercentVo> countSexPerson(QueryVo queryVo) {
        BasePersonnelInformation personnelInformation = new BasePersonnelInformation();
        personnelInformation.setCommunityId(queryVo.getCommunityId());
        personnelInformation.setCommunityChildId(queryVo.getCommunityChildId());
        personnelInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnelInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        List<GroupByVo> list = aCountMapper.countSexPerson(personnelInformation);
        int allNum = list.stream().mapToInt(GroupByVo::getNum).sum();
        DecimalFormat df = new DecimalFormat("0.00");
        List<PercentVo> percentVoList = new ArrayList<>();
        list.forEach(vo ->{
            int num = vo.getNum();
            String rate = df.format((float)num/allNum);
            percentVoList.add(new PercentVo(vo.getName(),rate));
        });
        return percentVoList;
    }

    @Override
    public List<Map<String,String>> countDegreePerson(QueryVo queryVo) {
        BasePersonnelInformation personnelInformation = new BasePersonnelInformation();
        personnelInformation.setCommunityId(queryVo.getCommunityId());
        personnelInformation.setCommunityChildId(queryVo.getCommunityChildId());
        personnelInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnelInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countDegreePerson(personnelInformation);
    }

    @Override
    public Map<String,String> countAgePerson(QueryVo queryVo) {
        BasePersonnelInformation information = new BasePersonnelInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countAgePerson(information);
    }

    @Override
    public List<GroupByVo> countNationPerson(QueryVo queryVo) {
        BasePersonnelInformation information = new BasePersonnelInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countNationPerson(information);
    }

    @Override
    public List<GroupByVo> countResidencePerson(QueryVo queryVo) {
        BasePersonnelInformation information = new BasePersonnelInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countResidencePerson(information);
    }

    @Override
    public List<GroupByVo> countCommunityPerson(QueryVo queryVo) {
        BasePersonnelInformation information = new BasePersonnelInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countCommunityPerson(information);
    }

    @Override
    public List<GroupByVo> countCommunityVehicle(QueryVo queryVo) {
        BaseVehicleInformation information = new BaseVehicleInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countCommunityVehicle(information);
    }

    @Override
    public List<VehicleInformationVo> getLimitedByCommunity(QueryVo queryVo) {
        BaseVehicleInformation information = new BaseVehicleInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.getLimitedByCommunity(information);
    }

    @Override
    public List<GroupByVo> getVehicleSort(QueryVo queryVo) {
        BaseVehicleInformation information = new BaseVehicleInformation();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.getVehicleSort(information);
    }

    @Override
    public List<GroupByVo> countEventName(QueryVo queryVo) {
        TCommunityEvent event = new TCommunityEvent();
        event.setCommunityId(queryVo.getCommunityId());
        event.setCommunityChildId(queryVo.getCommunityChildId());
        event.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        event.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        LocalDate  lastYear = LocalDate.now().plusYears(-1);
        Date date = Date.from(lastYear.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        event.setCreateTime(date);
        return aCountMapper.countEventName(event);
    }

    @Override
    public List<GroupByVo> eventRing(QueryVo queryVo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate today = LocalDate.now();
        String thirteenMonth = today.plusMonths(-12).format(formatter);
        String twelveMonth = today.plusMonths(-11).format(formatter);
        String elvenMonth = today.plusMonths(-10).format(formatter);
        String tenMonth = today.plusMonths(-9).format(formatter);
        String nineMonth = today.plusMonths(-8).format(formatter);
        String eightMonth = today.plusMonths(-7).format(formatter);
        String sevenMonth = today.plusMonths(-6).format(formatter);
        String sixMonth = today.plusMonths(-5).format(formatter);
        String fiveMonth = today.plusMonths(-4).format(formatter);
        String fourMonth = today.plusMonths(-3).format(formatter);
        String threeMonth = today.plusMonths(-2).format(formatter);
        String twoMonth = today.plusMonths(-1).format(formatter);
        String oneMonth = today.format(formatter);

        List<GroupByVo> eachList = new ArrayList<>();
        eachList.add(new GroupByVo(0,thirteenMonth));
        eachList.add(new GroupByVo(0,twelveMonth));
        eachList.add(new GroupByVo(0,elvenMonth));
        eachList.add(new GroupByVo(0,tenMonth));
        eachList.add(new GroupByVo(0,nineMonth));
        eachList.add(new GroupByVo(0,eightMonth));
        eachList.add(new GroupByVo(0,sevenMonth));
        eachList.add(new GroupByVo(0,sixMonth));
        eachList.add(new GroupByVo(0,fiveMonth));
        eachList.add(new GroupByVo(0,fourMonth));
        eachList.add(new GroupByVo(0,threeMonth));
        eachList.add(new GroupByVo(0,twoMonth));
        eachList.add(new GroupByVo(0,oneMonth));

        TCommunityEvent event = new TCommunityEvent();
        event.setCommunityId(queryVo.getCommunityId());
        event.setCommunityChildId(queryVo.getCommunityChildId());
        event.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        event.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        LocalDate  lastYear = LocalDate.now().plusYears(-1).plusMonths(-1);
        Date date = Date.from(lastYear.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        event.setCreateTime(date);
        List<GroupByVo> eachMonthEventList = aCountMapper.countEachMonthEvent(event);

        eachMonthEventList.forEach(each ->{
            String eachName = each.getName();
            int num = each.getNum();
            eachList.forEach(vo ->{
                String name = vo.getName();
                if (StringUtils.equals(eachName,name)){
                    vo.setNum(num);
                }
            });
        });

        return eachList;
    }

    @Override
    public List<EventVo> countCaringEvent(QueryVo queryVo) {
        LocalDate now = LocalDate.now();
        LocalDate thirdDay = now.plusDays(-3);
        TCaringEvent event = new TCaringEvent();
        event.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        event.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        event.setCommunityId(queryVo.getCommunityId());
        event.setCommunityChildId(queryVo.getCommunityChildId());
        event.setDelFlag("0");
        Date date = Date.from(thirdDay.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        event.setCreateTime(date);
        List<GroupByVo> list = aCountMapper.countCaringEvent(event);
        List<TCaringEvent> eventList = tCaringEventMapper.selectTCaringEventList(event);

        List<EventVo> eventVoList = new ArrayList<>();

        list.forEach(vo ->{
            List<TCaringEvent> noProcessedList = eventList.stream().filter(
                    item -> vo.getName().equals(item.getName()) && "0".equals(item.getProcessed())
            ).collect(Collectors.toList());//未处理

            List<TCaringEvent> processedList = eventList.stream().filter(
                    item -> vo.getName().equals(item.getName()) && "1".equals(item.getProcessed())
            ).collect(Collectors.toList());//已处理
            EventVo eventVo = new EventVo();
            eventVo.setNum(vo.getNum());
            eventVo.setCommunityId(queryVo.getCommunityId());
            event.setCommunityChildId(queryVo.getCommunityChildId());
            eventVo.setNoProcessedNum(noProcessedList.size());
            eventVo.setProcessedNum(processedList.size());
            if(noProcessedList != null && noProcessedList.size()>0){
                eventVo.setType(noProcessedList.get(0).getType());
                eventVo.setAlarmType(String.valueOf(noProcessedList.get(0).getAlarmtype()));
                eventVo.setEventName(noProcessedList.get(0).getName());
            }else{
                eventVo.setType(processedList.get(0).getType());
                eventVo.setAlarmType(String.valueOf(processedList.get(0).getAlarmtype()));
                eventVo.setEventName(processedList.get(0).getName());
            }
            eventVoList.add(eventVo);
        });
        return eventVoList;
    }

    @Override
    public List<GroupByVo> countHouseUseNature(QueryVo queryVo) {
        BaseCommunityHouses houses = new BaseCommunityHouses();
        houses.setCommunityId(queryVo.getCommunityId());
        houses.setCommunityBuildingId(queryVo.getCommunityChildId());
        houses.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        houses.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        List<GroupByVo> list = aCountMapper.countHouseUseNature(houses);
        return list;

    }

    @Override
    public List<GroupByVo> countNoNormalClass(QueryVo queryVo) {
        BaseCommunityHouses houses = new BaseCommunityHouses();
        houses.setCommunityId(queryVo.getCommunityId());
        houses.setCommunityChildId(queryVo.getCommunityChildId());
        houses.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        houses.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        List<BasePersonnelInformation> list = aCountMapper.getAllPerson(houses);
        long specialCount = list.stream().filter(item -> !"0".equals(item.getSpecial())).count(); //特殊人员
        long limitCount = list.stream().filter(item -> "1".equals(item.getLimited())).count();//限制人员
        long caringCount = list.stream().filter(item -> "1".equals(item.getCaring())).count();//关爱人员
        List<GroupByVo> voList = new ArrayList<>();
        voList.add(new GroupByVo((int) specialCount,"specialCount"));
        voList.add(new GroupByVo((int) limitCount,"limitCount"));
        voList.add(new GroupByVo((int) caringCount,"caringCount"));
        return voList;
    }

    @Override
    public List<GroupByVo> countVehicleAge(QueryVo queryVo) {
        BaseVehicleInformation information = new BaseVehicleInformation();
        information.setCommunityId(queryVo.getCommunityChildId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        List<BaseVehicleInformation> vehicleList = aCountMapper.getAllVehicle(information);
        //使用年限统计（0-6年，6年以上到15年，15年以上）
        LocalDate now = LocalDate.now();
        LocalDate sixLocalDate = now.plusYears(-6);
        LocalDate fifteenLocalDate = now.plusYears(-15);
        Date sixDate = Date.from(sixLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        Date fifteenDate = Date.from(fifteenLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        long count = vehicleList.stream().filter(item -> item.getBuyTime().before(fifteenDate)).count(); //15年以上
        long count1 = vehicleList.stream().filter(item -> item.getBuyTime().before(sixDate) && item.getBuyTime().after(fifteenDate)).count();//6年以上到15年
        long count2 = vehicleList.stream().filter(item -> item.getBuyTime().after(sixDate)).count();//0-6年
        List<GroupByVo> voList = new ArrayList<>();
        voList.add(new GroupByVo((int) count,"fifteen"));
        voList.add(new GroupByVo((int) count1,"six"));
        voList.add(new GroupByVo((int) count2,"zero"));
        return voList;
    }

    @Override
    public List<GroupByVo> countVehicleEnergy(QueryVo queryVo) {
        BaseVehicleInformation information = new BaseVehicleInformation();
        information.setCommunityId(queryVo.getCommunityChildId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        List<GroupByVo> voList = aCountMapper.countVehicleEnergy(information);
        return voList;
    }

    @Override
    public List<GroupByVo> getVisitVehicleType(QueryVo queryVo) {
        BaseVisitorVehicleInformation information = new BaseVisitorVehicleInformation();
        information.setTargetPersonCommunityId(queryVo.getCommunityId());
        information.setTargetPersonCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        //暂定统计一周的数据
        LocalDate endLocalDate = LocalDate.now();
        LocalDate startLocalDate = endLocalDate.plusWeeks(-1);
//        LocalDate startLocalDate = endLocalDate.plusYears(-1);
        Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        information.setVisitStartTime(startDate);
        information.setVisitEndTime(endDate);
        List<GroupByVo> list = aCountMapper.getVisitVehicleType(information);
        return list;
    }

    @Override
    public List<GroupByVo> countVehiclePass(QueryVo queryVo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        String sevenDay = now.plusDays(-7).format(formatter);
        String sixDay = now.plusDays(-6).format(formatter);
        String fiveDay = now.plusDays(-5).format(formatter);
        String fourDay = now.plusDays(-4).format(formatter);
        String threeDay = now.plusDays(-3).format(formatter);
        String twoDay = now.plusDays(-2).format(formatter);
        String oneDay = now.plusDays(-1).format(formatter);
        List<GroupByVo> list = new ArrayList<>();
        list.add(new GroupByVo(0,sevenDay));
        list.add(new GroupByVo(0,sixDay));
        list.add(new GroupByVo(0,fiveDay));
        list.add(new GroupByVo(0,fourDay));
        list.add(new GroupByVo(0,threeDay));
        list.add(new GroupByVo(0,twoDay));
        list.add(new GroupByVo(0,oneDay));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = LocalDateTime.now().plusDays(-7).withHour(0).withMinute(0).withSecond(0).format(formatter1);
        String end = LocalDateTime.now().plusDays(-1).withHour(23).withMinute(59).withSecond(59).format(formatter1);


        PassVehicleVo vehicle = new PassVehicleVo();
        vehicle.setCommunityId(queryVo.getCommunityId());
        vehicle.setCommunityChildId(queryVo.getCommunityChildId());
        vehicle.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        vehicle.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        vehicle.setStartPassTime(start);
        vehicle.setEndPassTime(end);
        List<BasePassVehicle> vehicleList = aCountMapper.getPassVehicleList(vehicle);


        List<GroupByVo> voList = new ArrayList<>();
        int type = queryVo.getType();
        if(type == 1){ //所有
            voList = aCountMapper.countVehiclePass(vehicle);
        }else if(type == 2){ //访客
            voList = aCountMapper.countVisitVehiclePass(vehicle);
        }

        voList.forEach(item -> {
            String name = item.getName();
            list.forEach(vo ->{
                String name1 = vo.getName();
                if(StringUtils.equals(name,name1)){
                    vo.setNum(item.getNum());
                }
            });
        });
        return list;
    }

    @Override
    public List<GroupByVo> countPersonPass(QueryVo queryVo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String today = now.format(formatter);
        String sixDay = now.plusDays(-6).format(formatter);
        String fiveDay = now.plusDays(-5).format(formatter);
        String fourDay = now.plusDays(-4).format(formatter);
        String threeDay = now.plusDays(-3).format(formatter);
        String twoDay = now.plusDays(-2).format(formatter);
        String oneDay = now.plusDays(-1).format(formatter);
        List<GroupByVo> list = new ArrayList<>();
        list.add(new GroupByVo(0,sixDay));
        list.add(new GroupByVo(0,fiveDay));
        list.add(new GroupByVo(0,fourDay));
        list.add(new GroupByVo(0,threeDay));
        list.add(new GroupByVo(0,twoDay));
        list.add(new GroupByVo(0,oneDay));
        list.add(new GroupByVo(0,today));

        PassPersonnelVo personnel = new PassPersonnelVo();
        personnel.setCommunityId(queryVo.getCommunityId());
        personnel.setCommunityChildId(queryVo.getCommunityChildId());

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = LocalDateTime.now().plusDays(-7).withHour(0).withMinute(0).withSecond(0).format(formatter1);
        String end = LocalDateTime.now().plusDays(-1).withHour(23).withMinute(59).withSecond(59).format(formatter1);

        personnel.setStartMarkTime(start);
        personnel.setEndMarkTime(end);

        List<GroupByVo> markList = new ArrayList<>();
        int type = queryVo.getType();
        if(type == 1){
            markList = aCountMapper.countPersonnelPass(personnel);
        }else if(type == 2){
            markList = aCountMapper.countVisitPersonnelPass(personnel);
        }

        markList.forEach(item -> {
            String name = item.getName();
            list.forEach(vo ->{
                String name1 = vo.getName();
                if(StringUtils.equals(name,name1)){
                    vo.setNum(item.getNum());
                }
            });
        });
        return list;
    }

    @Override
    public Map<String, List<GroupByVo>> countVisit(QueryVo queryVo) {
        queryVo.setType(2);
        List<GroupByVo> vehicleList = this.countVehiclePass(queryVo);
        List<GroupByVo> personList = this.countPersonPass(queryVo);
        Map<String,List<GroupByVo>> map = new HashMap<>();
        map.put("vehicleList",vehicleList);
        map.put("personList",personList);
        return map;
    }

    @Override
    public Map<String, List<GroupByVo>> countVisitType(QueryVo queryVo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startLocalDate = LocalDateTime.now().plusWeeks(-1);
        String start = startLocalDate.withHour(0).withMinute(0).withSecond(0).format(formatter);

        LocalDateTime yesterday = LocalDateTime.now().plusDays(-1);
        String end = yesterday.withHour(23).withMinute(59).withSecond(59).format(formatter);

        PassVehicleVo vehicle = new PassVehicleVo();
        vehicle.setCommunityId(queryVo.getCommunityId());
        vehicle.setCommunityChildId(queryVo.getCommunityChildId());
        vehicle.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        vehicle.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        vehicle.setStartPassTime(start);
        vehicle.setEndPassTime(end);
        List<GroupByVo> vehicleList = aCountMapper.countVehicleVisitType(vehicle);

        PassPersonnelVo personnel = new PassPersonnelVo();
        personnel.setCommunityId(queryVo.getCommunityId());
        personnel.setCommunityChildId(queryVo.getCommunityChildId());
        personnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        personnel.setStartMarkTime(start);
        personnel.setEndMarkTime(end);
        List<GroupByVo> personList = aCountMapper.countPersonVisitType(personnel);
        Map<String,List<GroupByVo>> map = new HashMap<>();
        map.put("vehicleList",vehicleList);
        map.put("personList",personList);
        return map;
    }

    @Override
    public List<GroupByVo> countPassYesterday(QueryVo queryVo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime yesterday = LocalDateTime.now().plusDays(-1);
        String start = yesterday.withHour(0).withMinute(0).withSecond(0).format(formatter);
        String end = yesterday.withHour(23).withMinute(59).withSecond(59).format(formatter);


        //早上：05：30---8：00
        //上午：08：00---11：30
        //中午：11：30---14：00
        //下午：14：00---17：30
        //晚上：17：30---21：00
        //深夜：21：00---05：30

        List<GroupByVo> voList = new ArrayList<>();

        Date time_0530 = Date.from(yesterday.withHour(5).withMinute(30).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        Date time_0800 = Date.from(yesterday.withHour(8).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        Date time_1130 = Date.from(yesterday.withHour(11).withMinute(30).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        Date time_1400 = Date.from(yesterday.withHour(14).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        Date time_1730 = Date.from(yesterday.withHour(17).withMinute(30).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        Date time_2100 = Date.from(yesterday.withHour(21).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toInstant());

        int type = queryVo.getType();
        int count_0530_0800 = 0;
        int count_0800_1130 = 0;
        int count_1130_1400 = 0;
        int count_1400_1730 = 0;
        int count_1730_2100 = 0;
        int count_2100_0530 = 0;

        if (type == 1){ //统计车辆通行
            PassVehicleVo vo = new PassVehicleVo();
            vo.setCommunityId(queryVo.getCommunityId());
            vo.setCommunityChildId(queryVo.getCommunityChildId());
            vo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            vo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
            vo.setStartPassTime(start);
            vo.setEndPassTime(end);
            List<BasePassVehicle> list = aCountMapper.getPassVehicleList(vo);

            count_0530_0800 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_0530)>=0 && item.getPassTime().compareTo(time_0800) < 0).count();
            count_0800_1130 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_0800)>=0 && item.getPassTime().compareTo(time_1130) < 0).count();
            count_1130_1400 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_1130)>=0 && item.getPassTime().compareTo(time_1400) < 0).count();
            count_1400_1730 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_1400)>=0 && item.getPassTime().compareTo(time_1730) < 0).count();
            count_1730_2100 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_1730)>=0 && item.getPassTime().compareTo(time_2100) < 0).count();
            count_2100_0530 = (int) list.stream().filter(item -> item.getPassTime().compareTo(time_2100)>=0 || item.getPassTime().compareTo(time_0530) < 0).count();
        }else if (type == 2){//统计人员通行
            PassPersonnelVo vo = new PassPersonnelVo();
            vo.setCommunityId(queryVo.getCommunityId());
            vo.setCommunityChildId(queryVo.getCommunityChildId());
            vo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            vo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
            vo.setStartMarkTime(start);
            vo.setEndMarkTime(end);
            List<BasePassPersonnel> list = aCountMapper.getPassPersonnelList(vo);

            count_0530_0800 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_0530)>=0 && item.getLocationMarkTime().compareTo(time_0800) < 0).count();
            count_0800_1130 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_0800)>=0 && item.getLocationMarkTime().compareTo(time_1130) < 0).count();
            count_1130_1400 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_1130)>=0 && item.getLocationMarkTime().compareTo(time_1400) < 0).count();
            count_1400_1730 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_1400)>=0 && item.getLocationMarkTime().compareTo(time_1730) < 0).count();
            count_1730_2100 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_1730)>=0 && item.getLocationMarkTime().compareTo(time_2100) < 0).count();
            count_2100_0530 = (int) list.stream().filter(item -> item.getLocationMarkTime().compareTo(time_2100)>=0 || item.getLocationMarkTime().compareTo(time_0530) < 0).count();
        }

        voList.add(new GroupByVo(count_0530_0800,"count_0530_0800"));
        voList.add(new GroupByVo(count_0800_1130,"count_0800_1130"));
        voList.add(new GroupByVo(count_1130_1400,"count_1130_1400"));
        voList.add(new GroupByVo(count_1400_1730,"count_1400_1730"));
        voList.add(new GroupByVo(count_1730_2100,"count_1730_2100"));
        voList.add(new GroupByVo(count_2100_0530,"count_2100_0530"));

        return voList;
    }

    @Override
    public Map<String,Integer> countPass(QueryVo queryVo) {

        Map<String,Integer> allMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //当日居民通行 人/车  访客数量 人/车
        String start = now.withHour(0).withMinute(0).withSecond(0).format(formatter);
        String end = now.withHour(23).withMinute(59).withSecond(59).format(formatter);
        queryVo.setStartTime(start);
        queryVo.setEndTime(end);
        Map<String, Integer> dayMap = this.getPassByTime(queryVo);
        allMap.put("day_person",dayMap.get("person"));
        allMap.put("day_visit_person",dayMap.get("visit_person"));
        allMap.put("day_vehicle",dayMap.get("vehicle"));
        allMap.put("day_visit_vehicle",dayMap.get("visit_vehicle"));

        allMap.put("day_visit_person_linshi",dayMap.get("visit_person_linshi"));
        allMap.put("day_visit_person_yuyue",dayMap.get("visit_person_yuyue"));
        allMap.put("day_visit_vehicle_linshi",dayMap.get("visit_vehicle_linshi"));
        allMap.put("day_visit_vehicle_yuyue",dayMap.get("visit_vehicle_yuyue"));

        //当周居民通行 人/车  访客数量 人/车
        String currWeekStart = DateUtil.format(DateUtil.beginOfWeek(new Date()), "yyyy-MM-dd HH:mm:ss");
        String currWeekEnd = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        queryVo.setStartTime(currWeekStart);
        queryVo.setEndTime(currWeekEnd);
        Map<String, Integer> weekMap = this.getPassByTime(queryVo);

        allMap.put("week_person",weekMap.get("person"));
        allMap.put("week_visit_person",weekMap.get("visit_person"));
        allMap.put("week_vehicle",weekMap.get("vehicle"));
        allMap.put("week_visit_vehicle",weekMap.get("visit_vehicle"));

        allMap.put("week_visit_person_linshi",weekMap.get("visit_person_linshi"));
        allMap.put("week_visit_person_yuyue",weekMap.get("visit_person_yuyue"));
        allMap.put("week_visit_vehicle_linshi",weekMap.get("visit_vehicle_linshi"));
        allMap.put("week_visit_vehicle_yuyue",weekMap.get("visit_vehicle_yuyue"));


        //当月居民通行 人/车  访客数量 人/车
        start = LocalDateTime.of(now.getYear(), now.getMonth(), 1,0,0,0).format(formatter);
        queryVo.setStartTime(start);
        Map<String, Integer> monthMap = this.getPassByTime(queryVo);

        allMap.put("month_person",monthMap.get("person"));
        allMap.put("month_visit_person",monthMap.get("visit_person"));
        allMap.put("month_vehicle",monthMap.get("vehicle"));
        allMap.put("month_visit_vehicle",monthMap.get("visit_vehicle"));

        allMap.put("month_visit_person_linshi",monthMap.get("visit_person_linshi"));
        allMap.put("month_visit_person_yuyue",monthMap.get("visit_person_yuyue"));
        allMap.put("month_visit_vehicle_linshi",monthMap.get("visit_vehicle_linshi"));
        allMap.put("month_visit_vehicle_yuyue",monthMap.get("visit_vehicle_yuyue"));

        return allMap;
    }

    @Override
    public Map<String,Integer> getPassByTime(QueryVo queryVo) {
        PassPersonnelVo personnelVo = new PassPersonnelVo();
        personnelVo.setCommunityId(queryVo.getCommunityId());
        personnelVo.setCommunityChildId(queryVo.getCommunityChildId());
        personnelVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        personnelVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());

        PassVehicleVo vehicleVo = new PassVehicleVo();
        vehicleVo.setCommunityId(queryVo.getCommunityId());
        vehicleVo.setCommunityChildId(queryVo.getCommunityChildId());
        vehicleVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        vehicleVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());

        Map<String,Integer> map = new HashMap<>();
        String start = queryVo.getStartTime();
        String end = queryVo.getEndTime();

        personnelVo.setStartMarkTime(start);
        personnelVo.setEndMarkTime(end);
        List<BasePassPersonnel> dayPersonList = aCountMapper.getPassPersonnelList(personnelVo);
        int person = (int) dayPersonList.stream().filter(item -> "base_personnel_information".equals(item.getSourceTableName())).count();
        int visit_person = (int) dayPersonList.stream().filter(
                item -> ("base_temp_visitor_personnel_information").equals(item.getSourceTableName()) || ("base_visitor_personnel_information").equals(item.getSourceTableName())).count();
        int visit_person_linshi = (int) dayPersonList.stream().filter( item -> ("base_temp_visitor_personnel_information").equals(item.getSourceTableName())).count();
        int visitor_person_yuyue = (int) dayPersonList.stream().filter( item -> ("base_visitor_personnel_information").equals(item.getSourceTableName())).count();

        map.put("person",person);
        map.put("visit_person",visit_person);
        map.put("visit_person_linshi",visit_person_linshi);
        map.put("visit_person_yuyue",visitor_person_yuyue);

        vehicleVo.setStartPassTime(start);
        vehicleVo.setEndPassTime(end);
        List<BasePassVehicle> dayVehicleList = aCountMapper.getPassVehicleList(vehicleVo);
        int vehicle = (int) dayVehicleList.stream().filter(item -> ("base_vehicle_information").equals(item.getSourceTableName())).count();
        int visit_vehicle = (int) dayVehicleList.stream().filter(
                item -> ("base_temp_visitor_vehicle_information").equals(item.getSourceTableName()) || ("base_visitor_vehicle_information").equals(item.getSourceTableName())).count();
        int visit_vehicle_yuyue = (int) dayVehicleList.stream().filter(item ->  ("base_visitor_vehicle_information").equals(item.getSourceTableName())).count();
        int visit_vehicle_linshi = (int) dayVehicleList.stream().filter(item -> ("base_temp_visitor_vehicle_information").equals(item.getSourceTableName())).count();
        map.put("vehicle",vehicle);
        map.put("visit_vehicle",visit_vehicle);
        map.put("visit_vehicle_yuyue",visit_vehicle_yuyue);
        map.put("visit_vehicle_linshi",visit_vehicle_linshi);

        return map;
    }

    @Override
    public Map<String, List<GroupByVo>> trafficTrends(QueryVo queryVo){
        Map<String,List<GroupByVo>> map = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<GroupByVo> personList = new ArrayList<>();
        List<GroupByVo> visitPersonList = new ArrayList<>();
        List<GroupByVo> vehicleList = new ArrayList<>();
        List<GroupByVo> visitVehicleList = new ArrayList<>();

        int type = queryVo.getType();
        if(type == 1){
            LocalDate startDate = LocalDate.parse(queryVo.getStartTime(),formatter);
            LocalDate endDate = LocalDate.parse(queryVo.getEndTime(),formatter);
            int limitNum = Period.between(startDate, endDate).getDays();
            queryVo.setLimitNum(limitNum);
            String startDateStr = queryVo.getStartTime().substring(0, 10);
            queryVo.setStartDate(startDateStr);
            queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
            personList = aCountMapper.countPassPersonDate(queryVo);
            visitPersonList = aCountMapper.countPassVisitPersonDate(queryVo);
            vehicleList = aCountMapper.countPassVehicleDate(queryVo);
            visitVehicleList = aCountMapper.countPassVisitVehicleDate(queryVo);
        }else {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long diff = 0;
            try {
                diff = sd.parse(queryVo.getEndTime()).getTime() - sd.parse(queryVo.getStartTime()).getTime();
            } catch (ParseException e) {
                logger.info("trafficTrends error" ,e);
            }
            long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
            long nh = 1000 * 60 * 60;// 一小时的毫秒数
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh + day * 24;
            queryVo.setLimitNum((int) hour);
            queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
            personList = aCountMapper.countPassPersonDateTime(queryVo);
            visitPersonList = aCountMapper.countPassVisitPersonDateTime(queryVo);
            visitVehicleList = aCountMapper.countPassVisitVehicleDateTime(queryVo);
            vehicleList = aCountMapper.countPassVehicleDateTime(queryVo);
        }

        map.put("personList",personList);
        map.put("visitPersonList",visitPersonList);
        map.put("vehicleList",vehicleList);
        map.put("visitVehicleList",visitVehicleList);
        return map;
    }

    @Override
    public List<BlackPersonnelVo> getBlackPersonnelByCommunity(QueryVo queryVo) {
        BaseBlackPersonnel information = new BaseBlackPersonnel();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.getBlackPersonnelByCommunity(information);
    }

    @Override
    public Map<String, String> countCommunityArea(QueryVo queryVo) {
        BaseCommunityHouses information = new BaseCommunityHouses();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countCommunityArea(information);
    }

    @Override
    public List<Map<String,String>> personnelPassSnap(QueryVo queryVo) {
        BasePassPersonnel information = new BasePassPersonnel();
        information.setCommunityId(queryVo.getCommunityId());

        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.personnelPassSnap(information);
    }

    @Override
    public List<Map<String,String>> vehiclePassSnap(QueryVo queryVo) {
        BasePassVehicle information = new BasePassVehicle();
        information.setCommunityId(queryVo.getCommunityId());
        information.setCommunityChildId(queryVo.getCommunityChildId());
        information.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        information.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.vehiclePassSnap(information);
    }

    @Override
    public Map<String, String> countActualInformation(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countActualInformation(queryVo);
    }

    @Override
    public List<Map<String, String>> countCaringEventByType(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countCaringEventByType(queryVo);
    }

    @Override
    public Map<String, String> countSpecial(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.countSpecial(queryVo);
    }

    @Override
    public Map<String, String> countHouseholds(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        Map<String, String> map = aCountMapper.countHouseholds(queryVo);
        if(StringUtils.isNotEmpty(queryVo.getCommunityChildId())){
            map.put("communityChildCount","1");
        }
        return map;
    }

    @Override
    public Map<String, String> isolationOfFamily(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.isolationOfFamily(queryVo);
    }

    @Override
    public Map<String, String> isolationOfPersonnel(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.isolationOfPersonnel(queryVo);
    }

    @Override
    public Map<String, String> nucleicAcidTestingNum(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.nucleicAcidTestingNum(queryVo);
    }

    @Override
    public List<GroupByVo> isolationReason(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return aCountMapper.isolationReason(queryVo);
    }

    @Override
    public Map<String, List<GroupByVo>> isolationFamilyTimeCurve(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        Map<String,List<GroupByVo>> map = new HashMap<>();
        //尚有隔离家庭-时间曲线
        queryVo.setType(1);
        List<GroupByVo> allIsolationFamilyList = aCountMapper.isolationFamilyList(queryVo);
        //新增隔离家庭-时间曲线
        queryVo.setType(2);
        List<GroupByVo> newIsolationFamilyList = aCountMapper.isolationFamilyList(queryVo);
        map.put("allIsolationFamilyList",allIsolationFamilyList);
        map.put("newIsolationFamilyList",newIsolationFamilyList);
        return map;
    }

    @Override
    public Map<String, List<GroupByVo>> reportInfo(QueryVo queryVo) {
        queryVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        queryVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        Map<String,List<GroupByVo>> map = new HashMap<>();
        //个人上报-总共人数-时间曲线
        List<GroupByVo> allReportInfoList = aCountMapper.reportInfoList(queryVo);
        map.put("allReportInfoList",allReportInfoList);
        //获取风险判定数据字典
        List<SysDictData> sysDictData = dictTypeService.selectDictDataByType("base_epidemic_risk");
        StringBuffer otherData = new StringBuffer();
        //个人上报-有风险人数-时间曲线
        sysDictData.stream().forEach(item->{
            String dictLabel = item.getDictLabel();
            String dictValue = item.getDictValue();
            if(StringUtils.isNotEmpty(dictLabel)&&StringUtils.isNotEmpty(dictValue)){
                String name = dictLabel.replaceAll("[A-Z]", "_$0").toLowerCase();
                otherData.append(name);
                otherData.append(" = ");
                otherData.append("'"+dictValue+"'");
                otherData.append(" or ");
            }
        });
        queryVo.setOtherData(otherData.substring(0, otherData.length() - 4));
        List<GroupByVo> riskInfoList = aCountMapper.reportInfoList(queryVo);
        map.put("riskInfoList",riskInfoList);
        return map;
    }

    public static void main(String[] args) {
        System.out.println("otherData".replaceAll("[A-Z]", "_$0").toLowerCase());
    }


}

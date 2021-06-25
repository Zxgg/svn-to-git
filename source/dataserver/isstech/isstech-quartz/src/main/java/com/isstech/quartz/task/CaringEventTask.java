package com.isstech.quartz.task;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseCaringPerson;
import com.isstech.isstechadmin.domain.TCaringEvent;
import com.isstech.isstechadmin.domain.vo.CaringPerson;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.ACountMapper;
import com.isstech.isstechadmin.mapper.BaseCaringPersonMapper;
import com.isstech.isstechadmin.mapper.TCaringEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service("caringEventTask")
public class CaringEventTask {

    @Autowired
    private BaseCaringPersonMapper baseCaringPersonMapper;

    @Autowired
    private ACountMapper aCountMapper;

    @Autowired
    private TCaringEventMapper tCaringEventMapper;

    /**
     * 统计长时间未外出的类型 目前统计独居老人
     * @param day 定义时间
     * @param type 定义类型 目前类型为独居老人
     * @author qishuwei
     * @date 2020-12-30
     */
    public void peopleLongTimeNoOut(String type,Integer day){
        CaringPerson caringPerson = new CaringPerson();
        caringPerson.setType(type);
        List<CaringPerson> peopleList = baseCaringPersonMapper.selectBaseCaringAddPersonList(caringPerson);
        if(peopleList == null || peopleList.size() == 0){
            return;
        }
        //获取所有符合条件的关爱人员
        List<String> personIdList
                = peopleList.stream().map(person -> person.getPersonId()).collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String endTime = now.plusDays(-1).withHour(23).withMinute(59).withSecond(59).format(formatter);
        String startTime = now.plusDays(-day).withHour(0).withMinute(0).withSecond(0).format(formatter);

        //根据关爱人员person_id查询符合时间内有通行记录的人员
        QueryVo queryVo = new QueryVo();
        queryVo.setPersonIds(personIdList);
        queryVo.setStartTime(startTime);
        queryVo.setEndTime(endTime);
        List<GroupByVo> voList = aCountMapper.countByPersonIdListAndTime(queryVo);

        //去除有通行记录的人员
        Iterator<CaringPerson> iterator = peopleList.iterator();
        while (iterator.hasNext()){
            BaseCaringPerson next = iterator.next();
            String personId = next.getPersonId();
            voList.forEach(groupByVo -> {
                String name = groupByVo.getName();
                if(StringUtils.equals(personId,name)){
                    iterator.remove();
                }
            });
        }

        List<TCaringEvent> eventList = new ArrayList<>();
//        String username = SecurityUtils.getUsername();
        peopleList.forEach(people->{
            TCaringEvent event = new TCaringEvent();
            event.setCreateBy("admin");
            event.setCreateTime(DateUtils.getNowDate());
            event.setRemark("独居老人长时间未外出Task");
            event.setName("孤寡老人长时间未外出");
            event.setType(type);
            event.setPersonid(people.getId()+","+people.getPersonName());
            event.setAlarmtype(2L);
            event.setContent("孤寡老人长时间未外出");
            event.setCommunityId(people.getCommunityId());
            event.setCommunityChildId(people.getCommunityChildId());
            eventList.add(event);
        });
        //登记关爱人员事件表
        tCaringEventMapper.insertBatch(eventList);
    }


}

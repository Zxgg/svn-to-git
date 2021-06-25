package com.isstech.quartz.task;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.TCommunityEvent;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.ACountMapper;
import com.isstech.isstechadmin.mapper.BasePersonnelInformationMapper;
import com.isstech.isstechadmin.mapper.TCommunityEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("communityEventTask")
public class CommunityEventTask {

    @Autowired
    private ACountMapper aCountMapper;

    @Autowired
    private BasePersonnelInformationMapper basePersonnelInformationMapper;

    @Autowired
    private TCommunityEventMapper tCommunityEventMapper;

    public void dayHideNightOut(Integer day,Double rate){

        List<List<String>> allList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(int i=1;i<=day;i++){
            LocalDateTime localDateTime = now.plusDays(-i);
            String startTimeTwo = localDateTime.withHour(2).withMinute(0).withSecond(0).format(formatter);
            String endTimeFour = localDateTime.withHour(4).withMinute(0).withSecond(0).format(formatter);
            String startTime = localDateTime.withHour(0).withMinute(0).withSecond(0).format(formatter);
            String endTime = localDateTime.withHour(23).withMinute(59).withSecond(59).format(formatter);
            QueryVo queryVo = new QueryVo();
            queryVo.setStartTime(startTime);
            queryVo.setEndTime(endTime);
            queryVo.setStartTimeTwo(startTimeTwo);
            queryVo.setEndTimeFour(endTimeFour);
            queryVo.setRate(rate);
            List<String>  list = aCountMapper.countDayHideNightOUT(queryVo);
            if(list == null || list.size() == 0){
                return;
            }
            allList.add(list);
        }

        if(allList == null || allList.size() == 0){
            return;
        }


        List<String> diff = allList.get(0);
        for(int i=1;i<allList.size();i++){
            List<String> voList1 = allList.get(i);
            diff = shaixun(diff,voList1);
        }

        List<BasePersonnelInformation> personList = basePersonnelInformationMapper.selectByIdList(diff);

        List<TCommunityEvent> eventList = new ArrayList<>();

//        String username = SecurityUtils.getUsername();
        personList.forEach(person->{
            TCommunityEvent event = new TCommunityEvent();
            event.setCreateBy("admin");
            event.setCreateTime(DateUtils.getNowDate());
            event.setRemark("昼伏夜出Task");
            event.setName("昼伏夜出");
            event.setType("001");
            event.setPersonid(person.getId()+","+person.getName());
            event.setAlarmtype(2L);
            event.setContent("昼伏夜出");
            event.setCommunityId(person.getCommunityId());
            event.setCommunityChildId(person.getCommunityChildId());
            eventList.add(event);
        });

        tCommunityEventMapper.insertBatch(eventList);
    }

    private List<String> shaixun(List<String> diff, List<String> voList1) {
        List<String> same = new ArrayList<>();
        diff.forEach(str->{
            boolean flag = voList1.contains(str);
            if(flag){
                same.add(str);
            }
        });
        return same;
    }

}

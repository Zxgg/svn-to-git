package com.isstech.quartz.task;

import com.alibaba.fastjson.JSONArray;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.isstechadmin.utils.domain.SysDataSynchronization;
import com.isstech.isstechadmin.utils.mapper.SysDataSynchronizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("dataSynchronizationTask")
public class DataSynchronizationTask {

    @Autowired
    private SysDataSynchronizationMapper sysDataSynchronizationMapper;
    /**服务默认端口*/
    @Value("${server.port}")
    public String synPort;
    /**数据同步路由地址*/
    @Value("${dataSynchronization.synAddress}")
    public String synAddress;

    public void sendData(){
        try{
            //标记每次查询出需要同步的数据超过100条
            boolean flag = true;
            SysDataSynchronization information = new SysDataSynchronization();
            information.setSynFlag("0");
            while(flag){
                //获取10分钟内未发送过的数据
                Calendar beforeTime = Calendar.getInstance();
                beforeTime.add(Calendar.MINUTE, -10);
                information.setSendTime(beforeTime.getTime());
                List<SysDataSynchronization> sysDataSynchronizations = sysDataSynchronizationMapper.selectSysDataSynchronizationList(information);
                if(sysDataSynchronizations ==null ||sysDataSynchronizations.size()==0){
                    return;
                }
                //当查询条数少于100条时，跳出本次循环，执行结束，定时任务结束
                if(sysDataSynchronizations.size()<100){
                    flag = false;
                }
                sysDataSynchronizations.forEach(item->{
                    try{
                        if(StringUtils.isEmpty(item.getSynIp())){
                            item.setDelFlag("1");
                            sysDataSynchronizationMapper.updateSysDataSynchronization(item);
                        }else{
                            StringBuffer url = new StringBuffer();
                            url.append("http://");
                            url.append(getIpAndPort(item.getSynIp()));
                            url.append(synAddress);
                            if("0".equals(item.getType())){
                                url.append("next");
                            }else {
                                url.append("up");
                            }
                            Resp resp = HttpClientUtil.doPostJson(url.toString(), JSONArray.toJSONString(item));
                            System.out.println(resp);
                            if(!"200".equals(resp.getStatus())){
                                item.setSendTime(new Date());
                            }else{
                                item.setSynFlag("1");
                            }
                            sysDataSynchronizationMapper.updateSysDataSynchronization(item);
                        }
                    }catch (Exception e){
                        item.setSendTime(new Date());
                        sysDataSynchronizationMapper.updateSysDataSynchronization(item);
                    }
                });
            }
        }catch (Exception e){}
    }

    /**
     * 获取同步ip和端口
     * */
    public String getIpAndPort(String ip){
        StringBuffer ipAndPort = new StringBuffer();
        if(ip.contains(":")){
            return ip;
        }
        ipAndPort.append(ip).append(":").append(synPort);
        return ipAndPort.toString();
    }

}

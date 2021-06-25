package com.isstech.web.service;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.PageInfo;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.framework.manager.AsyncManager;
import com.isstech.framework.manager.factory.AsyncFactory;
import com.isstech.isstechadmin.domain.OneToFour;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 数据处理方法
 * @Author yzp
 * @Date 2020/12/14 16:16
 */
@Component
@Slf4j
public class DataProcessingService
{
    /**服务默认端口*/
    @Value("${proxy-vcm}")
    private String url;
    @Value(value = "${ONE_FOUR_GET}")
    private String oneFourGet;


    /**
     *  异步人员关系维护
     * @param repositorWhiteIds
     * @param roster01
     * @param repositorBlackIds
     * @param roster02
     */
    public void syc(String repositorWhiteIds,String roster01,String repositorBlackIds,String roster02,String repositorStrangers,String roster03) {
        System.out.println(url);
        Map mapWhite = new HashMap();
        Map map1 = new HashMap();
        int noWhite = 1;
        int size = 1000;
        map1.put("no", noWhite);
        map1.put("size", size);
        map1.put("sort", "desc");
        map1.put("orderName", "time");
        mapWhite.put("repositorIds",repositorWhiteIds);
        mapWhite.put("page",map1);

        String json = JSONObject.toJSONString(mapWhite);
        Resp ajaxResult = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", json);
        if(ajaxResult.getData() != null){
            AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResult.getData().toString(),roster01));
        }
        PageInfo page = ajaxResult.getPage();
        if(page != null ) {
            int total = page.getTotal();
            int pages;
            if (total % size > 0) {
                pages = total / size + 1;
            } else {
                pages = total / size;
            }
            for (int i = 1; i <pages ; i++) {
                map1.put("no", noWhite + i);
                mapWhite.put("page",map1);
                String jsonFor = JSONObject.toJSONString(mapWhite);
                Resp ajaxResultFor = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", jsonFor);
                if(ajaxResult.getData() != null){
                    AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResultFor.getData().toString(),roster01));
                }
            }
        }

        Map mapBlack = new HashMap();
        Map map2 = new HashMap();
        int noBlack = 1;
        map2.put("no", noBlack);
        map2.put("size", size);
        map2.put("sort", "desc");
        map2.put("orderName", "time");
        mapBlack.put("repositorIds",repositorBlackIds);
        mapBlack.put("page",map2);
        String jsonBlack = JSONObject.toJSONString(mapBlack);
        Resp ajaxResultBlack = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", jsonBlack);
        if(ajaxResultBlack.getData() != null){
            AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResultBlack.getData().toString(),roster02));
        }
        PageInfo pageBlack = ajaxResultBlack.getPage();
        if(pageBlack != null ) {
            int total2 = pageBlack.getTotal();
            int pages2;
            if (total2 % size > 0) {
                pages2 = total2 / size + 1;
            } else {
                pages2 = total2 / size;
            }
            for (int i = 1; i <pages2 ; i++) {
                map2.put("no", noBlack + i);
                mapBlack.put("page",map2);
                String jsonForBlack = JSONObject.toJSONString(mapBlack);
                Resp ajaxResultForBlack = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", jsonForBlack);
                if(ajaxResultForBlack.getData() != null){
                    AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResultForBlack.getData().toString(),roster02));
                }
            }
        }

        Map mapStranger = new HashMap();
        Map map3 = new HashMap();
        int noStranger = 1;
        map3.put("no", noStranger);
        map3.put("size", size);
        map3.put("sort", "desc");
        map3.put("orderName", "time");
        mapStranger.put("repositorIds",repositorStrangers);
        mapStranger.put("page",map3);
        String jsonStrangerd = JSONObject.toJSONString(mapStranger);
        Resp ajaxResultStranger = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", jsonStrangerd);
        if(ajaxResultStranger.getData() != null){
            AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResultStranger.getData().toString(),roster02));
        }
        PageInfo pageStranger = ajaxResultStranger.getPage();
        if(pageStranger != null ) {
            int total3 = pageStranger.getTotal();
            int pages3;
            if (total3 % size > 0) {
                pages3 = total3 / size + 1;
            } else {
                pages3 = total3 / size;
            }
            for (int i = 1; i <pages3 ; i++) {
                map3.put("no", noBlack + i);
                mapStranger.put("page",map3);
                String jsonForStranger = JSONObject.toJSONString(mapStranger);
                Resp ajaxResultForStranger = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", jsonForStranger);
                if(ajaxResultForStranger.getData() != null){
                    AsyncManager.me().execute(AsyncFactory.maintainRelationPerson(ajaxResultForStranger.getData().toString(),roster02));
                }
            }
        }
    }

    /**
     * 车辆关系维护
     * @param maps
     * @param roster02
     */
    public void sycVehicle(List<Map<String, String>> maps, String roster02) {
        maps.stream().forEach(item ->{
            String suspectId = item.get("suspectId");
            String groupIdstr = item.get("groupIds");
            String[] groupIds = groupIdstr.split(",");
            for (int i = 0; i < groupIds.length; i++) {
                String groupId = groupIds[i];
                if(StringUtils.isNotEmpty(groupId)){
                    Map mapWhite = new HashMap();
                    Map map1 = new HashMap();
                    Map map2 = new HashMap();
                    int no = 1;
                    int size = 1000;
                    map1.put("page", no);
                    map1.put("limit", size);
                    map2.put("groupid", groupId);
                    mapWhite.put("type","0");
                    mapWhite.put("plate",map2);
                    mapWhite.put("condition",map1);

                    String json = JSONObject.toJSONString(mapWhite);
                    Resp ajaxResult = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/search_bwdata", json);
                    if(ajaxResult.getData() != null){
                        AsyncManager.me().execute(AsyncFactory.maintainRelationVehicle(ajaxResult.getData().toString(),roster02,suspectId,groupId));
                    }
                    PageInfo page = ajaxResult.getPage();
                    if(page != null ) {
                        int total = page.getTotal();
                        int pages;
                        if (total % size > 0) {
                            pages = total / size + 1;
                        } else {
                            pages = total / size;
                        }
                        for (int j = 1; j <pages ; j++) {
                            map1.put("no", no + j);
                            mapWhite.put("page",map1);
                            String jsonFor = JSONObject.toJSONString(mapWhite);
                            Resp ajaxResultFor = HttpClientUtil.doPostJson(url+"/api/open/V1.0.0/vcm/intelligent_analysis/search_bwdata", jsonFor);
                            if(ajaxResult.getData() != null){
                                AsyncManager.me().execute(AsyncFactory.maintainRelationVehicle(ajaxResultFor.getData().toString(),roster02,suspectId,groupId));
                            }
                        }
                    }
                }
            }
        });
    }

    public void sycOneToFour() {
        AsyncManager.me().execute(AsyncFactory.maintainRelationOneToFour(oneFourGet));
    }
}

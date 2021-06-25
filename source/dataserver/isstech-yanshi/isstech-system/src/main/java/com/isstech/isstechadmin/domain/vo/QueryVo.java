package com.isstech.isstechadmin.domain.vo;

import com.isstech.common.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class QueryVo implements Serializable {

    private String communityId; //社区id

    private String communityChildId; //小区id

    private String communityHousesId; //房屋id

    private int type; //统计通行类型  1代表统计所有  2统计访客

    private String startTime;

    private String endTime;

    private List<String> personIds = new ArrayList<>();

    //分页数
    private Integer limitNum;

    //分页条数
    private Integer limitSize;

    private String startDate;

    private String delFlag;

    private String startTimeTwo;

    private String endTimeFour;

    private String otherData;

    private double rate;

    private List<Map<String,String>> dataAccessList;

    private boolean isAllDataAccess;

    public List<Map<String, String>> getDataAccessList() {
        return dataAccessList;
    }

    public void setDataAccessList(List<Map<String, String>> dataAccessList) {
        this.dataAccessList = dataAccessList;
    }

    public boolean isAllDataAccess() {
        return isAllDataAccess;
    }

    public void setAllDataAccess(boolean allDataAccess) {
        isAllDataAccess = allDataAccess;
    }


    public void setCommunityId(String communityId) {
        if(StringUtils.isBlank(communityId)||"undefined".equals(communityId) || "null".equals(communityId)) communityId = null ;
        this.communityId = communityId;
    }

    public void setCommunityChildId(String communityChildId) {
        if(StringUtils.isBlank(communityChildId)||"undefined".equals(communityChildId) || "null".equals(communityChildId)) communityChildId = null;
        this.communityChildId = communityChildId;
    }



}

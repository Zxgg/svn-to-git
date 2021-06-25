package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author zengxi
 * @version 1.0
 * @date 2021/5/8 6:28 下午
 **/
@Data
public class PersonDetailVo {

    /**
     * 网格id
     */
    private String  grid_id;

    /**
     * 总数
     */
    private Integer totalNum;

    private HashMap<String,Integer> sexGroupNum;

    /**
     * 各学位数量
     */
    private HashMap<String,Integer> degreeGroupNum;

    /**
     * 各年龄段数量
     */
    private HashMap<String,Integer> ageGroupNum;

    /**
     * 各名族数量
     */
    private HashMap<String,Integer> nationGroupNum;

    /**
     * 各关爱人员数量
     */
    private HashMap<String,Integer> careGroupNum;

    /**
     * 各特殊人群
     */
    private HashMap<String,Integer> specialGroupNum;

    /**
     * 各管控人员
     */
    private List<BasePersonnelInformation> controlPersons;

}

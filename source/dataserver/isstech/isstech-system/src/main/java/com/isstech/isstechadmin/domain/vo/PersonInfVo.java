package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zengxi
 * @version 1.0
 * @date 2021/5/9 4:35 下午
 **/
@Data
public class PersonInfVo {
    /**
     * 网格id
     */
    private String  grid_id;

    /**
     * 总数
     */
    private Integer totalNum;

    /**
     * 性别
     */
    private List<CommonMap> sexData;

    /**
     * 教育程度
     */
    private List<CommonMap> eduData;

    /**
     * 年龄段
     */
    private AgeGroupVo ageData;

    /**
     * 民族
     */
    private List<CommonMap> nationXData;

}

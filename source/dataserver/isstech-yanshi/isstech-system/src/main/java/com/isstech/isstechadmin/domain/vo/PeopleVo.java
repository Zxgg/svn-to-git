package com.isstech.isstechadmin.domain.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description 对应华为人员列表
 * @Author yzp
 * @Date 2020/12/30 14:08
 */
@Data
public class PeopleVo implements Serializable {

    /** 身份证类型 */
    private String credentialType;

    /** 身份证号 */
    private String credentialNumber;

    /** 出生日期 */
    private String bornTime;

    /** 性别 */
    private String gender;

    /** 姓名 */
    private String name;

    /** 国家 */
    private String country;

    /** 民族 */
    private String nationality;

    /** 职业 */
    private String occupation;

    /** 描述信息 */
    private String description;

    private Map pictures;


}

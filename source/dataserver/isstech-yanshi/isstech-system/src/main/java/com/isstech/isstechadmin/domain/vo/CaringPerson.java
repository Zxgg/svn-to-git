package com.isstech.isstechadmin.domain.vo;

import com.isstech.common.annotation.Excel;
import com.isstech.isstechadmin.domain.BaseCaringPerson;
import lombok.Data;

/**
 * 关爱人员信息补充
 * 
 * @author yzp
 * @date 2020-11-26
 */
@Data
public class CaringPerson extends BaseCaringPerson
{
    /** 社区名称 */
    @Excel(name = "社区名称")
    private String communityName;

    /** 小区名称 */
    @Excel(name = "小区名称")
    private String communityChildName;

    /** 人员姓名 */
    @Excel(name = "姓名")
    private String personName;

    /** 联系方式 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 性别 */
    @Excel(name = "性别",readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 证件类型代码（默认为中国身份证） */
    @Excel(name = "证件类型",readConverterExp = "111=身份证,114=中国人民解放军军官证,115=中国人民武装警察部队警官证,414=护照,554=外国人居留证,511=台湾居民来往大陆通行证,516=港澳居民来往内地通行证")
    private String idType;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String identificationNumber;

    @Excel(name = "关爱类型")
    private String typeName;

    /** 当前住所信息 */
    @Excel(name = "详细地址")
    private String addressDetail;

    /** 图片地址 */
    private String imgUrl;

    /** 年龄 */
    private String age;

    private String pics;

}

package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 疫情信息收集对象 t_person_epidemic_state
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Data
public class TPersonEpidemicState extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 社区ID */
    /*@Excel(name = "社区ID")*/
    private String communityId;

    /** 本条记录所属社区名字 */
    @Excel(name = "本条记录所属社区名字")
    private String communityName;

    /** 本条记录所属小区ID */
    /*@Excel(name = "本条记录所属小区ID")*/
    private String communityChildId;

    /** 本条记录所属小区名字 */
    @Excel(name = "本条记录所属小区名字")
    private String communityChildName;

    /** 唯一标识 */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idCard;

    /** 省市县三级 */
    @Excel(name = "现住地点-省市县三级")
    private String addressArea;

    /** 现住详址 */
    @Excel(name = "现住详址")
    private String addressDetail;

    /** 工作地点-省市区三级 */
    @Excel(name = "工作地点-省市区三级")
    private String addressJobArea;

    /** 工作地点详址 */
    @Excel(name = "工作地点详址")
    private String addressJobDetail;

    /** 是否有防疫绿码 */
    @Excel(name = "是否有防疫绿码")
    private String epidemicGreenCode;

    /** 是否合租 */
    @Excel(name = "是否合租")
    private String roommates;

    /** 合租/同住人员是否有疑似症状 */
    @Excel(name = "合租/同住人员是否有疑似症状")
    private String roommatesSuspectedSymptoms;

    /** 合租/同住人员是否有病患接触史 */
    @Excel(name = "合租/同住人员是否有病患接触史")
    private String roommatesPatientExposureHistory;

    /** 14天内是否离开本市 */
    @Excel(name = "14天内是否离开本市")
    private String leaveLocal;

    /** 14天内出行信息 */
    @Excel(name = "14天内出行信息")
    private String leaveInformation;

    /** 14天内是否去过中高风险区 */
    @Excel(name = "14天内是否去过中高风险区")
    private String havaBeenRiskArea;

    /** 14天内去过中高风险区的记录 */
    @Excel(name = "14天内去过中高风险区的记录")
    private String havaBeenRiskAreaInformation;

    /** 14天内同住人员是否去过中高风险区 */
    @Excel(name = "14天内同住人员是否去过中高风险区")
    private String roommatesHavaBeenRiskArea;

    /** 同住人员14天内去过中高风险区的记录 */
    @Excel(name = "同住人员14天内去过中高风险区的记录")
    private String roommatesHavaBeenRiskAreaInformation;

    /** 是否14天内回国 */
    @Excel(name = "是否14天内回国")
    private String backHome;

    /** 同住人员是否14天内回国 */
    @Excel(name = "同住人员是否14天内回国")
    private String roommatesBackHome;

    /** 是否被要求医学观察 */
    @Excel(name = "是否被要求医学观察")
    private String medicalWatch;

    /** 是否做过核酸检测  （阴性阳性未出结果未检测） */
    @Excel(name = "是否做过核酸检测  ")
    private String nucleicAcidTesting;

    /** 是否已经注射第一针疫苗 */
    @Excel(name = "是否已经注射第一针疫苗")
    private String injectionVaccineOne;

    /** 第一针疫苗 注射时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第一针疫苗 注射时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date injectionVaccineOneTime;

    /** 是否已经注射第二针疫苗 */
    @Excel(name = "是否已经注射第二针疫苗")
    private String injectionVaccineTwo;

    /** 第二针疫苗 注射时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第二针疫苗 注射时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date injectionVaccineTwoTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

}

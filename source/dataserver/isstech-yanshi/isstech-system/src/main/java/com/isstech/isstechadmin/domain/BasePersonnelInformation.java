package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 人员信息对象 base_personnel_information
 * 
 * @author yzp
 * @date 2020-11-23
 */
@Data
public class BasePersonnelInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 曾用名 */
//    @Excel(name = "曾用名")
    private String oldName;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 国籍（地区） */
//    @Excel(name = "国籍", readConverterExp = "地=区")
    private String nationality;

    /** 证件类型代码（默认为中国身份证） */
    @Excel(name = "证件类型代码", readConverterExp = "默认为中国身份证")
    private String idType;

    /** 证件号码(默认是18位身份证号码) */
    @Excel(name = "证件号码(默认是18位身份证号码)")
    private String identificationNumber;

    /** 证件有效期开始时间 */
//    @Excel(name = "证件有效期开始时间")
    private String idcardStartDate;

    /** 证件有效期结束时间 */
//    @Excel(name = "证件有效期结束时间")
    private String idcardEndDate;

    /** 民族 */
//    @Excel(name = "民族")
    private String nation;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String location;

    /** 户籍详址 */
    @Excel(name = "户籍详址")
    private String residenceDetail;

    /** 现住地区划 */
    @Excel(name = "现住地区划")
    private String address;

    /** 现住详址 */
    @Excel(name = "现住详址")
    private String addressDetail;

    /** 婚姻状况(0否1是,默认0) */
    @Excel(name = "婚姻状况",readConverterExp = "0=否,1=是,2=离异,3=丧偶")
    private String marital;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String political;

    /** 学历 */
    @Excel(name = "学历")
    private String degree;

    /** 宗教信仰 */
//    @Excel(name = "宗教信仰")
    private String religion;

    /** 职业类别 */
//    @Excel(name = "职业类别")
    private String profession;

    /** 职业信息（不是字典项） */
//    @Excel(name = "职业信息", readConverterExp = "不=是字典项")
    private String professioDetail;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 特殊人员,(0否1是,默认0) */
    @Excel(name = "特殊人员",readConverterExp = "0=否,1=是")
    private String special;

    /** 管控限制人员,(0否1是,默认0) */
    @Excel(name = "管控限制人员",readConverterExp = "0=否,1=是")
    private String limited;

    /** 关爱人员,(0否1是,默认0) */
    @Excel(name = "关爱人员",readConverterExp = "0=否,1=是")
    private String caring;

    /** 是否是流动人员,(0否1是,默认0) */
    @Excel(name = "是否是流动人员",readConverterExp = "0=否,1=是")
    private String floating;

    /** 最后入境（国境）时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "最后入境", readConverterExp = "国=境")
    private Date arrivalCountryDate;

    /** 最后入境（省级边界）时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "最后入境", readConverterExp = "省=级边界")
    private Date arrivalProvinceDate;

    /** 预计离开日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "预计离开日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date departureDate;

    /** 流入原因 */
//    @Excel(name = "流入原因")
    private String floatingReasons;

    /** 流入地办证类型 */
//    @Excel(name = "流入地办证类型")
    private String cardType;

    /** 流入地办证机关 */
//    @Excel(name = "流入地办证机关")
    private String cardHandlingAgency;

    /** 流入地证件号码 */
//    @Excel(name = "流入地证件号码")
    private String cardNum;

    /** 流入地证件登记日期 */
//    @Excel(name = "流入地证件登记日期")
    private String cardRecordDate;

    /** 照片列表，多张照片以逗号隔开，默认显示第一张 */
    @Excel(name = "照片列表，多张照片以逗号隔开，默认显示第一张")
    private String pics;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认",readConverterExp = "0=否,1=是")
    private String checked;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date brithDate;

    /** 服务处所（当前工作单位信息） */
    private Date servicePremises;

    /** 唯一标识 */
    private String communityId;
    /** 唯一标识 */
    private String communityChildId;
    /** 图片 */
    private String imgUrl;
    /** 人户一致标志 */
    private String isSame;
    /** 户号 */
    private String doorNo;
    /** 户主身份证号 */
    private String householdIdc;
    /** 户主姓名 */
    private String householdName;
    /** 与户主关系 */
    private String householdRelation;
    /** 户主联系方式 */
    private String householdPhone;
    /** 通过“文件上传”接口上传图片后系统返回的图片id */
    private String fileId;

    /** 人员类型 01户籍人口 02 流动人口 03 国外人员 */
    @Excel(name = "人员类型",readConverterExp = "01=户籍人口,02=流动人口,03=国外人员")
    private String personType;

    /** 是否留守  0代表非留守 01表示留守老人 02表示留守妇女 03表示留守儿童 */
    @Excel(name = "是否留守",readConverterExp = "0=非留守,01=留守老人,02=留守妇女,03=留守儿童")
    private String stayBehind;

    /** 身份证正面照片 */
    @Excel(name = "身份证正面照片")
    private String identificationPositive;

    /** 身份证反面照片 */
    @Excel(name = "身份证反面照片")
    private String identificationReverse;

    /** 自定义标签 */
    @Excel(name = "自定义标签")
    private String customTag;

    /**
     * 网格id
     */
    private String gridId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getIdcardStartDate() {
        return idcardStartDate;
    }

    public void setIdcardStartDate(String idcardStartDate) {
        this.idcardStartDate = idcardStartDate;
    }

    public String getIdcardEndDate() {
        return idcardEndDate;
    }

    public void setIdcardEndDate(String idcardEndDate) {
        this.idcardEndDate = idcardEndDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResidenceDetail() {
        return residenceDetail;
    }

    public void setResidenceDetail(String residenceDetail) {
        this.residenceDetail = residenceDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessioDetail() {
        return professioDetail;
    }

    public void setProfessioDetail(String professioDetail) {
        this.professioDetail = professioDetail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getLimited() {
        return limited;
    }

    public void setLimited(String limited) {
        this.limited = limited;
    }

    public String getCaring() {
        return caring;
    }

    public void setCaring(String caring) {
        this.caring = caring;
    }

    public String getFloating() {
        return floating;
    }

    public void setFloating(String floating) {
        this.floating = floating;
    }

    public Date getArrivalCountryDate() {
        return arrivalCountryDate;
    }

    public void setArrivalCountryDate(Date arrivalCountryDate) {
        this.arrivalCountryDate = arrivalCountryDate;
    }

    public Date getArrivalProvinceDate() {
        return arrivalProvinceDate;
    }

    public void setArrivalProvinceDate(Date arrivalProvinceDate) {
        this.arrivalProvinceDate = arrivalProvinceDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getFloatingReasons() {
        return floatingReasons;
    }

    public void setFloatingReasons(String floatingReasons) {
        this.floatingReasons = floatingReasons;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardHandlingAgency() {
        return cardHandlingAgency;
    }

    public void setCardHandlingAgency(String cardHandlingAgency) {
        this.cardHandlingAgency = cardHandlingAgency;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardRecordDate() {
        return cardRecordDate;
    }

    public void setCardRecordDate(String cardRecordDate) {
        this.cardRecordDate = cardRecordDate;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }

    public Date getServicePremises() {
        return servicePremises;
    }

    public void setServicePremises(Date servicePremises) {
        this.servicePremises = servicePremises;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityChildId() {
        return communityChildId;
    }

    public void setCommunityChildId(String communityChildId) {
        this.communityChildId = communityChildId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsSame() {
        return isSame;
    }

    public void setIsSame(String isSame) {
        this.isSame = isSame;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getHouseholdIdc() {
        return householdIdc;
    }

    public void setHouseholdIdc(String householdIdc) {
        this.householdIdc = householdIdc;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public String getHouseholdRelation() {
        return householdRelation;
    }

    public void setHouseholdRelation(String householdRelation) {
        this.householdRelation = householdRelation;
    }

    public String getHouseholdPhone() {
        return householdPhone;
    }

    public void setHouseholdPhone(String householdPhone) {
        this.householdPhone = householdPhone;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getStayBehind() {
        return stayBehind;
    }

    public void setStayBehind(String stayBehind) {
        this.stayBehind = stayBehind;
    }

    public String getIdentificationPositive() {
        return identificationPositive;
    }

    public void setIdentificationPositive(String identificationPositive) {
        this.identificationPositive = identificationPositive;
    }

    public String getIdentificationReverse() {
        return identificationReverse;
    }

    public void setIdentificationReverse(String identificationReverse) {
        this.identificationReverse = identificationReverse;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag;
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }
}

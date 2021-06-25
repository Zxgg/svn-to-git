package com.isstech.isstechadmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 物业评价 对象 base_property_company_evaluation
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Data
public class BasePropertyCompanyEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 评价月份  yyyy-MM,一般来说只能评价当月和上个月 */
    @Excel(name = "评价月份  yyyy-MM,一般来说只能评价当月和上个月")
    private String monthly;

    /** 业主的personID */
    @Excel(name = "业主的personID")
    private String personId;

    /** 社区ID */
    @Excel(name = "社区ID")
    private String communityId;

    /** 小区ID */
    @Excel(name = "小区ID")
    private String communityChildId;

    /** 评价等级，1-10分，10分为最满意，6分为及格 */
    @Excel(name = "评价等级，1-10分，10分为最满意，6分为及格")
    private String evaluation;

    /** 是否匿名评价 */
    @Excel(name = "是否匿名评价")
    private String anonymous;

    /** 回复 */
    @Excel(name = "回复")
    private String reply;

    /** 回复人 */
    @Excel(name = "回复人")
    private String replyer;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;



}

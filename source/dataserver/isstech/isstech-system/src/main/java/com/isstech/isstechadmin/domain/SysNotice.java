package com.isstech.isstechadmin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Description 通知公告表 sys_notice
 * @Author yzp
 * @Date 2020/12/21 13:40
 */
@Data
public class SysNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private String id;

    /** 公告标题 */
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String title;

    /** 公告类型（1通知 2公告） */
    private String type;

    /** 公告内容 */
    private String content;

    /** 公告状态（0正常 1关闭） */
    private String status;

    /** 创建单位 */
    private String deptId;

    /**删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 推送类型(1 pc  2 app) */
    private String pushType;

    /** 公告级别(1:普通 2:一般 3:重要) */
    private String level;

    /** 附件url */
    private String files;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;

    /** 发布状态(0:不发布,1发布) */
    private String isPublish;

    /** 更新单位 */
    private String updateDeptId;
}

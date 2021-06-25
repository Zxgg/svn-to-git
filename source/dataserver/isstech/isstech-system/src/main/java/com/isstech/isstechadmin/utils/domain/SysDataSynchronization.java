package com.isstech.isstechadmin.utils.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 数据同步对象 sys_data_synchronization
 *
 * @author yzp
 * @date 2021-02-02
 */
@Data
public class SysDataSynchronization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 同步类型（0-向下同步，1-向上同步） */
    @Excel(name = "同步类型", readConverterExp = "0=-向下同步，1-向上同步")
    private String type;

    /** 本部门名称 */
    @Excel(name = "本部门名称")
    private String name;

    /** 本部门的部门id */
    @Excel(name = "本部门的部门id")
    private String deptid;

    /** 本部分唯一标识code值 */
    @Excel(name = "本部分唯一标识code值")
    private String code;

    /** 同步部门名称 */
    @Excel(name = "同步部门名称")
    private String synName;

    /** 本服务端口 */
    @Excel(name = "本服务端口")
    private String port;

    /** 同步ip地址 */
    @Excel(name = "同步ip地址")
    private String synIp;

    /** 同步数据 */
    @Excel(name = "同步数据")
    private String synData;

    /** 同步成功标识（0-未同步，1-已同步） */
    @Excel(name = "同步成功标识", readConverterExp = "0=-未同步，1-已同步")
    private String synFlag;

    /** 本条数据同步到的部门deptId */
    @Excel(name = "本条数据同步到的部门deptId")
    private String synDeptid;

    /** 本条数据所属最终部门deptId */
    @Excel(name = "本条数据所属最终部门deptId")
    private String synEndDeptid;

    /** 同步消息时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "同步消息时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 删除标识（0-正常，1-删除） */
    private String delFlag;



}

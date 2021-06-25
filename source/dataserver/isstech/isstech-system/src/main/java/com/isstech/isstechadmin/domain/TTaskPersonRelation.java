package com.isstech.isstechadmin.domain;

import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 人脸和人员对应关系任务 对象 t_task_person_relation
 * 
 * @author yzp
 * @date 2020-12-17
 */
@Data
public class TTaskPersonRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员名单id */
    private String peopleId;

    /** 人员id */
    private String personId;

    /** 社区id */
    @Excel(name = "社区id")
    private String communityId;

    /** 小区id */
    @Excel(name = "小区id")
    private String communityChildId;

    /** 分析任务ID */
    @Excel(name = "分析任务ID")
    private String taskId;

    /** 布控ID */
    private String suspectId;

    /** 人脸ids */
    @Excel(name = "人脸ids")
    private String faceId;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 来源 01住户 02访客 03临时访客  */
    @Excel(name = "来源 01住户 02访客 03临时访客 ")
    private String sourceType;

    /** 名单类型 01 白名单  02 黑名单 */
    @Excel(name = "名单类型 01 白名单  02 黑名单")
    private String rosterType;

    public void setPeopleId(String peopleId) 
    {
        this.peopleId = peopleId;
    }

    public String getPeopleId() 
    {
        return peopleId;
    }
    public void setPersonId(String personId) 
    {
        this.personId = personId;
    }

    public String getPersonId() 
    {
        return personId;
    }
    public void setCommunityId(String communityId) 
    {
        this.communityId = communityId;
    }

    public String getCommunityId() 
    {
        return communityId;
    }
    public void setCommunityChildId(String communityChildId) 
    {
        this.communityChildId = communityChildId;
    }

    public String getCommunityChildId() 
    {
        return communityChildId;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setSuspectId(String suspectId) 
    {
        this.suspectId = suspectId;
    }

    public String getSuspectId() 
    {
        return suspectId;
    }
    public void setFaceId(String faceId) 
    {
        this.faceId = faceId;
    }

    public String getFaceId() 
    {
        return faceId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setSourceType(String sourceType) 
    {
        this.sourceType = sourceType;
    }

    public String getSourceType() 
    {
        return sourceType;
    }
    public void setRosterType(String rosterType) 
    {
        this.rosterType = rosterType;
    }

    public String getRosterType() 
    {
        return rosterType;
    }

}

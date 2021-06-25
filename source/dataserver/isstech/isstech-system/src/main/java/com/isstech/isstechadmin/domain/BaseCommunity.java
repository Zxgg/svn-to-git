package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * @Description 社区信息对象 base_community
 * @Author yzp
 * @Date 2020/11/18 14:28
 */
public class BaseCommunity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 唯一标识 */
    private String id;

    /** 社区名称 */
    @Excel(name = "社区名称")
    private String name;

    /** 社区地址-省 */
    @Excel(name = "社区地址-省")
    private String addressProvincial;

    /** 社区地址-市 */
    @Excel(name = "社区地址-市")
    private String addressCitie;

    /** 社区地址-县 */
    @Excel(name = "社区地址-县")
    private String addressCountie;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String code;

    /** 街道 */
    @Excel(name = "街道")
    private String untitled;

    /** 经度 */
    @Excel(name = "经度")
    private String longitud;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 多个部门组织 */
    private String deptIds;

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAddressProvincial(String addressProvincial) 
    {
        this.addressProvincial = addressProvincial;
    }

    public String getAddressProvincial() 
    {
        return addressProvincial;
    }
    public void setAddressCitie(String addressCitie) 
    {
        this.addressCitie = addressCitie;
    }

    public String getAddressCitie() 
    {
        return addressCitie;
    }
    public void setAddressCountie(String addressCountie) 
    {
        this.addressCountie = addressCountie;
    }

    public String getAddressCountie() 
    {
        return addressCountie;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setUntitled(String untitled) 
    {
        this.untitled = untitled;
    }

    public String getUntitled() 
    {
        return untitled;
    }
    public void setLongitud(String longitud) 
    {
        this.longitud = longitud;
    }

    public String getLongitud() 
    {
        return longitud;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("id", getId())
            .append("name", getName())
            .append("addressProvincial", getAddressProvincial())
            .append("addressCitie", getAddressCitie())
            .append("addressCountie", getAddressCountie())
            .append("code", getCode())
            .append("untitled", getUntitled())
            .append("longitud", getLongitud())
            .append("latitude", getLatitude())
            .toString();
    }
}

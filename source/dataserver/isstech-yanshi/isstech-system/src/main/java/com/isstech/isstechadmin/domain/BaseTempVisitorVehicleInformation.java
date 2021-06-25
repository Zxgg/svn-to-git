package com.isstech.isstechadmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.isstech.common.annotation.Excel;
import com.isstech.common.core.domain.BaseEntity;

/**
 * 临时访客车辆信息对象 base_temp_visitor_vehicle_information
 * 
 * @author yzp
 * @date 2020-11-27
 */
public class BaseTempVisitorVehicleInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String id;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String plate;

    /** 车型 */
    @Excel(name = "车型",readConverterExp = "00=轿车,01=SUV,02=厢式车,03=多用途汽车,04=跑车,05=小型货车,06=中型货车,07=大型货车,08=小型巴士,09=大型巴士")
    private String model;

    /** 车品牌 */
    @Excel(name = "车品牌")
    private String brand;

    /** 具体品牌型号 */
    @Excel(name = "具体品牌型号")
    private String specificBrand;

    /** 座位数 */
    @Excel(name = "座位数")
    private Long numberOfSeats;

    /** 驾驶员姓名 */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /** 驾驶员身份证号码(18位身份证号码) */
    @Excel(name = "驾驶员身份证号码(18位身份证号码)")
    private String driverIdentificationNumber;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String driverSex;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String driverPhoneNumber;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 是否已经审核确认,(0否1是,默认0) */
    @Excel(name = "是否已经审核确认,(0否1是,默认0)")
    private String checked;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPlate(String plate) 
    {
        this.plate = plate;
    }

    public String getPlate() 
    {
        return plate;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setSpecificBrand(String specificBrand) 
    {
        this.specificBrand = specificBrand;
    }

    public String getSpecificBrand() 
    {
        return specificBrand;
    }
    public void setNumberOfSeats(Long numberOfSeats) 
    {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getNumberOfSeats() 
    {
        return numberOfSeats;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setDriverIdentificationNumber(String driverIdentificationNumber) 
    {
        this.driverIdentificationNumber = driverIdentificationNumber;
    }

    public String getDriverIdentificationNumber() 
    {
        return driverIdentificationNumber;
    }
    public void setDriverSex(String driverSex) 
    {
        this.driverSex = driverSex;
    }

    public String getDriverSex() 
    {
        return driverSex;
    }
    public void setDriverPhoneNumber(String driverPhoneNumber) 
    {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public String getDriverPhoneNumber() 
    {
        return driverPhoneNumber;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setChecked(String checked) 
    {
        this.checked = checked;
    }

    public String getChecked() 
    {
        return checked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("plate", getPlate())
            .append("model", getModel())
            .append("brand", getBrand())
            .append("specificBrand", getSpecificBrand())
            .append("numberOfSeats", getNumberOfSeats())
            .append("driverName", getDriverName())
            .append("driverIdentificationNumber", getDriverIdentificationNumber())
            .append("driverSex", getDriverSex())
            .append("driverPhoneNumber", getDriverPhoneNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("checked", getChecked())
            .toString();
    }
}

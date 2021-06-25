package com.isstech.isstechadmin.domain.query;

/**
 * @author fanShiHao
 * @date 2021/5/6 13:21
 * @description :做车辆车主等联合查询的接收参数dto
 */
public class QueryVehicleInformation {
    /** 车牌号码 */
    private String plate;
    /** 车主姓名 */
    private String name;
    /** 证件号码 */
    private String identificationNumber;
    /** 分页起始值 */
    private String pageNum;
    /** 每页最大值 */
    private String pageSize;

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPlate() {
        return plate;
    }

    public String getName() {
        return name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getPageNum() {
        return pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }
}

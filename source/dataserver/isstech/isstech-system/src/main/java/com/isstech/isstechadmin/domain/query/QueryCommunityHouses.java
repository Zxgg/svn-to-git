package com.isstech.isstechadmin.domain.query;

/**
 * @author fanShiHao
 * @date 2021/5/6 13:45
 * @description
 */
public class QueryCommunityHouses {
    /** 房屋名称 */
    private String name;
    /** 人员名称 */
    private String personName;
    /** 每页起始值 */
    private String pageNum;
    /** 每页最大值 */
    private String pageSize;

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPageNum() {
        return pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }
}

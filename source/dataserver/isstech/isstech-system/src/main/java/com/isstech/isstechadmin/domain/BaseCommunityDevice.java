package com.isstech.isstechadmin.domain;

import com.isstech.common.core.domain.BaseEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @author author
 */
@Component
public class BaseCommunityDevice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1621495543834L;


    /**
     * 主键
     * 主键
     * isNullAble:0
     */
    private String id;

    /**
     * 设备照片
     * isNullAble:1
     */
    private String equipmentPhotos;

    /**
     * 设备名
     * isNullAble:0
     */
    private String name;

    /**
     * 设备类型
     * isNullAble:1
     */
    @NonNull
    private String type;

    /**
     * 序列号
     * isNullAble:1
     */
    private String sn;

    /**
     * 编号
     * isNullAble:1
     */
    private String number;

    /**
     * 型号
     * isNullAble:1
     */
    private String model;

    /**
     * 规格
     * isNullAble:1
     */
    private String specifications;

    /**
     * 制造商
     * isNullAble:1
     */
    private String manufacturer;

    /**
     * 出场日期
     * isNullAble:1
     */
    private Date outTime;

    /**
     * 安装日期
     * isNullAble:1
     */
    private Date installTime;

    /**
     * 安装地点
     * isNullAble:1
     */
    private String installAddress;

    /**
     * 厂家联系人
     * isNullAble:1
     */
    private String manufacturerContacts;

    /**
     * 厂家联系人电话
     * isNullAble:1
     */
    private String manufacturerContactsNumber;

    /**
     * 设备定位状态
     * isNullAble:1
     */
    private String locationInfo;

    /**
     * 设备状态
     * isNullAble:1
     */
    private String status;

    /**
     * 审核范围
     * isNullAble:1
     */
    private String auditScope;

    /**
     * 社区id
     * isNullAble:0
     */
    @NonNull
    private String communityId;

    /**
     * 社区组织id
     * isNullAble:1
     */
    private String communityOrgId;

    /**
     * 创建人
     * isNullAble:1
     */
    // private String createBy;

    /**
     * 创建时间
     * isNullAble:0
     */
    // @NonNull
    //private Date createTime;

    /**
     * 更新人
     * isNullAble:1
     */
    // private String updateBy;

    /**
     * 更新时间
     * isNullAble:1
     */
    // private Date updateTime;

    /**
     * 备注
     * isNullAble:1
     */
    private String remark;

    /**
     * 删除标志（0代表存在 1代表删除）
     * isNullAble:0,defaultVal:0
     */
    private String delFlag;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setEquipmentPhotos(String equipmentPhotos) {
        this.equipmentPhotos = equipmentPhotos;
    }

    public String getEquipmentPhotos() {
        return this.equipmentPhotos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return this.sn;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSpecifications() {
        return this.specifications;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getOutTime() {
        return this.outTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getInstallTime() {
        return this.installTime;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

    public String getInstallAddress() {
        return this.installAddress;
    }

    public void setManufacturerContacts(String manufacturerContacts) {
        this.manufacturerContacts = manufacturerContacts;
    }

    public String getManufacturerContacts() {
        return this.manufacturerContacts;
    }

    public void setManufacturerContactsNumber(String manufacturerContactsNumber) {
        this.manufacturerContactsNumber = manufacturerContactsNumber;
    }

    public String getManufacturerContactsNumber() {
        return this.manufacturerContactsNumber;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getLocationInfo() {
        return this.locationInfo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAuditScope(String auditScope) {
        this.auditScope = auditScope;
    }

    public String getAuditScope() {
        return this.auditScope;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityId() {
        return this.communityId;
    }

    public void setCommunityOrgId(String communityOrgId) {
        this.communityOrgId = communityOrgId;
    }

    public String getCommunityOrgId() {
        return this.communityOrgId;
    }

    public void setCreateBy(String createBy) {
        super.setCreateBy(createBy);
    }

    public String getCreateBy() {
        return super.getCreateBy();
    }

    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    public Date getCreateTime() {
        return super.getCreateTime();
    }

    public void setUpdateBy(String updateBy) {
        super.setUpdateBy(updateBy);
    }

    public String getUpdateBy() {
        return super.getUpdateBy();
    }

    public void setUpdateTime(Date updateTime) {
        super.setUpdateTime(updateTime);
    }

    public Date getUpdateTime() {
        return super.getUpdateTime();
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    @Override
    public String toString() {
        return "BaseCommunityDevice{" +
                "id='" + id + '\'' +
                "equipmentPhotos='" + equipmentPhotos + '\'' +
                "name='" + name + '\'' +
                "type='" + type + '\'' +
                "sn='" + sn + '\'' +
                "number='" + number + '\'' +
                "model='" + model + '\'' +
                "specifications='" + specifications + '\'' +
                "manufacturer='" + manufacturer + '\'' +
                "outTime='" + outTime + '\'' +
                "installTime='" + installTime + '\'' +
                "installAddress='" + installAddress + '\'' +
                "manufacturerContacts='" + manufacturerContacts + '\'' +
                "manufacturerContactsNumber='" + manufacturerContactsNumber + '\'' +
                "locationInfo='" + locationInfo + '\'' +
                "status='" + status + '\'' +
                "auditScope='" + auditScope + '\'' +
                "communityId='" + communityId + '\'' +
                "communityOrgId='" + communityOrgId + '\'' +
                "createBy='" + super.getCreateBy() + '\'' +
                "createTime='" + super.getCreateTime() + '\'' +
                "updateBy='" + super.getUpdateBy() + '\'' +
                "updateTime='" + super.getUpdateTime() + '\'' +
                "remark='" + remark + '\'' +
                "delFlag='" + delFlag + '\'' +
                '}';
    }

    public static Builder Build() {
        return new Builder();
    }

    public static ConditionBuilder ConditionBuild() {
        return new ConditionBuilder();
    }

    public static UpdateBuilder UpdateBuild() {
        return new UpdateBuilder();
    }

    public static QueryBuilder QueryBuild() {
        return new QueryBuilder();
    }

    public static class UpdateBuilder {

        private BaseCommunityDevice set;

        private ConditionBuilder where;

        public UpdateBuilder set(BaseCommunityDevice set) {
            this.set = set;
            return this;
        }

        public BaseCommunityDevice getSet() {
            return this.set;
        }

        public UpdateBuilder where(ConditionBuilder where) {
            this.where = where;
            return this;
        }

        public ConditionBuilder getWhere() {
            return this.where;
        }

        public UpdateBuilder build() {
            return this;
        }
    }

    public static class QueryBuilder extends BaseCommunityDevice {
        /**
         * 需要返回的列
         */
        private Map<String, Object> fetchFields;

        public Map<String, Object> getFetchFields() {
            return this.fetchFields;
        }

        private List<String> idList;

        public List<String> getIdList() {
            return this.idList;
        }


        private List<String> fuzzyId;

        public List<String> getFuzzyId() {
            return this.fuzzyId;
        }

        private List<String> rightFuzzyId;

        public List<String> getRightFuzzyId() {
            return this.rightFuzzyId;
        }

        private List<String> equipmentPhotosList;

        public List<String> getEquipmentPhotosList() {
            return this.equipmentPhotosList;
        }


        private List<String> fuzzyEquipmentPhotos;

        public List<String> getFuzzyEquipmentPhotos() {
            return this.fuzzyEquipmentPhotos;
        }

        private List<String> rightFuzzyEquipmentPhotos;

        public List<String> getRightFuzzyEquipmentPhotos() {
            return this.rightFuzzyEquipmentPhotos;
        }

        private List<String> nameList;

        public List<String> getNameList() {
            return this.nameList;
        }


        private List<String> fuzzyName;

        public List<String> getFuzzyName() {
            return this.fuzzyName;
        }

        private List<String> rightFuzzyName;

        public List<String> getRightFuzzyName() {
            return this.rightFuzzyName;
        }

        private List<String> typeList;

        public List<String> getTypeList() {
            return this.typeList;
        }


        private List<String> fuzzyType;

        public List<String> getFuzzyType() {
            return this.fuzzyType;
        }

        private List<String> rightFuzzyType;

        public List<String> getRightFuzzyType() {
            return this.rightFuzzyType;
        }

        private List<String> snList;

        public List<String> getSnList() {
            return this.snList;
        }


        private List<String> fuzzySn;

        public List<String> getFuzzySn() {
            return this.fuzzySn;
        }

        private List<String> rightFuzzySn;

        public List<String> getRightFuzzySn() {
            return this.rightFuzzySn;
        }

        private List<String> numberList;

        public List<String> getNumberList() {
            return this.numberList;
        }


        private List<String> fuzzyNumber;

        public List<String> getFuzzyNumber() {
            return this.fuzzyNumber;
        }

        private List<String> rightFuzzyNumber;

        public List<String> getRightFuzzyNumber() {
            return this.rightFuzzyNumber;
        }

        private List<String> modelList;

        public List<String> getModelList() {
            return this.modelList;
        }


        private List<String> fuzzyModel;

        public List<String> getFuzzyModel() {
            return this.fuzzyModel;
        }

        private List<String> rightFuzzyModel;

        public List<String> getRightFuzzyModel() {
            return this.rightFuzzyModel;
        }

        private List<String> specificationsList;

        public List<String> getSpecificationsList() {
            return this.specificationsList;
        }


        private List<String> fuzzySpecifications;

        public List<String> getFuzzySpecifications() {
            return this.fuzzySpecifications;
        }

        private List<String> rightFuzzySpecifications;

        public List<String> getRightFuzzySpecifications() {
            return this.rightFuzzySpecifications;
        }

        private List<String> manufacturerList;

        public List<String> getManufacturerList() {
            return this.manufacturerList;
        }


        private List<String> fuzzyManufacturer;

        public List<String> getFuzzyManufacturer() {
            return this.fuzzyManufacturer;
        }

        private List<String> rightFuzzyManufacturer;

        public List<String> getRightFuzzyManufacturer() {
            return this.rightFuzzyManufacturer;
        }

        private List<Date> outTimeList;

        public List<Date> getOutTimeList() {
            return this.outTimeList;
        }

        private Date outTimeSt;

        private Date outTimeEd;

        public Date getOutTimeSt() {
            return this.outTimeSt;
        }

        public Date getOutTimeEd() {
            return this.outTimeEd;
        }

        private List<Date> installTimeList;

        public List<Date> getInstallTimeList() {
            return this.installTimeList;
        }

        private Date installTimeSt;

        private Date installTimeEd;

        public Date getInstallTimeSt() {
            return this.installTimeSt;
        }

        public Date getInstallTimeEd() {
            return this.installTimeEd;
        }

        private List<String> installAddressList;

        public List<String> getInstallAddressList() {
            return this.installAddressList;
        }


        private List<String> fuzzyInstallAddress;

        public List<String> getFuzzyInstallAddress() {
            return this.fuzzyInstallAddress;
        }

        private List<String> rightFuzzyInstallAddress;

        public List<String> getRightFuzzyInstallAddress() {
            return this.rightFuzzyInstallAddress;
        }

        private List<String> manufacturerContactsList;

        public List<String> getManufacturerContactsList() {
            return this.manufacturerContactsList;
        }


        private List<String> fuzzyManufacturerContacts;

        public List<String> getFuzzyManufacturerContacts() {
            return this.fuzzyManufacturerContacts;
        }

        private List<String> rightFuzzyManufacturerContacts;

        public List<String> getRightFuzzyManufacturerContacts() {
            return this.rightFuzzyManufacturerContacts;
        }

        private List<String> manufacturerContactsNumberList;

        public List<String> getManufacturerContactsNumberList() {
            return this.manufacturerContactsNumberList;
        }


        private List<String> fuzzyManufacturerContactsNumber;

        public List<String> getFuzzyManufacturerContactsNumber() {
            return this.fuzzyManufacturerContactsNumber;
        }

        private List<String> rightFuzzyManufacturerContactsNumber;

        public List<String> getRightFuzzyManufacturerContactsNumber() {
            return this.rightFuzzyManufacturerContactsNumber;
        }

        private List<String> locationInfoList;

        public List<String> getLocationInfoList() {
            return this.locationInfoList;
        }


        private List<String> fuzzyLocationInfo;

        public List<String> getFuzzyLocationInfo() {
            return this.fuzzyLocationInfo;
        }

        private List<String> rightFuzzyLocationInfo;

        public List<String> getRightFuzzyLocationInfo() {
            return this.rightFuzzyLocationInfo;
        }

        private List<String> statusList;

        public List<String> getStatusList() {
            return this.statusList;
        }


        private List<String> fuzzyStatus;

        public List<String> getFuzzyStatus() {
            return this.fuzzyStatus;
        }

        private List<String> rightFuzzyStatus;

        public List<String> getRightFuzzyStatus() {
            return this.rightFuzzyStatus;
        }

        private List<String> auditScopeList;

        public List<String> getAuditScopeList() {
            return this.auditScopeList;
        }


        private List<String> fuzzyAuditScope;

        public List<String> getFuzzyAuditScope() {
            return this.fuzzyAuditScope;
        }

        private List<String> rightFuzzyAuditScope;

        public List<String> getRightFuzzyAuditScope() {
            return this.rightFuzzyAuditScope;
        }

        private List<String> communityIdList;

        public List<String> getCommunityIdList() {
            return this.communityIdList;
        }


        private List<String> fuzzyCommunityId;

        public List<String> getFuzzyCommunityId() {
            return this.fuzzyCommunityId;
        }

        private List<String> rightFuzzyCommunityId;

        public List<String> getRightFuzzyCommunityId() {
            return this.rightFuzzyCommunityId;
        }

        private List<String> communityOrgIdList;

        public List<String> getCommunityOrgIdList() {
            return this.communityOrgIdList;
        }


        private List<String> fuzzyCommunityOrgId;

        public List<String> getFuzzyCommunityOrgId() {
            return this.fuzzyCommunityOrgId;
        }

        private List<String> rightFuzzyCommunityOrgId;

        public List<String> getRightFuzzyCommunityOrgId() {
            return this.rightFuzzyCommunityOrgId;
        }

        private List<String> createByList;

        public List<String> getCreateByList() {
            return this.createByList;
        }


        private List<String> fuzzyCreateBy;

        public List<String> getFuzzyCreateBy() {
            return this.fuzzyCreateBy;
        }

        private List<String> rightFuzzyCreateBy;

        public List<String> getRightFuzzyCreateBy() {
            return this.rightFuzzyCreateBy;
        }

        private List<Date> createTimeList;

        public List<Date> getCreateTimeList() {
            return this.createTimeList;
        }

        private Date createTimeSt;

        private Date createTimeEd;

        public Date getCreateTimeSt() {
            return this.createTimeSt;
        }

        public Date getCreateTimeEd() {
            return this.createTimeEd;
        }

        private List<String> updateByList;

        public List<String> getUpdateByList() {
            return this.updateByList;
        }


        private List<String> fuzzyUpdateBy;

        public List<String> getFuzzyUpdateBy() {
            return this.fuzzyUpdateBy;
        }

        private List<String> rightFuzzyUpdateBy;

        public List<String> getRightFuzzyUpdateBy() {
            return this.rightFuzzyUpdateBy;
        }

        private List<Date> updateTimeList;

        public List<Date> getUpdateTimeList() {
            return this.updateTimeList;
        }

        private Date updateTimeSt;

        private Date updateTimeEd;

        public Date getUpdateTimeSt() {
            return this.updateTimeSt;
        }

        public Date getUpdateTimeEd() {
            return this.updateTimeEd;
        }

        private List<String> remarkList;

        public List<String> getRemarkList() {
            return this.remarkList;
        }


        private List<String> fuzzyRemark;

        public List<String> getFuzzyRemark() {
            return this.fuzzyRemark;
        }

        private List<String> rightFuzzyRemark;

        public List<String> getRightFuzzyRemark() {
            return this.rightFuzzyRemark;
        }

        private List<String> delFlagList;

        public List<String> getDelFlagList() {
            return this.delFlagList;
        }


        private List<String> fuzzyDelFlag;

        public List<String> getFuzzyDelFlag() {
            return this.fuzzyDelFlag;
        }

        private List<String> rightFuzzyDelFlag;

        public List<String> getRightFuzzyDelFlag() {
            return this.rightFuzzyDelFlag;
        }

        private QueryBuilder() {
            this.fetchFields = new HashMap<>();
        }

        public QueryBuilder fuzzyId(List<String> fuzzyId) {
            this.fuzzyId = fuzzyId;
            return this;
        }

        public QueryBuilder fuzzyId(String... fuzzyId) {
            this.fuzzyId = solveNullList(fuzzyId);
            return this;
        }

        public QueryBuilder rightFuzzyId(List<String> rightFuzzyId) {
            this.rightFuzzyId = rightFuzzyId;
            return this;
        }

        public QueryBuilder rightFuzzyId(String... rightFuzzyId) {
            this.rightFuzzyId = solveNullList(rightFuzzyId);
            return this;
        }

        public QueryBuilder id(String id) {
            setId(id);
            return this;
        }

        public QueryBuilder idList(String... id) {
            this.idList = solveNullList(id);
            return this;
        }

        public QueryBuilder idList(List<String> id) {
            this.idList = id;
            return this;
        }

        public QueryBuilder fetchId() {
            setFetchFields("fetchFields", "id");
            return this;
        }

        public QueryBuilder excludeId() {
            setFetchFields("excludeFields", "id");
            return this;
        }

        public QueryBuilder fuzzyEquipmentPhotos(List<String> fuzzyEquipmentPhotos) {
            this.fuzzyEquipmentPhotos = fuzzyEquipmentPhotos;
            return this;
        }

        public QueryBuilder fuzzyEquipmentPhotos(String... fuzzyEquipmentPhotos) {
            this.fuzzyEquipmentPhotos = solveNullList(fuzzyEquipmentPhotos);
            return this;
        }

        public QueryBuilder rightFuzzyEquipmentPhotos(List<String> rightFuzzyEquipmentPhotos) {
            this.rightFuzzyEquipmentPhotos = rightFuzzyEquipmentPhotos;
            return this;
        }

        public QueryBuilder rightFuzzyEquipmentPhotos(String... rightFuzzyEquipmentPhotos) {
            this.rightFuzzyEquipmentPhotos = solveNullList(rightFuzzyEquipmentPhotos);
            return this;
        }

        public QueryBuilder equipmentPhotos(String equipmentPhotos) {
            setEquipmentPhotos(equipmentPhotos);
            return this;
        }

        public QueryBuilder equipmentPhotosList(String... equipmentPhotos) {
            this.equipmentPhotosList = solveNullList(equipmentPhotos);
            return this;
        }

        public QueryBuilder equipmentPhotosList(List<String> equipmentPhotos) {
            this.equipmentPhotosList = equipmentPhotos;
            return this;
        }

        public QueryBuilder fetchEquipmentPhotos() {
            setFetchFields("fetchFields", "equipmentPhotos");
            return this;
        }

        public QueryBuilder excludeEquipmentPhotos() {
            setFetchFields("excludeFields", "equipmentPhotos");
            return this;
        }

        public QueryBuilder fuzzyName(List<String> fuzzyName) {
            this.fuzzyName = fuzzyName;
            return this;
        }

        public QueryBuilder fuzzyName(String... fuzzyName) {
            this.fuzzyName = solveNullList(fuzzyName);
            return this;
        }

        public QueryBuilder rightFuzzyName(List<String> rightFuzzyName) {
            this.rightFuzzyName = rightFuzzyName;
            return this;
        }

        public QueryBuilder rightFuzzyName(String... rightFuzzyName) {
            this.rightFuzzyName = solveNullList(rightFuzzyName);
            return this;
        }

        public QueryBuilder name(String name) {
            setName(name);
            return this;
        }

        public QueryBuilder nameList(String... name) {
            this.nameList = solveNullList(name);
            return this;
        }

        public QueryBuilder nameList(List<String> name) {
            this.nameList = name;
            return this;
        }

        public QueryBuilder fetchName() {
            setFetchFields("fetchFields", "name");
            return this;
        }

        public QueryBuilder excludeName() {
            setFetchFields("excludeFields", "name");
            return this;
        }

        public QueryBuilder fuzzyType(List<String> fuzzyType) {
            this.fuzzyType = fuzzyType;
            return this;
        }

        public QueryBuilder fuzzyType(String... fuzzyType) {
            this.fuzzyType = solveNullList(fuzzyType);
            return this;
        }

        public QueryBuilder rightFuzzyType(List<String> rightFuzzyType) {
            this.rightFuzzyType = rightFuzzyType;
            return this;
        }

        public QueryBuilder rightFuzzyType(String... rightFuzzyType) {
            this.rightFuzzyType = solveNullList(rightFuzzyType);
            return this;
        }

        public QueryBuilder type(String type) {
            setType(type);
            return this;
        }

        public QueryBuilder typeList(String... type) {
            this.typeList = solveNullList(type);
            return this;
        }

        public QueryBuilder typeList(List<String> type) {
            this.typeList = type;
            return this;
        }

        public QueryBuilder fetchType() {
            setFetchFields("fetchFields", "type");
            return this;
        }

        public QueryBuilder excludeType() {
            setFetchFields("excludeFields", "type");
            return this;
        }

        public QueryBuilder fuzzySn(List<String> fuzzySn) {
            this.fuzzySn = fuzzySn;
            return this;
        }

        public QueryBuilder fuzzySn(String... fuzzySn) {
            this.fuzzySn = solveNullList(fuzzySn);
            return this;
        }

        public QueryBuilder rightFuzzySn(List<String> rightFuzzySn) {
            this.rightFuzzySn = rightFuzzySn;
            return this;
        }

        public QueryBuilder rightFuzzySn(String... rightFuzzySn) {
            this.rightFuzzySn = solveNullList(rightFuzzySn);
            return this;
        }

        public QueryBuilder sn(String sn) {
            setSn(sn);
            return this;
        }

        public QueryBuilder snList(String... sn) {
            this.snList = solveNullList(sn);
            return this;
        }

        public QueryBuilder snList(List<String> sn) {
            this.snList = sn;
            return this;
        }

        public QueryBuilder fetchSn() {
            setFetchFields("fetchFields", "sn");
            return this;
        }

        public QueryBuilder excludeSn() {
            setFetchFields("excludeFields", "sn");
            return this;
        }

        public QueryBuilder fuzzyNumber(List<String> fuzzyNumber) {
            this.fuzzyNumber = fuzzyNumber;
            return this;
        }

        public QueryBuilder fuzzyNumber(String... fuzzyNumber) {
            this.fuzzyNumber = solveNullList(fuzzyNumber);
            return this;
        }

        public QueryBuilder rightFuzzyNumber(List<String> rightFuzzyNumber) {
            this.rightFuzzyNumber = rightFuzzyNumber;
            return this;
        }

        public QueryBuilder rightFuzzyNumber(String... rightFuzzyNumber) {
            this.rightFuzzyNumber = solveNullList(rightFuzzyNumber);
            return this;
        }

        public QueryBuilder number(String number) {
            setNumber(number);
            return this;
        }

        public QueryBuilder numberList(String... number) {
            this.numberList = solveNullList(number);
            return this;
        }

        public QueryBuilder numberList(List<String> number) {
            this.numberList = number;
            return this;
        }

        public QueryBuilder fetchNumber() {
            setFetchFields("fetchFields", "number");
            return this;
        }

        public QueryBuilder excludeNumber() {
            setFetchFields("excludeFields", "number");
            return this;
        }

        public QueryBuilder fuzzyModel(List<String> fuzzyModel) {
            this.fuzzyModel = fuzzyModel;
            return this;
        }

        public QueryBuilder fuzzyModel(String... fuzzyModel) {
            this.fuzzyModel = solveNullList(fuzzyModel);
            return this;
        }

        public QueryBuilder rightFuzzyModel(List<String> rightFuzzyModel) {
            this.rightFuzzyModel = rightFuzzyModel;
            return this;
        }

        public QueryBuilder rightFuzzyModel(String... rightFuzzyModel) {
            this.rightFuzzyModel = solveNullList(rightFuzzyModel);
            return this;
        }

        public QueryBuilder model(String model) {
            setModel(model);
            return this;
        }

        public QueryBuilder modelList(String... model) {
            this.modelList = solveNullList(model);
            return this;
        }

        public QueryBuilder modelList(List<String> model) {
            this.modelList = model;
            return this;
        }

        public QueryBuilder fetchModel() {
            setFetchFields("fetchFields", "model");
            return this;
        }

        public QueryBuilder excludeModel() {
            setFetchFields("excludeFields", "model");
            return this;
        }

        public QueryBuilder fuzzySpecifications(List<String> fuzzySpecifications) {
            this.fuzzySpecifications = fuzzySpecifications;
            return this;
        }

        public QueryBuilder fuzzySpecifications(String... fuzzySpecifications) {
            this.fuzzySpecifications = solveNullList(fuzzySpecifications);
            return this;
        }

        public QueryBuilder rightFuzzySpecifications(List<String> rightFuzzySpecifications) {
            this.rightFuzzySpecifications = rightFuzzySpecifications;
            return this;
        }

        public QueryBuilder rightFuzzySpecifications(String... rightFuzzySpecifications) {
            this.rightFuzzySpecifications = solveNullList(rightFuzzySpecifications);
            return this;
        }

        public QueryBuilder specifications(String specifications) {
            setSpecifications(specifications);
            return this;
        }

        public QueryBuilder specificationsList(String... specifications) {
            this.specificationsList = solveNullList(specifications);
            return this;
        }

        public QueryBuilder specificationsList(List<String> specifications) {
            this.specificationsList = specifications;
            return this;
        }

        public QueryBuilder fetchSpecifications() {
            setFetchFields("fetchFields", "specifications");
            return this;
        }

        public QueryBuilder excludeSpecifications() {
            setFetchFields("excludeFields", "specifications");
            return this;
        }

        public QueryBuilder fuzzyManufacturer(List<String> fuzzyManufacturer) {
            this.fuzzyManufacturer = fuzzyManufacturer;
            return this;
        }

        public QueryBuilder fuzzyManufacturer(String... fuzzyManufacturer) {
            this.fuzzyManufacturer = solveNullList(fuzzyManufacturer);
            return this;
        }

        public QueryBuilder rightFuzzyManufacturer(List<String> rightFuzzyManufacturer) {
            this.rightFuzzyManufacturer = rightFuzzyManufacturer;
            return this;
        }

        public QueryBuilder rightFuzzyManufacturer(String... rightFuzzyManufacturer) {
            this.rightFuzzyManufacturer = solveNullList(rightFuzzyManufacturer);
            return this;
        }

        public QueryBuilder manufacturer(String manufacturer) {
            setManufacturer(manufacturer);
            return this;
        }

        public QueryBuilder manufacturerList(String... manufacturer) {
            this.manufacturerList = solveNullList(manufacturer);
            return this;
        }

        public QueryBuilder manufacturerList(List<String> manufacturer) {
            this.manufacturerList = manufacturer;
            return this;
        }

        public QueryBuilder fetchManufacturer() {
            setFetchFields("fetchFields", "manufacturer");
            return this;
        }

        public QueryBuilder excludeManufacturer() {
            setFetchFields("excludeFields", "manufacturer");
            return this;
        }

        public QueryBuilder outTimeBetWeen(Date outTimeSt, Date outTimeEd) {
            this.outTimeSt = outTimeSt;
            this.outTimeEd = outTimeEd;
            return this;
        }

        public QueryBuilder outTimeGreaterEqThan(Date outTimeSt) {
            this.outTimeSt = outTimeSt;
            return this;
        }

        public QueryBuilder outTimeLessEqThan(Date outTimeEd) {
            this.outTimeEd = outTimeEd;
            return this;
        }


        public QueryBuilder outTime(Date outTime) {
            setOutTime(outTime);
            return this;
        }

        public QueryBuilder outTimeList(Date... outTime) {
            this.outTimeList = solveNullList(outTime);
            return this;
        }

        public QueryBuilder outTimeList(List<Date> outTime) {
            this.outTimeList = outTime;
            return this;
        }

        public QueryBuilder fetchOutTime() {
            setFetchFields("fetchFields", "outTime");
            return this;
        }

        public QueryBuilder excludeOutTime() {
            setFetchFields("excludeFields", "outTime");
            return this;
        }

        public QueryBuilder installTimeBetWeen(Date installTimeSt, Date installTimeEd) {
            this.installTimeSt = installTimeSt;
            this.installTimeEd = installTimeEd;
            return this;
        }

        public QueryBuilder installTimeGreaterEqThan(Date installTimeSt) {
            this.installTimeSt = installTimeSt;
            return this;
        }

        public QueryBuilder installTimeLessEqThan(Date installTimeEd) {
            this.installTimeEd = installTimeEd;
            return this;
        }


        public QueryBuilder installTime(Date installTime) {
            setInstallTime(installTime);
            return this;
        }

        public QueryBuilder installTimeList(Date... installTime) {
            this.installTimeList = solveNullList(installTime);
            return this;
        }

        public QueryBuilder installTimeList(List<Date> installTime) {
            this.installTimeList = installTime;
            return this;
        }

        public QueryBuilder fetchInstallTime() {
            setFetchFields("fetchFields", "installTime");
            return this;
        }

        public QueryBuilder excludeInstallTime() {
            setFetchFields("excludeFields", "installTime");
            return this;
        }

        public QueryBuilder fuzzyInstallAddress(List<String> fuzzyInstallAddress) {
            this.fuzzyInstallAddress = fuzzyInstallAddress;
            return this;
        }

        public QueryBuilder fuzzyInstallAddress(String... fuzzyInstallAddress) {
            this.fuzzyInstallAddress = solveNullList(fuzzyInstallAddress);
            return this;
        }

        public QueryBuilder rightFuzzyInstallAddress(List<String> rightFuzzyInstallAddress) {
            this.rightFuzzyInstallAddress = rightFuzzyInstallAddress;
            return this;
        }

        public QueryBuilder rightFuzzyInstallAddress(String... rightFuzzyInstallAddress) {
            this.rightFuzzyInstallAddress = solveNullList(rightFuzzyInstallAddress);
            return this;
        }

        public QueryBuilder installAddress(String installAddress) {
            setInstallAddress(installAddress);
            return this;
        }

        public QueryBuilder installAddressList(String... installAddress) {
            this.installAddressList = solveNullList(installAddress);
            return this;
        }

        public QueryBuilder installAddressList(List<String> installAddress) {
            this.installAddressList = installAddress;
            return this;
        }

        public QueryBuilder fetchInstallAddress() {
            setFetchFields("fetchFields", "installAddress");
            return this;
        }

        public QueryBuilder excludeInstallAddress() {
            setFetchFields("excludeFields", "installAddress");
            return this;
        }

        public QueryBuilder fuzzyManufacturerContacts(List<String> fuzzyManufacturerContacts) {
            this.fuzzyManufacturerContacts = fuzzyManufacturerContacts;
            return this;
        }

        public QueryBuilder fuzzyManufacturerContacts(String... fuzzyManufacturerContacts) {
            this.fuzzyManufacturerContacts = solveNullList(fuzzyManufacturerContacts);
            return this;
        }

        public QueryBuilder rightFuzzyManufacturerContacts(List<String> rightFuzzyManufacturerContacts) {
            this.rightFuzzyManufacturerContacts = rightFuzzyManufacturerContacts;
            return this;
        }

        public QueryBuilder rightFuzzyManufacturerContacts(String... rightFuzzyManufacturerContacts) {
            this.rightFuzzyManufacturerContacts = solveNullList(rightFuzzyManufacturerContacts);
            return this;
        }

        public QueryBuilder manufacturerContacts(String manufacturerContacts) {
            setManufacturerContacts(manufacturerContacts);
            return this;
        }

        public QueryBuilder manufacturerContactsList(String... manufacturerContacts) {
            this.manufacturerContactsList = solveNullList(manufacturerContacts);
            return this;
        }

        public QueryBuilder manufacturerContactsList(List<String> manufacturerContacts) {
            this.manufacturerContactsList = manufacturerContacts;
            return this;
        }

        public QueryBuilder fetchManufacturerContacts() {
            setFetchFields("fetchFields", "manufacturerContacts");
            return this;
        }

        public QueryBuilder excludeManufacturerContacts() {
            setFetchFields("excludeFields", "manufacturerContacts");
            return this;
        }

        public QueryBuilder fuzzyManufacturerContactsNumber(List<String> fuzzyManufacturerContactsNumber) {
            this.fuzzyManufacturerContactsNumber = fuzzyManufacturerContactsNumber;
            return this;
        }

        public QueryBuilder fuzzyManufacturerContactsNumber(String... fuzzyManufacturerContactsNumber) {
            this.fuzzyManufacturerContactsNumber = solveNullList(fuzzyManufacturerContactsNumber);
            return this;
        }

        public QueryBuilder rightFuzzyManufacturerContactsNumber(List<String> rightFuzzyManufacturerContactsNumber) {
            this.rightFuzzyManufacturerContactsNumber = rightFuzzyManufacturerContactsNumber;
            return this;
        }

        public QueryBuilder rightFuzzyManufacturerContactsNumber(String... rightFuzzyManufacturerContactsNumber) {
            this.rightFuzzyManufacturerContactsNumber = solveNullList(rightFuzzyManufacturerContactsNumber);
            return this;
        }

        public QueryBuilder manufacturerContactsNumber(String manufacturerContactsNumber) {
            setManufacturerContactsNumber(manufacturerContactsNumber);
            return this;
        }

        public QueryBuilder manufacturerContactsNumberList(String... manufacturerContactsNumber) {
            this.manufacturerContactsNumberList = solveNullList(manufacturerContactsNumber);
            return this;
        }

        public QueryBuilder manufacturerContactsNumberList(List<String> manufacturerContactsNumber) {
            this.manufacturerContactsNumberList = manufacturerContactsNumber;
            return this;
        }

        public QueryBuilder fetchManufacturerContactsNumber() {
            setFetchFields("fetchFields", "manufacturerContactsNumber");
            return this;
        }

        public QueryBuilder excludeManufacturerContactsNumber() {
            setFetchFields("excludeFields", "manufacturerContactsNumber");
            return this;
        }

        public QueryBuilder fuzzyLocationInfo(List<String> fuzzyLocationInfo) {
            this.fuzzyLocationInfo = fuzzyLocationInfo;
            return this;
        }

        public QueryBuilder fuzzyLocationInfo(String... fuzzyLocationInfo) {
            this.fuzzyLocationInfo = solveNullList(fuzzyLocationInfo);
            return this;
        }

        public QueryBuilder rightFuzzyLocationInfo(List<String> rightFuzzyLocationInfo) {
            this.rightFuzzyLocationInfo = rightFuzzyLocationInfo;
            return this;
        }

        public QueryBuilder rightFuzzyLocationInfo(String... rightFuzzyLocationInfo) {
            this.rightFuzzyLocationInfo = solveNullList(rightFuzzyLocationInfo);
            return this;
        }

        public QueryBuilder locationInfo(String locationInfo) {
            setLocationInfo(locationInfo);
            return this;
        }

        public QueryBuilder locationInfoList(String... locationInfo) {
            this.locationInfoList = solveNullList(locationInfo);
            return this;
        }

        public QueryBuilder locationInfoList(List<String> locationInfo) {
            this.locationInfoList = locationInfo;
            return this;
        }

        public QueryBuilder fetchLocationInfo() {
            setFetchFields("fetchFields", "locationInfo");
            return this;
        }

        public QueryBuilder excludeLocationInfo() {
            setFetchFields("excludeFields", "locationInfo");
            return this;
        }

        public QueryBuilder fuzzyStatus(List<String> fuzzyStatus) {
            this.fuzzyStatus = fuzzyStatus;
            return this;
        }

        public QueryBuilder fuzzyStatus(String... fuzzyStatus) {
            this.fuzzyStatus = solveNullList(fuzzyStatus);
            return this;
        }

        public QueryBuilder rightFuzzyStatus(List<String> rightFuzzyStatus) {
            this.rightFuzzyStatus = rightFuzzyStatus;
            return this;
        }

        public QueryBuilder rightFuzzyStatus(String... rightFuzzyStatus) {
            this.rightFuzzyStatus = solveNullList(rightFuzzyStatus);
            return this;
        }

        public QueryBuilder status(String status) {
            setStatus(status);
            return this;
        }

        public QueryBuilder statusList(String... status) {
            this.statusList = solveNullList(status);
            return this;
        }

        public QueryBuilder statusList(List<String> status) {
            this.statusList = status;
            return this;
        }

        public QueryBuilder fetchStatus() {
            setFetchFields("fetchFields", "status");
            return this;
        }

        public QueryBuilder excludeStatus() {
            setFetchFields("excludeFields", "status");
            return this;
        }

        public QueryBuilder fuzzyAuditScope(List<String> fuzzyAuditScope) {
            this.fuzzyAuditScope = fuzzyAuditScope;
            return this;
        }

        public QueryBuilder fuzzyAuditScope(String... fuzzyAuditScope) {
            this.fuzzyAuditScope = solveNullList(fuzzyAuditScope);
            return this;
        }

        public QueryBuilder rightFuzzyAuditScope(List<String> rightFuzzyAuditScope) {
            this.rightFuzzyAuditScope = rightFuzzyAuditScope;
            return this;
        }

        public QueryBuilder rightFuzzyAuditScope(String... rightFuzzyAuditScope) {
            this.rightFuzzyAuditScope = solveNullList(rightFuzzyAuditScope);
            return this;
        }

        public QueryBuilder auditScope(String auditScope) {
            setAuditScope(auditScope);
            return this;
        }

        public QueryBuilder auditScopeList(String... auditScope) {
            this.auditScopeList = solveNullList(auditScope);
            return this;
        }

        public QueryBuilder auditScopeList(List<String> auditScope) {
            this.auditScopeList = auditScope;
            return this;
        }

        public QueryBuilder fetchAuditScope() {
            setFetchFields("fetchFields", "auditScope");
            return this;
        }

        public QueryBuilder excludeAuditScope() {
            setFetchFields("excludeFields", "auditScope");
            return this;
        }

        public QueryBuilder fuzzyCommunityId(List<String> fuzzyCommunityId) {
            this.fuzzyCommunityId = fuzzyCommunityId;
            return this;
        }

        public QueryBuilder fuzzyCommunityId(String... fuzzyCommunityId) {
            this.fuzzyCommunityId = solveNullList(fuzzyCommunityId);
            return this;
        }

        public QueryBuilder rightFuzzyCommunityId(List<String> rightFuzzyCommunityId) {
            this.rightFuzzyCommunityId = rightFuzzyCommunityId;
            return this;
        }

        public QueryBuilder rightFuzzyCommunityId(String... rightFuzzyCommunityId) {
            this.rightFuzzyCommunityId = solveNullList(rightFuzzyCommunityId);
            return this;
        }

        public QueryBuilder communityId(String communityId) {
            setCommunityId(communityId);
            return this;
        }

        public QueryBuilder communityIdList(String... communityId) {
            this.communityIdList = solveNullList(communityId);
            return this;
        }

        public QueryBuilder communityIdList(List<String> communityId) {
            this.communityIdList = communityId;
            return this;
        }

        public QueryBuilder fetchCommunityId() {
            setFetchFields("fetchFields", "communityId");
            return this;
        }

        public QueryBuilder excludeCommunityId() {
            setFetchFields("excludeFields", "communityId");
            return this;
        }

        public QueryBuilder fuzzyCommunityOrgId(List<String> fuzzyCommunityOrgId) {
            this.fuzzyCommunityOrgId = fuzzyCommunityOrgId;
            return this;
        }

        public QueryBuilder fuzzyCommunityOrgId(String... fuzzyCommunityOrgId) {
            this.fuzzyCommunityOrgId = solveNullList(fuzzyCommunityOrgId);
            return this;
        }

        public QueryBuilder rightFuzzyCommunityOrgId(List<String> rightFuzzyCommunityOrgId) {
            this.rightFuzzyCommunityOrgId = rightFuzzyCommunityOrgId;
            return this;
        }

        public QueryBuilder rightFuzzyCommunityOrgId(String... rightFuzzyCommunityOrgId) {
            this.rightFuzzyCommunityOrgId = solveNullList(rightFuzzyCommunityOrgId);
            return this;
        }

        public QueryBuilder communityOrgId(String communityOrgId) {
            setCommunityOrgId(communityOrgId);
            return this;
        }

        public QueryBuilder communityOrgIdList(String... communityOrgId) {
            this.communityOrgIdList = solveNullList(communityOrgId);
            return this;
        }

        public QueryBuilder communityOrgIdList(List<String> communityOrgId) {
            this.communityOrgIdList = communityOrgId;
            return this;
        }

        public QueryBuilder fetchCommunityOrgId() {
            setFetchFields("fetchFields", "communityOrgId");
            return this;
        }

        public QueryBuilder excludeCommunityOrgId() {
            setFetchFields("excludeFields", "communityOrgId");
            return this;
        }

        public QueryBuilder fuzzyCreateBy(List<String> fuzzyCreateBy) {
            this.fuzzyCreateBy = fuzzyCreateBy;
            return this;
        }

        public QueryBuilder fuzzyCreateBy(String... fuzzyCreateBy) {
            this.fuzzyCreateBy = solveNullList(fuzzyCreateBy);
            return this;
        }

        public QueryBuilder rightFuzzyCreateBy(List<String> rightFuzzyCreateBy) {
            this.rightFuzzyCreateBy = rightFuzzyCreateBy;
            return this;
        }

        public QueryBuilder rightFuzzyCreateBy(String... rightFuzzyCreateBy) {
            this.rightFuzzyCreateBy = solveNullList(rightFuzzyCreateBy);
            return this;
        }

        public QueryBuilder createBy(String createBy) {
            setCreateBy(createBy);
            return this;
        }

        public QueryBuilder createByList(String... createBy) {
            this.createByList = solveNullList(createBy);
            return this;
        }

        public QueryBuilder createByList(List<String> createBy) {
            this.createByList = createBy;
            return this;
        }

        public QueryBuilder fetchCreateBy() {
            setFetchFields("fetchFields", "createBy");
            return this;
        }

        public QueryBuilder excludeCreateBy() {
            setFetchFields("excludeFields", "createBy");
            return this;
        }

        public QueryBuilder createTimeBetWeen(Date createTimeSt, Date createTimeEd) {
            this.createTimeSt = createTimeSt;
            this.createTimeEd = createTimeEd;
            return this;
        }

        public QueryBuilder createTimeGreaterEqThan(Date createTimeSt) {
            this.createTimeSt = createTimeSt;
            return this;
        }

        public QueryBuilder createTimeLessEqThan(Date createTimeEd) {
            this.createTimeEd = createTimeEd;
            return this;
        }


        public QueryBuilder createTime(Date createTime) {
            setCreateTime(createTime);
            return this;
        }

        public QueryBuilder createTimeList(Date... createTime) {
            this.createTimeList = solveNullList(createTime);
            return this;
        }

        public QueryBuilder createTimeList(List<Date> createTime) {
            this.createTimeList = createTime;
            return this;
        }

        public QueryBuilder fetchCreateTime() {
            setFetchFields("fetchFields", "createTime");
            return this;
        }

        public QueryBuilder excludeCreateTime() {
            setFetchFields("excludeFields", "createTime");
            return this;
        }

        public QueryBuilder fuzzyUpdateBy(List<String> fuzzyUpdateBy) {
            this.fuzzyUpdateBy = fuzzyUpdateBy;
            return this;
        }

        public QueryBuilder fuzzyUpdateBy(String... fuzzyUpdateBy) {
            this.fuzzyUpdateBy = solveNullList(fuzzyUpdateBy);
            return this;
        }

        public QueryBuilder rightFuzzyUpdateBy(List<String> rightFuzzyUpdateBy) {
            this.rightFuzzyUpdateBy = rightFuzzyUpdateBy;
            return this;
        }

        public QueryBuilder rightFuzzyUpdateBy(String... rightFuzzyUpdateBy) {
            this.rightFuzzyUpdateBy = solveNullList(rightFuzzyUpdateBy);
            return this;
        }

        public QueryBuilder updateBy(String updateBy) {
            setUpdateBy(updateBy);
            return this;
        }

        public QueryBuilder updateByList(String... updateBy) {
            this.updateByList = solveNullList(updateBy);
            return this;
        }

        public QueryBuilder updateByList(List<String> updateBy) {
            this.updateByList = updateBy;
            return this;
        }

        public QueryBuilder fetchUpdateBy() {
            setFetchFields("fetchFields", "updateBy");
            return this;
        }

        public QueryBuilder excludeUpdateBy() {
            setFetchFields("excludeFields", "updateBy");
            return this;
        }

        public QueryBuilder updateTimeBetWeen(Date updateTimeSt, Date updateTimeEd) {
            this.updateTimeSt = updateTimeSt;
            this.updateTimeEd = updateTimeEd;
            return this;
        }

        public QueryBuilder updateTimeGreaterEqThan(Date updateTimeSt) {
            this.updateTimeSt = updateTimeSt;
            return this;
        }

        public QueryBuilder updateTimeLessEqThan(Date updateTimeEd) {
            this.updateTimeEd = updateTimeEd;
            return this;
        }


        public QueryBuilder updateTime(Date updateTime) {
            setUpdateTime(updateTime);
            return this;
        }

        public QueryBuilder updateTimeList(Date... updateTime) {
            this.updateTimeList = solveNullList(updateTime);
            return this;
        }

        public QueryBuilder updateTimeList(List<Date> updateTime) {
            this.updateTimeList = updateTime;
            return this;
        }

        public QueryBuilder fetchUpdateTime() {
            setFetchFields("fetchFields", "updateTime");
            return this;
        }

        public QueryBuilder excludeUpdateTime() {
            setFetchFields("excludeFields", "updateTime");
            return this;
        }

        public QueryBuilder fuzzyRemark(List<String> fuzzyRemark) {
            this.fuzzyRemark = fuzzyRemark;
            return this;
        }

        public QueryBuilder fuzzyRemark(String... fuzzyRemark) {
            this.fuzzyRemark = solveNullList(fuzzyRemark);
            return this;
        }

        public QueryBuilder rightFuzzyRemark(List<String> rightFuzzyRemark) {
            this.rightFuzzyRemark = rightFuzzyRemark;
            return this;
        }

        public QueryBuilder rightFuzzyRemark(String... rightFuzzyRemark) {
            this.rightFuzzyRemark = solveNullList(rightFuzzyRemark);
            return this;
        }

        public QueryBuilder remark(String remark) {
            setRemark(remark);
            return this;
        }

        public QueryBuilder remarkList(String... remark) {
            this.remarkList = solveNullList(remark);
            return this;
        }

        public QueryBuilder remarkList(List<String> remark) {
            this.remarkList = remark;
            return this;
        }

        public QueryBuilder fetchRemark() {
            setFetchFields("fetchFields", "remark");
            return this;
        }

        public QueryBuilder excludeRemark() {
            setFetchFields("excludeFields", "remark");
            return this;
        }

        public QueryBuilder fuzzyDelFlag(List<String> fuzzyDelFlag) {
            this.fuzzyDelFlag = fuzzyDelFlag;
            return this;
        }

        public QueryBuilder fuzzyDelFlag(String... fuzzyDelFlag) {
            this.fuzzyDelFlag = solveNullList(fuzzyDelFlag);
            return this;
        }

        public QueryBuilder rightFuzzyDelFlag(List<String> rightFuzzyDelFlag) {
            this.rightFuzzyDelFlag = rightFuzzyDelFlag;
            return this;
        }

        public QueryBuilder rightFuzzyDelFlag(String... rightFuzzyDelFlag) {
            this.rightFuzzyDelFlag = solveNullList(rightFuzzyDelFlag);
            return this;
        }

        public QueryBuilder delFlag(String delFlag) {
            setDelFlag(delFlag);
            return this;
        }

        public QueryBuilder delFlagList(String... delFlag) {
            this.delFlagList = solveNullList(delFlag);
            return this;
        }

        public QueryBuilder delFlagList(List<String> delFlag) {
            this.delFlagList = delFlag;
            return this;
        }

        public QueryBuilder fetchDelFlag() {
            setFetchFields("fetchFields", "delFlag");
            return this;
        }

        public QueryBuilder excludeDelFlag() {
            setFetchFields("excludeFields", "delFlag");
            return this;
        }

        private <T> List<T> solveNullList(T... objs) {
            if (objs != null) {
                List<T> list = new ArrayList<>();
                for (T item : objs) {
                    if (item != null) {
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public QueryBuilder fetchAll() {
            this.fetchFields.put("AllFields", true);
            return this;
        }

        public QueryBuilder addField(String... fields) {
            List<String> list = new ArrayList<>();
            if (fields != null) {
                for (String field : fields) {
                    list.add(field);
                }
            }
            this.fetchFields.put("otherFields", list);
            return this;
        }

        @SuppressWarnings("unchecked")
        private void setFetchFields(String key, String val) {
            Map<String, Boolean> fields = (Map<String, Boolean>) this.fetchFields.get(key);
            if (fields == null) {
                fields = new HashMap<>();
            }
            fields.put(val, true);
            this.fetchFields.put(key, fields);
        }

        public BaseCommunityDevice build() {
            return this;
        }
    }


    public static class ConditionBuilder {
        private List<String> idList;

        public List<String> getIdList() {
            return this.idList;
        }


        private List<String> fuzzyId;

        public List<String> getFuzzyId() {
            return this.fuzzyId;
        }

        private List<String> rightFuzzyId;

        public List<String> getRightFuzzyId() {
            return this.rightFuzzyId;
        }

        private List<String> equipmentPhotosList;

        public List<String> getEquipmentPhotosList() {
            return this.equipmentPhotosList;
        }


        private List<String> fuzzyEquipmentPhotos;

        public List<String> getFuzzyEquipmentPhotos() {
            return this.fuzzyEquipmentPhotos;
        }

        private List<String> rightFuzzyEquipmentPhotos;

        public List<String> getRightFuzzyEquipmentPhotos() {
            return this.rightFuzzyEquipmentPhotos;
        }

        private List<String> nameList;

        public List<String> getNameList() {
            return this.nameList;
        }


        private List<String> fuzzyName;

        public List<String> getFuzzyName() {
            return this.fuzzyName;
        }

        private List<String> rightFuzzyName;

        public List<String> getRightFuzzyName() {
            return this.rightFuzzyName;
        }

        private List<String> typeList;

        public List<String> getTypeList() {
            return this.typeList;
        }


        private List<String> fuzzyType;

        public List<String> getFuzzyType() {
            return this.fuzzyType;
        }

        private List<String> rightFuzzyType;

        public List<String> getRightFuzzyType() {
            return this.rightFuzzyType;
        }

        private List<String> snList;

        public List<String> getSnList() {
            return this.snList;
        }


        private List<String> fuzzySn;

        public List<String> getFuzzySn() {
            return this.fuzzySn;
        }

        private List<String> rightFuzzySn;

        public List<String> getRightFuzzySn() {
            return this.rightFuzzySn;
        }

        private List<String> numberList;

        public List<String> getNumberList() {
            return this.numberList;
        }


        private List<String> fuzzyNumber;

        public List<String> getFuzzyNumber() {
            return this.fuzzyNumber;
        }

        private List<String> rightFuzzyNumber;

        public List<String> getRightFuzzyNumber() {
            return this.rightFuzzyNumber;
        }

        private List<String> modelList;

        public List<String> getModelList() {
            return this.modelList;
        }


        private List<String> fuzzyModel;

        public List<String> getFuzzyModel() {
            return this.fuzzyModel;
        }

        private List<String> rightFuzzyModel;

        public List<String> getRightFuzzyModel() {
            return this.rightFuzzyModel;
        }

        private List<String> specificationsList;

        public List<String> getSpecificationsList() {
            return this.specificationsList;
        }


        private List<String> fuzzySpecifications;

        public List<String> getFuzzySpecifications() {
            return this.fuzzySpecifications;
        }

        private List<String> rightFuzzySpecifications;

        public List<String> getRightFuzzySpecifications() {
            return this.rightFuzzySpecifications;
        }

        private List<String> manufacturerList;

        public List<String> getManufacturerList() {
            return this.manufacturerList;
        }


        private List<String> fuzzyManufacturer;

        public List<String> getFuzzyManufacturer() {
            return this.fuzzyManufacturer;
        }

        private List<String> rightFuzzyManufacturer;

        public List<String> getRightFuzzyManufacturer() {
            return this.rightFuzzyManufacturer;
        }

        private List<Date> outTimeList;

        public List<Date> getOutTimeList() {
            return this.outTimeList;
        }

        private Date outTimeSt;

        private Date outTimeEd;

        public Date getOutTimeSt() {
            return this.outTimeSt;
        }

        public Date getOutTimeEd() {
            return this.outTimeEd;
        }

        private List<Date> installTimeList;

        public List<Date> getInstallTimeList() {
            return this.installTimeList;
        }

        private Date installTimeSt;

        private Date installTimeEd;

        public Date getInstallTimeSt() {
            return this.installTimeSt;
        }

        public Date getInstallTimeEd() {
            return this.installTimeEd;
        }

        private List<String> installAddressList;

        public List<String> getInstallAddressList() {
            return this.installAddressList;
        }


        private List<String> fuzzyInstallAddress;

        public List<String> getFuzzyInstallAddress() {
            return this.fuzzyInstallAddress;
        }

        private List<String> rightFuzzyInstallAddress;

        public List<String> getRightFuzzyInstallAddress() {
            return this.rightFuzzyInstallAddress;
        }

        private List<String> manufacturerContactsList;

        public List<String> getManufacturerContactsList() {
            return this.manufacturerContactsList;
        }


        private List<String> fuzzyManufacturerContacts;

        public List<String> getFuzzyManufacturerContacts() {
            return this.fuzzyManufacturerContacts;
        }

        private List<String> rightFuzzyManufacturerContacts;

        public List<String> getRightFuzzyManufacturerContacts() {
            return this.rightFuzzyManufacturerContacts;
        }

        private List<String> manufacturerContactsNumberList;

        public List<String> getManufacturerContactsNumberList() {
            return this.manufacturerContactsNumberList;
        }


        private List<String> fuzzyManufacturerContactsNumber;

        public List<String> getFuzzyManufacturerContactsNumber() {
            return this.fuzzyManufacturerContactsNumber;
        }

        private List<String> rightFuzzyManufacturerContactsNumber;

        public List<String> getRightFuzzyManufacturerContactsNumber() {
            return this.rightFuzzyManufacturerContactsNumber;
        }

        private List<String> locationInfoList;

        public List<String> getLocationInfoList() {
            return this.locationInfoList;
        }


        private List<String> fuzzyLocationInfo;

        public List<String> getFuzzyLocationInfo() {
            return this.fuzzyLocationInfo;
        }

        private List<String> rightFuzzyLocationInfo;

        public List<String> getRightFuzzyLocationInfo() {
            return this.rightFuzzyLocationInfo;
        }

        private List<String> statusList;

        public List<String> getStatusList() {
            return this.statusList;
        }


        private List<String> fuzzyStatus;

        public List<String> getFuzzyStatus() {
            return this.fuzzyStatus;
        }

        private List<String> rightFuzzyStatus;

        public List<String> getRightFuzzyStatus() {
            return this.rightFuzzyStatus;
        }

        private List<String> auditScopeList;

        public List<String> getAuditScopeList() {
            return this.auditScopeList;
        }


        private List<String> fuzzyAuditScope;

        public List<String> getFuzzyAuditScope() {
            return this.fuzzyAuditScope;
        }

        private List<String> rightFuzzyAuditScope;

        public List<String> getRightFuzzyAuditScope() {
            return this.rightFuzzyAuditScope;
        }

        private List<String> communityIdList;

        public List<String> getCommunityIdList() {
            return this.communityIdList;
        }


        private List<String> fuzzyCommunityId;

        public List<String> getFuzzyCommunityId() {
            return this.fuzzyCommunityId;
        }

        private List<String> rightFuzzyCommunityId;

        public List<String> getRightFuzzyCommunityId() {
            return this.rightFuzzyCommunityId;
        }

        private List<String> communityOrgIdList;

        public List<String> getCommunityOrgIdList() {
            return this.communityOrgIdList;
        }


        private List<String> fuzzyCommunityOrgId;

        public List<String> getFuzzyCommunityOrgId() {
            return this.fuzzyCommunityOrgId;
        }

        private List<String> rightFuzzyCommunityOrgId;

        public List<String> getRightFuzzyCommunityOrgId() {
            return this.rightFuzzyCommunityOrgId;
        }

        private List<String> createByList;

        public List<String> getCreateByList() {
            return this.createByList;
        }


        private List<String> fuzzyCreateBy;

        public List<String> getFuzzyCreateBy() {
            return this.fuzzyCreateBy;
        }

        private List<String> rightFuzzyCreateBy;

        public List<String> getRightFuzzyCreateBy() {
            return this.rightFuzzyCreateBy;
        }

        private List<Date> createTimeList;

        public List<Date> getCreateTimeList() {
            return this.createTimeList;
        }

        private Date createTimeSt;

        private Date createTimeEd;

        public Date getCreateTimeSt() {
            return this.createTimeSt;
        }

        public Date getCreateTimeEd() {
            return this.createTimeEd;
        }

        private List<String> updateByList;

        public List<String> getUpdateByList() {
            return this.updateByList;
        }


        private List<String> fuzzyUpdateBy;

        public List<String> getFuzzyUpdateBy() {
            return this.fuzzyUpdateBy;
        }

        private List<String> rightFuzzyUpdateBy;

        public List<String> getRightFuzzyUpdateBy() {
            return this.rightFuzzyUpdateBy;
        }

        private List<Date> updateTimeList;

        public List<Date> getUpdateTimeList() {
            return this.updateTimeList;
        }

        private Date updateTimeSt;

        private Date updateTimeEd;

        public Date getUpdateTimeSt() {
            return this.updateTimeSt;
        }

        public Date getUpdateTimeEd() {
            return this.updateTimeEd;
        }

        private List<String> remarkList;

        public List<String> getRemarkList() {
            return this.remarkList;
        }


        private List<String> fuzzyRemark;

        public List<String> getFuzzyRemark() {
            return this.fuzzyRemark;
        }

        private List<String> rightFuzzyRemark;

        public List<String> getRightFuzzyRemark() {
            return this.rightFuzzyRemark;
        }

        private List<String> delFlagList;

        public List<String> getDelFlagList() {
            return this.delFlagList;
        }


        private List<String> fuzzyDelFlag;

        public List<String> getFuzzyDelFlag() {
            return this.fuzzyDelFlag;
        }

        private List<String> rightFuzzyDelFlag;

        public List<String> getRightFuzzyDelFlag() {
            return this.rightFuzzyDelFlag;
        }

        public ConditionBuilder fuzzyId(List<String> fuzzyId) {
            this.fuzzyId = fuzzyId;
            return this;
        }

        public ConditionBuilder fuzzyId(String... fuzzyId) {
            this.fuzzyId = solveNullList(fuzzyId);
            return this;
        }

        public ConditionBuilder rightFuzzyId(List<String> rightFuzzyId) {
            this.rightFuzzyId = rightFuzzyId;
            return this;
        }

        public ConditionBuilder rightFuzzyId(String... rightFuzzyId) {
            this.rightFuzzyId = solveNullList(rightFuzzyId);
            return this;
        }

        public ConditionBuilder idList(String... id) {
            this.idList = solveNullList(id);
            return this;
        }

        public ConditionBuilder idList(List<String> id) {
            this.idList = id;
            return this;
        }

        public ConditionBuilder fuzzyEquipmentPhotos(List<String> fuzzyEquipmentPhotos) {
            this.fuzzyEquipmentPhotos = fuzzyEquipmentPhotos;
            return this;
        }

        public ConditionBuilder fuzzyEquipmentPhotos(String... fuzzyEquipmentPhotos) {
            this.fuzzyEquipmentPhotos = solveNullList(fuzzyEquipmentPhotos);
            return this;
        }

        public ConditionBuilder rightFuzzyEquipmentPhotos(List<String> rightFuzzyEquipmentPhotos) {
            this.rightFuzzyEquipmentPhotos = rightFuzzyEquipmentPhotos;
            return this;
        }

        public ConditionBuilder rightFuzzyEquipmentPhotos(String... rightFuzzyEquipmentPhotos) {
            this.rightFuzzyEquipmentPhotos = solveNullList(rightFuzzyEquipmentPhotos);
            return this;
        }

        public ConditionBuilder equipmentPhotosList(String... equipmentPhotos) {
            this.equipmentPhotosList = solveNullList(equipmentPhotos);
            return this;
        }

        public ConditionBuilder equipmentPhotosList(List<String> equipmentPhotos) {
            this.equipmentPhotosList = equipmentPhotos;
            return this;
        }

        public ConditionBuilder fuzzyName(List<String> fuzzyName) {
            this.fuzzyName = fuzzyName;
            return this;
        }

        public ConditionBuilder fuzzyName(String... fuzzyName) {
            this.fuzzyName = solveNullList(fuzzyName);
            return this;
        }

        public ConditionBuilder rightFuzzyName(List<String> rightFuzzyName) {
            this.rightFuzzyName = rightFuzzyName;
            return this;
        }

        public ConditionBuilder rightFuzzyName(String... rightFuzzyName) {
            this.rightFuzzyName = solveNullList(rightFuzzyName);
            return this;
        }

        public ConditionBuilder nameList(String... name) {
            this.nameList = solveNullList(name);
            return this;
        }

        public ConditionBuilder nameList(List<String> name) {
            this.nameList = name;
            return this;
        }

        public ConditionBuilder fuzzyType(List<String> fuzzyType) {
            this.fuzzyType = fuzzyType;
            return this;
        }

        public ConditionBuilder fuzzyType(String... fuzzyType) {
            this.fuzzyType = solveNullList(fuzzyType);
            return this;
        }

        public ConditionBuilder rightFuzzyType(List<String> rightFuzzyType) {
            this.rightFuzzyType = rightFuzzyType;
            return this;
        }

        public ConditionBuilder rightFuzzyType(String... rightFuzzyType) {
            this.rightFuzzyType = solveNullList(rightFuzzyType);
            return this;
        }

        public ConditionBuilder typeList(String... type) {
            this.typeList = solveNullList(type);
            return this;
        }

        public ConditionBuilder typeList(List<String> type) {
            this.typeList = type;
            return this;
        }

        public ConditionBuilder fuzzySn(List<String> fuzzySn) {
            this.fuzzySn = fuzzySn;
            return this;
        }

        public ConditionBuilder fuzzySn(String... fuzzySn) {
            this.fuzzySn = solveNullList(fuzzySn);
            return this;
        }

        public ConditionBuilder rightFuzzySn(List<String> rightFuzzySn) {
            this.rightFuzzySn = rightFuzzySn;
            return this;
        }

        public ConditionBuilder rightFuzzySn(String... rightFuzzySn) {
            this.rightFuzzySn = solveNullList(rightFuzzySn);
            return this;
        }

        public ConditionBuilder snList(String... sn) {
            this.snList = solveNullList(sn);
            return this;
        }

        public ConditionBuilder snList(List<String> sn) {
            this.snList = sn;
            return this;
        }

        public ConditionBuilder fuzzyNumber(List<String> fuzzyNumber) {
            this.fuzzyNumber = fuzzyNumber;
            return this;
        }

        public ConditionBuilder fuzzyNumber(String... fuzzyNumber) {
            this.fuzzyNumber = solveNullList(fuzzyNumber);
            return this;
        }

        public ConditionBuilder rightFuzzyNumber(List<String> rightFuzzyNumber) {
            this.rightFuzzyNumber = rightFuzzyNumber;
            return this;
        }

        public ConditionBuilder rightFuzzyNumber(String... rightFuzzyNumber) {
            this.rightFuzzyNumber = solveNullList(rightFuzzyNumber);
            return this;
        }

        public ConditionBuilder numberList(String... number) {
            this.numberList = solveNullList(number);
            return this;
        }

        public ConditionBuilder numberList(List<String> number) {
            this.numberList = number;
            return this;
        }

        public ConditionBuilder fuzzyModel(List<String> fuzzyModel) {
            this.fuzzyModel = fuzzyModel;
            return this;
        }

        public ConditionBuilder fuzzyModel(String... fuzzyModel) {
            this.fuzzyModel = solveNullList(fuzzyModel);
            return this;
        }

        public ConditionBuilder rightFuzzyModel(List<String> rightFuzzyModel) {
            this.rightFuzzyModel = rightFuzzyModel;
            return this;
        }

        public ConditionBuilder rightFuzzyModel(String... rightFuzzyModel) {
            this.rightFuzzyModel = solveNullList(rightFuzzyModel);
            return this;
        }

        public ConditionBuilder modelList(String... model) {
            this.modelList = solveNullList(model);
            return this;
        }

        public ConditionBuilder modelList(List<String> model) {
            this.modelList = model;
            return this;
        }

        public ConditionBuilder fuzzySpecifications(List<String> fuzzySpecifications) {
            this.fuzzySpecifications = fuzzySpecifications;
            return this;
        }

        public ConditionBuilder fuzzySpecifications(String... fuzzySpecifications) {
            this.fuzzySpecifications = solveNullList(fuzzySpecifications);
            return this;
        }

        public ConditionBuilder rightFuzzySpecifications(List<String> rightFuzzySpecifications) {
            this.rightFuzzySpecifications = rightFuzzySpecifications;
            return this;
        }

        public ConditionBuilder rightFuzzySpecifications(String... rightFuzzySpecifications) {
            this.rightFuzzySpecifications = solveNullList(rightFuzzySpecifications);
            return this;
        }

        public ConditionBuilder specificationsList(String... specifications) {
            this.specificationsList = solveNullList(specifications);
            return this;
        }

        public ConditionBuilder specificationsList(List<String> specifications) {
            this.specificationsList = specifications;
            return this;
        }

        public ConditionBuilder fuzzyManufacturer(List<String> fuzzyManufacturer) {
            this.fuzzyManufacturer = fuzzyManufacturer;
            return this;
        }

        public ConditionBuilder fuzzyManufacturer(String... fuzzyManufacturer) {
            this.fuzzyManufacturer = solveNullList(fuzzyManufacturer);
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturer(List<String> rightFuzzyManufacturer) {
            this.rightFuzzyManufacturer = rightFuzzyManufacturer;
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturer(String... rightFuzzyManufacturer) {
            this.rightFuzzyManufacturer = solveNullList(rightFuzzyManufacturer);
            return this;
        }

        public ConditionBuilder manufacturerList(String... manufacturer) {
            this.manufacturerList = solveNullList(manufacturer);
            return this;
        }

        public ConditionBuilder manufacturerList(List<String> manufacturer) {
            this.manufacturerList = manufacturer;
            return this;
        }

        public ConditionBuilder outTimeBetWeen(Date outTimeSt, Date outTimeEd) {
            this.outTimeSt = outTimeSt;
            this.outTimeEd = outTimeEd;
            return this;
        }

        public ConditionBuilder outTimeGreaterEqThan(Date outTimeSt) {
            this.outTimeSt = outTimeSt;
            return this;
        }

        public ConditionBuilder outTimeLessEqThan(Date outTimeEd) {
            this.outTimeEd = outTimeEd;
            return this;
        }


        public ConditionBuilder outTimeList(Date... outTime) {
            this.outTimeList = solveNullList(outTime);
            return this;
        }

        public ConditionBuilder outTimeList(List<Date> outTime) {
            this.outTimeList = outTime;
            return this;
        }

        public ConditionBuilder installTimeBetWeen(Date installTimeSt, Date installTimeEd) {
            this.installTimeSt = installTimeSt;
            this.installTimeEd = installTimeEd;
            return this;
        }

        public ConditionBuilder installTimeGreaterEqThan(Date installTimeSt) {
            this.installTimeSt = installTimeSt;
            return this;
        }

        public ConditionBuilder installTimeLessEqThan(Date installTimeEd) {
            this.installTimeEd = installTimeEd;
            return this;
        }


        public ConditionBuilder installTimeList(Date... installTime) {
            this.installTimeList = solveNullList(installTime);
            return this;
        }

        public ConditionBuilder installTimeList(List<Date> installTime) {
            this.installTimeList = installTime;
            return this;
        }

        public ConditionBuilder fuzzyInstallAddress(List<String> fuzzyInstallAddress) {
            this.fuzzyInstallAddress = fuzzyInstallAddress;
            return this;
        }

        public ConditionBuilder fuzzyInstallAddress(String... fuzzyInstallAddress) {
            this.fuzzyInstallAddress = solveNullList(fuzzyInstallAddress);
            return this;
        }

        public ConditionBuilder rightFuzzyInstallAddress(List<String> rightFuzzyInstallAddress) {
            this.rightFuzzyInstallAddress = rightFuzzyInstallAddress;
            return this;
        }

        public ConditionBuilder rightFuzzyInstallAddress(String... rightFuzzyInstallAddress) {
            this.rightFuzzyInstallAddress = solveNullList(rightFuzzyInstallAddress);
            return this;
        }

        public ConditionBuilder installAddressList(String... installAddress) {
            this.installAddressList = solveNullList(installAddress);
            return this;
        }

        public ConditionBuilder installAddressList(List<String> installAddress) {
            this.installAddressList = installAddress;
            return this;
        }

        public ConditionBuilder fuzzyManufacturerContacts(List<String> fuzzyManufacturerContacts) {
            this.fuzzyManufacturerContacts = fuzzyManufacturerContacts;
            return this;
        }

        public ConditionBuilder fuzzyManufacturerContacts(String... fuzzyManufacturerContacts) {
            this.fuzzyManufacturerContacts = solveNullList(fuzzyManufacturerContacts);
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturerContacts(List<String> rightFuzzyManufacturerContacts) {
            this.rightFuzzyManufacturerContacts = rightFuzzyManufacturerContacts;
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturerContacts(String... rightFuzzyManufacturerContacts) {
            this.rightFuzzyManufacturerContacts = solveNullList(rightFuzzyManufacturerContacts);
            return this;
        }

        public ConditionBuilder manufacturerContactsList(String... manufacturerContacts) {
            this.manufacturerContactsList = solveNullList(manufacturerContacts);
            return this;
        }

        public ConditionBuilder manufacturerContactsList(List<String> manufacturerContacts) {
            this.manufacturerContactsList = manufacturerContacts;
            return this;
        }

        public ConditionBuilder fuzzyManufacturerContactsNumber(List<String> fuzzyManufacturerContactsNumber) {
            this.fuzzyManufacturerContactsNumber = fuzzyManufacturerContactsNumber;
            return this;
        }

        public ConditionBuilder fuzzyManufacturerContactsNumber(String... fuzzyManufacturerContactsNumber) {
            this.fuzzyManufacturerContactsNumber = solveNullList(fuzzyManufacturerContactsNumber);
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturerContactsNumber(List<String> rightFuzzyManufacturerContactsNumber) {
            this.rightFuzzyManufacturerContactsNumber = rightFuzzyManufacturerContactsNumber;
            return this;
        }

        public ConditionBuilder rightFuzzyManufacturerContactsNumber(String... rightFuzzyManufacturerContactsNumber) {
            this.rightFuzzyManufacturerContactsNumber = solveNullList(rightFuzzyManufacturerContactsNumber);
            return this;
        }

        public ConditionBuilder manufacturerContactsNumberList(String... manufacturerContactsNumber) {
            this.manufacturerContactsNumberList = solveNullList(manufacturerContactsNumber);
            return this;
        }

        public ConditionBuilder manufacturerContactsNumberList(List<String> manufacturerContactsNumber) {
            this.manufacturerContactsNumberList = manufacturerContactsNumber;
            return this;
        }

        public ConditionBuilder fuzzyLocationInfo(List<String> fuzzyLocationInfo) {
            this.fuzzyLocationInfo = fuzzyLocationInfo;
            return this;
        }

        public ConditionBuilder fuzzyLocationInfo(String... fuzzyLocationInfo) {
            this.fuzzyLocationInfo = solveNullList(fuzzyLocationInfo);
            return this;
        }

        public ConditionBuilder rightFuzzyLocationInfo(List<String> rightFuzzyLocationInfo) {
            this.rightFuzzyLocationInfo = rightFuzzyLocationInfo;
            return this;
        }

        public ConditionBuilder rightFuzzyLocationInfo(String... rightFuzzyLocationInfo) {
            this.rightFuzzyLocationInfo = solveNullList(rightFuzzyLocationInfo);
            return this;
        }

        public ConditionBuilder locationInfoList(String... locationInfo) {
            this.locationInfoList = solveNullList(locationInfo);
            return this;
        }

        public ConditionBuilder locationInfoList(List<String> locationInfo) {
            this.locationInfoList = locationInfo;
            return this;
        }

        public ConditionBuilder fuzzyStatus(List<String> fuzzyStatus) {
            this.fuzzyStatus = fuzzyStatus;
            return this;
        }

        public ConditionBuilder fuzzyStatus(String... fuzzyStatus) {
            this.fuzzyStatus = solveNullList(fuzzyStatus);
            return this;
        }

        public ConditionBuilder rightFuzzyStatus(List<String> rightFuzzyStatus) {
            this.rightFuzzyStatus = rightFuzzyStatus;
            return this;
        }

        public ConditionBuilder rightFuzzyStatus(String... rightFuzzyStatus) {
            this.rightFuzzyStatus = solveNullList(rightFuzzyStatus);
            return this;
        }

        public ConditionBuilder statusList(String... status) {
            this.statusList = solveNullList(status);
            return this;
        }

        public ConditionBuilder statusList(List<String> status) {
            this.statusList = status;
            return this;
        }

        public ConditionBuilder fuzzyAuditScope(List<String> fuzzyAuditScope) {
            this.fuzzyAuditScope = fuzzyAuditScope;
            return this;
        }

        public ConditionBuilder fuzzyAuditScope(String... fuzzyAuditScope) {
            this.fuzzyAuditScope = solveNullList(fuzzyAuditScope);
            return this;
        }

        public ConditionBuilder rightFuzzyAuditScope(List<String> rightFuzzyAuditScope) {
            this.rightFuzzyAuditScope = rightFuzzyAuditScope;
            return this;
        }

        public ConditionBuilder rightFuzzyAuditScope(String... rightFuzzyAuditScope) {
            this.rightFuzzyAuditScope = solveNullList(rightFuzzyAuditScope);
            return this;
        }

        public ConditionBuilder auditScopeList(String... auditScope) {
            this.auditScopeList = solveNullList(auditScope);
            return this;
        }

        public ConditionBuilder auditScopeList(List<String> auditScope) {
            this.auditScopeList = auditScope;
            return this;
        }

        public ConditionBuilder fuzzyCommunityId(List<String> fuzzyCommunityId) {
            this.fuzzyCommunityId = fuzzyCommunityId;
            return this;
        }

        public ConditionBuilder fuzzyCommunityId(String... fuzzyCommunityId) {
            this.fuzzyCommunityId = solveNullList(fuzzyCommunityId);
            return this;
        }

        public ConditionBuilder rightFuzzyCommunityId(List<String> rightFuzzyCommunityId) {
            this.rightFuzzyCommunityId = rightFuzzyCommunityId;
            return this;
        }

        public ConditionBuilder rightFuzzyCommunityId(String... rightFuzzyCommunityId) {
            this.rightFuzzyCommunityId = solveNullList(rightFuzzyCommunityId);
            return this;
        }

        public ConditionBuilder communityIdList(String... communityId) {
            this.communityIdList = solveNullList(communityId);
            return this;
        }

        public ConditionBuilder communityIdList(List<String> communityId) {
            this.communityIdList = communityId;
            return this;
        }

        public ConditionBuilder fuzzyCommunityOrgId(List<String> fuzzyCommunityOrgId) {
            this.fuzzyCommunityOrgId = fuzzyCommunityOrgId;
            return this;
        }

        public ConditionBuilder fuzzyCommunityOrgId(String... fuzzyCommunityOrgId) {
            this.fuzzyCommunityOrgId = solveNullList(fuzzyCommunityOrgId);
            return this;
        }

        public ConditionBuilder rightFuzzyCommunityOrgId(List<String> rightFuzzyCommunityOrgId) {
            this.rightFuzzyCommunityOrgId = rightFuzzyCommunityOrgId;
            return this;
        }

        public ConditionBuilder rightFuzzyCommunityOrgId(String... rightFuzzyCommunityOrgId) {
            this.rightFuzzyCommunityOrgId = solveNullList(rightFuzzyCommunityOrgId);
            return this;
        }

        public ConditionBuilder communityOrgIdList(String... communityOrgId) {
            this.communityOrgIdList = solveNullList(communityOrgId);
            return this;
        }

        public ConditionBuilder communityOrgIdList(List<String> communityOrgId) {
            this.communityOrgIdList = communityOrgId;
            return this;
        }

        public ConditionBuilder fuzzyCreateBy(List<String> fuzzyCreateBy) {
            this.fuzzyCreateBy = fuzzyCreateBy;
            return this;
        }

        public ConditionBuilder fuzzyCreateBy(String... fuzzyCreateBy) {
            this.fuzzyCreateBy = solveNullList(fuzzyCreateBy);
            return this;
        }

        public ConditionBuilder rightFuzzyCreateBy(List<String> rightFuzzyCreateBy) {
            this.rightFuzzyCreateBy = rightFuzzyCreateBy;
            return this;
        }

        public ConditionBuilder rightFuzzyCreateBy(String... rightFuzzyCreateBy) {
            this.rightFuzzyCreateBy = solveNullList(rightFuzzyCreateBy);
            return this;
        }

        public ConditionBuilder createByList(String... createBy) {
            this.createByList = solveNullList(createBy);
            return this;
        }

        public ConditionBuilder createByList(List<String> createBy) {
            this.createByList = createBy;
            return this;
        }

        public ConditionBuilder createTimeBetWeen(Date createTimeSt, Date createTimeEd) {
            this.createTimeSt = createTimeSt;
            this.createTimeEd = createTimeEd;
            return this;
        }

        public ConditionBuilder createTimeGreaterEqThan(Date createTimeSt) {
            this.createTimeSt = createTimeSt;
            return this;
        }

        public ConditionBuilder createTimeLessEqThan(Date createTimeEd) {
            this.createTimeEd = createTimeEd;
            return this;
        }


        public ConditionBuilder createTimeList(Date... createTime) {
            this.createTimeList = solveNullList(createTime);
            return this;
        }

        public ConditionBuilder createTimeList(List<Date> createTime) {
            this.createTimeList = createTime;
            return this;
        }

        public ConditionBuilder fuzzyUpdateBy(List<String> fuzzyUpdateBy) {
            this.fuzzyUpdateBy = fuzzyUpdateBy;
            return this;
        }

        public ConditionBuilder fuzzyUpdateBy(String... fuzzyUpdateBy) {
            this.fuzzyUpdateBy = solveNullList(fuzzyUpdateBy);
            return this;
        }

        public ConditionBuilder rightFuzzyUpdateBy(List<String> rightFuzzyUpdateBy) {
            this.rightFuzzyUpdateBy = rightFuzzyUpdateBy;
            return this;
        }

        public ConditionBuilder rightFuzzyUpdateBy(String... rightFuzzyUpdateBy) {
            this.rightFuzzyUpdateBy = solveNullList(rightFuzzyUpdateBy);
            return this;
        }

        public ConditionBuilder updateByList(String... updateBy) {
            this.updateByList = solveNullList(updateBy);
            return this;
        }

        public ConditionBuilder updateByList(List<String> updateBy) {
            this.updateByList = updateBy;
            return this;
        }

        public ConditionBuilder updateTimeBetWeen(Date updateTimeSt, Date updateTimeEd) {
            this.updateTimeSt = updateTimeSt;
            this.updateTimeEd = updateTimeEd;
            return this;
        }

        public ConditionBuilder updateTimeGreaterEqThan(Date updateTimeSt) {
            this.updateTimeSt = updateTimeSt;
            return this;
        }

        public ConditionBuilder updateTimeLessEqThan(Date updateTimeEd) {
            this.updateTimeEd = updateTimeEd;
            return this;
        }


        public ConditionBuilder updateTimeList(Date... updateTime) {
            this.updateTimeList = solveNullList(updateTime);
            return this;
        }

        public ConditionBuilder updateTimeList(List<Date> updateTime) {
            this.updateTimeList = updateTime;
            return this;
        }

        public ConditionBuilder fuzzyRemark(List<String> fuzzyRemark) {
            this.fuzzyRemark = fuzzyRemark;
            return this;
        }

        public ConditionBuilder fuzzyRemark(String... fuzzyRemark) {
            this.fuzzyRemark = solveNullList(fuzzyRemark);
            return this;
        }

        public ConditionBuilder rightFuzzyRemark(List<String> rightFuzzyRemark) {
            this.rightFuzzyRemark = rightFuzzyRemark;
            return this;
        }

        public ConditionBuilder rightFuzzyRemark(String... rightFuzzyRemark) {
            this.rightFuzzyRemark = solveNullList(rightFuzzyRemark);
            return this;
        }

        public ConditionBuilder remarkList(String... remark) {
            this.remarkList = solveNullList(remark);
            return this;
        }

        public ConditionBuilder remarkList(List<String> remark) {
            this.remarkList = remark;
            return this;
        }

        public ConditionBuilder fuzzyDelFlag(List<String> fuzzyDelFlag) {
            this.fuzzyDelFlag = fuzzyDelFlag;
            return this;
        }

        public ConditionBuilder fuzzyDelFlag(String... fuzzyDelFlag) {
            this.fuzzyDelFlag = solveNullList(fuzzyDelFlag);
            return this;
        }

        public ConditionBuilder rightFuzzyDelFlag(List<String> rightFuzzyDelFlag) {
            this.rightFuzzyDelFlag = rightFuzzyDelFlag;
            return this;
        }

        public ConditionBuilder rightFuzzyDelFlag(String... rightFuzzyDelFlag) {
            this.rightFuzzyDelFlag = solveNullList(rightFuzzyDelFlag);
            return this;
        }

        public ConditionBuilder delFlagList(String... delFlag) {
            this.delFlagList = solveNullList(delFlag);
            return this;
        }

        public ConditionBuilder delFlagList(List<String> delFlag) {
            this.delFlagList = delFlag;
            return this;
        }

        private <T> List<T> solveNullList(T... objs) {
            if (objs != null) {
                List<T> list = new ArrayList<>();
                for (T item : objs) {
                    if (item != null) {
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public ConditionBuilder build() {
            return this;
        }
    }

    public static class Builder {

        private BaseCommunityDevice obj;

        public Builder() {
            this.obj = new BaseCommunityDevice();
        }

        public Builder id(String id) {
            this.obj.setId(id);
            return this;
        }

        public Builder equipmentPhotos(String equipmentPhotos) {
            this.obj.setEquipmentPhotos(equipmentPhotos);
            return this;
        }

        public Builder name(String name) {
            this.obj.setName(name);
            return this;
        }

        public Builder type(String type) {
            this.obj.setType(type);
            return this;
        }

        public Builder sn(String sn) {
            this.obj.setSn(sn);
            return this;
        }

        public Builder number(String number) {
            this.obj.setNumber(number);
            return this;
        }

        public Builder model(String model) {
            this.obj.setModel(model);
            return this;
        }

        public Builder specifications(String specifications) {
            this.obj.setSpecifications(specifications);
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.obj.setManufacturer(manufacturer);
            return this;
        }

        public Builder outTime(Date outTime) {
            this.obj.setOutTime(outTime);
            return this;
        }

        public Builder installTime(Date installTime) {
            this.obj.setInstallTime(installTime);
            return this;
        }

        public Builder installAddress(String installAddress) {
            this.obj.setInstallAddress(installAddress);
            return this;
        }

        public Builder manufacturerContacts(String manufacturerContacts) {
            this.obj.setManufacturerContacts(manufacturerContacts);
            return this;
        }

        public Builder manufacturerContactsNumber(String manufacturerContactsNumber) {
            this.obj.setManufacturerContactsNumber(manufacturerContactsNumber);
            return this;
        }

        public Builder locationInfo(String locationInfo) {
            this.obj.setLocationInfo(locationInfo);
            return this;
        }

        public Builder status(String status) {
            this.obj.setStatus(status);
            return this;
        }

        public Builder auditScope(String auditScope) {
            this.obj.setAuditScope(auditScope);
            return this;
        }

        public Builder communityId(String communityId) {
            this.obj.setCommunityId(communityId);
            return this;
        }

        public Builder communityOrgId(String communityOrgId) {
            this.obj.setCommunityOrgId(communityOrgId);
            return this;
        }

        public Builder createBy(String createBy) {
            this.obj.setCreateBy(createBy);
            return this;
        }

        public Builder createTime(Date createTime) {
            this.obj.setCreateTime(createTime);
            return this;
        }

        public Builder updateBy(String updateBy) {
            this.obj.setUpdateBy(updateBy);
            return this;
        }

        public Builder updateTime(Date updateTime) {
            this.obj.setUpdateTime(updateTime);
            return this;
        }

        public Builder remark(String remark) {
            this.obj.setRemark(remark);
            return this;
        }

        public Builder delFlag(String delFlag) {
            this.obj.setDelFlag(delFlag);
            return this;
        }

        public BaseCommunityDevice build() {
            return obj;
        }
    }

}

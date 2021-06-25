package com.isstech.isstechadmin.service;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.isstechadmin.domain.BaseCommunityDevice;

import java.util.List;

public interface BaseCommunityDeviceService {
    int insertBaseCommunityDevice(BaseCommunityDevice object);

    int updateBaseCommunityDevice(BaseCommunityDevice object);

    int update(BaseCommunityDevice.UpdateBuilder object);

    List<BaseCommunityDevice> queryBaseCommunityDevice(BaseCommunityDevice object);

    BaseCommunityDevice queryBaseCommunityDeviceLimit1(BaseCommunityDevice object);

    /**
     * 业务接口
     *
     * @param object
     * @return
     */
    AjaxResult bizSave(BaseCommunityDevice object);

    AjaxResult deleteByIds(String[] strings);
}

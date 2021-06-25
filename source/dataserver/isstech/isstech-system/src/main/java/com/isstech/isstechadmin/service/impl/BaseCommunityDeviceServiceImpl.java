package com.isstech.isstechadmin.service.impl;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.uuid.UUID;
import com.isstech.isstechadmin.domain.BaseCommunityDevice;
import com.isstech.isstechadmin.mapper.BaseCommunityDeviceMapper;
import com.isstech.isstechadmin.service.BaseCommunityDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BaseCommunityDeviceServiceImpl implements BaseCommunityDeviceService {

    private final BaseCommunityDeviceMapper baseCommunityDeviceMapper;

    public BaseCommunityDeviceServiceImpl(BaseCommunityDeviceMapper baseCommunityDeviceMapper) {
        this.baseCommunityDeviceMapper = baseCommunityDeviceMapper;
    }

    @Override
    public int insertBaseCommunityDevice(BaseCommunityDevice object) {
        return baseCommunityDeviceMapper.insertBaseCommunityDevice(object);
    }

    @Override
    public int updateBaseCommunityDevice(BaseCommunityDevice object) {
        return baseCommunityDeviceMapper.updateBaseCommunityDevice(object);
    }

    @Override
    public int update(BaseCommunityDevice.UpdateBuilder object) {

// examples: like lamdaquery
//       BaseCommunityDevice.UpdateBuilder up = BaseCommunityDevice.UpdateBuild()
//                .where(BaseCommunityDevice.ConditionBuild()
//                        .idList()
//                        .build())
//                .build();

        return baseCommunityDeviceMapper.update(object);
    }

    @Override
    public List<BaseCommunityDevice> queryBaseCommunityDevice(BaseCommunityDevice object) {
        return baseCommunityDeviceMapper.queryBaseCommunityDevice(object);
    }

    @Override
    public BaseCommunityDevice queryBaseCommunityDeviceLimit1(BaseCommunityDevice object) {
        return baseCommunityDeviceMapper.queryBaseCommunityDeviceLimit1(object);
    }

    /**
     * 业务接口
     *
     * @param object
     * @return
     */
    @Override
    public AjaxResult bizSave(BaseCommunityDevice object) {
        if (StringUtils.isEmpty(object.getCommunityId()) ||
                StringUtils.isEmpty(object.getType()) ||
                StringUtils.isEmpty(object.getName()) ||
                StringUtils.isEmpty(object.getManufacturerContacts()) ||
                StringUtils.isEmpty(object.getManufacturerContactsNumber()) ||
                StringUtils.isEmpty(object.getCreateBy())
        ) {
            return AjaxResult.error("params is null");
        }
        Date dateNow = DateUtils.getNowDate();
        object.setId(UUID.fastUUID().toString());
        object.setCreateTime(dateNow);
        object.setUpdateTime(dateNow);

        return AjaxResult.success(this.insertBaseCommunityDevice(object));
    }

    @Override
    public AjaxResult deleteByIds(String[] strings) {
        if (CollectionUtils.isEmpty(Arrays.asList(strings))) {
            return AjaxResult.error("params is null");
        }
        return AjaxResult.success(baseCommunityDeviceMapper.deleteByIds(strings));
    }
}

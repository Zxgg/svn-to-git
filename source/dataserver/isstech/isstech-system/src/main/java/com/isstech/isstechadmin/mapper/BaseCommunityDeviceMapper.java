package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseCommunityDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author author
 */
@Mapper
public interface BaseCommunityDeviceMapper {
    int insertBaseCommunityDevice(BaseCommunityDevice object);

    int updateBaseCommunityDevice(BaseCommunityDevice object);

    int update(BaseCommunityDevice.UpdateBuilder object);

    List<BaseCommunityDevice> queryBaseCommunityDevice(BaseCommunityDevice object);

    BaseCommunityDevice queryBaseCommunityDeviceLimit1(BaseCommunityDevice object);

    int deleteByIds(String[] strings);

}
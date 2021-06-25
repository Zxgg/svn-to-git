package com.isstech.web.controller.communityUnit;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseCommunityDevice;
import com.isstech.isstechadmin.service.BaseCommunityDeviceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "BaseCommunityDeviceController", description = "社区设备信息")
@RestController
@RequestMapping("/community/device")
public class BaseCommunityDeviceController {

    @Autowired
    private BaseCommunityDeviceService baseCommunityDeviceService;

    /**
     * 新增设备
     *
     * @param baseCommunityDevice
     * @return
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BaseCommunityDevice baseCommunityDevice) {
        return baseCommunityDeviceService.bizSave(baseCommunityDevice);
    }

    @PostMapping("/query")
    public AjaxResult query(@RequestBody BaseCommunityDevice baseCommunityDevice) {
        return AjaxResult.success(baseCommunityDeviceService.queryBaseCommunityDevice(baseCommunityDevice));
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody BaseCommunityDevice baseCommunityDevice) {
        if (StringUtils.isEmpty(baseCommunityDevice.getCommunityId())) return AjaxResult.error("id is null");
        return AjaxResult.success(baseCommunityDeviceService.updateBaseCommunityDevice(baseCommunityDevice));
    }

    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestBody String[] strings) {
        return baseCommunityDeviceService.deleteByIds(strings);
    }


}

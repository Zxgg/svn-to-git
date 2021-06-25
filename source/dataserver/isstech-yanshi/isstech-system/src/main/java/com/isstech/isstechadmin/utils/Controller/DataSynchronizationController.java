package com.isstech.isstechadmin.utils.Controller;

import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.isstechadmin.utils.domain.SysDataSynchronization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/data/synchronization")
public class DataSynchronizationController extends BaseController {

    @Autowired
    private com.isstech.isstechadmin.utils.DataSynchronizationUtils DataSynchronizationUtils;

    /**
     * 数据同步接口-接收的数据为向上同步
     */
    @PostMapping("up")
    public AjaxResult up(HttpServletRequest request, @RequestBody SysDataSynchronization information)
    {
        return DataSynchronizationUtils.synUp(request,information);
    }

    /**
     * 数据同步接口-接收的数据为向下同步
     */
    @PostMapping("next")
    public AjaxResult next(@RequestBody SysDataSynchronization information)
    {
        return DataSynchronizationUtils.synNext(information);
    }
}

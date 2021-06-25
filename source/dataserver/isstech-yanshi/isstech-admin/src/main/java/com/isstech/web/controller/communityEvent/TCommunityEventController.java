package com.isstech.web.controller.communityEvent;

import java.util.List;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isstech.isstechadmin.domain.TCommunityEvent;
import com.isstech.isstechadmin.service.ITCommunityEventService;

/**
 * 社区事件 Controller
 *
 * @author yzp
 * @date 2020-12-12
 */
@Api(tags ="TCommunityEvent", description = "社区事件 ")
@RestController
@RequestMapping("/communityEvent/community_event")
public class TCommunityEventController extends BaseController
{
    @Autowired
    private ITCommunityEventService tCommunityEventService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区事件 列表
     */
    @ApiOperation("查询社区事件 列表")
//    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCommunityEvent tCommunityEvent)
    {
//        tCommunityEvent.setAllDataAccess(dataAccessUtils.isAllDataAccess());
//        tCommunityEvent.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TCommunityEvent> list = tCommunityEventService.selectTCommunityEventList(tCommunityEvent);
        return getDataTable(list);
    }

    /**
     * 导出社区事件 列表
     */
    @ApiOperation("导出社区事件 列表")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:export')")
    @Log(title = "社区事件 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TCommunityEvent tCommunityEvent)
    {
        List<TCommunityEvent> list = tCommunityEventService.selectTCommunityEventList(tCommunityEvent);
        ExcelUtil<TCommunityEvent> util = new ExcelUtil<TCommunityEvent>(TCommunityEvent.class);
        return util.exportExcel(list, "community_event");
    }

    /**
     * 获取社区事件 详细信息
     */
    @ApiOperation("获取社区事件 详细信息")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tCommunityEventService.selectTCommunityEventById(id));
    }

    /**
     * 新增社区事件 
     */
    @ApiOperation("新增社区事件 ")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:add')")
    @Log(title = "社区事件 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCommunityEvent tCommunityEvent)
    {
        return toAjax(tCommunityEventService.insertTCommunityEvent(tCommunityEvent));
    }

    /**
     * 修改社区事件 
     */
    @ApiOperation("修改社区事件 ")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:edit')")
    @Log(title = "社区事件 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCommunityEvent tCommunityEvent)
    {
        return toAjax(tCommunityEventService.updateTCommunityEvent(tCommunityEvent));
    }

    /**
     * 删除社区事件 
     */
    @ApiOperation("删除社区事件 ")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:remove')")
    @Log(title = "社区事件 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tCommunityEventService.deleteTCommunityEventByIds(ids));
    }
    /**
     * 软删除社区事件
     */
    @ApiOperation("软删除社区事件 ")
    @PreAuthorize("@ss.hasPermi('communityEvent:community_event:remove')")
    @Log(title = "社区事件 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tCommunityEventService.updateTCommunityEventByIds(ids));
    }
}

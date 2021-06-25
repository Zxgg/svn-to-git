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
import com.isstech.isstechadmin.domain.TCaringEvent;
import com.isstech.isstechadmin.service.ITCaringEventService;

/**
 * 关爱人员事件Controller
 *
 * @author qsw
 * @date 2020-12-12
 */
@Api(tags ="TCaringEvent", description = "关爱人员事件")
@RestController
@RequestMapping("/caringPerson/caring_event")
public class TCaringEventController extends BaseController
{
    @Autowired
    private ITCaringEventService tCaringEventService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询关爱人员事件列表
     */
    @ApiOperation("查询关爱人员事件列表")
//    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCaringEvent tCaringEvent)
    {
        tCaringEvent.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tCaringEvent.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TCaringEvent> list = tCaringEventService.selectTCaringEventList(tCaringEvent);
        return getDataTable(list);
    }

    /**
     * 导出关爱人员事件列表
     */
    @ApiOperation("导出关爱人员事件列表")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:export')")
    @Log(title = "关爱人员事件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TCaringEvent tCaringEvent)
    {
        List<TCaringEvent> list = tCaringEventService.selectTCaringEventList(tCaringEvent);
        ExcelUtil<TCaringEvent> util = new ExcelUtil<TCaringEvent>(TCaringEvent.class);
        return util.exportExcel(list, "caring_event");
    }

    /**
     * 获取关爱人员事件详细信息
     */
    @ApiOperation("获取关爱人员事件详细信息")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tCaringEventService.selectTCaringEventById(id));
    }

    /**
     * 新增关爱人员事件
     */
    @ApiOperation("新增关爱人员事件")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:add')")
    @Log(title = "关爱人员事件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCaringEvent tCaringEvent)
    {
        return toAjax(tCaringEventService.insertTCaringEvent(tCaringEvent));
    }

    /**
     * 修改关爱人员事件
     */
    @ApiOperation("修改关爱人员事件")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:edit')")
    @Log(title = "关爱人员事件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCaringEvent tCaringEvent)
    {
        return toAjax(tCaringEventService.updateTCaringEvent(tCaringEvent));
    }

    /**
     * 删除关爱人员事件
     */
    @ApiOperation("删除关爱人员事件")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:remove')")
    @Log(title = "关爱人员事件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tCaringEventService.deleteTCaringEventByIds(ids));
    }

    /**
     * 软删除关爱人员事件
     */
    @ApiOperation("软删除关爱人员事件")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_event:remove')")
    @Log(title = "关爱人员事件", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tCaringEventService.updateTCaringEventByIds(ids));
    }
}

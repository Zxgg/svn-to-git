package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.vo.PassPersonnelVo;
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
import com.isstech.isstechadmin.domain.BasePassPersonnel;
import com.isstech.isstechadmin.service.IBasePassPersonnelService;

/**
 * 人员通行Controller
 *
 * @author yzp
 * @date 2020-12-12
 */
@Api(tags ="BasePassPersonnel", description = "人员通行")
@RestController
@RequestMapping("/passManage/pass_personnel")
public class BasePassPersonnelController extends BaseController
{
    @Autowired
    private IBasePassPersonnelService basePassPersonnelService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询人员通行列表
     */
    @ApiOperation("查询人员通行列表")
//    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(PassPersonnelVo passPersonnelVo)
    {
        passPersonnelVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        passPersonnelVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePassPersonnel> list = basePassPersonnelService.selectBasePassPersonnelList(passPersonnelVo);
        return getDataTable(list);
    }

    /**
     * 导出人员通行列表
     */
    @ApiOperation("导出人员通行列表")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:export')")
    @Log(title = "人员通行", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PassPersonnelVo passPersonnelVo)
    {
        List<BasePassPersonnel> list = basePassPersonnelService.selectBasePassPersonnelList(passPersonnelVo);
        ExcelUtil<BasePassPersonnel> util = new ExcelUtil<BasePassPersonnel>(BasePassPersonnel.class);
        return util.exportExcel(list, "pass_personnel");
    }

    /**
     * 获取人员通行详细信息
     */
    @ApiOperation("获取人员通行详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:query')")
    @GetMapping(value = "/{faceId}")
    public AjaxResult getInfo(@PathVariable("faceId") String faceId)
    {
        return AjaxResult.success(basePassPersonnelService.selectBasePassPersonnelById(faceId));
    }

    /**
     * 新增人员通行
     */
    @ApiOperation("新增人员通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:add')")
    @Log(title = "人员通行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePassPersonnel basePassPersonnel)
    {
        return toAjax(basePassPersonnelService.insertBasePassPersonnel(basePassPersonnel));
    }

    /**
     * 修改人员通行
     */
    @ApiOperation("修改人员通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:edit')")
    @Log(title = "人员通行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePassPersonnel basePassPersonnel)
    {
        return toAjax(basePassPersonnelService.updateBasePassPersonnel(basePassPersonnel));
    }

    /**
     * 删除人员通行
     */
    @ApiOperation("删除人员通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:remove')")
    @Log(title = "人员通行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{faceIds}")
    public AjaxResult remove(@PathVariable String[] faceIds)
    {
        return toAjax(basePassPersonnelService.deleteBasePassPersonnelByIds(faceIds));
    }
    /**
     * 软删除人员通行
     */
    @ApiOperation("删除人员通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_personnel:remove')")
    @Log(title = "人员通行", businessType = BusinessType.DELETE)
	@PutMapping("/{faceIds}")
    public AjaxResult update(@PathVariable String[] faceIds)
    {
        return toAjax(basePassPersonnelService.updateBasePassPersonnelByIds(faceIds));
    }

    /**
     * 查询关爱人员的通行记录
     * @param passPersonnelVo
     * @return 结果
     */
    @GetMapping("/getCarePassList")
    public TableDataInfo getCarePassList(PassPersonnelVo passPersonnelVo){
        startPage();
        List<PassPersonnelVo> list = basePassPersonnelService.getCarePassList(passPersonnelVo);
        return getDataTable(list);
    }
}

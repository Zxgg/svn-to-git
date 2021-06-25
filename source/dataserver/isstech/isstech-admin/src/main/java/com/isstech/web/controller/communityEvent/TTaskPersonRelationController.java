package com.isstech.web.controller.communityEvent;

import java.util.List;

import com.isstech.isstechadmin.mapper.BasePassAnalysisTaskMapper;
import com.isstech.web.service.DataProcessingService;
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
import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.enums.BusinessType;
import com.isstech.isstechadmin.domain.TTaskPersonRelation;
import com.isstech.isstechadmin.service.ITTaskPersonRelationService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

import javax.annotation.PostConstruct;

/**
 * 人脸和人员对应关系任务 Controller
 *
 * @author yzp
 * @date 2020-12-17
 */
@Api(tags ="TTaskPersonRelation", description = "人脸和人员对应关系任务 ")
@RestController
@RequestMapping("/isstechadmin/relation")
public class TTaskPersonRelationController extends BaseController
{
    @Autowired
    private ITTaskPersonRelationService tTaskPersonRelationService;
    @Autowired
    private BasePassAnalysisTaskMapper basePassAnalysisTaskMapper;
    @Autowired
    private DataProcessingService dataProcessingService;

    /**
     * 查询人脸和人员对应关系任务 列表
     */
    @ApiOperation("查询人脸和人员对应关系任务 列表")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTaskPersonRelation tTaskPersonRelation)
    {
        startPage();
        List<TTaskPersonRelation> list = tTaskPersonRelationService.selectTTaskPersonRelationList(tTaskPersonRelation);
        return getDataTable(list);
    }

    /**
     * 导出人脸和人员对应关系任务 列表
     */
    @ApiOperation("导出人脸和人员对应关系任务 列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:export')")
    @Log(title = "人脸和人员对应关系任务 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TTaskPersonRelation tTaskPersonRelation)
    {
        List<TTaskPersonRelation> list = tTaskPersonRelationService.selectTTaskPersonRelationList(tTaskPersonRelation);
        ExcelUtil<TTaskPersonRelation> util = new ExcelUtil<TTaskPersonRelation>(TTaskPersonRelation.class);
        return util.exportExcel(list, "relation");
    }

    /**
     * 获取人脸和人员对应关系任务 详细信息
     */
    @ApiOperation("获取人脸和人员对应关系任务 详细信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:query')")
    @GetMapping(value = "/{peopleId}")
    public AjaxResult getInfo(@PathVariable("peopleId") String peopleId)
    {
        return AjaxResult.success(tTaskPersonRelationService.selectTTaskPersonRelationById(peopleId));
    }

    /**
     * 新增人脸和人员对应关系任务
     */
    @ApiOperation("新增人脸和人员对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:add')")
    @Log(title = "人脸和人员对应关系任务 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTaskPersonRelation tTaskPersonRelation)
    {
        return toAjax(tTaskPersonRelationService.insertTTaskPersonRelation(tTaskPersonRelation));
    }

    /**
     * 修改人脸和人员对应关系任务
     */
    @ApiOperation("修改人脸和人员对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:edit')")
    @Log(title = "人脸和人员对应关系任务 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTaskPersonRelation tTaskPersonRelation)
    {
        return toAjax(tTaskPersonRelationService.updateTTaskPersonRelation(tTaskPersonRelation));
    }

    /**
     * 删除人脸和人员对应关系任务
     */
    @ApiOperation("删除人脸和人员对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:remove')")
    @Log(title = "人脸和人员对应关系任务 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{peopleIds}")
    public AjaxResult remove(@PathVariable String[] peopleIds)
    {
        return toAjax(tTaskPersonRelationService.deleteTTaskPersonRelationByIds(peopleIds));
    }

    /**
     * 软删除人脸和人员对应关系任务
     */
    @ApiOperation("软删除人脸和人员对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('isstechadmin:relation:remove')")
    @Log(title = "人脸和人员对应关系任务 ", businessType = BusinessType.DELETE)
	@PutMapping("/{peopleIds}")
    public AjaxResult update(@PathVariable String[] peopleIds)
    {
        return toAjax(tTaskPersonRelationService.updateTTaskPersonRelationByIds(peopleIds));
    }


    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init()
    {
        //人员白名单
        List<String> suspectWhiteIds = basePassAnalysisTaskMapper.querySuspectIdsByRoster02();
        //陌生人
        List<String> suspectStrangers = basePassAnalysisTaskMapper.querySuspectIdsByRoster04();
        //人员黑名单
        List<String> suspectBlackIds = basePassAnalysisTaskMapper.querySuspectIdsByRoster01();
        StringBuilder repositorWhiteIdstr = new StringBuilder();
        StringBuilder repositorBlackIdstr = new StringBuilder();
        StringBuilder repositorStrangerstr = new StringBuilder();
        suspectWhiteIds.stream().forEach(id ->{
            repositorWhiteIdstr.append(","+id);
        });
        String repositorWhiteIds = repositorWhiteIdstr.toString().replaceFirst(",", "");
        suspectBlackIds.stream().forEach(id ->{
            repositorBlackIdstr.append(","+id);
        });
        String repositorBlackIds = repositorBlackIdstr.toString().replaceFirst(",", "");
        suspectStrangers.stream().forEach(id ->{
            repositorStrangerstr.append(","+id);
        });
        String repositorStrangers = repositorStrangerstr.toString().replaceFirst(",", "");
        dataProcessingService.syc(repositorWhiteIds,"01",repositorBlackIds,"02",repositorStrangers,"03");
    }
}

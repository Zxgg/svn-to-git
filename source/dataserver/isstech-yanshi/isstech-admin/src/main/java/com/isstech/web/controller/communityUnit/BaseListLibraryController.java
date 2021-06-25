package com.isstech.web.controller.communityUnit;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.framework.web.fastdfs.FastDfsConfig;
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
import com.isstech.isstechadmin.domain.BaseListLibrary;
import com.isstech.isstechadmin.service.IBaseListLibraryService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 名单库Controller
 *
 * @author yzp
 * @date 2021-01-05
 */
@Api(tags = "BaseListLibrary", description = "名单库")
@RestController
@RequestMapping("/list/library")
public class BaseListLibraryController extends BaseController {
    @Autowired
    private IBaseListLibraryService baseListLibraryService;

    /**
     * 查询名单库列表
     */
    @ApiOperation("查询名单库列表")
//    @PreAuthorize("@ss.hasPermi('list:library:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseListLibrary baseListLibrary) {
        startPage();
        List<BaseListLibrary> list = baseListLibraryService.selectBaseListLibraryList(baseListLibrary);
        return getDataTable(list);
    }

    /**
     * 导出名单库列表
     */
    @ApiOperation("导出名单库列表")
//    @PreAuthorize("@ss.hasPermi('list:library:export')")
    @Log(title = "名单库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseListLibrary baseListLibrary) {
        List<BaseListLibrary> list = baseListLibraryService.selectBaseListLibraryList(baseListLibrary);
        ExcelUtil<BaseListLibrary> util = new ExcelUtil<BaseListLibrary>(BaseListLibrary.class);
        return util.exportExcel(list, "library");
    }

    /**
     * 获取名单库详细信息
     */
    @ApiOperation("获取名单库详细信息")
//    @PreAuthorize("@ss.hasPermi('list:library:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(baseListLibraryService.selectBaseListLibraryById(id));
    }

    /**
     * 新增人员名单库
     */
    @ApiOperation("新增人员名单库")
    @PreAuthorize("@ss.hasPermi('list:library:add')")
    @Log(title = "名单库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody String json) {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/facerepositories", json);
        if (result.getData() == null&& !"200".equals(result.getStatus())) {
            String status = result.getStatus();
            String message = result.getMessage();
            return AjaxResult.error("添加人员名单库失败");
        }
        JSONObject addstr = JSONObject.parseObject(result.getData().toString());
        String listId = addstr.getString("id");

        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject repository = jsonObject.getJSONObject("repository");
        String type = repository.getString("type");
        String name = repository.getString("name");
        String description = repository.getString("description");
        BaseListLibrary baseListLibrary = new BaseListLibrary();
        baseListLibrary.setListId(listId);
        baseListLibrary.setListName(name);
        baseListLibrary.setDescribes(description);
        baseListLibrary.setType(type);
        return AjaxResult.success(baseListLibraryService.insertBaseListLibrary(baseListLibrary));
    }

    /**
     * 修改人员名单库
     */
    @ApiOperation("修改人员名单库")
    @PreAuthorize("@ss.hasPermi('list:library:edit')")
    @Log(title = "名单库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseListLibrary baseListLibrary) {
        Map map = new HashMap();
        Map map1 = new HashMap();
        map1.put("id", baseListLibrary.getListId());
        map1.put("name", baseListLibrary.getListName());
        map1.put("description",baseListLibrary.getDescribes());
        map.put("repository",map1);
        String json = JSONObject.toJSONString(map);
        Resp resp = HttpClientUtil.doPut(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/facerepositories_pa?id=" + baseListLibrary.getListId(), json);
        if (resp.getData() == null&& !"200".equals(resp.getStatus())) {
            String status = resp.getStatus();
            String message = resp.getMessage();
            return AjaxResult.error( "修改人员名单库失败");
        }
        return toAjax(baseListLibraryService.updateBaseListLibrary(baseListLibrary));
    }

    /**
     * 删除人员名单库
     */
    @ApiOperation("删除人员名单库")
    @PreAuthorize("@ss.hasPermi('list:library:remove')")
    @Log(title = "名单库", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id") String id) {
        BaseListLibrary baseListLibrary = baseListLibraryService.selectBaseListLibraryById(id);
        Resp resp = HttpClientUtil.doPut(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/facerepositories_id?id=" + baseListLibrary.getListId(), "{}");
        if (resp.getData() == null&& !"200".equals(resp.getStatus())) {
            String status = resp.getStatus();
            String message = resp.getMessage();
            return AjaxResult.error( "删除人员名单库失败");
        }
        return toAjax(baseListLibraryService.deleteBaseListLibraryById(id));
    }
    /**
     * 新增车辆名单库
     */
    @ApiOperation("新增车辆名单库")
    @PreAuthorize("@ss.hasPermi('list:library:add')")
    @Log(title = "名单库", businessType = BusinessType.INSERT)
    @PostMapping("/addVehicleList")
    public AjaxResult addVehicleList(@RequestBody String json) {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/add_bwgroup", json);
        if (result.getData() == null&& !"200".equals(result.getStatus())) {
            String status = result.getStatus();
            String message = result.getMessage();
            return AjaxResult.error( "添加车辆名单库失败");
        }
        JSONObject addstr = JSONObject.parseObject(result.getData().toString());
        String listId = addstr.getString("groupid");

        JSONObject jsonObject = JSONObject.parseObject(json);
        String type = jsonObject.getString("type");
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        BaseListLibrary baseListLibrary = new BaseListLibrary();
        baseListLibrary.setListId(listId);
        baseListLibrary.setListName(name);
        baseListLibrary.setDescribes(description);
        baseListLibrary.setType(type);
        return AjaxResult.success(baseListLibraryService.insertBaseListLibrary(baseListLibrary));
    }

    /**
     * 修改车辆名单库
     */
    @ApiOperation("修改车辆名单库")
    @PreAuthorize("@ss.hasPermi('list:library:edit')")
    @Log(title = "名单库", businessType = BusinessType.UPDATE)
    @PutMapping("/editVehicleList")
    public AjaxResult editVehicleList(@RequestBody BaseListLibrary baseListLibrary) {
        Map map = new HashMap();
        map.put("groupid", baseListLibrary.getListId());
        map.put("name", baseListLibrary.getListName());
        map.put("description",baseListLibrary.getDescribes());
        map.put("type",baseListLibrary.getType());
        String json = JSONObject.toJSONString(map);
        Resp resp = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/update_bwgroup" , json);
        if (resp.getData() == null&& !"200".equals(resp.getStatus())) {
            String status = resp.getStatus();
            String message = resp.getMessage();
            return AjaxResult.error( "修改车辆名单库失败");
        }
        return toAjax(baseListLibraryService.updateBaseListLibrary(baseListLibrary));
    }

    /**
     * 删除车辆名单库
     */
    @ApiOperation("删除车辆名单库")
    @PreAuthorize("@ss.hasPermi('list:library:remove')")
    @Log(title = "名单库", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteVehicleList/{id}")
    public AjaxResult removeVehicleList(@PathVariable("id") String id) {
        BaseListLibrary baseListLibrary = baseListLibraryService.selectBaseListLibraryById(id);
        String listId = baseListLibrary.getListId();
        Map map = new HashMap();
        Map map1 = new HashMap();
        List grouplist = new ArrayList();
        grouplist.add(listId);
        map1.put("groupid",grouplist);
        map.put("grouplist",map1);
        map.put("type","0");
        map.put("isDelList","true");
        String json = JSONObject.toJSONString(map);
        Resp resp = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn + "/api/open/V1.0.0/vcm/intelligent_analysis/delete_bwgroup" , json);
        if (resp.getData() == null&& !"200".equals(resp.getStatus())) {
            String status = resp.getStatus();
            String message = resp.getMessage();
            return AjaxResult.error( "删除车辆名单库失败");
        }
        return toAjax(baseListLibraryService.deleteBaseListLibraryById(id));
    }

    /**
     * 批量删除名单库
     */
    @ApiOperation("批量删除名单库")
    @PreAuthorize("@ss.hasPermi('list:library:remove')")
    @Log(title = "名单库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult removelist(@PathVariable String[] ids) {
        return toAjax(baseListLibraryService.deleteBaseListLibraryByIds(ids));
    }

    /**
     * 软删除名单库
     */
    @ApiOperation("软删除名单库")
    @PreAuthorize("@ss.hasPermi('list:library:remove')")
    @Log(title = "名单库", businessType = BusinessType.DELETE)
    @PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids) {
        return toAjax(baseListLibraryService.updateBaseListLibraryByIds(ids));
    }
}

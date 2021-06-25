package com.isstech.web.controller.communityUnit;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.framework.web.fastdfs.FastDfsConfig;
import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.vo.PeopleVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.service.IBasePersonnelInformationService;
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
import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.enums.BusinessType;
import com.isstech.isstechadmin.domain.BasePassAnalysisTask;
import com.isstech.isstechadmin.service.IBasePassAnalysisTaskService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的Controller
 *
 * @author yzp
 * @date 2020-12-18
 */
@Api(tags ="BasePassAnalysisTask", description = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的")
@RestController
@RequestMapping("/isstechadmin/task")
public class BasePassAnalysisTaskController extends BaseController
{
    @Autowired
    private IBasePassAnalysisTaskService basePassAnalysisTaskService;
    @Autowired
    private IBasePersonnelInformationService basePersonnelInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表
     */
    @ApiOperation("查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePassAnalysisTask basePassAnalysisTask)
    {
        basePassAnalysisTask.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        basePassAnalysisTask.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePassAnalysisTask> list = basePassAnalysisTaskService.selectBasePassAnalysisTaskList(basePassAnalysisTask);
        return getDataTable(list);
    }

    /**
     * 导出社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表
     */
    @ApiOperation("导出社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:export')")
    @Log(title = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasePassAnalysisTask basePassAnalysisTask)
    {
        List<BasePassAnalysisTask> list = basePassAnalysisTaskService.selectBasePassAnalysisTaskList(basePassAnalysisTask);
        ExcelUtil<BasePassAnalysisTask> util = new ExcelUtil<BasePassAnalysisTask>(BasePassAnalysisTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的详细信息
     */
    @ApiOperation("获取社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的详细信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basePassAnalysisTaskService.selectBasePassAnalysisTaskById(id));
    }

    /**
     * 新增社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @ApiOperation("新增社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:add')")
    @Log(title = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody String json)
    {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String hitType = jsonObject.getString("hitType");// 1 命中 2 非命中
        String communityId = jsonObject.getString("communityId");
        String communityChildId = jsonObject.getString("communityChildId");
        String rosterType = jsonObject.getString("rosterType");
        String groupNames = jsonObject.getString("groupNames");
        String bayonetNames = jsonObject.getString("bayonetNames");
        String groupIdstr = null;
        StringBuilder groupIds = new StringBuilder();
        StringBuilder groupNameStr = new StringBuilder();
        List grouplis = new ArrayList();
        // 白名单命中 ,  白名单非命中=陌生人
//        if("03".equals(rosterType) && "1".equals(hitType)){
//            QueryVo queryVo = new QueryVo();
//            queryVo.setCommunityId(communityId);
//            queryVo.setCommunityChildId(communityChildId);
//            queryVo.setDelFlag("0");
//            int count = basePersonnelInformationService.queryCount(queryVo);
//            int size = 100;
//            int pages = count / size + 1;
//            for (int i = 0; i < pages; i++) {
//                Map map = new HashMap();
//                Map map1 = new HashMap();
//                String groupName = "通行白名单"+communityId+"-"+i+"-"+new Random().nextInt()+"-"+communityChildId;
//                map1.put("type", 3);
//                map1.put("name", groupName);
//                map1.put("description", "");
//                map.put("repository",map1);
//                String whiteJson = JSONObject.toJSONString(map);
//                Resp addResult = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/facerepositories", whiteJson);
//                if(addResult.getData() == null && !"200".equals(addResult.getStatus())){
//                    String status = addResult.getStatus();
//                    String message = addResult.getMessage();
//                    return AjaxResult.error(status,message + "添加白名单库失败");
//                }
//                JSONObject addstr = JSONObject.parseObject(addResult.getData().toString());
//                String id = addstr.getString("id");
//
//                List peopleList = new ArrayList();
//                List people = new ArrayList();
//                Map mapPeople = new HashMap();
////                System.out.println("页码--"+i*size+"---条数----"+size);
//                queryVo.setLimitNum(i*size);
//                queryVo.setLimitSize(size);
//                List<BasePersonnelInformation> personList = basePersonnelInformationService.queryBasePerson(queryVo);
//                personList.stream().forEach(item->{
//                    PeopleVo peopleVo = new PeopleVo();
//                    peopleVo.setName(item.getName());
//                    peopleVo.setGender(item.getSex());
//                    peopleVo.setCredentialType(item.getIdType());
//                    peopleVo.setCredentialNumber(item.getIdentificationNumber());
//                    peopleVo.setCountry(item.getNationality());
//                    peopleVo.setNationality(item.getNation());
//                    peopleVo.setOccupation(item.getProfessioDetail());
//                    peopleVo.setBornTime(DateUtils.dateTime(item.getBrithDate()));
//                    peopleVo.setDescription(item.getRemark());
//                    String fileId = item.getFileId();
//                    List slist = new ArrayList();
//                    Map mapPicture = new HashMap();
//                    if(StringUtils.isNotEmpty(fileId)){
//                        String[] files =fileId.split(",");
//                        for (int j = 0; j < files.length; j++) {
//                            Map mapj = new HashMap();
//                            String file = files[j];
//                            mapj.put("fileId",file);
//                            slist.add(mapj);
//                        }
//                    }
//                    mapPicture.put("picture",slist);
//                    peopleVo.setPictures(mapPicture);
//                    people.add(peopleVo);
//                });
//
//                Map mapList = new HashMap();
//                mapList.put("sync","false");
//                mapList.put("repositorId",id);
//                mapList.put("callbackURL",null);
//                mapPeople.put("people",people);
//                peopleList.add(mapPeople);
//                mapList.put("peopleList",peopleList);
//                String perJson = JSONObject.toJSONString(mapList);
////                System.out.println(perJson);
//                Resp addPersons = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/face_Dif?repositorid="+id, perJson);
//                if(addPersons.getData() == null && !"200".equals(addResult.getStatus())){
//                    String status = addResult.getStatus();
//                    String message = addResult.getMessage();
//                    return AjaxResult.error(status,message + "人员批量添加白名单失败");
//                }
//                grouplis.add(id);
//                groupNameStr.append("," + id+":"+groupName);
//                groupIds.append("," + id);
//            }
//            groupIdstr = groupIds.toString().replaceFirst(",", "");
//            groupNames = groupNameStr.toString().replaceFirst(",", "");
//        }else {
            JSONObject groupList = jsonObject.getJSONObject("groupList");
            JSONArray groupId = groupList.getJSONArray("groupId");

            for (int i = 0; i < groupId.size(); i++) {
                String gid = groupId.getString(i);
                groupIds.append("," + gid);
            }
            groupIdstr = groupIds.toString().replaceFirst(",", "");
//        }

        String name = jsonObject.getString("name");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");
        String enable = jsonObject.getString("enable");
        String confidenceThreshold = jsonObject.getString("confidenceThreshold");
        String srcDomainCode = jsonObject.getString("srcDomainCode");
        String alarmLevel = jsonObject.getString("alarmLevel");
        String type = jsonObject.getString("type");
        String plateSimilar = jsonObject.getString("plateSimilar");

        JSONObject domains = jsonObject.getJSONObject("domains");

        JSONArray domain = domains.getJSONArray("domain");
        JSONObject jsonObject1 = domain.getJSONObject(0);
//        String string = domain.getString(0);
//        JSONObject jsonObject1 = JSONObject.parseObject(string);
        JSONArray bayonetList = jsonObject1.getJSONArray("bayonetList");
        JSONObject jsonObject2 = bayonetList.getJSONObject(0);
        JSONArray bayonetSn = jsonObject2.getJSONArray("bayonetSn");

//        JSONObject domain = domains.getJSONObject("domain");
//        JSONObject bayonetList = domain.getJSONObject("bayonetList");
//        JSONArray bayonetSn = bayonetList.getJSONArray("bayonetSn");
        JSONObject timeList = jsonObject.getJSONObject("timeList");


        Map suspMap = new HashMap();
        suspMap.put("name",name);
        suspMap.put("startDate",startDate);
        suspMap.put("endDate",endDate);
        suspMap.put("enable",enable);
        suspMap.put("confidenceThreshold",confidenceThreshold);
        suspMap.put("srcDomainCode",srcDomainCode);
        suspMap.put("alarmLevel",alarmLevel);
        suspMap.put("type",type);
        suspMap.put("hitType",hitType);
        suspMap.put("plateSimilar",plateSimilar);
        suspMap.put("domains",domains);
        suspMap.put("timeList",timeList);
        Map mapGroup = new HashMap();
        mapGroup.put("groupId",grouplis);
        suspMap.put("groupList",mapGroup);

        String suspJson = JSONObject.toJSONString(suspMap);

        Resp ajaxResult = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/add_suspect_task", suspJson);
        if(ajaxResult.getData() == null&& !"200".equals(ajaxResult.getStatus())){
            String status = ajaxResult.getStatus();
            String message = ajaxResult.getMessage();
            return AjaxResult.error(status,message + "添加布控任务失败");
        }
        String suspectId = ajaxResult.getData().toString();
        BasePassAnalysisTask basePassAnalysisTask = new BasePassAnalysisTask();

        StringBuilder bayonets = new StringBuilder();
        List bayonetSnList = new ArrayList();
        for (int j = 0; j < bayonetSn.size(); j++) {
            String baySn = bayonetSn.getString(j);
            bayonetSnList.add(baySn);
            bayonets.append(","+baySn);
        }
        String bayonetstr = bayonets.toString().replaceFirst(",", "");
        basePassAnalysisTask.setName(name);
        basePassAnalysisTask.setStartDate(DateUtils.parseDate(startDate));
        basePassAnalysisTask.setEndDate(DateUtils.parseDate(endDate));
        basePassAnalysisTask.setEnable(enable);
        basePassAnalysisTask.setConfidenceThreshold(Long.parseLong(confidenceThreshold));
        basePassAnalysisTask.setSrcDomainCode(srcDomainCode);
        basePassAnalysisTask.setAlarmLevel(Long.parseLong(alarmLevel));
        basePassAnalysisTask.setSuspectId(suspectId);
        basePassAnalysisTask.setType(type);
        basePassAnalysisTask.setHitType(hitType);
        basePassAnalysisTask.setSelectGroups(groupIdstr);
        basePassAnalysisTask.setSelectBayonets(bayonetstr);
        basePassAnalysisTask.setCommunityId(communityId);
        basePassAnalysisTask.setCommunityChildId(communityChildId);
        basePassAnalysisTask.setRosterType(rosterType);
        basePassAnalysisTask.setBayonetNames(bayonetNames);
        basePassAnalysisTask.setGroupNames(groupNames);
        return toAjax(basePassAnalysisTaskService.insertBasePassAnalysisTask(basePassAnalysisTask));
    }

    /**
     * 修改社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @ApiOperation("修改社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:edit')")
    @Log(title = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody String json)
    {
        Resp ajaxResult = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/edit_suspect_task", json);
        if(ajaxResult.getData() == null&& !"200".equals(ajaxResult.getStatus())){
            return AjaxResult.error("修改布控任务失败");
        }
        String suspectId = ajaxResult.getData().toString();
        BasePassAnalysisTask basePassAnalysisTask = basePassAnalysisTaskService.queryEntityBySuspectId(suspectId);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String name = jsonObject.getString("name");
        String groupNames = jsonObject.getString("groupNames");
        String bayonetNames = jsonObject.getString("bayonetNames");
        String startDate = jsonObject.getString("startDate");
        String endDate = jsonObject.getString("endDate");
        String enable = jsonObject.getString("enable");
        String confidenceThreshold = jsonObject.getString("confidenceThreshold");
        String srcDomainCode = jsonObject.getString("srcDomainCode");
        String alarmLevel = jsonObject.getString("alarmLevel");
        String type = jsonObject.getString("type");
        String hitType = jsonObject.getString("hitType");
        JSONObject groupList = jsonObject.getJSONObject("groupList");
        JSONArray groupId = groupList.getJSONArray("groupId");
        JSONObject domains = jsonObject.getJSONObject("domains");
//        JSONObject domain = domains.getJSONObject("domain");
//        JSONObject bayonetList = domain.getJSONObject("bayonetList");
//        JSONArray bayonetSn = bayonetList.getJSONArray("bayonetSn");


        JSONArray domain = domains.getJSONArray("domain");
        JSONObject jsonObject1 = domain.getJSONObject(0);
        JSONArray bayonetList = jsonObject1.getJSONArray("bayonetList");
        JSONObject jsonObject2 = bayonetList.getJSONObject(0);
        JSONArray bayonetSn = jsonObject2.getJSONArray("bayonetSn");
        StringBuilder groupIds = new StringBuilder();
        StringBuilder bayonets = new StringBuilder();
        for (int i = 0; i < groupId.size(); i++) {
            String gid = groupId.getString(i);
            groupIds.append("," + gid);
        }
        String groupIdstr = groupIds.toString().replaceFirst(",", "");
        for (int j = 0; j < bayonetSn.size(); j++) {
            String baySn = bayonetSn.getString(j);
            bayonets.append(","+baySn);
        }
        String bayonetstr = bayonets.toString().replaceFirst(",", "");
        basePassAnalysisTask.setName(name);
        basePassAnalysisTask.setStartDate(DateUtils.parseDate(startDate));
        basePassAnalysisTask.setEndDate(DateUtils.parseDate(endDate));
        basePassAnalysisTask.setEnable(enable);
        basePassAnalysisTask.setConfidenceThreshold(Long.parseLong(confidenceThreshold));
        basePassAnalysisTask.setSrcDomainCode(srcDomainCode);
        basePassAnalysisTask.setAlarmLevel(Long.parseLong(alarmLevel));
        basePassAnalysisTask.setType(type);
        basePassAnalysisTask.setHitType(hitType);
        basePassAnalysisTask.setSelectGroups(groupIdstr);
        basePassAnalysisTask.setSelectBayonets(bayonetstr);
        basePassAnalysisTask.setBayonetNames(bayonetNames);
        basePassAnalysisTask.setGroupNames(groupNames);
        return toAjax(basePassAnalysisTaskService.updateBasePassAnalysisTask(basePassAnalysisTask));
    }

    /**
     * 删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @ApiOperation("删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:remove')")
    @Log(title = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basePassAnalysisTaskService.deleteBasePassAnalysisTaskByIds(ids));
    }

    /**
     * 软删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @ApiOperation("软删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的")
    @PreAuthorize("@ss.hasPermi('isstechadmin:task:remove')")
    @Log(title = "社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(basePassAnalysisTaskService.updateBasePassAnalysisTaskByIds(ids));
    }


    @DeleteMapping("/deleteBySuspectId")
    public AjaxResult deleteBySuspectId(@RequestBody String json)
    {
        Resp ajaxResult = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/delete_suspect_task", json);
        if(!"200".equals(ajaxResult.getStatus())){
            return AjaxResult.error("删除布控任务失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject suspectList = jsonObject.getJSONObject("suspectList");
        JSONArray suspectId = suspectList.getJSONArray("suspectId");
        String[] susids = suspectId.toArray(new String[0]);
        return toAjax(basePassAnalysisTaskService.deleteBySuspectId(susids));
    }

    @PostMapping("/updateEnable")
    public AjaxResult updateEnable(@RequestBody String json)
    {
        Resp ajaxResult = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/enable_suspect_task", json);
        if(!"200".equals(ajaxResult.getStatus())){
            return AjaxResult.error("启停布控任务失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        String enable = jsonObject.getString("enable");
        JSONObject suspectList = jsonObject.getJSONObject("suspectList");
        JSONArray suspectId = suspectList.getJSONArray("suspectId");
        String susid = suspectId.getString(0);
        try {
            basePassAnalysisTaskService.updateEnableBySuspectId(susid,enable);
        } catch (Exception e) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


}

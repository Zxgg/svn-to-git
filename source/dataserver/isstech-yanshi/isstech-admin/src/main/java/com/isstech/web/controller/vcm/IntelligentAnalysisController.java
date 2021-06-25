package com.isstech.web.controller.vcm;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.Resp;
import com.isstech.common.utils.http.HttpClientUtil;
import com.isstech.framework.web.fastdfs.FastDfsConfig;
import com.isstech.isstechadmin.service.IBaseListLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 *  名单
 * @author zpyank
 * @version 1.0
 * @date 2021/1/5 16:34
 */
@RestController
@RequestMapping("/intelligentAnalysis")
public class IntelligentAnalysisController {

    @Autowired
    private IBaseListLibraryService iBaseListLibraryService;

    /**
     * 查询人员名单列表
     * @param json
     * @return
     */
    @PostMapping(value = "/queryPeoples")
    public Resp getInfo(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/query_peoples", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "查询人员名单失败");
        }
        return result;
    }

    /**
     *  新增人员名单
     * @param json
     * @return
     */
    @PostMapping(value = "/addPeopleFace")
    public AjaxResult addInfo(@RequestBody String json,@RequestParam(name = "listId") String listId)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/face_Dif?repositorid="+listId, json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return AjaxResult.error(status,message + "新增人员名单失败");
        }
        return AjaxResult.success();
    }

    /**
     *  修改人员名单
     * @param json
     * @return
     */
    @PostMapping(value = "/updatePeopleFace")
    public AjaxResult updateInfo(@RequestBody String json,@RequestParam(name = "listId") String listId,@RequestParam(name = "peopleId") String peopleId)
    {
        Resp result = HttpClientUtil.doPut(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/update_peoples_id?repositorid="+listId+"&id="+peopleId, json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return AjaxResult.error(status,message + "修改人员名单失败");
        }
        return AjaxResult.success();
    }

    /**
     *  删除人员名单
     * @param json
     * @return
     */
    @PostMapping(value = "/deletePeopleFace")
    public AjaxResult deleteInfo(@RequestBody String json,@RequestParam(name = "listId") String listId)
    {
        Resp result = HttpClientUtil.doPut(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/peoples_feature_delete?repositorid="+listId, json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return AjaxResult.error(status,message + "删除人员名单失败");
        }
        return AjaxResult.success();
    }

    /**
     * 下载base64 图片
     * @param fileId
     * @return
     */
    @GetMapping(value = "/downloadFile")
    public Resp downloadFile(@RequestParam(name = "fileId") String fileId)
    {
        Resp result = HttpClientUtil.doGet(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/mediaManagement/download_file?casefileid="+fileId);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "下载图片失败");
        }
        return result;
    }

    /**
     * 查询车辆名单列表
     * @param json
     * @return
     */
    @PostMapping(value = "/getVehicleList")
    public Resp getVehicleList(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/search_bwdata", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "查询车辆名单失败");
        }
        return result;
    }

    /**
     *  新增车辆名单
     * @param json
     * @return
     */
    @PostMapping(value = "/addVehicle")
    public Resp addVehicle(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/addb_wdata", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "新增车辆名单失败");
        }
        return result;
    }

    /**
     *  修改车辆名单
     * @param json
     * @return
     */
    @PostMapping(value = "/updateVehicle")
    public Resp updateVehicle(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/update_bwdata", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "修改车辆名单失败");
        }
        return result;
    }

    /**
     *  删除车辆名单
     * @param json
     * @return
     */
    @PostMapping(value = "/deleteVehicle")
    public Resp deleteVehicle(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/delete_bwdata", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "删除车辆名单失败");
        }
        return result;
    }

    /**
     * 查询人脸信息-人脸检索
     * @param json
     * @return
     */
    @PostMapping(value = "/peopleFaces")
    public Resp peopleFaces(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/people_faces", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "查询人脸失败");
        }
        return result;
    }


    /**
     * 根据特征值查询车辆信息
     * @param json
     * @return
     */
    @PostMapping(value = "/searchVehicle")
    public Resp searchVehicle(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/search_vehicle", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "根据特征值查询车辆信息失败");
        }
        return result;
    }

    /**
     * 根据特征值和图片查询车辆信息-车辆检索,以图搜图
     * @param json
     * @return
     */
    @PostMapping(value = "/imageSearchVehicle")
    public Resp imageSearchVehicle(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/image_search_vehicle", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "根据特征值和图片查询车辆信息失败");
        }
        return result;
    }

    /**
     * 导出过人过脸搜索结果——导出人脸结果
     * @param json
     * @return
     */
    @PostMapping(value = "/exportPeopleFaces")
    public Resp exportPeopleFaces(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/export_people_faces", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "出过人过脸搜索结果失败");
        }
        return result;
    }
    /**
     * 根据车辆特征搜索车辆，并导出
     * @param json
     * @return
     */
    @PostMapping(value = "/exportVehicleForFeature")
    public Resp exportVehicleForFeature(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/export_vehicle_for_feature", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "根据车辆特征搜索车辆，并导出失败");
        }
        return result;
    }
    /**
     * 根据图片搜索车辆，并导出到Excel
     * @param json
     * @return
     */
    @PostMapping(value = "/exportVehicleForImages")
    public Resp exportVehicleForImages(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/export_vehicle_for_images", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "根据图片搜索车辆，并导出到Excel失败");
        }
        return result;
    }

    /**
     * 布控预警查询
     * @param json
     * @return
     */
    @PostMapping(value = "/searchAlarm")
    public Resp searchAlarm(@RequestBody String json)
    {
        Resp result = HttpClientUtil.doPostJson(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/search_alarm", json);
        if(result.getData() == null && !"200".equals(result.getStatus())){
            String status = result.getStatus();
            String message = result.getMessage();
            return Resp.result(status,message + "布控预警查询失败");
        }
        return result;
    }

    /**
     * 布控预警查询结果-导出
     * @param pageNo
     * @param pageSize
     * @param rule
     * @param endTime
     * @param startTime
     * @param response
     * @return
     */
    @GetMapping(value = "/exportHistoryAlarm")
    public Resp exportHistoryAlarm(@RequestParam(required = false, defaultValue = "1") String pageNo,
                                   @RequestParam(required = false, defaultValue = "10") String pageSize,
                                   @RequestParam(required = false, defaultValue = "16") String rule,
                                   @RequestParam(required = false, defaultValue = "1611040217000") String endTime,
                                   @RequestParam(required = false, defaultValue = "1608422400000") String startTime,
                                   HttpServletResponse response)
    {
        Map<String, String> param = new HashMap<>();
        param.put("pageNo",pageNo);
        param.put("pageSize",pageSize);
        param.put("rule",rule);
        param.put("endTime",endTime);
        param.put("startTime",startTime);
//        Resp result = HttpClientUtil.doGet(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/export_history_alarm", param);
//        if(result.getData() == null && !"200".equals(result.getStatus())){
//            String status = result.getStatus();
//            String message = result.getMessage();
//            return Resp.result(status,message + "布控预警查询结果-导出失败");
//        }
        return HttpClientUtil.doGet(FastDfsConfig.hwVpn+"/api/open/V1.0.0/vcm/intelligent_analysis/export_history_alarm", param);
    }

}

package com.isstech.web.controller.common;

import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.service.IACountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.text.ParseException;
import java.util.List;

/**
 * @Description 统计量操作
 * @Author yzp
 * @Date 2020/12/1 17:24
 */
@RestController
public class CountController extends BaseController
{
    @Autowired
    private IACountService iaCountService;

    /**
     * 首页- 人员占比统计
     * @return 结果
     */
    @GetMapping("count/countPerProportion")
    public AjaxResult countPerProportion(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countPerProportion(queryVo));
    }

    /**
     * 首页-社区内组织统计
     * @return 结果
     */
    @GetMapping("count/countOrganization")
    public AjaxResult countOrganization (){
        return AjaxResult.success(iaCountService.countOrganization());
    }

    /**
     * 首页-摄像头数量  是否在线(0否1是,默认1)
     * @return 结果
     */
    @GetMapping("count/countCaptureOnline")
    public AjaxResult countCaptureOnline (){
        return AjaxResult.success(iaCountService.countCaptureOnline());
    }

    /**
     * 首页 - 关爱人员类型
     * @return 结果
     */
    @GetMapping("count/countCaringType")
    public AjaxResult countCaringType (QueryVo queryVo){
        return AjaxResult.success(iaCountService.countCaringType(queryVo));
    }

    /**
     * 首页 - 房屋统计（包括房屋数量和入住率）
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    @GetMapping("count/countHouse")
    public AjaxResult countHouse (QueryVo queryVo){
        return AjaxResult.success(iaCountService.countHouse(queryVo));
    }

    /**
     * 首页 - 最近车辆活动数
     * @return 结果
     */
    @GetMapping("count/recentVehicleActivity")
    public AjaxResult recentVehicleActivity (){
        //TODO 待定
        String c = "45678";
        return AjaxResult.success(c);
    }

    /**
     * 首页 - 社区事件类型统计
     * @return 结果
     */
    @GetMapping("count/eventNumberStatistics")
    public AjaxResult eventNumberStatistics (QueryVo queryVo){
        return AjaxResult.success(iaCountService.countEventName(queryVo));
    }

    /**
     * 首页 - 事件环比
     * @return 结果
     */
    @GetMapping("count/eventRing")
    public AjaxResult eventRing (QueryVo queryVo){
        return AjaxResult.success(iaCountService.eventRing(queryVo));
    }

    /**
     * 首页 - 人口流动情况
     * @return 结果
     */
    @GetMapping("count/flowPopulation")
    public AjaxResult flowPopulation (QueryVo queryVo){
        return AjaxResult.success(iaCountService.countFloat(queryVo));
    }

    /**
     * 人员信息 - 人员信息统计
     * @return 结果
     */
    @GetMapping("count/perStatistics")
    public AjaxResult perStatistics (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countSexPerson(queryVo));
    }

    /**
     * 人员信息 - 文化程度统计
     * @return 结果
     */
    @GetMapping("count/educationStatistics")
    public AjaxResult educationStatistics (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countDegreePerson(queryVo));
    }

    /**
     * 人员信息 - 年龄统计
     * @return 结果
     */
    @GetMapping("count/ageStatistics")
    public AjaxResult ageStatistics (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countAgePerson(queryVo));
    }

    /**
     * 人员信息 - 民族统计
     * @return 结果
     */
    @GetMapping("count/nationStatistics")
    public AjaxResult nationStatistics (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countNationPerson(queryVo));
    }

    /**
     * 人员信息 - 人员住房信息统计
     * @return 结果
     */
    @GetMapping("count/perHouseInformStatistics")
    public AjaxResult perHouseInformStatistics (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countResidencePerson(queryVo));
    }

    /**
     * 人员信息 - 各小区人员数量
     * @return 结果
     */
    @GetMapping("count/eachCommunityPerNum")
    public AjaxResult eachCommunityPerNum (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countCommunityPerson(queryVo));
    }

    /**
     * 人员信息 - 特殊/管控人群
     * @return 结果
     */
    @GetMapping("count/specialPopulation")
    public AjaxResult specialPopulation (QueryVo queryVo){

        return AjaxResult.success(iaCountService.countSpecialPerson(queryVo));
    }

    /**
     * 车辆信息 - 小区车辆统计
     * @return 结果
     * @author qishuwei
     * @date 2020-12-08
     */
    @GetMapping("/count/eachCommunityVehicle")
    public  AjaxResult eachCommunityVehicle(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countCommunityVehicle(queryVo));
    }

    /**
     *车辆信息-管控车辆
     *@return 结果
     *@author qishuwei
     *@date 2020-12-08
     */
    @GetMapping("/count/getLimitedByCommunity")
    public AjaxResult getLimitedByCommunity(QueryVo queryVo){
        return AjaxResult.success(iaCountService.getLimitedByCommunity(queryVo));
    }

    /**
     *车辆信息-车辆类别
     *@return 结果
     *@author qishuwei
     *@date 2020-12-08
     */
    @GetMapping("/count/getVehicleSort")
    public AjaxResult getVehicleSort(QueryVo queryVo){
        return AjaxResult.success(iaCountService.getVehicleSort(queryVo));
    }

    /**
     * 首页-关爱人员事件统计(当前时间-3天前)
     * @return 结果
     * @author qishuwei
     * @date 2020-12-11
     */
    @GetMapping("/count/countCaringEvent")
    public AjaxResult countCaringEvent(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countCaringEvent(queryVo));
    }

    /**
     * 首页-统计房屋的使用类型
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    @GetMapping("/count/countHouseUseNature")
    public AjaxResult countHouseUseNature(QueryVo queryVo) {
        return AjaxResult.success(iaCountService.countHouseUseNature(queryVo));
    }

    /**
     * 人员信息-类型统计（特殊，管控，关爱）
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    @GetMapping("/count/countNoNormalClass")
    public AjaxResult countNoNormalClass(QueryVo queryVo) {
        return AjaxResult.success(iaCountService.countNoNormalClass(queryVo));
    }

    /**
     * 车辆信息-统计车辆的使用年限（0-6年，6年以上到15年，15年以上）
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-16
     */
    @GetMapping("/count/countVehicleAge")
    public AjaxResult countVehicleAge(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countVehicleAge(queryVo));
    }

    /**
     * 统计车辆的能源类型
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-16
     */
    @GetMapping("/count/countVehicleEnergy")
    public AjaxResult countVehicleEnergy(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countVehicleEnergy(queryVo));
    }

    /**
     * 访客车辆类型统计（客运车 货运车 校车 公交车  快递车 垃圾车） 暂定一周
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-16
     */
    @GetMapping("/count/getVisitVehicleType")
    public AjaxResult getVisitVehicleType(QueryVo queryVo){
        return AjaxResult.success(iaCountService.getVisitVehicleType(queryVo));
    }


    /**
     * 统计过去一周车辆通行的次数
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-17
     */
    @GetMapping("/count/countVehiclePass")
    public AjaxResult countVehiclePass(QueryVo queryVo){
        queryVo.setType(1);
        return AjaxResult.success(iaCountService.countVehiclePass(queryVo));
    }

    /**
     * 统计过去一周访客记录（人和车）按照通行时间进行统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-17
     */
    @GetMapping("/count/countVisit")
    public AjaxResult countVisit(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countVisit(queryVo));
    }


    /**
     * 统计过去一周访客记录（人和车） 按照source_table_name进行统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-18
     */
    @GetMapping("/count/countVisitType")
    public AjaxResult countVisitType(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countVisitType(queryVo));
    }

    /**
     * 统计前一天的车辆或者人员通行（按照时间段进行统计） type==1代表车辆 type==2 代表人员
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-21
     */
    @GetMapping("/count/countPassYesterday")
    public AjaxResult countPassYesterday(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countPassYesterday(queryVo));
    }

    /**
     * 统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-23
     */
    @GetMapping("/count/countPass")
    public AjaxResult countPass(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countPass(queryVo));
    }

    /**
     * 统计通行趋势
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-24
     */
    @PostMapping("/count/trafficTrends")
    public AjaxResult trafficTrends(@RequestBody QueryVo queryVo) {
        return AjaxResult.success(iaCountService.trafficTrends(queryVo));
    }

    /**
     *布控人员统计
     *@return 结果
     *@author duanyifan
     *@date 2020-12-24
     */
    @GetMapping("/count/getBlackPersonnelByCommunity")
    public AjaxResult getBlackPersonnelByCommunity(QueryVo queryVo){
        return AjaxResult.success(iaCountService.getBlackPersonnelByCommunity(queryVo));
    }

    /**
     *社区建筑面积
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
     */
    @GetMapping("/count/countCommunityArea")
    public AjaxResult countCommunityArea(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countCommunityArea(queryVo));
    }

    /**
     *人员通行抓拍
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
     */
    @GetMapping("/count/personnelPassSnap")
    public TableDataInfo personnelPassSnap(QueryVo queryVo){
        startPage();
        return getDataTable(iaCountService.personnelPassSnap(queryVo));
    }

    /**
     *车辆通行抓拍
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
        */
    @GetMapping("/count/vehiclePassSnap")
    public TableDataInfo vehiclePassSnap(QueryVo queryVo){
        startPage();
        return getDataTable(iaCountService.vehiclePassSnap(queryVo));
    }

    /**
     *查询实有信息
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
     */
    @GetMapping("/count/countActualInformation")
    public AjaxResult countActualInformation(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countActualInformation(queryVo));
    }

    /**
     *各种类型的关爱提醒的数量（只统计未处理）
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
     */
    @GetMapping("/count/countCaringEventByType")
    public AjaxResult countCaringEventByType(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countCaringEventByType(queryVo));
    }

    /**
     * 特殊人员统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-28
     */
    @GetMapping("/count/countSpecial")
    public AjaxResult countSpecial(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countSpecial(queryVo));
    }

    /**
     *社区小区户数统计
     *@return 结果
     *@author duanyifan
     *@date 2021-01-05
     */
    @GetMapping("/count/countHouseholds")
    public AjaxResult countHouseholds(QueryVo queryVo){
        return AjaxResult.success(iaCountService.countHouseholds(queryVo));
    }

    /**
     *统计 正在隔离家庭/总共隔离家庭
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/isolationOfFamily")
    public AjaxResult isolationOfFamily(QueryVo queryVo){
        return AjaxResult.success(iaCountService.isolationOfFamily(queryVo));
    }

    /**
     *统计 正在隔离人员/总共隔离人员
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/isolationOfPersonnel")
    public AjaxResult isolationOfPersonnel(QueryVo queryVo){
        return AjaxResult.success(iaCountService.isolationOfPersonnel(queryVo));
    }

    /**
     *统计 隔离家庭核酸检测总次数
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/nucleicAcidTestingNum")
    public AjaxResult nucleicAcidTestingNum(QueryVo queryVo){
        return AjaxResult.success(iaCountService.nucleicAcidTestingNum(queryVo));
    }

    /**
     *统计 隔离原因类型 (根据字典进行统计)
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/isolationReason")
    public AjaxResult isolationReason(QueryVo queryVo){
        return AjaxResult.success(iaCountService.isolationReason(queryVo));
    }

    /**
     *统计 新增隔离家庭/已有隔离家庭  时间曲线（按天统计，时间跨度作为参数）
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/isolationFamilyTimeCurve")
    public AjaxResult isolationFamilyTimeCurve(QueryVo queryVo){
        return AjaxResult.success(iaCountService.isolationFamilyTimeCurve(queryVo));
    }

    /**
     *统计 个人上报 有风险人数/总共人数  时间曲线（按天统计，时间跨度作为参数）
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    @GetMapping("/count/reportInfo")
    public AjaxResult reportInfo(QueryVo queryVo){
        return AjaxResult.success(iaCountService.reportInfo(queryVo));
    }

}


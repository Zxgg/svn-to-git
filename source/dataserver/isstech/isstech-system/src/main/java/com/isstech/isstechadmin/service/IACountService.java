package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import com.isstech.isstechadmin.domain.BasePassVehicle;
import com.isstech.isstechadmin.domain.vo.*;

import javax.management.Query;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *  统计量 Service接口
 *
 * @author yzp
 * @date 2020-11-30
 */
public interface IACountService
{
    /**
     * 首页-外地人员占比
     * @return 结果
     */
    public Map countPerProportion(QueryVo queryVo);

    /**
     * 首页-社区内组织统计
     * @return 结果
     */
    public List<GroupByVo> countOrganization ();

    /**
     * 首页-摄像头数量  是否在线(0否1是,默认1)
     * @return 结果
     */
    public List<GroupByVo> countCaptureOnline ();

    /**
     * 首页 - 关爱人员类型
     * @return 结果
     */
    public List<GroupByVo> countCaringType (QueryVo queryVo);

    /**
     * 首页 - 房屋统计（包括房屋总数和入住率）
     * @return 结果
     */
    public Map<String,String> countHouse (QueryVo queryVo);

    /**
     * 首页-人口流动情况
     * @author qishuwei
     * @param queryVo  （社区id)
     * @return 新增流动人口和租住人口结果
     */
    public Map<String,List<GroupByVo>> countFloat(QueryVo queryVo);
    /**
     * 人员信息 - 特殊人群
     * @return 结果
     */
    public List<GroupByVo> countSpecialPerson (QueryVo queryVo);

    /**
     * 人员信息 - 人员信息统计
     * @return 结果
     */
    public List<PercentVo> countSexPerson (QueryVo queryVo);

    /**
     * 人员信息 - 文化程度统计
     * @return 结果
     */
    public List<Map<String,String>> countDegreePerson (QueryVo queryVo);

    /**
     * 人员信息 - 年龄统计
     * @return 结果
     */
    public Map<String,String> countAgePerson (QueryVo queryVo);

    /**
     * 人员信息 - 民族统计
     * @return 结果
     */
    public List<GroupByVo> countNationPerson (QueryVo queryVo);

    /**
     * 人员信息 - 人员住房信息
     * @return 结果
     */
    public List<GroupByVo> countResidencePerson (QueryVo queryVo);

    /**
     * 人员信息 - 各小区人员数量
     * @return 结果
     */
    public List<GroupByVo> countCommunityPerson (QueryVo queryVo);


    /**
     * 车辆信息- 小区车辆统计
     * @return 结果
     * @author qishuwei
     * @date 2020-12-08
     */
    public List<GroupByVo> countCommunityVehicle(QueryVo queryVo);

    /**
     *车辆信息-管控车辆
     *@return 结果
     *@author qishuwei
     *@date 2020-12-08
     */
    public List<VehicleInformationVo> getLimitedByCommunity(QueryVo queryVo);

    /**
     *车辆信息-车辆类别(根据社区id查询)
     * @author qishuwei
     * @return 结果
     * @date 2020-12-08
     */
    public List<GroupByVo> getVehicleSort(QueryVo queryVo);

    /**
     * 首页-事件数量统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-09
     */
    public List<GroupByVo> countEventName(QueryVo queryVo);

    public List<GroupByVo> eventRing(QueryVo queryVo);

    /**
     * 首页-关爱人员事件统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-11
     */
    public List<EventVo> countCaringEvent(QueryVo queryVo);

    /**
     * 统计房屋的使用类型
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    public List<GroupByVo> countHouseUseNature(QueryVo queryVo);

    /**
     * 统计非正常人员类型（关爱，管控，特殊）
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> countNoNormalClass(QueryVo queryVo);

    /**
     * 统计车辆的使用年限
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> countVehicleAge(QueryVo queryVo);

    /**
     * 统计车辆的能源类型
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> countVehicleEnergy(QueryVo queryVo);

    /**
     * 访客车辆类型统计（客运车 货运车 校车 公交车  快递车 垃圾车）
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> getVisitVehicleType(QueryVo queryVo);

    /**
     * 统计过去一周车辆通行的次数
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> countVehiclePass(QueryVo queryVo);

    /**
     * 统计过去一周人员通行的次数
     * @param queryVo
     * @return 结果
     */
    public List<GroupByVo> countPersonPass(QueryVo queryVo);



    /**
     * 统计一周内的访客(人和车) 按照访客时间进行统计
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-17
     */
    public Map<String,List<GroupByVo>> countVisit(QueryVo queryVo);


    /**
     * 统计一周内的访客（人和车） 按照访客类型统计
     * @param queryVo
     * @return 结果
     */
    public Map<String,List<GroupByVo>> countVisitType(QueryVo queryVo);

    /**
     * 统计前一天的车辆或者人员通行（按照时间段进行统计） type==1代表车辆 type==2 代表人员
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-21
     */
    public List<GroupByVo> countPassYesterday(QueryVo queryVo);

    /**
     * 统计通行  当日居民通行 人/车 当月居民通行 人/车 当日访客数量 人/车 当月访客数量 人/车
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-23
     */
    public Map<String,Integer> countPass(QueryVo queryVo);

    /**
     * 根据开始时间和结束时间统计居民和访客
     * @param queryVo
     * @return 结果集
     * @author qishuwei
     * @date 2020-12-23
     */
    public Map<String,Integer> getPassByTime(QueryVo queryVo);

    /**
     * 统计通行趋势
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-23
     */
    public Map<String,List<GroupByVo>> trafficTrends(QueryVo queryVo);

    /**
     * 布控人员统计
     * @param queryVo
     * @return 结果
     */
    public List<BlackPersonnelVo> getBlackPersonnelByCommunity(QueryVo queryVo);

    /**
     * 社区建筑面积
     * @param queryVo
     * @return 结果
     */
    public Map<String,String> countCommunityArea(QueryVo queryVo);

    /**
     * 人员通行抓拍
     * @param queryVo
     * @return 结果
     */
    public List<Map<String,String>> personnelPassSnap(QueryVo queryVo);

    /**
     * 车辆通行抓拍
     * @param queryVo
     * @return 结果
     */
    public List<Map<String,String>> vehiclePassSnap(QueryVo queryVo);

    /**
     * 查询实有信息
     * @param queryVo
     * @return 结果
     */
    public Map<String,String> countActualInformation(QueryVo queryVo);

    /**
     *各种类型的关爱提醒的数量（只统计未处理）
     *@return 结果
     *@author duanyifan
     *@date 2020-12-25
     */
    public List<Map<String,String>> countCaringEventByType(QueryVo queryVo);

    /**
     * 特殊人员类型统计
     * @param queryVo
     * @return 结果
     */
    public Map<String,String> countSpecial(QueryVo queryVo);

    /**
     *社区小区户数统计
     *@return 结果
     *@author duanyifan
     *@date 2021-01-05
     */
    public Map<String,String> countHouseholds(QueryVo queryVo);

    /**
     *统计 正在隔离家庭/总共隔离家庭
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public Map<String,String> isolationOfFamily(QueryVo queryVo);

    /**
     *统计 正在隔离家庭/总共隔离家庭
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public Map<String,String> isolationOfPersonnel(QueryVo queryVo);

    /**
     *统计 隔离家庭核酸检测总次数
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public Map<String,String> nucleicAcidTestingNum(QueryVo queryVo);

    /**
     *统计 隔离原因类型 (根据字典进行统计)
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public List<GroupByVo> isolationReason(QueryVo queryVo);

    /**
     *统计 新增隔离家庭/已有隔离家庭  时间曲线（按天统计，时间跨度作为参数）
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public Map<String,List<GroupByVo>> isolationFamilyTimeCurve(QueryVo queryVo);

    /**
     *统计 个人上报 有风险人数/总共人数  时间曲线（按天统计，时间跨度作为参数）
     *@return 结果
     *@author duanyifan
     *@date 2021-01-22
     */
    public Map<String,List<GroupByVo>> reportInfo(QueryVo queryVo);
}

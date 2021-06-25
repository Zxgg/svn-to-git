package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseElevatorInformation;

/**
 * 电梯信息Mapper接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface BaseElevatorInformationMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询电梯信息
     * 
     * @param id 电梯信息ID
     * @return 电梯信息
     */
    public BaseElevatorInformation selectBaseElevatorInformationById(String id);

    /**
     * 查询电梯信息列表
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 电梯信息集合
     */
    public List<BaseElevatorInformation> selectBaseElevatorInformationList(BaseElevatorInformation baseElevatorInformation);

    /**
     * 新增电梯信息
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 结果
     */
    public int insertBaseElevatorInformation(BaseElevatorInformation baseElevatorInformation);

    /**
     * 修改电梯信息
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 结果
     */
    public int updateBaseElevatorInformation(BaseElevatorInformation baseElevatorInformation);

    /**
     * 删除电梯信息
     * 
     * @param id 电梯信息ID
     * @return 结果
     */
    public int deleteBaseElevatorInformationById(String id);

    /**
     * 批量删除电梯信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseElevatorInformationByIds(String[] ids);

    /**
     * 批量软删除电梯信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseElevatorInformationByIds(String[] ids);
}

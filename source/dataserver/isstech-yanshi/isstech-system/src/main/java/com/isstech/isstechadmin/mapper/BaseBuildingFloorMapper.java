package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseBuildingFloor;

/**
 * 楼栋楼层 只做记录用，房屋数据直接使用楼层号Mapper接口
 * 
 * @author yzp
 * @date 2020-11-30
 */
public interface BaseBuildingFloorMapper 
{
    /**
     * 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param id 楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    public BaseBuildingFloor selectBaseBuildingFloorById(String id);

    /**
     * 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号列表
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 楼栋楼层 只做记录用，房屋数据直接使用楼层号集合
     */
    public List<BaseBuildingFloor> selectBaseBuildingFloorList(BaseBuildingFloor baseBuildingFloor);

    /**
     * 新增楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 结果
     */
    public int insertBaseBuildingFloor(BaseBuildingFloor baseBuildingFloor);

    /**
     * 修改楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param baseBuildingFloor 楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * @return 结果
     */
    public int updateBaseBuildingFloor(BaseBuildingFloor baseBuildingFloor);

    /**
     * 删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param id 楼栋楼层 只做记录用，房屋数据直接使用楼层号ID
     * @return 结果
     */
    public int deleteBaseBuildingFloorById(String id);

    /**
     * 批量删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseBuildingFloorByIds(String[] ids);

    /**
     * 批量软删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseBuildingFloorByIds(String[] ids);
}

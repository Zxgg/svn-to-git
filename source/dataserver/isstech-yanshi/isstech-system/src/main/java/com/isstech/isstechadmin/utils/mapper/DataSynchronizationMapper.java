package com.isstech.isstechadmin.utils.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataSynchronizationMapper {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    @Select("select COLUMN_NAME from information_schema.COLUMNS where table_schema = (select database()) and table_name = #{tableName} ")
    public List<String> selectDbTableColumnsByName(String tableName);

    /**
     * 根据表名称和数据id获取该数据所属部门的deptId
     *
     *
     * @param tableName 表名称
     * @param id
     * @return 列信息
     */
    @Select("select d.dept_ip as deptId from base_dept_community_child d where d.community_child_id = (select community_child_id from ${tableName} where id = #{id})")
    public String getDeptIdById(@Param("tableName") String tableName, @Param("id")String id);

    /**
     * 获取当前部门名称
     * */
    @Select("select name from sys_dept where parent_id = '0'")
    public String getDeptName();

    /**
     * 获取部门ip
     * */
    @Select("select dept_ip from sys_dept_ip where dept_id = #{id}")
    public String getDeptIp(@Param("id") String id);

    /**
     * 获取部门code
     * */
    @Select("select dept_code from sys_dept_ip where dept_id = #{id}")
    public String getDeptCode(@Param("id") String id);

    /**
     * 获取当前部门id
     * */
    @Select("select id from sys_dept where parent_id = '0'")
    public String getDeptId();
}

package com.isstech.isstechadmin.mapper;

import com.isstech.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 部门管理 数据层
 * @Author yzp
 * @Date 2020/12/21 13:46
 */
public interface SysDeptMapper
{
    /**
     *  获取uuid
     * */
    public String selectUuid();

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 根据角色ID查询部门树信息
     * 
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    public List<String> selectDeptListByRoleId(String roleId);

    /**
     * 根据部门ID查询信息
     * 
     * @param id 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(String id);

    /**
     * 根据ID查询所有子部门
     * 
     * @param id 部门ID
     * @return 部门列表
     */
    public List<SysDept> selectChildrenDeptById(String id);

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param id 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(String id);

    /**
     * 是否存在子节点
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(String id);

    /**
     * 查询部门是否存在用户
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(String id);

    /**
     * 校验部门名称是否唯一
     * 
     * @param name 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("name") String name, @Param("parentId") String parentId);

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改所在部门的父级部门状态
     * 
     * @param dept 部门
     */
    public void updateDeptStatus(SysDept dept);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 删除部门管理信息
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int deleteDeptById(String id);

    /**
     * 软删除部门管理信息
     *
     * @param id 部门ID
     * @return 结果
     */
    public int updateDeptById(String id);

    /**
     * 根据部门名称查询部门对应id
     * @param deptName
     * @return
     */
    public String queryDeptIdByDeptName(String deptName);
}

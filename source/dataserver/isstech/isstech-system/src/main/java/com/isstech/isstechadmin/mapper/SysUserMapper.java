package com.isstech.isstechadmin.mapper;

import java.util.List;

import com.isstech.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户表 数据层
 * @Author yzp
 * @Date 2020/12/21 13:44
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param id 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(String id);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     * 
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteUserById(String id);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);

    /**
     * 批量软删除用户信息
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int updateUserByIds(String[] ids);

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);

    /**
     * 社区id或者小区id 查看系统用户信息
     *
     */
    public List<SysUser> getSysUserData(@Param("communityId")String communityId ,@Param("communityChildId")String communityChildId);
}

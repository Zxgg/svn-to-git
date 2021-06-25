package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.SysNotice;

import java.util.List;

/**
 * @Description 通知公告表 数据层
 * @Author yzp
 * @Date 2020/12/21 13:45
 */
public interface SysNoticeMapper
{
    /**
     * 查询公告信息
     * 
     * @param id 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(String id);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(String noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(String[] noticeIds);

    /**
     * 批量软删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int updateNoticeByIds(String[] noticeIds);
}

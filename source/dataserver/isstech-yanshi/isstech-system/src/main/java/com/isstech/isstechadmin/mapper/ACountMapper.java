package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.domain.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 统计量 Mapper接口
 *
 * @author yzp
 * @date 2020-12-01
 */
public interface ACountMapper
{
    /**
     * 首页-外地人员占比
     * @return 结果
     */
    @Select("<script>"+
            "select count(*) b from base_personnel_information  where floating='1' and del_flag=0 " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>"+
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public String countNonlocal(QueryVo queryVo);

    /**
     * 首页-本地人员占比
     * @return 结果
     */
    @Select("<script>"+
            "select count(*) b from base_personnel_information  where floating='0' and del_flag=0 " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>"+
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public String countLocal(QueryVo queryVo);

    /**
     * 首页-访客量占比
     * @return 结果
     */
    @Select("<script>"+
            "select (select count(1) from base_visitor_personnel_information where del_flag = '0' )+"+
            "(select count(1) from base_temp_visitor_personnel_information where del_flag = '0' )"+
            " from dual"+
            "</script>"
    )
    public String countVisitors(QueryVo queryVo);

    /**
     * 首页-社区内组织统计
     * @return 结果
     */
    @Select("select count(1) num ,sdd.dict_label name from base_social_organization bso ,sys_dict_data sdd \n" +
            "where sdd.dict_type='base_social_organization' and sdd.dict_value = bso.social_organization_type and bso.del_flag=0 and sdd.status=0 \n" +
            "group by bso.social_organization_type \n")
    public List<GroupByVo> countOrganization ();

    /**
     * 首页-摄像头数量  是否在线(0否1是,默认1)
     * @return 结果
     */
    @Select("select count(1) num , case when bdc.online='0' then '未在线' else '在线' end as name from base_device_capture bdc where del_flag=0 group by  bdc.online")
    public List<GroupByVo> countCaptureOnline ();

    /**
     * 首页 - 关爱人员类型
     * @return 结果
     */
    @Select("<script>"+
            "select count(1) num, sdd.dict_label name  from base_caring_person bcp,sys_dict_data sdd \n" +
            "where sdd.dict_type='base_type_of_caring' and sdd.dict_value = bcp.type  and bcp.del_flag='0' and sdd.status='0' \n" +
            "<when test='communityId!=null'> and bcp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bcp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY bcp.type \n" +
            "</script>"
    )
    public List<GroupByVo> countCaringType (BaseCaringPerson caringPerson);

    /**
     * 首页 - 房屋总数
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    @Select("<script>"+
            "select count(1) from base_community_houses where del_flag=0 "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public int countHouseNum(BaseCommunityHouses houses);


    /**
     * 统计入住的房屋总数
     * @param houses
     * @return 结果
     * @author qishuwei
     * @date 2020-12-14
     */
    @Select("<script>"+
            "select count(DISTINCT bchp.community_houses_id) from base_community_houses as bch,base_community_houses_personnel as bchp " +
            "where bch.id=bchp.community_houses_id and  bch.del_flag=0 and bchp.del_flag=0" +
            "<when test='communityId!=null'> and bch.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bch.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bch.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bch.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public int countHouseLiveNum(BaseCommunityHouses houses);

    /**
     * 人员信息 - 特殊人群
     * @return 结果
     */
    @Select("<script>"+
            "select count(1) num ,sdd.dict_label name from  base_personnel_information bpi ,base_person_special_info bpsi ,base_community_houses_personnel bchp,sys_dict_data sdd \n" +
            "where bpsi.personnel_id = bpi.id and bchp.personnel_id = bpi.id  and bpsi.type = sdd.dict_value \n" +
            "and  bpi.special != '0' \n" +
            "and sdd.dict_type='base_special_person' and bpi.del_flag=0 and bpsi.del_flag=0 and bchp.del_flag='0' and sdd.`status`=0 \n" +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "group by bpsi.type \n" +
            "</script>"
    )
    public List<GroupByVo> countSpecialPerson (BaseCommunityHousesPersonnel personnel);

    /**
     * 人员信息 - 人员信息统计
     * @return 结果
     */
    @Select("<script>"+
            "SELECT bpi.sex as `name`,count(1) as num FROM base_personnel_information bpi,base_community_houses_personnel bchp \n" +
            "where bchp.personnel_id = bpi.id and bpi.del_flag='0' and bchp.del_flag='0' \n" +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY bpi.sex" +
            "</script>"
    )
    public List<GroupByVo> countSexPerson (BasePersonnelInformation personnelInformation);


    /**
     * 人员信息 - 文化程度统计
     * @return 结果
     */
    @Select("<script>"+
            "select count(bpi.id) num ,sdd.dict_label degree_name " +
            "from sys_dict_data sdd " +
            " LEFT JOIN base_personnel_information bpi on "+
            " bpi.degree = sdd.dict_value and bpi.del_flag='0' "+
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " where sdd.dict_type='base_degree' and sdd.status='0' "  +
            " GROUP BY degree_name "+
            "</script>"
    )
    public List<Map<String,String>> countDegreePerson (BasePersonnelInformation personnelInformation);

    /**
     * 人员信息 - 年龄统计
     * @return 结果
     */
    @Select("<script>"+
            "SELECT age19.a19 / s.num as y_19, age29.a29 / s.num as y_29,age39.a39 / s.num as y_39, age49.a49 / s.num as y_49,\n" +
            " ageGt50.a50/s.num as y_50 " +
            " FROM \n" +
            " (SELECT   count(bpi.id) a19\n" +
            "  FROM base_personnel_information bpi\n" +
            "  where  bpi.del_flag='0' and TIMESTAMPDIFF(YEAR,bpi.brith_date,CURDATE()) between '0'and '20' " +
            "   <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "   <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "   ) as age19,\n" +
            "  (SELECT   count(bpi.id) a29\n" +
            "   FROM base_personnel_information bpi\n" +
            "   where  bpi.del_flag='0' and TIMESTAMPDIFF(YEAR,bpi.brith_date,CURDATE()) between '20'and '30' \n" +
            "             <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "            <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "            ) as age29,\n" +
            "            (SELECT   count(bpi.id) a39\n" +
            "            FROM base_personnel_information bpi\n" +
            "            where  bpi.del_flag='0' and TIMESTAMPDIFF(YEAR,bpi.brith_date,CURDATE()) between '31'and '40' \n" +
            "             <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "            <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "            ) as age39,\n" +
            "            (SELECT   count(bpi.id) a49\n" +
            "            FROM base_personnel_information bpi\n" +
            "            where  bpi.del_flag='0' and TIMESTAMPDIFF(YEAR,bpi.brith_date,CURDATE()) between '40'and '50' \n" +
            "             <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "            <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "            ) as age49,\n" +
            "            (SELECT   count(bpi.id) a50\n" +
            "            FROM base_personnel_information bpi\n" +
            "            where  bpi.del_flag='0' and TIMESTAMPDIFF(YEAR,bpi.brith_date,CURDATE()) > 50 \n" +
            "             <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "            <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "            ) as ageGt50,\n" +
            "            (SELECT  count(id) num \n" +
            "            FROM base_personnel_information bpi \n" +
            "            where  bpi.del_flag='0' " +
            "             <when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "            <when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "                ) as s " +

            "</script>"
    )
    public Map<String,String> countAgePerson (BasePersonnelInformation information);

    /**
     * 人员信息 - 民族统计
     * @return 结果
     */
    @Select("<script>"+
            "select count(1) num ,sdd.dict_label name\n" +
            "FROM base_personnel_information bpi,sys_dict_data sdd,base_community_houses_personnel bchp\n" +
            "where bchp.personnel_id = bpi.id and sdd.dict_value = bpi.nation and sdd.dict_type='base_nation' and bpi.del_flag='0' and sdd.status='0' and bchp.del_flag='0' \n" +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY bpi.nation\n" +
            "</script>"
    )
    public List<GroupByVo> countNationPerson (BasePersonnelInformation information);

    /**
     * 人员信息 - 人员住房信息
     * @return 结果
     */
    @Select("<script>"+
            "SELECT count(1) num  ,sdd.dict_label name\n" +
            "FROM base_personnel_information bpi ,base_community_houses_personnel bchp ,sys_dict_data sdd\n" +
            "where bchp.personnel_id=bpi.id and bchp.type_of_residence=sdd.dict_value\n" +
            "and sdd.dict_type='base_type_of_residence' and bpi.del_flag='0' and bchp.del_flag='0' and sdd.status='0' " +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY bchp.type_of_residence\n" +
            "</script>"
    )
    public List<GroupByVo> countResidencePerson (BasePersonnelInformation information);

    /**
     * 人员信息 - 各小区人员数量
     * @return 结果
     */
    @Select("<script>"+
            "SELECT  communityName name , count(communityName) as num\n" +
            "FROM  ( SELECT bci.name as communityName\n" +
            "FROM base_personnel_information bpi ,base_community_houses_personnel bchp ,base_community_child bci\n" +
            "where bchp.personnel_id=bpi.id and bchp.community_child_id=bci.id and bpi.del_flag='0' and bchp.del_flag='0' and bci.del_flag='0' " +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as a  GROUP BY communityName\n" +
            "</script>"
    )
    public List<GroupByVo> countCommunityPerson (BasePersonnelInformation information);

    /**
     * 首页 - 人口流动情况 - 新增流动人口
     * @author qishuwei
     * @return 结果
     * @date 2020-12-04
     */
    @Select("<script>"+
            "select count(1) as num,start_date as `name` from (\n" +
            "select hp.id,left(hp.start_time,7) as start_date from \n" +
            "base_community_houses_personnel hp \n" +
            "join base_personnel_information p \n" +
            "on hp.personnel_id=p.id and hp.type_of_residence !='01' and hp.del_flag=0 and p.del_flag=0 \n" +
            "and hp.start_time &gt;= #{startTime} \n" +
            "<when test='communityId!=null'> and hp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and hp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and hp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and hp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") b  GROUP BY b.start_date" +
            "</script>"
    )
    public List<GroupByVo> countIncreaseFloating(BaseCommunityHousesPersonnel housesPersonnel);

    /**
     * 首页 - 人口流动情况 - 租住人口 - 统计六个月之前所有的租住人口
     * @author qishuwei
     * @return 结果
     * @date 2020-12-07
     */
    @Select("<script>"+
            "select count(1) from \n" +
            "base_community_houses_personnel hp  \n" +
            "join base_personnel_information p on hp.personnel_id=p.id and hp.type_of_residence !='01' and hp.del_flag=0 and p.del_flag=0 \n" +
            "where hp.start_time &lt; #{startTime}" +
            "<when test='communityId!=null'> and hp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and hp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and hp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and hp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public int countSix(BaseCommunityHousesPersonnel housesPersonnel);

    /**
     * 车辆信息 - 小区车辆统计
     * @author qishuwei
     * @return 结果
     * @date 2020-12-08
     */
    @Select("<script>"+
            "SELECT  communityName as name,count(communityName) as num FROM  \n" +
            "( SELECT bci.name as communityName  FROM base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp ,base_community_child bci  \n" +
            "where bvchp.vehicle_id=bvi.id and bvchp.community_child_id=bci.id and bvi.del_flag='0' \n" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "and bvchp.del_flag='0' and bci.del_flag='0'  ) as a  \n" +
            "GROUP BY communityName" +
            "</script>"
    )
    public List<GroupByVo> countCommunityVehicle(BaseVehicleInformation information);

    /**
     * 车辆信息-管控车辆  (根据社区id查看管控车辆)  暂时还没有违法抓拍的图片
     * @author qishuwei
     * @return 结果
     * @date 2020-12-08
     */
    @Select("<script>"+
            "select a.*,b.storage_url2 as limitPic from (\n" +
            "select bvi.plate,bpi.name as ownerName,bpi.identification_number as ownerIDNO \n" +
            "from  base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp,\n" +
            "base_personnel_information bpi \n" +
            "where bvchp.vehicle_id=bvi.id and bvi.limited='1' and bvchp.personnel_id=bpi.id \n" +
            "and bvi.del_flag='0' and bvchp.del_flag='0' \n" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") a,\n" +
            "(select Max(pass_time),plate_no,storage_url2 from base_pass_vehicle \n" +
            "where del_flag='0' GROUP BY plate_no\n" +
            ") b where a.plate=b.plate_no "+
            "</script>"
    )
    public List<VehicleInformationVo> getLimitedByCommunity(BaseVehicleInformation information);

    /**
     *车辆信息-车辆类别(根据社区id查询)
     * @author qishuwei
     * @return 结果
     * @date 2020-12-08
     */
    @Select("<script>"+
            "SELECT \"本地车\" as name,count(bvi.id) num FROM base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp \n" +
            "where bvchp.vehicle_id=bvi.id and bvi.del_flag='0' and bvchp.del_flag='0' and  bvi.plate like '苏K%' \n" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "UNION\n" +
            "SELECT \"外地车\" as name,count(bvi.id) num FROM base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp \n" +
            "where bvchp.vehicle_id=bvi.id and bvi.del_flag='0' and bvchp.del_flag='0' and bvi.plate not like '苏K%'" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<GroupByVo> getVehicleSort(BaseVehicleInformation information);


    /**
     * 首页-事件数量统计（一年）
     * @param event
     * @return 结果
     * @author qishuwei
     * @date 2020-12-09
     */
    @Select("<script>"+
            "select name,count(1) as num from t_community_event \n" +
            "where del_flag=0 and create_time>=#{createTime} \n" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY `name`"+
            "</script>"
    )
    public List<GroupByVo> countEventName(TCommunityEvent event);

    /**
     * 统计每个月事件的数量（一年）
     * @param event
     * @return 结果
     * @author qishuwei
     * @date 2020-12-09
     */
    @Select("<script>"+
            "select a.name,count(1) as num from (\n" +
            "select left(create_time,7) as `name` from t_community_event \n" +
            "where del_flag=0 and create_time>=#{createTime} \n" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") a GROUP BY a.name order by a.name " +
            "</script>"
    )
    public List<GroupByVo> countEachMonthEvent(TCommunityEvent event);

    /**
     * 统计关爱事件类型
     * @param event
     * @return 结果
     * @author qishuwei
     * @date 2020-12-11
     */
    @Select("<script>"+
            "select name,count(1) as num  from t_caring_event where del_flag=0 \n" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "and create_time>=#{createTime} \n" +
            "GROUP BY name "+
            "</script>"
    )
    public List<GroupByVo> countCaringEvent(TCaringEvent event);

    /**
     * 统计房屋的使用类型
     * @param houses
     * @return 结果
     */
    @Select("<script>"+
            "select count(1) as num,nature_of_use as name from base_community_houses " +
            "where del_flag=0 " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY nature_of_use "+
            "</script>"
    )
    public List<GroupByVo> countHouseUseNature(BaseCommunityHouses houses);


    /**
     * 查询符合社区或者小区的人员
     * @param houses
     * @return 结果
     */
    @Select("<script>"+
            "SELECT bpi.*\n" +
            "FROM base_personnel_information bpi ,base_community_houses_personnel bchp \n" +
            "where bchp.personnel_id=bpi.id and bpi.del_flag='0' and bchp.del_flag='0'" +
            "<when test='communityId!=null'> and bchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<BasePersonnelInformation> getAllPerson(BaseCommunityHouses houses);

    /**
     * 查询符合社区或者小区的车辆
     * @param information
     * @return 结果
     */
    @Select("<script>"+
            "SELECT bvi.id,bvi.energy_type as energyType,bvi.buy_time as buyTime FROM base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp \n" +
            "where bvchp.vehicle_id=bvi.id and bvi.del_flag='0' and bvchp.del_flag='0' \n" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<BaseVehicleInformation> getAllVehicle(BaseVehicleInformation information);


    @Select("<script>"+
            "SELECT bvi.energy_type as name ,count(1) as num FROM base_vehicle_information bvi,base_vehicle_community_houses_personnel bvchp \n" +
            "where bvchp.vehicle_id=bvi.id and bvi.del_flag='0' and bvchp.del_flag='0' \n" +
            "<when test='communityId!=null'> and bvchp.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bvchp.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bvchp.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bvchp.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY bvi.energy_type"  +
            "</script>"
    )
    public List<GroupByVo> countVehicleEnergy(BaseVehicleInformation information);

    /**
     * 访客车辆类型统计（客运车 货运车 校车 公交车  快递车 垃圾车）
     * @param information
     * @return 结果
     */
    @Select("<script>"+
            "select vehicle_type as name,count(1) as num from base_visitor_vehicle_information \n" +
            "where del_flag='0' \n" +
            " and not (visit_start_time &gt;= #{visitEndTime} or visit_end_time &lt;= #{visitStartTime}) \n" +
            "<when test='targetPersonCommunityId!=null'> and target_person_community_id=#{targetPersonCommunityId} </when>" +
            "<when test='targetPersonCommunityChildId!=null'> and target_person_community_child_id=#{targetPersonCommunityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='targetPersonCommunityId == null  or targetPersonCommunityId == \"\"'>"+
            "and target_person_community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='targetPersonCommunityChildId == null  or targetPersonCommunityChildId == \"\"'>"+
            "and target_person_community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " GROUP BY vehicle_type" +
            "</script>"
    )
    public List<GroupByVo> getVisitVehicleType(BaseVisitorVehicleInformation information);

    /**
     * 根据开始时间和结束时间统计车辆出入的次数(所有) 按照通行时间(yyyy-MM-dd)进行分组
     * @param vehicle
     * @return
     */
    @Select("<script>"+
            "select a.pass_time as name,count(1) as num \n" +
            "from (\n " +
            "select date_format(pass_time,\"%Y-%m-%d\") as pass_time from base_pass_vehicle where del_flag='0' " +
            "<when test='startPassTime!=null'> and pass_time &gt;= #{startPassTime} </when>" +
            "<when test='endPassTime!=null'> and pass_time &lt;= #{endPassTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a "+
            "GROUP BY a.pass_time" +
            "</script>"
    )
    public List<GroupByVo> countVehiclePass(BasePassVehicle vehicle);


    /**
     * 根据开始时间和结束时间统计人员出入的次数（所有） 按照通行时间(yyyy-MM-dd)进行分组
     * @param personnel
     * @return 结果
     */
    @Select("<script>"+
            "select a.mark_time as name,count(1) as num \n" +
            "from (\n " +
            " select date_format(location_mark_time,\"%Y-%m-%d\") as mark_time from base_pass_personnel where del_flag='0' " +
            "<when test='startMarkTime!=null'> and location_mark_time &gt;= #{startMarkTime} </when>" +
            "<when test='endMarkTime!=null'> and location_mark_time &lt;= #{endMarkTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a "+
            "GROUP BY a.mark_time " +
            "</script>"
    )
    public List<GroupByVo> countPersonnelPass(PassPersonnelVo personnel);


    /**
     * 根据开始时间和结束时间统计访客车辆出入的次数（预约访客和临时访客） 按照通行时间(yyyy-MM-dd)进行分组
     * @param vehicle
     * @return
     */
    @Select("<script>"+
            "select a.pass_time as name,count(1) as num \n" +
            "from (\n " +
            "select date_format(pass_time,\"%Y-%m-%d\") as pass_time from base_pass_vehicle " +
            "where del_flag='0' and source_table_name in ('base_temp_visitor_vehicle_information','base_visitor_vehicle_information') "+
            "<when test='startPassTime!=null'> and pass_time &gt;= #{startPassTime} </when>" +
            "<when test='endPassTime!=null'> and pass_time &lt;= #{endPassTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a "+
            "GROUP BY a.pass_time" +
            "</script>"
    )
    public List<GroupByVo> countVisitVehiclePass(BasePassVehicle vehicle);


    /**
     * 根据开始时间和结束时间统计访客人员出入的次数（预约访客和临时访客）按照通行时间(yyyy-MM-dd)进行分组
     * @param personnel
     * @return
     */
    @Select("<script>"+
            "select a.mark_time as name,count(1) as num \n" +
            "from (\n " +
            " select date_format(location_mark_time,\"%Y-%m-%d\") as mark_time from base_pass_personnel where del_flag='0' " +
            "and source_table_name in ('base_temp_visitor_personnel_information','base_visitor_personnel_information') " +
            "<when test='startMarkTime!=null'> and location_mark_time &gt;= #{startMarkTime} </when>" +
            "<when test='endMarkTime!=null'> and location_mark_time &lt;= #{endMarkTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a "+
            "GROUP BY a.mark_time " +
            "</script>"
    )
    public List<GroupByVo> countVisitPersonnelPass(PassPersonnelVo personnel);

    /**
     * 根据开始时间和结束时间统计车辆通行数据 按照source_table_name进行统计
     */
    @Select("<script>"+
            "select source_table_name as name,count(1) as num  from base_pass_vehicle where del_flag='0' " +
            "<when test='startPassTime!=null'> and pass_time &gt;= #{startPassTime} </when>" +
            "<when test='endPassTime!=null'> and pass_time &lt;= #{endPassTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY source_table_name" +
            "</script>"
    )
    public List<GroupByVo> countVehicleVisitType(PassVehicleVo vo);


    /**
     * 根据开始时间和结束时间统计人员通行数据  按照source_table_name进行统计
     * @param personnel
     * @return 结果
     */
    @Select("<script>"+
            "select source_table_name as name,count(1) as num  from base_pass_personnel where del_flag='0' " +
            "<when test='startMarkTime!=null'> and location_mark_time &gt;= #{startMarkTime} </when>" +
            "<when test='endMarkTime!=null'> and location_mark_time &lt;= #{endMarkTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "GROUP BY source_table_name" +
            "</script>"
    )
    public List<GroupByVo> countPersonVisitType(PassPersonnelVo personnel);


    /**
     * 根据开始时间和结束时间进行查询车辆通行记录(所有车辆)
     * @param vo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-21
     */
    @Select("<script>"+
            "select id,pass_time as passTime,source_table_name as sourceTableName from base_pass_vehicle where del_flag='0' " +
            "<when test='startPassTime!=null'> and appear_time &gt;= #{startPassTime} </when>" +
            "<when test='endPassTime!=null'> and appear_time &lt;= #{endPassTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<BasePassVehicle> getPassVehicleList(PassVehicleVo vo);


    /**
     * 根据开始时间和结束时间统计人员出入的次数（所有人员）
     * @param vo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-21
     */
    @Select("<script>"+
            "select id,location_mark_time,source_table_name as sourceTableName from base_pass_personnel where del_flag='0' " +
            "<when test='startMarkTime!=null'> and face_appear_time &gt;= #{startMarkTime} </when>" +
            "<when test='endMarkTime!=null'> and face_appear_time &lt;= #{endMarkTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<BasePassPersonnel> getPassPersonnelList(PassPersonnelVo vo);

    /**
     * 布控人员统计
     * @param vo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-24
     */
    @Select("<script>"+
            "select img_url as imageUrl,name,identification_number as CardID from base_black_personnel where del_flag='0' " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<BlackPersonnelVo> getBlackPersonnelByCommunity(BaseBlackPersonnel vo);

    /**
     * 社区建筑面积
     * @param vo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select SUM(h.building_area) as community_area from base_community_houses h where del_flag='0' " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public Map<String, String> countCommunityArea(BaseCommunityHouses vo);

    /**
     * 人员通行抓拍
     * @param vo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select p.pics as imageUrl,p.location_mark_addr as address,case " +
            "WHEN p.source_table_name = 'base_personnel_information' then '1' "+
            "WHEN p.source_table_name = 'base_visitor_personnel_information' then '2' "+
            "WHEN p.source_table_name = 'base_temp_visitor_personnel_information' then '3' end as visitorType,"+
            "p.face_appear_time as einlass ,name ,community_id as communityId,community_child_id as communityChildId from base_pass_personnel p where del_flag='0' and TO_DAYS(p.face_appear_time) = TO_DAYS(NOW())"+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<Map<String,String>> personnelPassSnap(BasePassPersonnel vo);

    /**
     * 车辆通行抓拍
     * @param vo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select v.plate_no as vehiclePlate,v.name_of_passed_road as channelName,v.storage_url2 as imageUrl,v.appear_time as einlass  " +
            "from base_pass_vehicle v where del_flag='0' and TO_DAYS(v.appear_time) = TO_DAYS(NOW())"+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "</script>"
    )
    public List<Map<String,String>> vehiclePassSnap(BasePassVehicle vo);

    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd)进行统计  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-24
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "SELECT\n" +
            " date_add( date_sub(#{startDate},interval 1 day), INTERVAL ( cast( help_topic_id AS signed) + 1 ) day ) date \n" +
            "FROM\n" +
            " mysql.help_topic \n" +
            "WHERE\n" +
            " help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            " help_topic_id" +
            ") cc\n" +
            "left join \n" +
            "(select a.pass_time as name,count(1) as num from \n" +
            "(select date_format(appear_time,'%Y-%m-%d') as pass_time from base_pass_vehicle \n" +
            " where del_flag='0' and source_table_name in ('base_temp_visitor_vehicle_information','base_visitor_vehicle_information')\n" +
            "<when test='startTime!=null'> and appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) a \n" +
            "GROUP BY a.pass_time\n" +
            ") d on cc.date=d.name order by cc.date" +
            "</script>"
    )
    public List<GroupByVo> countPassVisitVehicleDate(QueryVo queryVo);


    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-24
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "SELECT\n" +
            " date_add( date_sub(#{startDate},interval 1 day), INTERVAL ( cast( help_topic_id AS signed) + 1 ) day ) date \n" +
            "FROM\n" +
            " mysql.help_topic \n" +
            "WHERE\n" +
            " help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            " help_topic_id" +
            ") cc\n" +
            "left join \n" +
            "(select a.pass_time as name,count(1) as num from \n" +
            "(select date_format(appear_time,'%Y-%m-%d') as pass_time from base_pass_vehicle \n" +
            " where del_flag='0' and source_table_name='base_vehicle_information' \n" +
            "<when test='startTime!=null'> and appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) a \n" +
            "GROUP BY a.pass_time\n" +
            ") d on cc.date=d.name order by cc.date" +
            "</script>"
    )
    public List<GroupByVo> countPassVehicleDate(QueryVo queryVo);


    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-24
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "SELECT\n" +
            " date_add( date_sub(#{startDate},interval 1 day), INTERVAL ( cast( help_topic_id AS signed) + 1 ) day ) date \n" +
            "FROM\n" +
            " mysql.help_topic \n" +
            "WHERE\n" +
            " help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            " help_topic_id" +
            ") cc\n" +
            "left join \n" +
            "(\n" +
            " select a.mark_time as name,count(1) as num \n" +
            " from (\n" +
            " select date_format(face_appear_time,'%Y-%m-%d') as mark_time from base_pass_personnel where del_flag='0' \n" +
            " and source_table_name in ('base_temp_visitor_personnel_information','base_visitor_personnel_information') \n" +
            "<when test='startTime!=null'> and face_appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and face_appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) a\n" +
            " GROUP BY a.mark_time \n" +
            ") d on cc.date=d.name order by cc.date" +
            "</script>"
    )
    public List<GroupByVo> countPassVisitPersonDate(QueryVo queryVo);



    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-24
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "SELECT\n" +
            " date_add( date_sub(#{startDate},interval 1 day), INTERVAL ( cast( help_topic_id AS signed) + 1 ) day ) date \n" +
            "FROM\n" +
            " mysql.help_topic \n" +
            "WHERE\n" +
            " help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            " help_topic_id" +
            ") cc\n" +
            "left join \n" +
            "(\n" +
            " select a.mark_time as name,count(1) as num \n" +
            " from (\n" +
            " select date_format(face_appear_time,'%Y-%m-%d') as mark_time from base_pass_personnel where del_flag='0' \n" +
            " and source_table_name='base_personnel_information' \n" +
            "<when test='startTime!=null'> and face_appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and face_appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) a\n" +
            " GROUP BY a.mark_time \n" +
            ") d on cc.date=d.name order by cc.date" +
            "</script>"
    )
    public List<GroupByVo> countPassPersonDate(QueryVo queryVo);


    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd HH:mm:ss)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "select c.date as date from ( \n" +
            "SELECT\n" +
            "date_add( date_sub(#{startTime},interval 1 HOUR), INTERVAL ( cast( help_topic_id AS signed) + 1 ) HOUR ) date \n" +
            "FROM\n" +
            "mysql.help_topic \n" +
            "WHERE\n" +
            "help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            "help_topic_id\n" +
            ") c\n" +
            ") cc \n" +
            "left join \n" +
            " (\n" +
            "select a.mark_time as name,count(1) as num \n" +
            " from (\n" +
            " select date_format(face_appear_time,'%Y-%m-%d %H') as mark_time from base_pass_personnel where del_flag='0' \n" +
            " and source_table_name in ('base_temp_visitor_personnel_information','base_visitor_personnel_information') \n" +
            "<when test='startTime!=null'> and face_appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and face_appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            " ) a\n" +
            "  GROUP BY a.mark_time \n" +
            ") d on cc.date like concat('%',d.name,'%')" +
            "</script>"
    )
    public List<GroupByVo> countPassVisitPersonDateTime(QueryVo queryVo);

    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd HH:mm:ss)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "select c.date as date from ( \n" +
            "SELECT\n" +
            "date_add( date_sub(#{startTime},interval 1 HOUR), INTERVAL ( cast( help_topic_id AS signed) + 1 ) HOUR ) date \n" +
            "FROM\n" +
            "mysql.help_topic \n" +
            "WHERE\n" +
            "help_topic_id &lt; #{limitNum}\n" +
            "ORDER BY\n" +
            "help_topic_id\n" +
            ") c\n" +
            ") cc \n" +
            "left join \n" +
            " (\n" +
            "select a.mark_time as name,count(1) as num \n" +
            " from (\n" +
            " select date_format(face_appear_time,'%Y-%m-%d %H') as mark_time from base_pass_personnel where del_flag='0' \n" +
            " and source_table_name='base_personnel_information' \n" +
            "<when test='startTime!=null'> and face_appear_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and face_appear_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            " ) a\n" +
            "  GROUP BY a.mark_time \n" +
            ") d on cc.date like concat('%',d.name,'%')" +
            "</script>"
    )
    public List<GroupByVo> countPassPersonDateTime(QueryVo queryVo);

    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd HH:mm:ss)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "select c.date as date from (\n" +
            "SELECT date_add(date_sub(#{startTime},interval 1 HOUR), INTERVAL ( cast( help_topic_id AS signed) + 1 ) HOUR ) date \n" +
            "FROM mysql.help_topic WHERE help_topic_id &lt; #{limitNum} ORDER BY help_topic_id) c) cc \n" +
            "left join (select a.pass_time as name,count(1) as num from \n" +
            "(select date_format(pass_time,'%Y-%m-%d %H') as pass_time from base_pass_vehicle \n" +
            "where del_flag='0' and source_table_name in ('base_temp_visitor_vehicle_information','base_visitor_vehicle_information')" +
            "<when test='startTime!=null'> and pass_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and pass_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a \n" +
            "GROUP BY a.pass_time ) d on cc.date like concat('%',d.name,'%')" +
            "</script>"
    )
    public List<GroupByVo> countPassVisitVehicleDateTime(QueryVo queryVo);


    /**
     * 根据开始时间和结束时间以及limitNum的值 进行按照日期(yyyy-MM-dd HH:mm:ss)进行同期  如果某个日期没有数据 补充0
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select cc.date as name ,IFNULL(d.num,0) as num  from (\n" +
            "select c.date as date from (\n" +
            "SELECT date_add(date_sub(#{startTime},interval 1 HOUR), INTERVAL ( cast( help_topic_id AS signed) + 1 ) HOUR ) date \n" +
            "FROM mysql.help_topic WHERE help_topic_id &lt; #{limitNum} ORDER BY help_topic_id) c) cc \n" +
            "left join (select a.pass_time as name,count(1) as num from \n" +
            "(select date_format(pass_time,'%Y-%m-%d %H') as pass_time from base_pass_vehicle \n" +
            "where del_flag='0' and source_table_name='base_vehicle_information'" +
            "<when test='startTime!=null'> and pass_time &gt;= #{startTime} </when>" +
            "<when test='endTime!=null'> and pass_time &lt;= #{endTime} </when>" +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            ") a \n" +
            "GROUP BY a.pass_time ) d on cc.date like concat('%',d.name,'%')" +
            "</script>"
    )
    public List<GroupByVo> countPassVehicleDateTime(QueryVo queryVo);

    /**
     * 查询实有信息
     * @param queryVo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select " +
            "(select count(1) from base_community_houses where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) as buildingCount," +
            "(select count(1) from base_personnel_information where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when> " +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as personnelCount," +
            "(select count(1) from base_vehicle_information where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when> " +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as vehicleCount," +
            "(select count(1) from base_caring_person where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " ) as caringCount," +
            "(select count(1) from base_community_organization where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when> " +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as organizationCount," +
            "((select count(1) from t_caring_event where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when> " +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") +" +
            "(select count(1) from t_community_event where del_flag = '0' "+
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when> " +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            "))" +
            " as eventCount from dual" +
            "</script>"
    )
    public Map<String, String> countActualInformation(QueryVo queryVo);

    /**
     * 各种类型的关爱提醒的数量（只统计未处理）
     * @param queryVo
     * @return 结果
     * @author duanyifan
     * @date 2020-12-25
     */
    @Select("<script>"+
            "select type,count(1) as num from t_caring_event where del_flag='0' and processed = '0' " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            " GROUP BY type"+
            "</script>"
    )
    public List<Map<String, String>> countCaringEventByType(QueryVo queryVo);


    /**
     * 统计特殊人员类型
     * @param queryVo
     * @return 结果
     * @author qishuwei
     * @date 2020-12-28
     */
    @Select("<script>"+
            "select \n" +
            "(select count(1) from base_personnel_information bpi \n" +
            " join t_special_released_people tsrp on bpi.id=tsrp.personnel_id and bpi.del_flag='0' where bpi.del_flag='0' and bpi.special!='0' " +
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as '01',\n" +
            "(select count(1) from base_personnel_information bpi \n" +
            " join t_specia_correct tsc on bpi.id=tsc.personnel_id and tsc.del_flag='0' where bpi.del_flag='0' and bpi.special!='0'" +
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ")  as '02',\n" +
            "(select count(1) from base_personnel_information bpi \n" +
            "join t_specia_mental tsm on bpi.id=tsm.personnel_id and bpi.del_flag='0' where bpi.del_flag='0' and bpi.special!='0' " +
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as '03',\n" +
            "(select count(1) from base_personnel_information bpi \n" +
            "join t_special_drug_addicts tsda on bpi.id=tsda.personnel_id and tsda.del_flag='0' where bpi.del_flag='0' and bpi.special!='0' " +
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as '04', \n" +
            "(select count(1) from base_personnel_information bpi \n" +
            "join t_specia_aids tsa on bpi.id=tsa.personnel_id and tsa.del_flag='0' where bpi.del_flag='0' and bpi.special!='0' " +
            "<when test='communityId!=null'> and bpi.community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and bpi.community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and bpi.community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and bpi.community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ") as '05' \n" +
            "from dual;" +
            "</script>"
    )
    public Map<String,String> countSpecial(QueryVo queryVo);


    @Select("<script>"+
            "select count(1) as num,person_id as `name` from (\n" +
            "select person_id from base_pass_personnel \n" +
            "where person_id in " +
            "<foreach collection='personIds' item='id' index='index' open='(' close=')' separator=','>"+
            "#{id}"+
            "</foreach>"+
            " AND face_appear_time &gt;= #{startTime} and face_appear_time &lt;= #{endTime}\n" +
            ")a   GROUP BY a.person_id" +
            "</script>"
    )
    public List<GroupByVo> countByPersonIdListAndTime(QueryVo queryVo);


    @Select("<script>"+
            "select t.person_id from ("+
            "select a.num/b.num as rate,a.person_id  from \n" +
            "(\n" +
            "select count(1) as num,person_id from base_pass_personnel \n" +
            "where location_mark_time &gt;= #{startTime} and location_mark_time &lt;= #{endTime}\n" +
            "GROUP BY person_id \n" +
            " ) as a,\n" +
            "(\n" +
            "select count(1) as num,person_id from base_pass_personnel\n" +
            "where location_mark_time &gt;= #{startTime} and location_mark_time &lt;= #{endTime}\n" +
            "GROUP BY person_id \n" +
            ") as b \n" +
            "where a.person_id=b.person_id HAVING rate &gt; #{rate}" +
            ") as t" +
            "</script>"
    )
    public List<String> countDayHideNightOUT(QueryVo queryVo);

    /**
     *社区小区户数统计
     *@param queryVo
     *@return 结果
     *@author duanyifan
     *@date 2021-01-05
     */
    @Select("<script>"+
            "select "+
            "(select count(1) from base_community_child where del_flag='0' " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ")  as communityChildCount," +
            "(select count(1) from base_community_houses where del_flag='0' " +
            "<when test='communityId!=null'> and community_id=#{communityId} </when>" +
            "<when test='communityChildId!=null'> and community_child_id=#{communityChildId} </when>" +
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'>"+
            "<if test='communityId == null  or communityId == \"\"'>"+
            "and community_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityId}"+
            "</foreach>"+
            "</if>"+
            "<if test='communityChildId == null  or communityChildId == \"\"'>"+
            "and community_child_id in"+
            "<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>"+
            "#{dataAccess.communityChildId}"+
            "</foreach>"+
            "</if>"+
            "</if>"+
            ")  as householdsCount " +
            " from dual"+
            "</script>"
    )
    public Map<String, String> countHouseholds(QueryVo queryVo);

    @Select("<script>"+
            "select" +
            "(select count(1) from t_epidemic_isolation_family where del_flag = '0' " +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as allIsolationFamilyNum," +
            "(select count(1) from t_epidemic_isolation_family where del_flag = '0' and isolation_end_time > NOW() " +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as isolationFamilyNum " +
            "from dual"+
            "</script>"
    )
    public Map<String, String> isolationOfFamily(QueryVo queryVo);

    @Select("<script>"+
            "select" +
            "(select sum((CHAR_LENGTH(personInfo)-CHAR_LENGTH(REPLACE(personInfo,'\"name\"',''))) DIV 6) from t_epidemic_isolation_family where del_flag = '0' " +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as allIsolationPersonnelNum," +
            "(select case when (sum((CHAR_LENGTH(personInfo)-CHAR_LENGTH(REPLACE(personInfo,'\"name\"',''))) DIV 6)) is null then 0 else sum((CHAR_LENGTH(personInfo)-CHAR_LENGTH(REPLACE(personInfo,'\"name\"',''))) DIV 6) end from t_epidemic_isolation_family where del_flag = '0' and isolation_end_time > NOW() " +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as isolationPersonnelNum " +
            "from dual"+
            "</script>"
    )
    public Map<String, String> isolationOfPersonnel(QueryVo queryVo);

    @Select("<script>"+
            "select" +
            "(select sum((CHAR_LENGTH(nucleic_acid_testing_record)-CHAR_LENGTH(REPLACE(nucleic_acid_testing_record,'\"name\"',''))) DIV 6) from t_epidemic_isolation_family where del_flag = '0' " +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as nucleicAcidTestingPersonnelNum," +
            "(select count(1) from t_epidemic_isolation_family where del_flag = '0' and  nucleic_acid_testing_record like '%\"name\"%'" +
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            ") as nucleicAcidTestingFamilyNum " +
            "from dual"+
            "</script>"
    )
    public Map<String, String> nucleicAcidTestingNum(QueryVo queryVo);

    @Select("<script>"+
            "select count(teif.id) num ,sdd.dict_label as name from sys_dict_data sdd " +
            "LEFT JOIN t_epidemic_isolation_family teif "+
            "on  locate(sdd.dict_value,teif.isolation_reason) and teif.del_flag='0' "+
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            " where sdd.dict_type='base_isolation_reason' and sdd.status='0' GROUP BY sdd.dict_label "+
            "</script>"
    )
    public List<GroupByVo> isolationReason(QueryVo queryVo);

    @Select("<script>"+
            "select count(id) num , date.day as name from " +
            "(SELECT date_add( date_sub(#{startTime},interval 1 day), INTERVAL ( cast( help_topic_id AS signed ) + 1 ) day ) "+
            "day FROM mysql.help_topic WHERE help_topic_id &lt; DATEDIFF(#{endTime},date_sub(#{startTime},interval 1 day)) ORDER BY help_topic_id) date "+
            " LEFT JOIN t_epidemic_isolation_family teif "+
            "<if test='type == 1'> on date.day &lt;= date_format(teif.isolation_end_time,'%Y-%m-%d') and date.day &gt;= date_format(teif.isolatio_start_time,'%Y-%m-%d') </if> " +
            "<if test='type == 2'> on date.day = date_format(teif.isolatio_start_time,'%Y-%m-%d') </if>and teif.del_flag='0' "+
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            "GROUP BY date.day "+
            "</script>"
    )
    public List<GroupByVo> isolationFamilyList(QueryVo queryVo);

    @Select("<script>"+
            "select count(id) num , date.day as name from " +
            "(SELECT date_add( date_sub(#{startTime},interval 1 day), INTERVAL ( cast( help_topic_id AS signed ) + 1 ) day ) "+
            "day FROM mysql.help_topic WHERE help_topic_id &lt; DATEDIFF(#{endTime},date_sub(#{startTime},interval 1 day)) ORDER BY help_topic_id) date "+
            " LEFT JOIN t_person_epidemic_state tpes "+
            " on date.day = date_format(tpes.create_time,'%Y-%m-%d') and tpes.del_flag='0' <if test='otherData != null'> and ( ${otherData} )</if>"+
            //数据权限控制开始
            "<if test='isAllDataAccess !=null and !isAllDataAccess and dataAccessList !=null and dataAccessList.size > 0'><if test='communityId == null  or communityId == \"\"'>and community_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityId}</foreach></if><if test='communityChildId == null  or communityChildId == \"\"'>and community_child_id in<foreach item='dataAccess' collection='dataAccessList' open='(' separator=',' close=')'>#{dataAccess.communityChildId}</foreach></if></if>"+
            //数据权限控制结束
            "GROUP BY date.day "+
            "</script>"
    )
    public List<GroupByVo> reportInfoList(QueryVo queryVo);
}




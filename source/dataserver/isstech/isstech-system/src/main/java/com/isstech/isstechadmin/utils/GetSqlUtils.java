package com.isstech.isstechadmin.utils;

import com.isstech.isstechadmin.utils.domain.ArrayTemp;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GetSqlUtils {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 获取 mybatis执行的sql
     *
     * @param o  参数，对象
     * @param id xml ID
     * @return sql语句
     */
    public String getSql(Object o, String id) {
        BoundSql boundSql = sqlSessionTemplate.getConfiguration().
                getMappedStatement(id).
                getBoundSql(o);
        String sql = boundSql.getSql();
        //获取参数集合
        List<ParameterMapping> parms = boundSql.getParameterMappings();
        //参数不为空
        if (parms != null) {
            for (ParameterMapping p : parms) {
                //获取参数名字
                String str = p.getProperty();
                //封装成getXxx方法
                String s = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                try {
                    String javaType = o.getClass().getMethod(s).getReturnType().getName();
                    //反射getXxx()返回的值替换sql语句的的?
                    if("java.lang.String".equals(javaType)){
                        sql = sql.replaceFirst(" \\?", "'" + (String) o.getClass().getMethod(s).invoke(o) + "'");
                    }else if("java.util.Date".equals(javaType)){
                        java.util.Date date = (java.util.Date)o.getClass().getMethod(s).invoke(o);
                        java.sql.Timestamp st = new java.sql.Timestamp(date.getTime());
                        sql = sql.replaceFirst(" \\?", "'" + st.toString() + "'");
                    }else if("java.lang.Long".equals(javaType)){
                        sql = sql.replaceFirst(" \\?", "'" + (Long) o.getClass().getMethod(s).invoke(o) + "'");
                    }else if("java.lang.Integer".equals(javaType)){
                        sql = sql.replaceFirst(" \\?", "'" + (Integer) o.getClass().getMethod(s).invoke(o) + "'");
                    }else if("java.lang.Double".equals(javaType)){
                        sql = sql.replaceFirst(" \\?", "'" + (Double) o.getClass().getMethod(s).invoke(o) + "'");
                    }else if("java.math.BigDecimal".equals(javaType)){
                        sql = sql.replaceFirst(" \\?", "'" + (BigDecimal) o.getClass().getMethod(s).invoke(o) + "'");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        sql = sql.replaceAll("(\r?\n(\\s*\r?\n)+)", " ").replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("    ", " ").replaceAll("   ", " ").replaceAll("  ", " ");
        return sql;
    }

    /**
     * 获取 mybatis执行的sql-根据id查询，根据id删除
     *
     * @param str  参数，对象
     * @param id xml ID
     * @return sql语句
     */
    public String getSql(String str, String id) {
        BoundSql boundSql = sqlSessionTemplate.getConfiguration().
                getMappedStatement(id).
                getBoundSql(str);
        String sql = boundSql.getSql();
        sql = sql.replaceAll("\\?","'"+str+"'");
        sql = sql.replaceAll("(\r?\n(\\s*\r?\n)+)", " ").replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("    ", " ").replaceAll("   ", " ").replaceAll("  ", " ");
        return sql;
    }

    /**
     * 获取 mybatis执行的sql-根据ids删除及软删除
     *
     * @param stringArr  参数，对象
     * @param id xml ID
     * @return sql语句
     */
    public String getSql(String[] stringArr, String id) {
        BoundSql boundSql = sqlSessionTemplate.getConfiguration().
                getMappedStatement(id).
                getBoundSql(new ArrayTemp(stringArr));
        String sql = boundSql.getSql();
        System.out.println(sql);
        for (String str:stringArr) {
            sql = sql.replaceFirst("\\?","'"+str+"'");
        }
        sql = sql.replaceAll("(\r?\n(\\s*\r?\n)+)", " ").replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("    ", " ").replaceAll("   ", " ").replaceAll("  ", " ");
        return sql;
    }
}

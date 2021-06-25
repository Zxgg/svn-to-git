package com.isstech.isstechadmin.utils;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.entity.SysDept;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.mapper.SysDeptMapper;
import com.isstech.isstechadmin.utils.domain.SysDataSynchronization;
import com.isstech.isstechadmin.utils.domain.SysDeptIp;
import com.isstech.isstechadmin.utils.mapper.DataSynchronizationMapper;
import com.isstech.isstechadmin.utils.mapper.SysDataSynchronizationMapper;
import com.isstech.isstechadmin.utils.mapper.SysDeptIpMapper;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@Service
public class DataSynchronizationUtils {

    @Autowired
    private GetSqlUtils getSqlUtils;
    @Autowired
    private DataSynchronizationMapper dataSynchronizationMapper;
    @Autowired
    private SysDeptIpMapper sysDeptIpMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysDataSynchronizationMapper sysDataSynchronizationMapper;

    private static CCJSqlParserManager pm = new CCJSqlParserManager();

    /**同步开关*/
    @Value("${dataSynchronization.isSwitch}")
    public boolean isSwitch;

    /**同步上级ip*/
    @Value("${dataSynchronization.synIp}")
    public String synIp;

    /**本部门唯一code*/
    @Value("${dataSynchronization.synCode}")
    public String synCode;

    /**服务默认端口*/
    @Value("${server.port}")
    public String synPort;

    /**
     * 保存需要同步的数据 单条数据（适用于单条删除的sql）
     *
     *
     * @param dataId 参数，对象
     * @param id xml ID mapper的名称
     * @return 结果
     */
    public void dataParsing(String dataId, String id){
        if(!isDataSynchronization()){
            return;
        }
        try{
            String dataSql = getSqlUtils.getSql(dataId, id);
            List<String> tableNames = getTableNames(dataSql);
            if(tableNames == null || tableNames.size() != 1){
                return;
            }
            String tableName = tableNames.get(0);
            if (StringUtils.isEmpty(tableName)){
                return;
            }
            boolean flag = dataSynchronizationMapper.selectDbTableColumnsByName(tableName).stream().filter(item -> "community_child_id".equals(item)).count() > 0;
            if(!flag){
                return;
            }
            String deptId = dataSynchronizationMapper.getDeptIdById(tableName, dataId);
            if (StringUtils.isEmpty(deptId)){
                return;
            }
            dataSave(deptId,dataSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存需要同步的数据 多条数据（适用于批量删除与批量软删除的sql）
     *
     * @param dataIds 参数，对象
     * @param id xml ID mapper的名称
     * @return 结果
     */
    public void dataParsing(String[] dataIds, String id){
        if(!isDataSynchronization()){
            return;
        }
        try{
            String dataSql = getSqlUtils.getSql(dataIds, id);
            List<String> tableNames = getTableNames(dataSql);
            if(tableNames == null || tableNames.size() != 1){
                return;
            }
            String tableName = tableNames.get(0);
            if (StringUtils.isEmpty(tableName)){
                return;
            }
            boolean flag = dataSynchronizationMapper.selectDbTableColumnsByName(tableName).stream().filter(item -> "community_child_id".equals(item)).count() > 0;
            if(!flag){
                return;
            }
            for (String dataId: dataIds) {
                String deptId = dataSynchronizationMapper.getDeptIdById(tableName, dataId);
                if (StringUtils.isEmpty(deptId)){
                    continue;
                }
                dataSave(deptId,dataSql);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存需要同步的数据 单条数据（适用于单条insert和update）
     *
     * @param obj 参数，对象
     * @param id xml ID mapper的名称
     * @return 结果
     */
    public void dataParsing(Object obj, String id){
        if(!isDataSynchronization()){
            return;
        }
        try{
            String dataSql = getSqlUtils.getSql(obj, id);
            List<String> tableNames = getTableNames(dataSql);
            if(tableNames == null || tableNames.size() != 1){
                return;
            }
            String tableName = tableNames.get(0);
            if (StringUtils.isEmpty(tableName)){
                return;
            }
            boolean flag = dataSynchronizationMapper.selectDbTableColumnsByName(tableName).stream().filter(item -> "community_child_id".equals(item)).count() > 0;
            if(!flag){
                return;
            }
            String dataId = (String) obj.getClass().getMethod("getId").invoke(obj);
            if (StringUtils.isEmpty(dataId)){
                return;
            }
            String deptId = dataSynchronizationMapper.getDeptIdById(tableName,dataId);
            if (StringUtils.isEmpty(deptId)){
                return;
            }
            dataSave(deptId,dataSql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存同步数据
     * */
    public void dataSave(String deptId,String dataSql){
        if(!isDataSynchronization(deptId)){
            return;
        }
        dataSaveUp(deptId,dataSql);
        dataSaveNext(deptId,dataSql);
    }

    /**
     * 保存同步数据-向上级同步数据
     * */
    public void dataSaveUp(String deptId, String dataSql) {
        //判断同步开关是否开启
        if(!isSwitch){
            return;
        }
        if(StringUtils.isEmpty(synIp)){
            return;
        }
        SysDataSynchronization synData = new SysDataSynchronization();
        synData.setId(sysDataSynchronizationMapper.selectUuid());
        synData.setCode(synCode);
        synData.setName(getDeptName());
        synData.setDeptid(dataSynchronizationMapper.getDeptId());
        synData.setSynData(dataSql);
        synData.setCreateTime(new Date());
        synData.setSynEndDeptid(deptId);
        synData.setPort(synPort);

        synData.setSynDeptid("");
        synData.setSynName("上级");
        synData.setType("1");
        synData.setSynIp(synIp);
        sysDataSynchronizationMapper.insertSysDataSynchronization(synData);
    }

    /**
     * 保存同步数据-向下级同步数据
     * */
    public void dataSaveNext(String deptId, String dataSql) {
        SysDept nextDept = getNextDeptId(deptId);
        String deptIp = dataSynchronizationMapper.getDeptIp(nextDept.getId());
        if(StringUtils.isEmpty(deptIp)){
            return;
        }
        SysDataSynchronization synData = new SysDataSynchronization();
        synData.setId(sysDataSynchronizationMapper.selectUuid());
        synData.setName(getDeptName());
        synData.setDeptid(dataSynchronizationMapper.getDeptId());
        synData.setSynData(dataSql);
        synData.setSynEndDeptid(deptId);
        synData.setCreateTime(new Date());
        synData.setPort(synPort);

        synData.setType("0");
        synData.setSynDeptid(nextDept.getId());
        synData.setSynName(nextDept.getName());
        synData.setSynIp(deptIp);
        synData.setCode(synCode);
        sysDataSynchronizationMapper.insertSysDataSynchronization(synData);
    }

    /**
     * 获取当前部门名称
     * */
    public String getDeptName(){
        return dataSynchronizationMapper.getDeptName();
    }

    /**
     * 获取本条数据的紧贴下级deptId
     * */
    public SysDept getNextDeptId(String deptId){
        SysDept sysDept = sysDeptMapper.selectDeptById(deptId);
        while (!StringUtils.equals(sysDept.getParentId(),dataSynchronizationMapper.getDeptId())){
            sysDept = sysDeptMapper.selectDeptById(sysDept.getParentId());
        }
        return sysDept;
    }

    /**
     * 是否开启数据同步-针对数据
     * */
    public boolean isDataSynchronization(String deptId){
        //判断本条数据是否是本部门的，本部门且无上级部门时无需同步
        SysDept sysDept = sysDeptMapper.selectDeptById(deptId);
        if(StringUtils.equals("0",sysDept.getParentId())&&StringUtils.isEmpty(synIp)){
            return false;
        }
        return true;
    }


    /**
     * 是否开启数据同步
     * */
    public boolean isDataSynchronization(){
        //判断同步开关是否开启
        if(!isSwitch){
            return false;
        }
        //判断是否有上下级部门-无上下级部门无需同步
        SysDeptIp sysDeptIp = new SysDeptIp();
        sysDeptIp.setDeptType("2");
        List<SysDeptIp> sysDeptIps = sysDeptIpMapper.selectSysDeptIpList(sysDeptIp);
        if(sysDeptIps.size()<=0&&StringUtils.isEmpty(synIp)){
            return false;
        }
        return true;
    }

    /**
     * 服务端接收到数据后，执行sql，并判断是否继续向上同步
     * */
    public AjaxResult synUp(HttpServletRequest request,SysDataSynchronization information){
        try{
            String ipAddr = getIpAddr(request);
            ipAddr = ipAddr + ":" +information.getPort();
            SysDeptIp sysDeptIp = new SysDeptIp();
            sysDeptIp.setDeptId(information.getDeptid());
            List<SysDeptIp> sysDeptIps = sysDeptIpMapper.selectSysDeptIpList(sysDeptIp);
            //此时的if判断满足是首次同步到本部门 ，需添加下级部门，和下级部门的ip等信息
            if(sysDeptIps==null || (sysDeptIps!=null && sysDeptIps.size()<=0) || (sysDeptIps!=null && StringUtils.equals(ipAddr,sysDeptIps.get(0).getDeptIp())) ){
                //创建部门
                SysDept sysDept = new SysDept();
                sysDept.setId(information.getDeptid());
                sysDept.setParentId(dataSynchronizationMapper.getDeptId());
                sysDept.setStatus("0");
                sysDept.setName(information.getName());
                sysDept.setOrderNum("0");
                sysDept.setCreateBy("admin");
                sysDept.setCreateTime(new Date());
                sysDeptMapper.insertDept(sysDept);
                //创建部门ip信息
                sysDeptIp.setId(sysDeptIpMapper.selectUuid());
                sysDeptIp.setDeptId(information.getDeptid());
                sysDeptIp.setDeptName(information.getName());
                sysDeptIp.setDeptType("2");
                sysDeptIp.setDeptIp(ipAddr);
                sysDeptIpMapper.insertSysDeptIp(sysDeptIp);
                //判断是否有上级，有上级则需要把信息同步给上级部门
                if(StringUtils.isNotEmpty(synIp)){
                    dataSaveUp(information.getDeptid(),getSqlUtils.getSql(sysDept,"insertDept"));
                }
            }
        }catch (Exception e){}
        try{
            if(information.getSynData().toLowerCase().contains("update")){
                sysDataSynchronizationMapper.executeUpdate(information.getSynData());
            }else if(information.getSynData().toLowerCase().contains("delete from")){
                sysDataSynchronizationMapper.executeDelete(information.getSynData());
            }else if(information.getSynData().toLowerCase().contains("insert into")){
                sysDataSynchronizationMapper.executeInsert(information.getSynData());
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.success("同步成功");
        }finally {
            //需要同步的部门和最终部门id一样时说明到达终点
            if(information.getSynDeptid().equals(information.getSynEndDeptid())){
                return AjaxResult.success("同步成功");
            }
            //判断是否有上级继续同步
            if(StringUtils.isEmpty(synIp)){
                return AjaxResult.success("同步成功");
            }
            dataSaveUp(information.getSynEndDeptid(),information.getSynData());
            return AjaxResult.success("同步成功");
        }
    }

    /**
     * 服务端接收到数据后，执行sql，并判断是否继续向下同步
     * */
    public AjaxResult synNext(SysDataSynchronization information){
        try{
            if(information.getSynData().toLowerCase().contains("update")){
                sysDataSynchronizationMapper.executeUpdate(information.getSynData());
            }else if(information.getSynData().toLowerCase().contains("delete from")){
                sysDataSynchronizationMapper.executeDelete(information.getSynData());
            }else if(information.getSynData().toLowerCase().contains("insert into")){
                sysDataSynchronizationMapper.executeInsert(information.getSynData());
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.success("同步成功");
        }finally {
            //需要同步的部门和最终部门id一样时说明到达终点
            if(information.getSynDeptid().equals(information.getSynEndDeptid())){
                return AjaxResult.success("同步成功");
            }
            dataSaveNext(information.getSynEndDeptid(),information.getSynData());
            return AjaxResult.success("同步成功");
        }
    }


    /**
     * 根据sql获取表名
     * */
    private static List<String> getTableNames(String sql) {
        sql = sql.replace("enable","enabled");
        List<String> tablenames = null;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        Statement statement = null;
        try {
            statement = pm.parse(new StringReader(sql));
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        if (statement instanceof Select) {
            tablenames = tablesNamesFinder.getTableList((Select) statement);
        } else if (statement instanceof Update) {
            tablenames = tablesNamesFinder.getTableList((Update) statement);
        } else if (statement instanceof Delete) {
            tablenames = tablesNamesFinder.getTableList((Delete) statement);
        } else if (statement instanceof Replace) {
            tablenames = tablesNamesFinder.getTableList((Replace) statement);
        } else if (statement instanceof Insert) {
            tablenames = tablesNamesFinder.getTableList((Insert) statement);
        }
        return tablenames;
    }

    /**
     * 获取ip地址
     * */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String localIp = "127.0.0.1";
            String localIpv6 = "0:0:0:0:0:0:0:1";
            if (ipAddress.equals(localIp) || ipAddress.equals(localIpv6)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        String ipSeparate = ",";
        int ipLength = 15;
        if (ipAddress != null && ipAddress.length() > ipLength) {
            if (ipAddress.indexOf(ipSeparate) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(ipSeparate));
            }
        }
        return ipAddress;
    }

}

package com.isstech.isstechadmin.utils;

import com.isstech.common.core.domain.entity.SysRole;
import com.isstech.common.core.domain.entity.SysUser;
import com.isstech.common.core.domain.model.LoginUser;
import com.isstech.common.core.redis.RedisCache;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.ServletUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseDeptCommunityChild;
import com.isstech.isstechadmin.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataAccessUtils {

     /***
      * 判断当前登录登录用户所有角色中是否有全部权限
      * @return 结果
      */

     @Autowired
     private SysRoleMapper roleMapper;

     public boolean isAllDataAccess(){
          LoginUser loginUser = SecurityUtils.getLoginUser();
          if(StringUtils.isNotEmpty(loginUser.isAllDataAccess())){
               return "true".equals(loginUser.isAllDataAccess());
          }else{
               SysUser user = loginUser.getUser();
               List<SysRole> roles = roleMapper.selectRolePermissionByUserId(user.getId());
               List<SysRole> collect = roles.stream().filter(item -> "1".equals(item.getDataScope())).collect(Collectors.toList());
               return StringUtils.isNotEmpty(collect);
          }
     }

     public List<Map<String, String>> getCurrUserDataAccessDeptToList(){
          LoginUser loginUser = SecurityUtils.getLoginUser();
          if(StringUtils.isNotEmpty(loginUser.getDataAccessList())){
               return loginUser.getDataAccessList();
          }else{
               List<Map<String, String>> baseDeptCommunityChildren = new ArrayList<Map<String, String>>();
               SysUser user = loginUser.getUser();
               baseDeptCommunityChildren = roleMapper.selectDeptCommunityChildListMapByUserId(user.getId());
               return baseDeptCommunityChildren;
          }
     }

     public boolean isAllDataAccess(LoginUser loginUser){
          if(loginUser == null){
               loginUser = SecurityUtils.getLoginUser();
          }
          if(loginUser.isAllDataAccess()!=null){
               return "true".equals(loginUser.isAllDataAccess());
          }else{
               SysUser user = loginUser.getUser();
               List<SysRole> roles = roleMapper.selectRolePermissionByUserId(user.getId());
               List<SysRole> collect = roles.stream().filter(item -> "1".equals(item.getDataScope())).collect(Collectors.toList());
               return collect!=null&&collect.size()>0;
          }
     }

     public List<Map<String, String>> getCurrUserDataAccessDeptToList(LoginUser loginUser){
          if(loginUser == null){
               loginUser = SecurityUtils.getLoginUser();
          }
          if(loginUser.getDataAccessList()!=null){
               return loginUser.getDataAccessList();
          }else{
               List<Map<String, String>> baseDeptCommunityChildren = new ArrayList<Map<String, String>>();
               SysUser user = loginUser.getUser();
               baseDeptCommunityChildren = roleMapper.selectDeptCommunityChildListMapByUserId(user.getId());
               if (StringUtils.isEmpty(baseDeptCommunityChildren)){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("communityId","0");
                    map.put("communityChildId","0");
                    baseDeptCommunityChildren.add(map);
               }
               return baseDeptCommunityChildren;
          }
     }

}



#版本部署路径
mainpath=/root/HLS/Nginx/html
back=${mainpath}/../backup/comm/
#备份版本包
deploy_backup()
{
   #判断是否有comm.zip
   if [ ! -e comm.zip ]
   then
   echo 请先上传comm.zip文件
   exit 0
   fi;

  if [ ! -e comm ]
  then 
  echo 第一次上传，不进行备份
  return 0
  fi;
  tar -zcvf comm_`date +%Y%m%d%H%M%S`.tar.gz comm;
  sleep 3
  ls;
  mv *.tar.gz ${back};
}


#解压版本包
deploy_unzip()
{
cd ${mainpath};
ls;
sleep 3;
#解压
unzip -o comm.zip;
#删除
rm -rf ./comm.zip;
echo 操作成功！请重启Nginx服务
}


#版本回滚
deploy_Rollback()  
{  
  #进入备份文件夹  
  cd ${back}
  #获取最新备份文件  
  file_name_new=''  
  for i in `ls -tr`;  
  do    
      file_name_new=$i;  
  done;
  #将备份文件复制到部署路径下
  cp ${file_name_new} ${mainpath}
  if [ $? -eq 0 ]
  then 
    echo 复制${file_name_new}成功
  else 
    echo 复制失败，退出
  exit 1 
  fi
  #进入部署路径
  cd ${mainpath} 
  #解压
  tar -zxf ${file_name_new}
  if [ $? -eq 0 ]
  then 
    echo 解压成功
  else 
    echo 解压失败
   exit 1 
  fi
  rm -rf ${file_name_new}
  echo 版本回滚成功
}  


echo ------------欢迎使用shell自动部署脚本
echo -e "请输入对应的操作编号：\n0.版本包回滚;\n其他键.一件部署;"
#获取用户操作
read wm2
case $wm2 in
0)
  deploy_Rollback
  ;;
*)
  deploy_backup
  deploy_unzip
  ;;
esac





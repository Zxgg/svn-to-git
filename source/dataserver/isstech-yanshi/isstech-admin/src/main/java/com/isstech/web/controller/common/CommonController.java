package com.isstech.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.redis.RedisCache;
import com.isstech.isstechadmin.domain.XzqhTree;
import com.isstech.isstechadmin.service.XzqhService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.isstech.common.config.ProConfig;
import com.isstech.common.constant.Constants;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.file.FileUploadUtils;
import com.isstech.common.utils.file.FileUtils;
import com.isstech.framework.config.ServerConfig;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 通用请求处理
 * @Author yzp
 * @Date 2020/12/17 14:59
 */
@RestController
public class CommonController
{
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private XzqhService xzqhService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ProConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = ProConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload( String name, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 本地资源路径
        String localPath = ProConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(name, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }


    //获取事件类型树
    @GetMapping(value = "/common/divisiontree")
    public AjaxResult getadministrativeDivisionTree() {
        //从redis获取所有事件类型编码
        JSONArray jsonArray = redisCache.getCacheObject("sys_dict:base_event_type");
        List<XzqhTree> collect = jsonArray.stream().map(v -> {
            XzqhTree xzqhTree = new XzqhTree();
            JSONObject json = (JSONObject) JSON.toJSON(v);
            xzqhTree.setValue(json.getLongValue("dictValue"));
            xzqhTree.setDictParentValue(json.getLongValue("dictParentValue"));
            xzqhTree.setLabel(json.getString("dictLabel"));
            return xzqhTree;
        }).collect(Collectors.toList());
        List<XzqhTree> xzqhTrees = xzqhService.buildxzqhTree(collect);
        return AjaxResult.success(xzqhTrees);

    }
}

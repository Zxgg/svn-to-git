package com.isstech.web.controller.tool;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.framework.web.fastdfs.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 文件接口
 *
 * @author yzp
 * @version 1.0
 * @date 2020/12/11 14:48
 */
@RestController
public class FastdfsController {

    private FastDFSClient fastDFSClient = new FastDFSClient();
    /**
     * 上传文件通用，只上传文件到服务器，不会保存记录到数据库
     * @param file
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping(value = "/upload/file/sample")
    @ResponseBody
    public FileResponseData uploadFileSample(MultipartFile file, HttpServletRequest request) {
        return uploadSample(file, request);
    }

    /**
     * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
     *
     * @param files
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping("/upload/image/sample")
    @ResponseBody
    public AjaxResult uploadImageSample(@RequestParam MultipartFile[] files, MultipartHttpServletRequest request) throws FastDFSException {
        List<MultipartFile> list = request.getMultiFileMap().get("file");// 获取到文件的列表
        // 检查文件类型
        for (MultipartFile file: list) {
            if (!FileCheck.checkImage(file.getOriginalFilename())) {
                return AjaxResult.success("上传失败","请检查文件类型");
            }
        }
        StringBuffer filePaths = new StringBuffer();
        for (MultipartFile file: list)  {
            String filePath = uploadSampleToHttpUrl(file, request);
            if(StringUtils.isEmpty(filePath)){
                return AjaxResult.success("上传失败","上传文件服务器失败！");
            }
            filePaths.append(filePath).append(",");
        }
        return AjaxResult.success("上传成功",filePaths.substring(0, filePaths.length() - 1));
    }

    /**
     * 只能上传图片base64，只上传文件到服务器，不会保存记录到数据库. <br>
     *
     * @param map
     * @return 返回文件路径等信息
     */
    @RequestMapping("/upload/base64/sample")
    @ResponseBody
    public AjaxResult uploadBase64Sample(@RequestBody Map<String,String> map) throws FastDFSException {
        // 上传到服务器
        String filepath = fastDFSClient.uploadFileWithBase64(map.get("base64"),map.get("filename"));

        // 设置访文件的Http地址. 有时效性.
        String token = FastDFSClient.getToken(filepath, FastDfsConfig.fastDFSHttpSecretKey);
        String httpUrl = "http://"+FastDfsConfig.fileServerAddr + "/" + filepath + "?" + token;
        return AjaxResult.success("上传成功",httpUrl);
    }

    /**
     * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
     *
     * @param files
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping("/uploadToHW/image/sample")
    @ResponseBody
    public AjaxResult uploadAndHWImageSample(@RequestParam MultipartFile[] files, MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> list = request.getMultiFileMap().get("file");// 获取到文件的列表
        // 检查文件类型
        for (MultipartFile file: list) {
            if (!FileCheck.checkImage(file.getOriginalFilename())) {
                return AjaxResult.success("上传失败","请检查文件类型");
            }
        }
        StringBuffer caseFileIds = new StringBuffer();
        for (MultipartFile file: list)  {
            String caseFileId = fastDFSClient.uploadToHW(file, request);
            if(StringUtils.isEmpty(caseFileId)){
                return AjaxResult.success("上传失败","上传华为失败！");
            }
            caseFileIds.append(caseFileId).append(",");
        }
        Map<String,String> map = new HashMap<String,String>();
        if (StringUtils.isNotEmpty(caseFileIds.toString())){
            map.put("fileId",caseFileIds.substring(0, caseFileIds.length() - 1));
        }
        return AjaxResult.success("上传成功",map);
    }

    /**
     * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
     *
     * @param files
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping("/uploadToHWAndFileServer/image/sample")
    @ResponseBody
    public AjaxResult uploadToHWAndFileServerImageSample(@RequestParam MultipartFile[] files, MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> list = request.getMultiFileMap().get("file");// 获取到文件的列表
        // 检查文件类型
        for (MultipartFile file: list) {
            if (!FileCheck.checkImage(file.getOriginalFilename())) {
                return AjaxResult.success("上传失败","请检查文件类型");
            }
        }
        StringBuffer caseFileIds = new StringBuffer();
        StringBuffer filePaths = new StringBuffer();
        for (MultipartFile file: list)  {
            String caseFileId = fastDFSClient.uploadToHW(file, request);
            if(StringUtils.isEmpty(caseFileId)){
                return AjaxResult.success("上传失败","上传华为失败！");
            }
            String filePath = uploadSampleToHttpUrl(file, request);
            if(StringUtils.isEmpty(filePath)){
                return AjaxResult.success("上传失败","上传文件服务器失败！");
            }
            caseFileIds.append(caseFileId).append(",");
            filePaths.append(filePath).append(",");
        }
        Map<String,String> map = new HashMap<String,String>();
        if (StringUtils.isNotEmpty(caseFileIds.toString())&&StringUtils.isNotEmpty(filePaths.toString())){
            map.put("fileId",caseFileIds.substring(0, caseFileIds.length() - 1));
            map.put("imgUrl",filePaths.substring(0, filePaths.length() - 1));
        }
        return AjaxResult.success("上传成功",map);
    }

    /**
     * 只能上传文档，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型.
     *
     * @param file
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping("/upload/doc/sample")
    @ResponseBody
    public FileResponseData uploadDocSample(@RequestParam MultipartFile file, HttpServletRequest request) {
        // 检查文件类型
        if (!FileCheck.checkDoc(file.getOriginalFilename())) {
            FileResponseData responseData = new FileResponseData(false);
            responseData.setCode(ErrorCode.FILE_TYPE_ERROR_DOC.CODE);
            responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_DOC.MESSAGE);
            return responseData;
        }

        return uploadSample(file, request);
    }

    /**
     * 以附件形式下载文件
     *
     * @param filePath 文件地址
     * @param response
     */
    @RequestMapping("/download/file")
    public void downloadFile(String filePath, HttpServletResponse response) throws FastDFSException {
        try {
            fastDFSClient.downloadFile(filePath, response);
        } catch (FastDFSException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取图片 使用输出流输出字节码，可以使用< img>标签显示图片<br>
     *
     * @param filePath 图片地址
     * @param response
     */
    @RequestMapping("/download/image")
    public void downloadImage(String filePath, HttpServletResponse response) throws FastDFSException {
        try {
            fastDFSClient.downloadFile(filePath, null, response);
        } catch (FastDFSException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 根据指定的路径删除服务器文件，适用于没有保存数据库记录的文件
     *
     * @param filePath
     */
    @RequestMapping("/delete/file")
    public FileResponseData deleteFile(String filePath, Locale locale) {
        FileResponseData responseData = new FileResponseData();
        try {
            fastDFSClient.deleteFile(filePath);
        } catch (FastDFSException e) {
            e.printStackTrace();
            responseData.setSuccess(false);
            responseData.setCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }
        return responseData;
    }

    /**
     * 获取访问文件的token
     *
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping("/get/token")
    @ResponseBody
    public FileResponseData getToken(String filePath) {
        FileResponseData responseData = new FileResponseData();
        // 设置访文件的Http地址. 有时效性.
        String token = FastDFSClient.getToken(filePath, FastDfsConfig.fastDFSHttpSecretKey);
        responseData.setToken(token);
        responseData.setHttpUrl(FastDfsConfig.fileServerAddr + "/" + filePath + "?" + token);

        return responseData;
    }

    /**
     * 上传通用方法，只上传到服务器，不保存记录到数据库
     *
     * @param file
     * @param request
     * @return
     */
    public FileResponseData uploadSample(MultipartFile file, HttpServletRequest request) {
        FileResponseData responseData = new FileResponseData();
        try {
            // 上传到服务器
            String filepath = fastDFSClient.uploadFileWithMultipart(file);

            responseData.setFileName(file.getOriginalFilename());
            responseData.setFilePath(filepath);
            responseData.setFileType(FastDFSClient.getFilenameSuffix(file.getOriginalFilename()));
            // 设置访文件的Http地址. 有时效性.
            String token = FastDFSClient.getToken(filepath, FastDfsConfig.fastDFSHttpSecretKey);
            responseData.setToken(token);
            responseData.setHttpUrl(FastDfsConfig.fileServerAddr + "/" + filepath + "?" + token);
        } catch (FastDFSException e) {
            responseData.setSuccess(false);
            responseData.setCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }

        return responseData;
    }

    /**
     * 上传通用方法，只上传到服务器，不保存记录到数据库，只返回文件的httpUrl地址
     *
     * @param file
     * @param request
     * @return
     */
    public String uploadSampleToHttpUrl(MultipartFile file, HttpServletRequest request) {
        String httpUrl = "";
        try {
            // 上传到服务器
            String filepath = fastDFSClient.uploadFileWithMultipart(file);

            // 设置访文件的Http地址. 有时效性.
            String token = FastDFSClient.getToken(filepath, FastDfsConfig.fastDFSHttpSecretKey);
            httpUrl = "/" + filepath + "?" + token;
        } catch (FastDFSException e) {
            return httpUrl;
        }
        return httpUrl;
    }

    /**
     * 上传通用方法，只上传到服务器，不保存记录到数据库，根据图片imageUrl上传到文件服务器并返回图片的httpUrl地址
     *
     * @param imageUrl
     * @return
     */
    public String uploadImageSample(String imageUrl) {
        String httpUrl = "";
        try {
            // 上传到服务器
            String filepath = fastDFSClient.uploadFileWithHttpFilepath(imageUrl);

            // 设置访文件的Http地址. 有时效性.
            String token = FastDFSClient.getToken(filepath, FastDfsConfig.fastDFSHttpSecretKey);
            httpUrl = "http://"+FastDfsConfig.fileServerAddr + "/" + filepath + "?" + token;
        } catch (FastDFSException e) {
            return httpUrl;
        }
        return httpUrl;
    }
}

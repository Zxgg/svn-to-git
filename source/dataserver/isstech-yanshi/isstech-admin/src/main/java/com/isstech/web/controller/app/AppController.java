package com.isstech.web.controller.app;

import com.isstech.common.annotation.Log;
import com.isstech.common.constant.Constants;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.entity.SysAppUser;
import com.isstech.common.core.domain.entity.SysUser;
import com.isstech.common.core.domain.model.LoginBody;
import com.isstech.common.core.domain.model.LoginUser;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.core.redis.RedisCache;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.exception.CustomException;
import com.isstech.common.exception.user.CaptchaException;
import com.isstech.common.exception.user.CaptchaExpireException;
import com.isstech.common.exception.user.UserPasswordNotMatchException;
import com.isstech.common.utils.MessageUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.bean.BeanUtils;
import com.isstech.framework.manager.AsyncManager;
import com.isstech.framework.manager.factory.AsyncFactory;
import com.isstech.framework.web.fastdfs.FastDFSClient;
import com.isstech.framework.web.fastdfs.FastDFSException;
import com.isstech.framework.web.fastdfs.FastDfsConfig;
import com.isstech.framework.web.fastdfs.FileCheck;
import com.isstech.framework.web.service.SysLoginService;
import com.isstech.framework.web.service.TokenService;
import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 手机端对外接口Controller
 *
 * @author dyf
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/app")
public class AppController extends BaseController {

    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private IBaseCommunityService baseCommunityService;
    @Autowired
    private IBaseVoteRecordService baseVoteRecordService;
    @Autowired
    private IBaseVoteProjectService baseVoteProjectService;
    @Autowired
    private IBaseRepairRecordService baseRepairRecordService;
    @Autowired
    private IBaseOpinionRecordService baseOpinionRecordService;
    @Autowired
    private IBaseCommunityChildService baseCommunityChildService;
    @Autowired
    private IBasePersonnelInformationService basePersonnelInformationService;
    @Autowired
    private IBaseVisitorPersonnelInformationService baseVisitorPersonnelInformationService;

    private FastDFSClient fastDFSClient = new FastDFSClient();

    /**
     * 查询社区信息列表
     */
    @ApiOperation("查询社区信息列表")
    @GetMapping("/communityUnitCommunityList")
    public TableDataInfo list(BaseCommunity baseCommunity)
    {
        startPage();
        List<BaseCommunity> list = baseCommunityService.selectBaseCommunityList(baseCommunity);
        return getDataTable(list);
    }

    /**
     * 查询小区信息  列表
     */
    @ApiOperation("查询小区信息  列表")
    @GetMapping("/communityUnitCommunityChildList")
    public TableDataInfo list(BaseCommunityChild baseCommunityChild)
    {
        startPage();
        List<BaseCommunityChild> list = baseCommunityChildService.selectBaseCommunityChildList(baseCommunityChild);
        return getDataTable(list);
    }

    /**
     * 新增人员信息
     */
    @ApiOperation("新增人员信息")
    @Log(title = "人员信息", businessType = BusinessType.INSERT)
    @PostMapping("/communityUnitPersonnelInformation")
    public AjaxResult add(@RequestBody BasePersonnelInformation basePersonnelInformation)
    {
        basePersonnelInformation.setCreateBy("admin");
        return new AjaxResult(200,"操作成功",basePersonnelInformationService.insertBasePersonnelInformation(basePersonnelInformation));
    }

    /**
     * 新增访客人员信息
     */
    @ApiOperation("新增访客人员信息")
    @Log(title = "访客人员信息", businessType = BusinessType.INSERT)
    @PostMapping("/passManageVisitPerson")
    public AjaxResult add(@RequestBody BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        baseVisitorPersonnelInformation.setCreateBy("admin");
        return toAjax(baseVisitorPersonnelInformationService.insertBaseVisitorPersonnelInformation(baseVisitorPersonnelInformation));
    }

    /**
     * 获取通知公告列表
     */
    @GetMapping("/systemNoticeList")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/system/notice/{noticeId}")
    public AjaxResult getInfo(@PathVariable String noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 查询报事报修记录列表
     */
    @GetMapping("/communityServicesRepairRecordList")
    public TableDataInfo list(BaseRepairRecord baseRepairRecord)
    {
        startPage();
        List<BaseRepairRecord> list = baseRepairRecordService.selectBaseRepairRecordList(baseRepairRecord);
        return getDataTable(list);
    }

    /**
     * 新增报事报修记录
     */
    @ApiOperation("新增报事报修记录")
    @Log(title = "报事报修记录", businessType = BusinessType.INSERT)
    @PostMapping("/communityServicesRepairRecord")
    public AjaxResult add(@RequestBody BaseRepairRecord baseRepairRecord)
    {
        baseRepairRecord.setCreateBy("admin");
        return toAjax(baseRepairRecordService.insertBaseRepairRecord(baseRepairRecord));
    }

    /**
     * 查询投诉意见记录列表
     */
    @GetMapping("communityServicesOpinionRecordList")
    public TableDataInfo list(BaseOpinionRecord baseOpinionRecord)
    {
        startPage();
        List<BaseOpinionRecord> list = baseOpinionRecordService.selectBaseOpinionRecordList(baseOpinionRecord);
        return getDataTable(list);
    }

    /**
     * 新增投诉意见记录
     */
    @ApiOperation("新增投诉意见记录")
    @Log(title = "投诉意见记录", businessType = BusinessType.INSERT)
    @PostMapping("/communityServicesOpinionRecord")
    public AjaxResult add(@RequestBody BaseOpinionRecord baseOpinionRecord)
    {
        baseOpinionRecord.setCreateBy("admin");
        return toAjax(baseOpinionRecordService.insertBaseOpinionRecord(baseOpinionRecord));
    }

    /**
     * 查询投票项目信息 列表
     */
    @ApiOperation("查询投票项目信息 列表")
    @GetMapping("/communityServicesVoteProjectList")
    public TableDataInfo list(BaseVoteProject baseVoteProject)
    {
        startPage();
        List<BaseVoteProject> list = baseVoteProjectService.selectBaseVoteProjectList(baseVoteProject);
        return getDataTable(list);
    }

    /**
     * 获取投票项目信息 详细信息
     */
    @ApiOperation("获取投票项目信息 详细信息")
    @GetMapping(value = "/communityServices/vote_project/{id}")
    public AjaxResult getVoteProjectInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVoteProjectService.selectBaseVoteProjectById(id));
    }

    /**
     * 新增投票记录
     */
    @ApiOperation("新增投票记录 ")
    @Log(title = "投票记录 ", businessType = BusinessType.INSERT)
    @PostMapping("/communityServicesVoteRecord")
    public AjaxResult add(@RequestBody BaseVoteRecord baseVoteRecord)
    {
        baseVoteRecord.setCreateBy("admin");
        return toAjax(baseVoteRecordService.insertBaseVoteRecord(baseVoteRecord));
    }

    /**
     * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
     * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
     *
     * @param request
     * @return 返回文件路径等信息
     */
    @RequestMapping("/uploadImageSample")
    @ResponseBody
    public AjaxResult uploadImageSample(MultipartHttpServletRequest request) {
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
            httpUrl = "http://"+FastDfsConfig.fileServerAddr + "/" + filepath + "?" + token;
        } catch (FastDFSException e) {
            return httpUrl;
        }
        return httpUrl;
    }



}
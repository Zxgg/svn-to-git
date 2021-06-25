package com.isstech.web.controller.app;

import com.isstech.common.constant.Constants;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.entity.SysAppUser;
import com.isstech.common.core.domain.model.LoginBody;
import com.isstech.common.core.domain.model.LoginUser;
import com.isstech.common.core.redis.RedisCache;
import com.isstech.common.exception.user.CaptchaException;
import com.isstech.common.exception.user.CaptchaExpireException;
import com.isstech.common.exception.user.UserPasswordNotMatchException;
import com.isstech.common.utils.MessageUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.bean.BeanUtils;
import com.isstech.framework.manager.AsyncManager;
import com.isstech.framework.manager.factory.AsyncFactory;
import com.isstech.framework.web.service.TokenService;
import com.isstech.isstechadmin.domain.BaseCommunityChildUser;
import com.isstech.isstechadmin.service.IBaseCommunityChildUserService;
import com.isstech.isstechadmin.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 手机端对外接口Controller
 *
 * @author dyf
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/appSystem")
public class AppLoginController extends BaseController {

    @Autowired
    private IBaseCommunityChildUserService baseCommunityChildUserService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody BaseCommunityChildUser baseCommunityChildUser) {
        BaseCommunityChildUser baseCommunityChildUser1 = new BaseCommunityChildUser();
        baseCommunityChildUser1.setPhoneNumber(baseCommunityChildUser.getPhoneNumber());
        List<BaseCommunityChildUser> baseCommunityChildUsers = baseCommunityChildUserService.selectBaseCommunityChildUserList(baseCommunityChildUser1);
        if (baseCommunityChildUsers != null && baseCommunityChildUsers.size() > 0) {
            return AjaxResult.error("手机号:" + baseCommunityChildUser.getPhoneNumber() + "已注册！");
        }
        return toAjax(baseCommunityChildUserService.insertBaseCommunityChildUser(baseCommunityChildUser));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = appLogin(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, "token");
        return ajax;
    }

    /**
     * app登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String appLogin(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户验证
        BaseCommunityChildUser baseCommunityChildUser = new BaseCommunityChildUser();
        baseCommunityChildUser.setPhoneNumber(username);
        baseCommunityChildUser.setPwd(password);
        List<BaseCommunityChildUser> baseCommunityChildUsers = baseCommunityChildUserService.selectBaseCommunityChildUserList(baseCommunityChildUser);
        if (baseCommunityChildUsers == null || baseCommunityChildUsers.size() <= 0) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userService.selectUserById("1"));
        SysAppUser appUser = new SysAppUser();
        BeanUtils.copyBeanProp(appUser, baseCommunityChildUsers.get(0));
        loginUser.setAppUser(appUser);
        // 生成token
        return tokenService.createToken(loginUser);
    }

    public SysAppUser getAppUser() {
        return SecurityUtils.getLoginUser().getAppUser();
    }
}


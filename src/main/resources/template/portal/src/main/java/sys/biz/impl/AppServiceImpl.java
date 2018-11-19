package ${packageName}.${subPackageName}.portal.sys.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.SysAppFeedback;
import com.towcent.base.common.model.SysAppVersion;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.app.client.sys.dto.SysFrontAccount;
import ${packageName}.${subPackageName}.app.client.sys.service.SysCommonApi;
import ${packageName}.${subPackageName}.common.Constant;
import ${packageName}.${subPackageName}.portal.common.utils.UserUtils;
import ${packageName}.${subPackageName}.portal.sys.biz.AppService;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppFeedbackIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppTestFlagIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppValidateVersionIn;
import ${packageName}.${subPackageName}.portal.sys.vo.output.AppTestFlagOut;
import ${packageName}.${subPackageName}.portal.sys.vo.output.AppValidateVersionOut;

/**
 * AppServiceImpl
 *
 * @author
 * @version 0.0.2
 */
@Service
public class AppServiceImpl extends BasePortalService implements AppService {
    
	@Resource
    private SysCommonApi sysCommonApi;
	
	@Value("${r'${environment.test.flag}'}")
    private String testFlag;
	
    @Override
    public ResultVo validateVersion(AppValidateVersionIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            AppValidateVersionOut outParam = new AppValidateVersionOut();

            SysAppVersion appVersion = new SysAppVersion();
            appVersion.setSysType(paramIn.getSysType());
            appVersion.setCurrentVersion(paramIn.getCurrentVersion());
            appVersion.setAppType(paramIn.getAppType());

            SysAppVersion sysAppVersion = sysCommonApi.validateVersion(appVersion);

            if (sysAppVersion != null) {
                outParam.setIsUpgrade(Integer.parseInt(Constant.YES));
                outParam.setEnforceFlag(Integer.parseInt(sysAppVersion.getEnforceFlag()));
                outParam.setVersionName(sysAppVersion.getVersionName());
                outParam.setUrl(sysAppVersion.getUrl());
                outParam.setUpdateContent(sysAppVersion.getUpdateContent());
                outParam.setVersion(sysAppVersion.getCurrentVersion());
            }
            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "检测更新失败.");
            logger.error("检测更新失败.", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo feedback(AppFeedbackIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            SysAppFeedback feedBack = new SysAppFeedback();
            feedBack.setAccount(account.getAccount());
            feedBack.setAppType(paramIn.getAppType());
            feedBack.setAppVersion(paramIn.getAppVersion());
            feedBack.setContact(paramIn.getContactInfo());
            feedBack.setContent(paramIn.getContent());
            feedBack.setCreateDate(new Date());
            feedBack.setFeedbackType(paramIn.getType());
            feedBack.setPhoneModel(paramIn.getPhoneModel());
            feedBack.setPicUrl(paramIn.getImages());
            feedBack.setSysType(paramIn.getSysType());
            feedBack.setSysVersion(paramIn.getOsVersion());
            sysCommonApi.addFeedback(feedBack);
            resultVo.setErrorMessage("添加成功");
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "添加意见反馈失败");
            logger.error("添加意见反馈失败", e);
        }
        return resultVo;
    }
    
    @Override
	public ResultVo testFlag(AppTestFlagIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			AppTestFlagOut outParam = new AppTestFlagOut();
			boolean flag = StringUtils.endsWithIgnoreCase(testFlag, "true");
			outParam.setIsTestEnv(flag ? "1" : "0");
			resultVo.setData(outParam);
		} catch (Exception e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

}
package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppFeedbackIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppTestFlagIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AppValidateVersionIn;

/**
 * AppService
 * @author 
 * @version 0.0.2
 */
public interface AppService {

	ResultVo validateVersion(AppValidateVersionIn paramIn);

	ResultVo feedback(AppFeedbackIn paramIn);

	ResultVo testFlag(AppTestFlagIn paramIn);
}
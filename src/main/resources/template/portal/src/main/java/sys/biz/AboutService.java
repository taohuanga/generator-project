package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AboutIn;

/**
 * AboutService
 * @author licheng
 * @version 0.0.1
 */
public interface AboutService {

	ResultVo about(AboutIn paramIn);


}
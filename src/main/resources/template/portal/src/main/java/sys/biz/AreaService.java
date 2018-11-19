package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AreaDescIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.AreaListIn;

/**
 * AreaService
 * @author huangtao
 * @version 0.0.1
 */
public interface AreaService {

	ResultVo list(AreaListIn paramIn);

	ResultVo desc(AreaDescIn paramIn);
}
package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.CarouselListIn;

/**
 * CarouselService
 * @author licheng
 * @version 0.0.1
 */
public interface CarouselService {

	ResultVo list(CarouselListIn paramIn);
}
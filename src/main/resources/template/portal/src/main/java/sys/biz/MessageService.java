package ${packageName}.${subPackageName}.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MessageListIn;
import ${packageName}.${subPackageName}.portal.sys.vo.input.MessageReadIn;

/**
 * MessageService
 * @author huangtao
 * @version 0.0.1
 */
public interface MessageService {

	ResultVo list(MessageListIn paramIn);

	ResultVo read(MessageReadIn paramIn);
}
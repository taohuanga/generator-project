package ${packageName}.${subPackageName}.portal.sys.vo.input;



import ${packageName}.${subPackageName}.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 1.1.0 数据字典接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DictListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	private String key;		// 数据字典key
	
}
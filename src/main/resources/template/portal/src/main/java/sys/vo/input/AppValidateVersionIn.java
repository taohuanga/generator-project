package ${packageName}.${subPackageName}.portal.sys.vo.input;

import javax.validation.constraints.NotNull;

import ${packageName}.${subPackageName}.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.3.2 验证App版本信息
 * @author 
 * @version 0.0.2
 */
@Data
public class AppValidateVersionIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "currentVersion不能为空.")
	private Integer currentVersion;		// 当前App版本号
	
}
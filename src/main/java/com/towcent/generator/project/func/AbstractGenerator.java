package com.towcent.generator.project.func;

import static com.towcent.generator.project.utils.FreeMarkers.renderString;
import static com.towcent.generator.project.utils.Global.getFileContent;
import static com.towcent.generator.project.utils.Global.joinPath;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.towcent.generator.project.constant.Constants;
import com.towcent.generator.project.utils.FileUtils;

/**
 * 生成工程接口
 * @author huangtao
 *
 */
public abstract class AbstractGenerator {
	
	public void exec(Map<String, Object> model) {
		
		FileUtils.deleteDirectory(this.getProjectPath());
		
		this.createDirectory();
		
		this.copyDirectoryCover();
		
		this.copyReplaceVarToFile(model);
		
		System.out.println(this.getClass().getSimpleName() + "生成完毕!");
	}
	
	/**
	 * 获取生成dest工程根路径
	 * @return
	 */
	public abstract String getProjectPath();
	
	/**
	 * 模板目录路径
	 * @return
	 */
	public abstract String getTemplatePath();
	
	/**
	 * 获取包路径
	 * @return
	 */
	public String getPackagePath() {
		return joinPath(joinPath(StringUtils.split(Constants.packageName, ".")), Constants.subPackageName);
	}
	
	/**
	 * 获取工程代码路径(相对路径)
	 * @return
	 */
	public String getProjectCodePath() {
		return getProjectCodePath(false);
	}
	
	/**
	 * 获取工程代码路径
	 * @param absolute true:绝对  false:相对
	 * @return
	 */
	public String getProjectCodePath(boolean absolute) {
		if (absolute) {
			return joinPath(getProjectPath(), "src", "main", "java", getPackagePath());
		} else {
			return joinPath("src", "main", "java", getPackagePath());
		}
	}
	
	/**
	 * 获取工程测试代码路径(相对路径)
	 * @return
	 */
	public String getProjectTestCodePath() {
		return getProjectTestCodePath(false);
	}
	
	/**
	 * 获取工程测试代码路径
	 * @param absolute true:绝对  false:相对
	 * @return
	 */
	public String getProjectTestCodePath(boolean absolute) {
		if (absolute) {
			return joinPath(getProjectPath(), "src", "test", "java", getPackagePath());
		} else {
			return joinPath("src", "test", "java", getPackagePath());
		}
	}
	
	/**
	 * 创建目录
	 */
	public abstract void createDirectory();
	
	/**
	 * 拷贝目录(不替换变量)
	 */
	public abstract void copyDirectoryCover();
	
	/**
	 * FreeMarkers进行变量替换
	 */
	public abstract void copyReplaceVarToFile(Map<String, Object> model);
	
	/**
	 * <pre>
	 * 直接copy文件到指定目录
	 * srcFile相对工程模板的路径
	 * destFile相对工程的路径
	 * </pre>
	 * @param srcFile
	 * @param destFile
	 */
	public void copyFileCover(String srcFile, String destFile) {
		FileUtils.copyFileCover(joinPath(getTemplatePath(), srcFile), joinPath(getProjectPath(), destFile), true);
	}
	
	public void copyDirectoryCover(String srcFile, String destFile) {
		FileUtils.copyDirectoryCover(joinPath(getTemplatePath(), srcFile), joinPath(getProjectPath(), destFile), true);
	}
	
	/**
	 * <pre>
	 * 通过模板文件生成文件到指定目录
	 * srcFile只需要相对(相对工程)路径（template）
	 * destFile相对路径
	 * </pre>
	 * @param srcFile 模板文件
	 * @param destFile 生成的文件
	 * @param model 参数集合
	 */
	public void writeToFile(String srcFile, String destFile, Map<String, Object> model) {
		FileUtils.writeToFile(joinPath(getProjectPath(), destFile), renderString(getFileContent(joinPath(getTemplatePath(), srcFile)), model), 0);
	}
	
}

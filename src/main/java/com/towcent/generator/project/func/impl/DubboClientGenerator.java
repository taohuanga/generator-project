package com.towcent.generator.project.func.impl;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

import static com.towcent.generator.project.utils.Global.*;

import java.util.Map;

import static com.towcent.generator.project.constant.Constants.*;

/**
 * Dubbo Client工程
 * @author huangtao
 *
 */
public class DubboClientGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-dubbo", projectName + "-dubbo-client");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "dubbo", "dubbo-client");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
//		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "app", "client", "sys", "dto"));
	}

	public void copyDirectoryCover() {
		
	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
		
		writeToFile(joinPath("src", "SysUser.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "dto", "SysUser.java"), model);
		
		writeToFile(joinPath("src", "SysFrontAccount.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "dto", "SysFrontAccount.java"), model);
		writeToFile(joinPath("src", "Session.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "dto", "Session.java"), model);
		writeToFile(joinPath("src", "SysParamQueryVo.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "dto", "SysParamQueryVo.java"), model);
		
		writeToFile(joinPath("src", "SessionApi.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "service", "SessionApi.java"), model);
		writeToFile(joinPath("src", "SysCommonApi.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "service", "SysCommonApi.java"), model);
		writeToFile(joinPath("src", "SysFrontAccountApi.java"), joinPath(getProjectCodePath(), "app", "client", "sys", "service", "SysFrontAccountApi.java"), model);
		
	}

}

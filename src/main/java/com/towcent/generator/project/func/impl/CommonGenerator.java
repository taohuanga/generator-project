package com.towcent.generator.project.func.impl;

import static com.towcent.generator.project.constant.Constants.projectName;
import static com.towcent.generator.project.constant.Constants.projectPath;
import static com.towcent.generator.project.utils.Global.joinPath;

import java.util.Map;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

/**
 * common工程
 * @author huangtao
 *
 */
public class CommonGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-common");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "common");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
//		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "common"));
//		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "main", "resources"));
//		FileUtils.createDirectory(joinPath(getProjectTestCodePath(true), "common"));
//		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "test", "resources"));
	}

	public void copyDirectoryCover() {
		// TODO Auto-generated method stub

	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
		
		writeToFile(joinPath("src", "CacheKeyUtils.java"), joinPath(getProjectCodePath(), "common", "CacheKeyUtils.java"), model);
		writeToFile(joinPath("src", "ConfigUtil.java"), joinPath(getProjectCodePath(), "common", "ConfigUtil.java"), model);
		writeToFile(joinPath("src", "Constant.java"), joinPath(getProjectCodePath(), "common", "Constant.java"), model);
		
	}

}

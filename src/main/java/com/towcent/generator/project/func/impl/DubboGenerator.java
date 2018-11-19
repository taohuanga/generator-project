package com.towcent.generator.project.func.impl;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

import static com.towcent.generator.project.utils.Global.*;

import java.util.Map;

import static com.towcent.generator.project.constant.Constants.*;

/**
 * Dubbo工程
 * @author huangtao
 *
 */
public class DubboGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-dubbo");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "dubbo");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());

	}

	public void copyDirectoryCover() {
		// TODO Auto-generated method stub

	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);

	}

}

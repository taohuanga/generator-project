package com.towcent.generator.project.func.impl;

import static com.towcent.generator.project.constant.Constants.projectPath;
import static com.towcent.generator.project.constant.Constants.projectName;
import static com.towcent.generator.project.utils.Global.joinPath;

import java.util.Map;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

/**
 * 模板工程根目录
 * @author huangtao
 *
 */
public class RootGenerator extends AbstractGenerator {
	
	public String getProjectPath() {
		return joinPath(projectPath, projectName);
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
	}

	public void copyDirectoryCover() {
		copyFileCover(joinPath("compile.bat"), joinPath("compile.bat"));
		copyFileCover(joinPath("deploy.bat"), joinPath("deploy.bat"));
		copyFileCover(joinPath("部署指导文档.docx"), joinPath("部署指导文档.docx"));
	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
	}

}

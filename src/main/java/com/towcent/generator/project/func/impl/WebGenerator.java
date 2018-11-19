package com.towcent.generator.project.func.impl;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

import static com.towcent.generator.project.utils.Global.*;

import java.io.File;
import java.util.Map;

import static com.towcent.generator.project.constant.Constants.*;

/**
 * web 工程
 * @author huangtao
 *
 */
public class WebGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-web");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "web");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
		
		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "main", "resources", "mapper"));
	}

	public void copyDirectoryCover() {
		copyDirectoryCover(joinPath("src", "main", "webapp") + File.separator, joinPath("src", "main", "webapp") + File.separator);
		// copyDirectoryCover(joinPath("src", "main", "resources", "templates", "modules") + File.separator, joinPath("src", "main", "resources", "templates", "modules") + File.separator);
		copyDirectoryCover(joinPath("db") + File.separator, joinPath("db") + File.separator);
	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
		
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-conf.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-conf.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-context.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-context.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-context-shiro.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-context-shiro.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-dubbo-client.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-dubbo-client.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-mvc.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-mvc.xml"), model);
		
		// 配置文件
		writeToFile(joinPath("src", "main", "resources", "application.properties"), joinPath("src", "main", "resources", "application.properties"), model);
		writeToFile(joinPath("src", "main", "resources", "errors.properties"), joinPath("src", "main", "resources", "errors.properties"), model);
		writeToFile(joinPath("src", "main", "resources", "log4j.properties"), joinPath("src", "main", "resources", "log4j.properties"), model);
		writeToFile(joinPath("src", "main", "resources", "mybatis-refresh.properties"), joinPath("src", "main", "resources", "mybatis-refresh.properties"), model);
		
		// 生成代码的模板
		// TODO
		
		// Test用例
		writeToFile(joinPath("src", "test", "java", "BaseServiceTest.java"), joinPath(getProjectTestCodePath(), "web", "BaseServiceTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "SysCommonTest.java"), joinPath(getProjectTestCodePath(), "web", "common", "SysCommonTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "SmsTest.java"), joinPath(getProjectTestCodePath(), "web", "common", "SmsTest.java"), model);
		
		writeToFile(joinPath("src", "test", "resources", "spring-context-test.xml"), joinPath("src", "test", "resources", "spring-context-test.xml"), model);
		
	}

}

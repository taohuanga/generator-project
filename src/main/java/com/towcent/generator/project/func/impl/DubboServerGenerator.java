package com.towcent.generator.project.func.impl;

import static com.towcent.generator.project.constant.Constants.projectName;
import static com.towcent.generator.project.constant.Constants.projectPath;
import static com.towcent.generator.project.utils.FileUtils.deleteFile;
import static com.towcent.generator.project.utils.Global.getFileContent;
import static com.towcent.generator.project.utils.Global.joinPath;

import java.io.File;
import java.util.Map;

import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

/**
 * Dubbo Server 工程
 * @author huangtao
 *
 */
public class DubboServerGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-dubbo", projectName + "-dubbo-server");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "dubbo", "dubbo-server");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
//		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "app", "client", "sys", "dto"));
	}

	public void copyDirectoryCover() {
		copyDirectoryCover(joinPath("bin") + File.separator, joinPath("bin") + File.separator);
	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
		// mybatis-generator
		// writeToFile(joinPath("generatorConfig.xml"), joinPath("generatorConfig.xml"), model);
		
		writeToFile(joinPath("src", "main", "java", "Main.java"), joinPath(getProjectCodePath(), "app", "server", "Main.java"), model);
		writeToFile(joinPath("src", "main", "java", "job", "DemoJobHandler.java"), joinPath(getProjectCodePath(), "app", "server", "job", "DemoJobHandler.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "CodeMain.java"), joinPath(getProjectCodePath(), "app", "server", "common", "gen", "CodeMain.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "Database2WordMain.java"), joinPath(getProjectCodePath(), "app", "server", "common", "gen", "Database2WordMain.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "sys", "dao", "SysFrontAccountMapper.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "dao", "SysFrontAccountMapper.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "dao", "SysUserMapper.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "dao", "SysUserMapper.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "service", "SysFrontAccountService.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "service", "SysFrontAccountService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "service", "SysUserService.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "service", "SysUserService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "service", "impl", "SysFrontAccountServiceImpl.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "service", "impl", "SysFrontAccountServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "service", "impl", "SysUserServiceImpl.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "service", "impl", "SysUserServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "manager", "SessionApiImpl.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "manager", "SessionApiImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "manager", "SysCommonApiImpl.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "manager", "SysCommonApiImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "manager", "SysFrontAccountApiImpl.java"), joinPath(getProjectCodePath(), "app", "server", "sys", "manager", "SysFrontAccountApiImpl.java"), model);
		
		writeToFile(joinPath("src", "main", "resources", "mapper", "SysUserMapper.xml"), joinPath("src", "main", "resources", "mapper", "sys", "SysUserMapper.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "mapper", "SysFrontAccountMapper.xml"), joinPath("src", "main", "resources", "mapper", "sys", "SysFrontAccountMapper.xml"), model);
		
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring-conf.xml"), joinPath("src", "main", "resources", "META-INF", "spring-conf.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring-context.xml"), joinPath("src", "main", "resources", "META-INF", "spring-context.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring-dubbo-client.xml"), joinPath("src", "main", "resources", "META-INF", "spring-dubbo-client.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring-dubbo-server.xml"), joinPath("src", "main", "resources", "META-INF", "spring-dubbo-server.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-manager.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-manager.xml"), model);
		
		// 属性配置文件
		writeToFile(joinPath("src", "main", "resources", "application.properties"), joinPath("src", "main", "resources", "application.properties"), model);
		writeToFile(joinPath("src", "main", "resources", "log4j.properties"), joinPath("src", "main", "resources", "log4j.properties"), model);
		
		// dubbo package
		// unix
		writeToFile(joinPath("src", "main", "assembly", "unix", "assembly.xml"), joinPath("src", "main", "assembly", "unix", "assembly.xml"), model);
		writeToFile(joinPath("src", "main", "assembly", "unix", "wrapper.xml"), joinPath("src", "main", "assembly", "unix", "wrapper.xml"), model);
		writeToFile(joinPath("src", "main", "config", "unix", "wrapper.conf"), joinPath("src", "main", "config", "unix", "wrapper.conf"), model);
		// windows
		writeToFile(joinPath("src", "main", "assembly", "windows", "assembly.xml"), joinPath("src", "main", "assembly", "windows", "assembly.xml"), model);
		writeToFile(joinPath("src", "main", "config", "windows", "wrapper.conf"), joinPath("src", "main", "config", "windows", "wrapper.conf"), model);
		
		// Test用例
		writeToFile(joinPath("src", "test", "java", "BaseServiceTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "BaseServiceTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "coding", "CodingRuleTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "coding", "CodingRuleTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "sms", "SmsTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "sms", "SmsTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "pay", "PayAccountTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "pay", "PayAccountTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "sys", "service", "SysUserServiceTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "sys", "service", "SysUserServiceTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "sys", "dao", "SysUserTest.java"), joinPath(getProjectTestCodePath(), "app", "server", "sys", "dao", "SysUserTest.java"), model);
		
		writeToFile(joinPath("src", "test", "resources", "spring-service-test.xml"), joinPath("src", "test", "resources", "spring-service-test.xml"), model);
		writeToFile(joinPath("src", "test", "resources", "log4j.properties"), joinPath("src", "test", "resources", "log4j.properties"), model);
		
		// wrapper Start
		deleteFile(joinPath(getProjectPath(), "bin", "StartWrapper_tmp"));
		deleteFile(joinPath(getProjectPath(), "bin", "StartWrapper"));
		FileUtils.writeToFile(joinPath(getProjectPath(), "bin", "StartWrapper"), getFileContent(joinPath(getTemplatePath(), "bin", "StartWrapper_tmp")), 0);
		writeToFile(joinPath("bin", "StartWrapper"), joinPath("bin", "StartWrapper"), model);
		
	}

}

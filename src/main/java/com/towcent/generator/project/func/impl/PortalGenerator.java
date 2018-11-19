package com.towcent.generator.project.func.impl;

import static com.towcent.generator.project.constant.Constants.projectName;
import static com.towcent.generator.project.constant.Constants.projectPath;
import static com.towcent.generator.project.utils.Global.joinPath;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.Lists;
import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.Global;

/**
 * Portal工程
 * @author huangtao
 *
 */
public class PortalGenerator extends AbstractGenerator {

	public String getProjectPath() {
		return joinPath(projectPath, projectName, projectName + "-portal");
	}

	public String getTemplatePath() {
		return joinPath(Global.getProjectPath(), "src", "main", "resources", "template", "portal");
	}

	public void createDirectory() {
		FileUtils.createDirectory(getProjectPath());
		
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "common", "gen"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "common", "utils"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "common", "vo"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "common", "web"));
		
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "sys", "biz", "impl"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "sys", "vo", "input"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "sys", "vo", "output"));
		FileUtils.createDirectory(joinPath(getProjectCodePath(true), "portal", "sys", "web"));
		
		FileUtils.createDirectory(joinPath(getProjectTestCodePath(true), "portal", "client", "sys"));
		
		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "main", "resources", "META-INF", "spring"));
		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "main", "resources", "template"));
		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "test", "resources"));
		
		FileUtils.createDirectory(joinPath(getProjectPath(), "src", "main", "webapp", "WEB-INF"));
	}

	public void copyDirectoryCover() {
		copyDirectoryCover(joinPath("src", "main", "resources", "template") + File.separator, joinPath("src", "main", "resources", "template") + File.separator);
	}

	public void copyReplaceVarToFile(Map<String, Object> model) {
		writeToFile(joinPath("tpom.xml"), joinPath("pom.xml"), model);
		FileUtils.copyFileCover(joinPath(getTemplatePath(), "InterfaceDoc.xlsx"), joinPath(getProjectPath(), "InterfaceDoc.xlsx"), true);
		FileUtils.copyFileCover(joinPath(getTemplatePath(), "移动端接口文档.xlsx"), joinPath(getProjectPath(), "移动端接口文档.xlsx"), true);

		writeToFile(joinPath("src", "main", "java", "common", "gen", "Main.java"), joinPath(getProjectCodePath(), "portal", "common", "gen", "Main.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "common", "utils", "SignUtils.java"), joinPath(getProjectCodePath(), "portal", "common", "utils", "SignUtils.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "utils", "UserUtils.java"), joinPath(getProjectCodePath(), "portal", "common", "utils", "UserUtils.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "vo", "BaseParam.java"), joinPath(getProjectCodePath(), "portal", "common", "vo", "BaseParam.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "vo", "PaginationVo.java"), joinPath(getProjectCodePath(), "portal", "common", "vo", "PaginationVo.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "common", "web", "BaseImageController.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "BaseImageController.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "web", "BodyHttpServletRequestWrapper.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "BodyHttpServletRequestWrapper.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "web", "CommonParamInterceptor.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "CommonParamInterceptor.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "web", "ControllerMethodInterceptor.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "ControllerMethodInterceptor.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "web", "HttpServletRequestReplacedFilter.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "HttpServletRequestReplacedFilter.java"), model);
		writeToFile(joinPath("src", "main", "java", "common", "web", "LoginInterceptor.java"), joinPath(getProjectCodePath(), "portal", "common", "web", "LoginInterceptor.java"), model);

		// sys模块
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "AboutService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "AboutService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "AboutServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "AboutServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "AppService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "AppService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "AppServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "AppServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "AreaService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "AreaService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "AreaServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "AreaServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "CarouselService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "CarouselService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "CarouselServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "CarouselServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "DictService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "DictService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "DictServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "DictServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "MemberAccountService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "MemberAccountService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "MemberAccountServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "MemberAccountServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "MessageService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "MessageService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "MessageServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "MessageServiceImpl.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "SmsService.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "SmsService.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "biz", "impl", "SmsServiceImpl.java"), joinPath(getProjectCodePath(), "portal", "sys", "biz", "impl", "SmsServiceImpl.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AboutIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AboutIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AppFeedbackIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AppFeedbackIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AppTestFlagIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AppTestFlagIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AppValidateVersionIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AppValidateVersionIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AreaDescIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AreaDescIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "AreaListIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "AreaListIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "CarouselListIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "CarouselListIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "DictListIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "DictListIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "ImageVo.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "ImageVo.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "LoginParamVo.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "LoginParamVo.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountBaseInfoIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountBaseInfoIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountFastLoginIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountFastLoginIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountLoginIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountLoginIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountPhoneExistIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountPhoneExistIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountRegisterIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountRegisterIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountResetIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountResetIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountSaveBaseInfoIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountSaveBaseInfoIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountUpdatePasswdIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountUpdatePasswdIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountUpdatePhoneIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountUpdatePhoneIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MemberAccountUpdateWithdrawPassIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MemberAccountUpdateWithdrawPassIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MessageListIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MessageListIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "MessageReadIn.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "MessageReadIn.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "SmsParamVo.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "SmsParamVo.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "input", "SmsSendParamVo.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "input", "SmsSendParamVo.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "AboutOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "AboutOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "AppTestFlagOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "AppTestFlagOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "AppValidateVersionOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "AppValidateVersionOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "AreaDescOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "AreaDescOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "AreaListOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "AreaListOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "CarouselListOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "CarouselListOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "DictListOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "DictListOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "ImageOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "ImageOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "MemberAccountBaseInfoOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "MemberAccountBaseInfoOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "MemberAccountLoginOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "MemberAccountLoginOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "MemberAccountPhoneExistOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "MemberAccountPhoneExistOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "MemberAccountQueryCarrierStatusOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "MemberAccountQueryCarrierStatusOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "MessageListOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "MessageListOut.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "vo", "output", "WxKfMessageOut.java"), joinPath(getProjectCodePath(), "portal", "sys", "vo", "output", "WxKfMessageOut.java"), model);
		
		writeToFile(joinPath("src", "main", "java", "sys", "web", "AboutController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "AboutController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "AppController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "AppController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "AreaController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "AreaController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "CarouselController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "CarouselController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "DictController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "DictController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "MemberAccountController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "MemberAccountController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "MessageController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "MessageController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "ImageController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "ImageController.java"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "ImageConfig.txt"), joinPath(getProjectCodePath(), "portal", "sys", "web", "ImageConfig.txt"), model);
		writeToFile(joinPath("src", "main", "java", "sys", "web", "SmsController.java"), joinPath(getProjectCodePath(), "portal", "sys", "web", "SmsController.java"), model);
		
		// 属性配置文件
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-conf.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-conf.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-context.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-context.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-dubbo-client.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-dubbo-client.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "META-INF", "spring", "spring-mvc.xml"), joinPath("src", "main", "resources", "META-INF", "spring", "spring-mvc.xml"), model);
		writeToFile(joinPath("src", "main", "resources", "application.properties"), joinPath("src", "main", "resources", "application.properties"), model);
		writeToFile(joinPath("src", "main", "resources", "log4j.properties"), joinPath("src", "main", "resources", "log4j.properties"), model);
		
		// 测试用例
		writeToFile(joinPath("src", "test", "java", "BaseTest.java"), joinPath(getProjectTestCodePath(), "portal", "BaseTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "DubboTest.java"), joinPath(getProjectTestCodePath(), "portal", "DubboTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "SignUtilsTest.java"), joinPath(getProjectTestCodePath(), "portal", "SignUtilsTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "BaseAppTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "BaseAppTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "BaseClientTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "BaseClientTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "readme.txt"), joinPath(getProjectTestCodePath(), "portal", "client", "readme.txt"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "ImageControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "ImageControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "SmsControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "SmsControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "AppControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "AppControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "AboutControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "AboutControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "CarouselControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "CarouselControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "DictControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "DictControllerTest.java"), model);
		writeToFile(joinPath("src", "test", "java", "client", "sys", "MessageControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "MessageControllerTest.java"), model);
		
		writeToFile(joinPath("src", "test", "java", "client", "sys", "SysFrontAccountControllerTest.java"), joinPath(getProjectTestCodePath(), "portal", "client", "sys", "SysFrontAccountControllerTest.java"), model);
		
		writeToFile(joinPath("src", "test", "resources", "env"), joinPath("src", "test", "resources", "env"), model);
		writeToFile(joinPath("src", "test", "resources", "spring-context-test.xml"), joinPath("src", "test", "resources", "spring-context-test.xml"), model);
		
		writeToFile(joinPath("src", "main", "webapp", "WEB-INF", "web.xml"), joinPath("src", "main", "webapp", "WEB-INF", "web.xml"), model);
		writeToFile(joinPath("src", "main", "webapp", "index.jsp"), joinPath("src", "main", "webapp", "index.jsp"), model);
		writeToFile(joinPath("src", "main", "webapp", "MP_verify_U87DMlloiolVpP8w.txt"), joinPath("src", "main", "webapp", "MP_verify_U87DMlloiolVpP8w.txt"), model);
		
	}
	
	/**
	 * joinPath("src", "main", "java", strs)
	 * @param strs
	 * @return
	 */
	private static String joinSrcMainJava(String... strs) {
		ArrayList<String> list = Lists.newArrayList("src", "main", "java");
		for (String str : strs) {
			list.add(str);
		}
		String[] objs = new String[list.size()];
		list.toArray(objs);
		return joinPath(objs);
	}
	
	private static String joinSrcTestJava(String... strs) {
		ArrayList<String> list = Lists.newArrayList("src", "test", "java");
		for (String str : strs) {
			list.add(str);
		}
		String[] objs = new String[list.size()];
		list.toArray(objs);
		return joinPath(objs);
	}
	
	public static void main(String[] args) {
		System.out.println(joinSrcMainJava("common", "utils"));
	}
}

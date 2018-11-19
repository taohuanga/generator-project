package com.towcent.generator.project;

import java.util.HashMap;
import java.util.Map;

import com.towcent.generator.project.constant.Constants;
import com.towcent.generator.project.func.AbstractGenerator;
import com.towcent.generator.project.func.impl.CommonGenerator;
import com.towcent.generator.project.func.impl.DubboClientGenerator;
import com.towcent.generator.project.func.impl.DubboGenerator;
import com.towcent.generator.project.func.impl.DubboServerGenerator;
import com.towcent.generator.project.func.impl.PortalGenerator;
import com.towcent.generator.project.func.impl.RootGenerator;
import com.towcent.generator.project.func.impl.WebGenerator;

/**
 * 生成业务工程代码
 * @author huangtao
 *
 */
public class Main {

	public static void main(String[] args) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("projectDesc", Constants.projectDesc);
		model.put("projectName", Constants.projectName);
		model.put("packageName", Constants.packageName);
		model.put("subPackageName", Constants.subPackageName);
		model.put("redisPrefix", Constants.redisPrefix);
		
		// db
		model.put("jdbcType", Constants.jdbcType);
		model.put("jdbcDriver", Constants.jdbcDriver);
		model.put("jdbcUrl", Constants.jdbcUrl);
		model.put("jdbcUsername", Constants.jdbcUsername);
		model.put("jdbcPassword", Constants.jdbcPassword);
		
		// dubbo端口
		model.put("dubboPort", Constants.dubboPort);
		model.put("portalPort", Constants.portalPort);
		model.put("webPort", Constants.webPort);
		
		// 根工程
		AbstractGenerator root = new RootGenerator();
		root.exec(model);

		// Common工程
		AbstractGenerator common = new CommonGenerator();
		common.exec(model);
		
		// Dubbo工程
		AbstractGenerator dubbo = new DubboGenerator();
		dubbo.exec(model);
		
		// Dubbo Client工程
		AbstractGenerator dubboClient = new DubboClientGenerator();
		dubboClient.exec(model);
		
		// Dubbo Server工程
		AbstractGenerator dubboServer = new DubboServerGenerator();
		dubboServer.exec(model);
		
		// Portal工程
		AbstractGenerator portal = new PortalGenerator();
		portal.exec(model);
				
		// Web工程
		AbstractGenerator web = new WebGenerator();
		web.exec(model);
		
		System.out.println("生成模板工程完毕!");
	}

}

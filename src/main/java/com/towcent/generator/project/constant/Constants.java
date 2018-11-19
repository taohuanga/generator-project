package com.towcent.generator.project.constant;

/**
 * 常量类
 * @author huangtao
 *
 */
public class Constants {
	
	/** 项目描述 */
	public static final String projectDesc = "出行宝管理平台";
	/** 工程存放的目录地址 */
	public static final String projectPath = "D:/Project";
	/** 工程名称 */
	public static final String projectName = "md-trip";
	/** 工程包名 */
	public static final String packageName = "com.towcent.md";
	/** 工程子包 */
	public static final String subPackageName = "trip";
	/** 缓存前缀 */
	public static final String redisPrefix = "mt";
	
	/** 数据库配置 */
	public static final String jdbcType = "mysql";
	public static final String jdbcDriver = "com.mysql.jdbc.Driver";
	public static final String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/dfc_chip?useUnicode=true&characterEncoding=utf-8";
	public static final String jdbcUsername = "root";
	public static final String jdbcPassword = "123456";
	
//	public static final String jdbcType = "oracle";
//	public static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	public static final String jdbcUrl = "jdbc:oracle:thin:@180.169.105.245:1521:zzbank";
//	public static final String jdbcUsername = "bank";
//	public static final String jdbcPassword = "bank";
	
	/** dubbo端口 */
	public static final String dubboPort = "28873";
	
	/** portal端口 */
	public static final String portalPort = "8088";
	/** web端口 */
	public static final String webPort = "8089";
}

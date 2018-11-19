package com.towcent.generator.db.code.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.generator.db.code.gen.dao.GenDataBaseDictDao;
import com.towcent.generator.db.code.gen.entity.GenTable;
import com.towcent.generator.db.code.gen.entity.GenTableColumn;
import com.towcent.generator.db.code.gen.entity.GenTemplate;
import com.towcent.generator.project.utils.FileUtils;
import com.towcent.generator.project.utils.FreeMarkers;
import com.towcent.generator.project.utils.Global;

/**
 * 代码生成工具类
 * @version 2013-11-16
 */
public class GenUtils {

	private static Logger logger = LoggerFactory.getLogger(GenUtils.class);

	/**
	 * 初始化列属性字段
	 * @param genTable
	 */
	public static void initColumnField(GenTable genTable){
		for (GenTableColumn column : genTable.getColumnList()){
			// 设置字段说明
			if (StringUtils.isBlank(column.getComments())){
				column.setComments(column.getName());
			}
			
			column.setSimpleJavaType(getJavaSimpleType(column.getJdbcType()));
			
			// 设置java类型
			column.setJdbcType(getJdbcType(column.getJdbcType()));
			
			// 设置java字段名
			column.setJavaField(StringUtils.toCamelCase(column.getName()));
			
			// 是否是主键
			column.setIsPk(genTable.getPkList().contains(column.getName())?"1":"0");
			
			// 设置特定类型和字段名
			
			column.setDxJavaField(StringUtils.toCapitalizeCamelCase(column.getName()));
			column.setUpperName(StringUtils.upperCase(column.getName()));
		}
	}
	
	private static String getJdbcType(String jdbcType) {
		if (StringUtils.endsWith(Global.getConfig("jdbc.type"), "mysql")) {
			if (StringUtils.startsWithIgnoreCase(jdbcType, "int")) {
				return "INTEGER";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "bigint")) {
				return "BIGINT";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "varchar")) {
				return "VARCHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "char")) {
				return "CHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "text")) {
				return "VARCHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "tinyint")) {
				return "TINYINT";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "smallint")) {
				return "SMALLINT";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "datetime")) {
				return "TIMESTAMP";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "date")) {
				return "DATE";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "timestamp")) {
				return "TIMESTAMP";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "decimal")) {
				return "DECIMAL";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "double")) {
				return "DOUBLE";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "float")) {
				return "FLOAT";
			}
		} else {
			// oracle
			if (StringUtils.startsWithIgnoreCase(jdbcType, "number(1)")) {
				return "BIT";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(2)")) {
				return "INTEGER";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(3)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(4)")) {
				return "INTEGER";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(5)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(6)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(7)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(8)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(9)")) {
				return "INTEGER";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(10)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(11)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(12)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(13)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(14)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(15)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(16)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(17)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(18)")) {
				return "BIGINT";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number")) {
				return "DECIMAL";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "varchar2")) {
				return "VARCHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "nvarchar2")) {
				return "VARCHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "char")) {
				return "CHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "nchar")) {
				return "CHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "long")) {
				return "LONGVARCHAR";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "datetime")) {
				return "TIMESTAMP";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "date")) {
				return "TIMESTAMP";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "timestamp")) {
				return "TIMESTAMP";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "blob")) {
				return "BLOB";
			} 
		}
		return "VARCHAR";
	}
	
	private static String getJavaSimpleType(String jdbcType) {
		if (StringUtils.endsWith(Global.getConfig("jdbc.type"), "mysql")) {
			if (StringUtils.startsWithIgnoreCase(jdbcType, "int")) {
				return "Integer";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "bigint")) {
				return "Long";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "varchar")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "char")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "text")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "tinyint")) {
				return "Byte";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "smallint")) {
				return "Short";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "datetime")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "date")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "timestamp")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "decimal")) {
				return "BigDecimal";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "double")) {
				return "Double";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "float")) {
				return "Double";
			}
		} else {
			// oracle
			if (StringUtils.startsWithIgnoreCase(jdbcType, "number(1)")) {
				return "Boolean";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(2)")) {
				return "Integer";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(3)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(4)")) {
				return "Integer";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(5)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(6)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(7)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(8)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(9)")) {
				return "Integer";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number(10)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(11)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(12)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(13)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(14)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(15)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(16)") || StringUtils.startsWithIgnoreCase(jdbcType, "number(17)")
					|| StringUtils.startsWithIgnoreCase(jdbcType, "number(18)")) {
				return "Long";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "number")) {
				return "BigDecimal";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "varchar2")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "nvarchar2")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "char")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "nchar")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "long")) {
				return "String";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "datetime")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "date")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "timestamp")) {
				return "Date";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "decimal")) {
				return "BigDecimal";
			} else if (StringUtils.startsWithIgnoreCase(jdbcType, "blob")) {
				return "Object";
			} 
		}
		return "String";
	}
	
	/**
	 * 获取模板路径
	 * @return
	 */
	public static String getTemplatePath(){
		try{
			File file = new DefaultResourceLoader().getResource("").getFile();
			if(file != null){
				return file.getAbsolutePath() + File.separator + StringUtils.replaceEach(GenUtils.class.getName(), 
						new String[]{"util."+GenUtils.class.getSimpleName(), "."}, new String[]{"template", File.separator});
			}			
		}catch(Exception e){
			logger.error("{}", e);
		}

		return "";
	}
	
	/**
	 * XML文件转换为对象
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fileToObject(String fileName, Class<?> clazz){
		try {
			String pathName = "/codeTpl/" + fileName;
			//	logger.debug("File to object: {}", pathName);
			Resource resource = new ClassPathResource(pathName); 
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();  
			while (true) {
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line).append("\r\n");
			}
			if (is != null) {
				is.close();
			}
			if (br != null) {
				br.close();
			}
			//	logger.debug("Read file content: {}", sb.toString());
			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			logger.warn("Error file convert: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 根据分类获取模板列表
	 * @param config
	 * @return
	 */
	public static List<GenTemplate> getTemplateList(String flag){
		List<GenTemplate> templateList = Lists.newArrayList();
		templateList.add((GenTemplate) fileToObject("entity.xml", GenTemplate.class));
		templateList.add((GenTemplate) fileToObject("dao.xml", GenTemplate.class));
		if("true".equals(flag)){
			templateList.add((GenTemplate) fileToObject("service.xml", GenTemplate.class));
			templateList.add((GenTemplate) fileToObject("serviceImpl.xml", GenTemplate.class));
		}
		if ("mysql".equals(Global.getConfig("jdbc.type"))) {
			templateList.add((GenTemplate) fileToObject("mapper.xml", GenTemplate.class));
			//根据配置判断是否生成扩展
			if("true".equals(flag)){
				templateList.add((GenTemplate) fileToObject("daoChild.xml", GenTemplate.class));
				templateList.add((GenTemplate) fileToObject("mapperChild.xml", GenTemplate.class));
			}
			templateList.add((GenTemplate) fileToObject("mapperTest.xml", GenTemplate.class));
		} else {
			templateList.add((GenTemplate) fileToObject("mapperToOracle.xml", GenTemplate.class));
			templateList.add((GenTemplate) fileToObject("mapperOracleTest.xml", GenTemplate.class));
		}
		
		return templateList;
	}
	
	/**
	 * 获取数据模型
	 * @param genScheme
	 * @return
	 */
	public static Map<String, Object> getDataModel(GenTable table){
		Map<String, Object> model = Maps.newHashMap();
		
		model.put("packageName", Global.getConfig("project.package"));
		model.put("moduleName", Global.getConfig("project.module"));
		model.put("className", StringUtils.toCamelCase(table.getName()));
		model.put("ClassName", StringUtils.toCapitalizeCamelCase(table.getName()));
		
		model.put("author", Global.getConfig("project.author"));
		model.put("versionName", Global.getConfig("project.version"));
		model.put("dateTime", DateUtils.formatDateTime(new Date()));
		
		model.put("dbType", Global.getConfig("jdbc.type"));

		model.put("table", table);
		
		return model;
	}
	
	/**
	 * 生成到文件
	 * @param tpl
	 * @param model
	 * @param isReplaceFile
	 * @return
	 */
	public static String generateToFile(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile){
		String projectPath = Global.getProjectPath();
		projectPath = StringUtils.substringBeforeLast(projectPath, "-");
		// 获取生成文件
		String fileName = projectPath + "-" + tpl.getProject() + File.separator
				+ StringUtils.replaceEach(FreeMarkers.renderString(tpl.getFilePath() + "/", model), 
						new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})
				+ FreeMarkers.renderString(tpl.getFileName(), model);
		logger.debug(" fileName === " + fileName);

		// 获取生成文件内容
		String content = FreeMarkers.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);
		logger.debug(" content === \r\n" + content);
		
		// 如果选择替换文件，则删除原文件
		if (isReplaceFile){
			FileUtils.deleteFile(fileName);
		}
		
		// 创建并写入文件
		if (FileUtils.createFile(fileName)){
			FileUtils.writeToFile(fileName, content, true);
			logger.debug(" file create === " + fileName);
			return "生成成功："+fileName+"<br/>";
		}else{
			logger.debug(" file extents === " + fileName);
			return "文件已存在："+fileName+"<br/>";
		}
	}
	
	public static void generateCode(GenDataBaseDictDao dao){
		StringBuilder result = new StringBuilder();
		List<GenTable> genTables = Lists.newArrayList();
		
		String[] tabs = StringUtils.split(Global.getConfig("project.tables"), ",");
		// 查询主表及字段列
		for (String table : tabs) {
			GenTable genTable = new GenTable();
			genTable.setName(table);
			List<GenTable> list = dao.findTableList(genTable);
			if (CollectionUtils.isEmpty(list)) continue;
			
			GenTable tmpTab = list.get(0);
			tmpTab.setColumnList(dao.findTableColumnList(genTable));
			tmpTab.setPkList(dao.findTablePK(genTable));
			genTables.add(tmpTab);
		}
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(Global.getConfig("project.all"));
		
		// 生成主表模板代码
		for (GenTable table : genTables) {
			GenUtils.initColumnField(table);
			Map<String, Object> model = GenUtils.getDataModel(table);
			for (GenTemplate tpl : templateList){
				result.append(GenUtils.generateToFile(tpl, model, true));
			}
		}
	}
}

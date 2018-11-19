package com.towcent.generator.project.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.common.collect.Maps;
import com.towcent.generator.db.code.utils.PropertiesLoader;

public class Global {

	public static void main(String[] args) {
		System.out.println(getProjectPath());
		System.out.println(getFileContent(getProjectPath() + File.separator + "pom.xml"));
	}

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("application.properties");

    /**
     * 获取配置
     * @see '${fns:getConfig('adminPath')}'
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
    
	/**
	 * 获取当前工程路径
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		String projectPath = "";
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()) {
						break;
					}
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
	}
	
	/**
	 * 拼接路径
	 * @param strs
	 * @return
	 */
	public static String joinPath(String... strs) {
		StringBuffer path = new StringBuffer();
		if (!ArrayUtils.isEmpty(strs)) {
			for (String str : strs) {
				path.append(str);
				path.append(File.separator);
			}
			path.setLength(path.length() - 1);
		}
		return path.toString();
	}
	
	public static String getFileContent(String filePath) {
		/*
		 * InputStreamReader+BufferedReader读取字符串 ，
		 * InputStreamReader类是从字节流到字符流的桥梁
		 */
		/* 按行读对于要处理的格式化数据是一种读取的好方式 */
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(filePath);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null)
			{
				if (len != 0) // 处理换行符的问题
				{
					str.append("\r\n" + line);
				}
				else
				{
					str.append(line);
				}
				len++;
			}
			in.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();

	}
}

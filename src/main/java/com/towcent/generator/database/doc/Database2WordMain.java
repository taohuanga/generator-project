package com.towcent.generator.database.doc;

import java.util.List;

import org.apache.log4j.Logger;

import com.towcent.generator.database.doc.word.WordKit;
import com.towcent.generator.db.code.gen.dao.GenDataBaseDictDao;
import com.towcent.generator.db.code.gen.entity.GenTable;

public class Database2WordMain {
	
	private final static Logger logger = Logger.getLogger(Database2WordMain.class);
	
	public static void main(String[] arg) throws Exception {
		// exportDoc();
	}

	public static void exportDoc(GenDataBaseDictDao dao) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("starting to export mysql table info....");
		
		List<GenTable> tableList = dao.findTableList(new GenTable());
		for (GenTable genTable : tableList) {
			genTable.setColumnList(dao.findTableColumnList(genTable));
		}
		
		logger.info("获取mysql 表格结构完成...");
		logger.info("开始将mysql表结果信息写入word文档中....");
		WordKit wordKit = new WordKit();
		wordKit.writeTableToWord(tableList);
		logger.info("export successfully!....");
		logger.debug("本次导出总耗时：" + (System.currentTimeMillis() - startTime) / 1000 + " s");
	}
}

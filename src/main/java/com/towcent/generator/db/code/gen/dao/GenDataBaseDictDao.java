package com.towcent.generator.db.code.gen.dao;

import java.util.List;

import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.generator.db.code.gen.base.CrudDao;
import com.towcent.generator.db.code.gen.entity.GenTable;
import com.towcent.generator.db.code.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {

	/**
	 * 查询表列表
	 * @param genTable
	 * @return
	 */
	List<GenTable> findTableList(GenTable genTable);

	/**
	 * 获取数据表字段
	 * @param genTable
	 * @return
	 */
	List<GenTableColumn> findTableColumnList(GenTable genTable);
	
	/**
	 * 获取数据表主键
	 * @param genTable
	 * @return
	 */
	List<String> findTablePK(GenTable genTable);
	
}

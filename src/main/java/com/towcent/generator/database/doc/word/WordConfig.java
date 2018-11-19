package com.towcent.generator.database.doc.word;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

public abstract class WordConfig {

	// 表格第一行相关设置
	protected final String FIRST_ROW_COLOR = "C3BED4";// 表格第一行背景色
	protected final boolean IS_FIRST_ROW_COLOR = true;// 是否显示表格背景色
	protected final int FIRST_ROW_HEIGHT = 300;// 表格第一行高度
	protected final int FIRST_ROW_CEL_WIDTH = 2000;// 表格第一行中单元格的宽度
	protected final int FIRST_ROW_FONT_SIZE = 10;// 单元格中文字大小
	protected final String FIRST_ROW_FONT_FAMILY = "黑体";// 单元格中文字字体
	protected final boolean FIRST_ROW_FONT_BOLD = true;// 单元格中文字是否加粗
	protected final String FIRST_ROW_FONT_COLOR = "000000";// 单元格中文字颜色
	protected final ParagraphAlignment FIRST_ROW_ALIGNMENT = ParagraphAlignment.LEFT;// 设置表格文字对齐方式

	// 表格除了第一行，其他行相关设置
	protected final String ROW_COLOR = "EEEEFF";// （除了第一行外）表格内容行背景色
	protected final boolean IS_ROW_COLOR = false;// 是否显示表格背景色
	protected final int ROW_HEIGHT = 270;// 表格内容行高度
	protected final int ROW_CEL_WIDTH = 2000;// 表格内容行中单元格的宽度
	protected final int ROW_FONT_SIZE = 10;// 单元格中文字大小
	protected final String ROW_FONT_FAMILY = "Courier New";// 单元格中文字字体
	protected final boolean ROW_FONT_BOLD = false;// 单元格中文字是否加粗
	protected final String ROW_FONT_COLOR = "000000";// 单元格中文字颜色
	protected final ParagraphAlignment ROW_ALIGNMENT = ParagraphAlignment.LEFT;// 设置表格文字对齐方式

	// 表格标题相关设置
	protected final int TITLE_FONT_SIZE = 16;// 表格标题文字大小
	protected final String TITLE_FONT_FAMILY = "Courier New";// 表格标题文字字体
	protected final boolean TITLE_FONT_BOLD = true;// 表格标题文字是否加粗
	protected final boolean TITLE_ADD_INDEX = true;// 表格标题是否自动加上序号
	protected final ParagraphAlignment TITLE_ALIGNMENT = ParagraphAlignment.LEFT;// 设置表格文字对齐方式
	protected final boolean IS_RETURN_ROW = false;// 是否换行

	// 表格主体相关设置
	protected final String TABLE_WIDTH = "9923";// 表格宽度

	protected final String EXPORT_FILE_PATH = "D:/数据字典.docx";// 导出文件的存放地址
	
	protected final String[] tableRelation = new String[]{"", "字段", "类型", "描述", "默认", "必填项"};
	protected final Integer[] relationWidth = new Integer[]{509, 2035, 1992, 3686, 709, 992};
	
}

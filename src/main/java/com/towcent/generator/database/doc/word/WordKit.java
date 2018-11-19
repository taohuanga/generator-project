package com.towcent.generator.database.doc.word;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.towcent.generator.db.code.gen.entity.GenTable;
import com.towcent.generator.db.code.gen.entity.GenTableColumn;
import com.towcent.generator.db.code.utils.StringUtils;

public class WordKit extends WordConfig {
	private final static Logger logger = Logger.getLogger(WordKit.class);
	private static XWPFDocument document = new XWPFDocument();

	public void writeTableToWord(List<GenTable> tableList) throws Exception {
		for (GenTable table : tableList) {
			if (TITLE_ADD_INDEX) {
				int index = tableList.indexOf(table) + 1;
				if (StringUtils.isBlank(table.getComments())) {
					table.setTitle(index + ". " + table.getName());
				} else {
					table.setTitle(index + ". " + table.getComments() + (table.getComments().contains(table.getName()) ? "" : " (" + table.getName() + ")"));
				}
			}
			createSimpleTableNormal(table);
		}
		saveDocument(EXPORT_FILE_PATH);
		logger.debug("文件写入成功.");
		logger.debug("成功将文件保存在>>>" + EXPORT_FILE_PATH);
	}

	// 往word插入一张表格
	private void createSimpleTableNormal(GenTable genTable) throws Exception {
		// 添加一个文档
		addNewPage(BreakType.TEXT_WRAPPING);

		// 设置标题
		setTableTitle(genTable.getTitle());
		// 创建表格
		XWPFTable table = createTable(genTable.getColumnList().size(), 6);
		// 设置表格中行列内容
		setRowText(table, genTable);
		// 往表格中插入第一列标题内容
		setFirstRowText(table);
	}

	// 创建表格
	private XWPFTable createTable(int rowNum, int celNum) {
		XWPFTable table = document.createTable(rowNum, celNum);
		CTTbl ttbl = table.getCTTbl();
		CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
		CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
		CTJc cTJc = tblPr.addNewJc();
		cTJc.setVal(STJc.Enum.forString("center"));
		tblWidth.setW(new BigInteger(TABLE_WIDTH));
		tblWidth.setType(STTblWidth.AUTO);
		return table;
	}

	// 设置表格标题
	private void setTableTitle(String tableTitle) {
		XWPFParagraph p2 = document.createParagraph();

		addCustomHeadingStyle(document, "标题 2", 2);

		p2.setAlignment(TITLE_ALIGNMENT);
		XWPFRun r2 = p2.createRun();
		p2.setStyle("标题 2");
		// r2.setTextPosition(5);
		r2.setText(tableTitle);
		if (TITLE_FONT_BOLD) {
			r2.setBold(TITLE_FONT_BOLD);
		}
		r2.setFontFamily(TITLE_FONT_FAMILY);
		r2.setFontSize(TITLE_FONT_SIZE);
		if (IS_RETURN_ROW) {
			r2.addCarriageReturn();// 是否换行
		}
	}

	// 设置表格第一行内容
	private void setFirstRowText(XWPFTable table) {
		XWPFTableRow firstRow = null;
		XWPFTableCell firstCell = null;
		firstRow = table.insertNewTableRow(0);
		firstRow.setHeight(FIRST_ROW_HEIGHT);
		// 表关系列
		int i = 0;
		for (String fieldValue : tableRelation) {
			firstCell = firstRow.addNewTableCell();
			int with = relationWidth[i];
			createVSpanCell(firstCell, fieldValue, FIRST_ROW_COLOR, with, STMerge.RESTART);
			i++;
		}
	}

	// 设置每行的内容
	private void setRowText(XWPFTable table, GenTable genTable) {
		XWPFTableRow firstRow = null;
		XWPFTableCell firstCell = null;

		List<GenTableColumn> fieldList = genTable.getColumnList();
		// List<String[]> fieldList = genTable.getFieldList();
		GenTableColumn column = null;
		for (int i = 0, fieldListSize = fieldList.size(); i < fieldListSize; i++) {
			firstRow = table.getRow(i);
			firstRow.setHeight(ROW_HEIGHT);
			column = fieldList.get(i);
			
			if (StringUtils.isBlank(column.getName())) continue;
			
			for (int k = 0; k < relationWidth.length; k++) {
				firstCell = firstRow.getCell(k);
				int with = relationWidth[k];
				String fieldVal = "";
				if (k == 0) {
					fieldVal = (i + 1) + "";
				} else if (k == 1) {
					fieldVal = column.getName();
				} else if (k == 2) {
					fieldVal = column.getJdbcType();
				} else if (k == 3) {
					fieldVal = column.getComments();
				} else if (k == 4) {
					fieldVal = column.getDef();
				} else if (k == 5) {
					fieldVal = "0".equals(column.getIsNull()) ? "Y" : "";
				}
				setCellText(firstCell, fieldVal, ROW_COLOR, with);
			}			 
			
		}

	}

	// 添加单个列的内容
	private void setCellText(XWPFTableCell cell, String value, String bgcolor, int width) {
		CTTc cttc = cell.getCTTc();
		CTTcPr cellPr = cttc.addNewTcPr();
		cellPr.addNewTcW().setW(BigInteger.valueOf(width));

		if (IS_ROW_COLOR)// 设置颜色
			cell.setColor(bgcolor);

		/*
		 * ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		 * cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
		 * cell.setText(value);
		 */

		XWPFParagraph p = getXWPFParagraph(ROW_ALIGNMENT, ROW_FONT_BOLD, ROW_FONT_FAMILY, ROW_FONT_SIZE, ROW_FONT_COLOR,
				value);
		cell.setParagraph(p);
	}

	// 往第一行插入一列
	private void createVSpanCell(XWPFTableCell cell, String value, String bgcolor, int width, STMerge.Enum stMerge) {
		CTTc cttc = cell.getCTTc();
		CTTcPr cellPr = cttc.addNewTcPr();
		cellPr.addNewTcW().setW(BigInteger.valueOf(width));
		if (IS_FIRST_ROW_COLOR)// 设置颜色
			cell.setColor(bgcolor);
		/*
		 * cellPr.addNewVMerge().setVal(stMerge);
		 * cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		 * cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
		 * cttc.getPList().get(0).addNewR().addNewT().setStringValue(value);
		 */

		XWPFParagraph p = getXWPFParagraph(FIRST_ROW_ALIGNMENT, FIRST_ROW_FONT_BOLD, FIRST_ROW_FONT_FAMILY,
				FIRST_ROW_FONT_SIZE, FIRST_ROW_FONT_COLOR, value);
		cell.setParagraph(p);
	}

	private XWPFParagraph getXWPFParagraph(ParagraphAlignment alignment, boolean isBold, String fontFamily,
			int fontSize, String fontColor, String celValue) {
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun r2 = p.createRun();
		p.setAlignment(alignment);
		if (isBold)
			r2.setBold(isBold);
		r2.setFontFamily(fontFamily);
		r2.setFontSize(fontSize);
		r2.setText(celValue);
		r2.setColor(fontColor);
		return p;
	}

	// 添加新的一个文档
	private void addNewPage(BreakType breakType) {
		XWPFParagraph xp = document.createParagraph();
		xp.createRun().addBreak(breakType);
	}

	// 输出文件
	public void saveDocument(String savePath) throws Exception {
		FileOutputStream fos = new FileOutputStream(savePath);
		document.write(fos);
		fos.close();
	}

	/**
	 * 增加自定义标题样式。这里用的是stackoverflow的源码
	 * 
	 * @param docxDocument
	 *            目标文档
	 * @param strStyleId
	 *            样式名称
	 * @param headingLevel
	 *            样式级别
	 */
	private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

		CTStyle ctStyle = CTStyle.Factory.newInstance();
		ctStyle.setStyleId(strStyleId);

		CTString styleName = CTString.Factory.newInstance();
		styleName.setVal(strStyleId);
		ctStyle.setName(styleName);

		CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
		indentNumber.setVal(BigInteger.valueOf(headingLevel));

		// lower number > style is more prominent in the formats bar
		ctStyle.setUiPriority(indentNumber);

		CTOnOff onoffnull = CTOnOff.Factory.newInstance();
		ctStyle.setUnhideWhenUsed(onoffnull);

		// style shows up in the formats bar
		ctStyle.setQFormat(onoffnull);

		// style defines a heading of the given level
		CTPPr ppr = CTPPr.Factory.newInstance();
		ppr.setOutlineLvl(indentNumber);
		ctStyle.setPPr(ppr);

		XWPFStyle style = new XWPFStyle(ctStyle);

		// is a null op if already defined
		XWPFStyles styles = docxDocument.createStyles();

		style.setType(STStyleType.PARAGRAPH);
		styles.addStyle(style);

	}

}

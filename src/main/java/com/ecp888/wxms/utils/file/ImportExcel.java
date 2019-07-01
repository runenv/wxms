package com.ecp888.wxms.utils.file;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcel {
	
	public static final String USER_FILE_2007 = "userData.xlsx";
	public static final String USER_FILE_2003 = "userData.xls";
	
	public static final String TEMPLATE_FILE = "template.xlsx";
	
	public static String TEMPLATE_FILE_SUFFIX = ".xlsx";
	public static String TEMPLATE_FILE_SUFFIX_2007 = ".xlsx";
	public static String TEMPLATE_FILE_SUFFIX_2003 = ".xls";

	public ArrayList<ArrayList<String>> readExcel(String fileName, String path) {
		ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();
		try {
			Workbook workBook = null;
			try {
				//.xlsx
				workBook = new XSSFWorkbook(path + "/" + fileName); 
			} catch (Exception ex) {
				// .xls 
				workBook = new HSSFWorkbook(new FileInputStream(path + "/" + fileName));
			}
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workBook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 循环列Cell
					ArrayList<String> arrCell = new ArrayList<String>();
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
						arrCell.add(getValue(cell));
					}
					Row.add(arrCell);
				}
			}
		} catch (IOException e) {
			System.out.println("e:" + e);
		}
		return Row;
	}
	
	public ArrayList<ArrayList<String>> readExcel(String filePath) {
		ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();
		try {
			Workbook workBook = null;
			try {
				//.xlsx
				workBook = new XSSFWorkbook(filePath); 
			} catch (Exception ex) {
				// .xls 
				workBook = new HSSFWorkbook(new FileInputStream(filePath));
			}
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workBook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 循环列Cell
					ArrayList<String> arrCell = new ArrayList<String>();
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
						arrCell.add(getValue(cell));
					}
					Row.add(arrCell);
				}
			}
		} catch (IOException e) {
			System.out.println("e:" + e);
		}
		return Row;
	}

	@SuppressWarnings("static-access")
	private String getValue(Cell cell) {
		
		DecimalFormat df = new DecimalFormat("0");  
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(df.format(cell.getNumericCellValue()));
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public static void main(String[] args) {
		ImportExcel s = new ImportExcel();
		ArrayList<ArrayList<String>> rows = s.readExcel("2014年3月17日 用户量.xls",
				"C:\\Users\\Administrator\\Desktop");
		System.out.println("size:" + rows.size());
		for (ArrayList<String> cells : rows) {
			for (String str : cells) {
				System.out.println(str);
			}
		}
	}
}

package org.task.DataDrivenTestNG;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReadFromExcel {
	public static void main(String[] args) throws Throwable {
		getValuesFromExcel();

	}

	public static Object[][] getValuesFromExcel() throws Throwable {
		String stringCellValue;
		int numericCellValue;

		File f = new File("./src/test/resources/TestData.xlsx");
		FileInputStream obj = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(obj);
		Sheet s = w.getSheet("Sheet1");

		ArrayList<LinkedHashMap<String, String>> refMapList = new ArrayList<LinkedHashMap<String, String>>();
		Object[][] cellData = new Object[s.getLastRowNum()][2];

		Row headerRow = s.getRow(0);
		int objectI = 0;
		for (int i = 1; i < s.getPhysicalNumberOfRows(); i++, objectI++) {
			Row currentRow = s.getRow(i);

			int objectJ = 0;
			LinkedHashMap<String, String> refMap = new LinkedHashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++, objectJ++) {
				Cell cell = currentRow.getCell(j);
				// int cellType = cell.getCellType();
				if (cell.getCellTypeEnum() == CellType.STRING) {

					stringCellValue = cell.getStringCellValue();
//					System.out.println(stringCellValue);
					refMap.put(headerRow.getCell(j).getStringCellValue(), stringCellValue);
					cellData[objectI][objectJ] = stringCellValue;
//					System.out.println("cell data" + cellData[objectI][objectJ]);

				}
				if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					double numericCellValueRef = cell.getNumericCellValue();
					numericCellValue = (int) numericCellValueRef;
					String castToString = String.valueOf(numericCellValue);
//					System.out.println(numericCellValue);
					refMap.put(headerRow.getCell(j).getStringCellValue(), castToString);

					cellData[objectI][objectJ] = castToString;
//					System.out.println("cell data" + cellData[objectI][objectJ]);
				}

			}

			refMapList.add(refMap);

//			System.out.println(refMapList);

		}
		w.close();

		return cellData;
	}
}
package com.lemon.auto.phoenix.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.omg.CORBA.OBJ_ADAPTER;


/**
 * excel的工具类（通用性）
 * poi编程
 * 在maven中引入poi3.15，但是要注意此时可能没有WorkbookFactory这个类，我们需要再引入poi-ooxml3.15
 * @author zhangying
 *
 */
/**
 * c常用快捷键
 * Ctrl+T 快速显示当前类的继承结构
 * Ctrl+L 定位在某行 (对于程序超过100的人就有福音了)
 * Ctrl+E 快速显示当前Editer的下拉列表(如果当前页面没有显示的用黑体表示)
 * Ctrl+K 参照选中的Word快速定位到下一个
 * @author zhangying
 *
 */
public class ExcelUtil {

	/**
	 * 准备数据
	 * 1、可能会读取很多excel-->需要excel的具体路径--》excelPath
	 * 2、一个excel中有多个sheet，可以通过名称或者索引来获得sheet
	 * 3、获取连续的几行--》起止行：startRow，endRow
	 * 4、获取一行中某几列，需要获取列数，-->startCell,endCell
	 * @return 
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static Object[][] readExcel(String excelPath,int sheetIndex) {
		InputStream inputStream = ExcelUtil.class.getResourceAsStream(excelPath);
		//每一个excel文件就是一个工作簿
		Workbook workbook;
		Object[][] datas = null;
		try {
			workbook = WorkbookFactory.create(inputStream);
			//得到sheet对象
			Sheet sheet = workbook.getSheetAt(sheetIndex-1);
			int rowNum = sheet.getLastRowNum();
			datas = new Object[rowNum][];
			//先得到某一行，然后通过对应列得到某一列
			//如果要把数据全部解析出来--》两层for循环
			//1、遍历需要读取行，从startRow到endRow
			for (int i = 1; i <= rowNum; i++) {
				Row row = sheet.getRow(i);
				int cellNum = row.getLastCellNum();
				Object[] rowData = new Object[cellNum];
				for (int j = 0; j <cellNum; j++) {
					//得到单元格对象
					/**Cell cell = row.getCell(j-1);这个方法会报下面错误：
					 * 1、Exception in thread "main" java.lang.NullPointerException
					 * 因为当单元格内容为空时，cell对象为none
					 */
//					Cell cell = row.getCell(j-1);
					Cell cell =row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);//当单元格为null时，作为空白格子
					/**
					 * 2、 java.lang.IllegalStateException:Cannot get a STRING value from a NUMERIC cell
					 *  要设置单元格的类型
					 */
					cell.setCellType(CellType.STRING);
					//得到单元格中数据-->由于excel中单元格有很多格式，为了通用性，我们统一当作字符串
					String cellValue =cell.getStringCellValue();
				   rowData[j]= cellValue;
				}
				//将二维数组中的每一个一维数组初始化
			   datas[i-1]=rowData;
			}
			
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return datas;
	}
	
	
	public static Object[][] readExcel(String excelPath,int sheetIndex,int startRow,int endRow,int startCell,int endCell) {
		InputStream inputStream = ExcelUtil.class.getResourceAsStream(excelPath);
		//每一个excel文件就是一个工作簿
		Workbook workbook;
		Object[][] datas = new Object[endRow-startRow+1][endCell-startCell+1];
		try {
			workbook = WorkbookFactory.create(inputStream);
			//得到sheet对象
			Sheet sheet = workbook.getSheetAt(sheetIndex-1);
			//先得到某一行，然后通过对应列得到某一列
			//如果要把数据全部解析出来--》两层for循环
			//1、遍历需要读取行，从startRow到endRow
			for (int i = startRow; i <=endRow; i++) {
				Row row = sheet.getRow(i-1);
				for (int j = startCell; j <=endCell; j++) {
					//得到单元格对象
					/**Cell cell = row.getCell(j-1);这个方法会报下面错误：
					 * 1、Exception in thread "main" java.lang.NullPointerException
					 * 因为当单元格内容为空时，cell对象为none
					 */
//					Cell cell = row.getCell(j-1);
					Cell cell =row.getCell(j-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);//当单元格为null时，作为空白格子
					/**
					 * 2、 java.lang.IllegalStateException:Cannot get a STRING value from a NUMERIC cell
					 *  要设置单元格的类型
					 */
					cell.setCellType(CellType.STRING);
					//得到单元格中数据-->由于excel中单元格有很多格式，为了通用性，我们统一当作字符串
					String cellValue =cell.getStringCellValue();
				    datas[i-startRow][j-startCell] = cellValue;
				}
			
			}
			
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return datas;
	}
	
	public static void main(String[] args) {
		Object[][] datas = readExcel("/testcase/registerData.xlsx", 2);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.print(object+",");
			}
			System.out.println();
		}
	}
}

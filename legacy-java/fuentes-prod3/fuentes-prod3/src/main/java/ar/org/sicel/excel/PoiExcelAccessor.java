package ar.org.sicel.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ar.org.sicel.excel.excepciones.CargaMasivaException;
import ar.org.sicel.excel.excepciones.FormatCellException;


public class PoiExcelAccessor {
	
	static Logger log = Logger.getLogger(PoiExcelAccessor.class);
	
	private HSSFWorkbook wb = null;
	private String sheetName = null;
	private String fileName = null; // nombre del archivo
	private FileInputStream inputStream = null;
	private HSSFSheet sheet = null;
	
	public PoiExcelAccessor(String fileName)  {
		this.fileName = fileName;
		try {
			inputStream = new FileInputStream(this.fileName);
			wb = new HSSFWorkbook(inputStream); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * Adicinalmente inicializa el sheetName
	 * @param fileName
	 * @throws CargaMasivaException 
	 */
	public void setSheetName(String sheetName) throws CargaMasivaException {
		this.sheetName = sheetName;
		sheet = wb.getSheet(this.sheetName);
		if (sheet == null)
			throw new CargaMasivaException("En el archivo excel no existe la hoja "+sheetName);
	}
	
	/**
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @return - null si la celda esta vacia
	 */
	public Object getValue(int rowNumber, int columnNumber){		
		HSSFRow row = sheet.getRow(rowNumber);        
		HSSFCell cell   = row.getCell((short)columnNumber);  		
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING ) {
			return cell.getRichStringCellValue();
		} else if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ) {
			return new BigDecimal(cell.getNumericCellValue());			
		} else 
			return null;				
	}
	
	public String getStringValue(int rowNumber, int columnNumber) {
		HSSFRow row     = sheet.getRow(rowNumber);        // third row
		HSSFCell cell   = row.getCell((short)columnNumber);  // fourth cell		
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING && cell.getRichStringCellValue() != null &&
				StringUtils.isNotEmpty(cell.getRichStringCellValue().getString())) 
			return cell.getRichStringCellValue().getString();
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			return new BigDecimal(cell.getNumericCellValue()).toString();		
		
		return null;
	}
	
	/**
	 * 
	 * @param rowNumber
	 * @param columnNumber
	 * @return
	 * @throws FormatCellException 
	 */
	public BigDecimal getDoubleValue(int rowNumber, int columnNumber) throws FormatCellException {
		HSSFRow row     = sheet.getRow(rowNumber);        // third row
		HSSFCell cell   = row.getCell((short)columnNumber);  // fourth cell		
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
			return new BigDecimal(cell.getNumericCellValue());
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING && cell.getRichStringCellValue() != null) {
			String str = cell.getRichStringCellValue().toString();
			try {
				BigDecimal aux = new BigDecimal(str);
				return aux;
			}
			catch (NumberFormatException e) {
				throw new FormatCellException("El formato de la celda no es numerico", rowNumber, columnNumber);
			}
		}
		
		if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
			throw new FormatCellException("El formato de la celda no es numerico", rowNumber, columnNumber);
		return null;
	}
	
	public Date getDateValue(int rowNumber, int columnNumber) throws FormatCellException {
		HSSFRow row     = sheet.getRow(rowNumber);        // third row
		HSSFCell cell   = row.getCell((short)columnNumber);  // fourth cell		
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
			return cell.getDateCellValue();
		if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
			throw new FormatCellException("El formato de la celda no es numerico", rowNumber, columnNumber);
		
		return null;
	}
	
	public int getFirstRowNum() {
		return sheet.getFirstRowNum();
	}
	
	public int getLastRowNum() {
		return sheet.getLastRowNum();		
	}	
	
	
	/**
	 * Retorna el numero de la 1ra celda en la fila con numero rowNumber
	 * @param rowNumber - numero de la fila
	 * @return
	 * @throws CargaMasivaException 
	 */
	public int getFirstCellNum(int rowNumber) throws POIException  {
		HSSFRow row = sheet.getRow(rowNumber);
		if (row == null)
			throw new POIException("Problemas al obtener la primera celda de la fila "+rowNumber);
		return row.getFirstCellNum();
	}
	
	/**
	 * 
	 * @param rowNumber
	 * @return
	 * @throws POIException 
	 */
	public int getLastCellNum(int rowNumber) throws POIException {
		HSSFRow row = sheet.getRow(rowNumber);
		if (row == null)
			throw new POIException("Problemas al obtener la ultima celda de la fila "+rowNumber);
		return row.getLastCellNum();
	}
	

	public void close() throws CargaMasivaException {
		try {
			this.inputStream.close();
			File f = new File(this.fileName);
			if (f.delete()) {
				System.out.println("Se borro el archivo "+this.fileName);
			}
			else {
				System.out.println("No se pudo borrar el archivo "+this.fileName);
			}
		} catch (IOException e) {
			throw new CargaMasivaException("Problemas mientras se cerro el input stream", e);
		
		}
		
	}



	public String getSheetName() {
		return sheetName;
	}


	public boolean isEmpty(int rowNumber) throws POIException {
		int i = this.getFirstCellNum(rowNumber);
		int n = this.getLastCellNum(rowNumber);
		if (i >= n) return true;
		for (int j = i; j <= n ; j++) {
			//HSSFRow row     = sheet.getRow(rowNumber);        			
			Object obj = this.getValue(rowNumber, j);
			if (obj != null)
				return false;
		}	
		
		return true;
	}


	
}

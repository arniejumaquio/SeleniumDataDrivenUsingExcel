package rahulshettyacademy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GetDataFromExcelUtility {

    public ArrayList<String> getData(String testCaseName) throws IOException {

        ArrayList<String> data = new ArrayList<String>();

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/TestData.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        int numOfSheet = xssfWorkbook.getNumberOfSheets();

        for(int i = 0; i < numOfSheet; i++){

            String sheetName =  xssfWorkbook.getSheetName(i);
            if(sheetName.equalsIgnoreCase("Sheet1")){
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                Iterator<Row> rowIterator = xssfSheet.iterator();

                Row firstRow = rowIterator.next();
                Iterator<Cell> cellIterator = firstRow.cellIterator();

                int count = 0;
                int index = 0;
                while (cellIterator.hasNext()){
                    Cell eachCell = cellIterator.next();

                    if(eachCell.getStringCellValue().equalsIgnoreCase("Testcases")){
                        index = count;
                    }

                    count++;
                }

                //from here since we know the column now.We need to scan the entire column

                while (rowIterator.hasNext()){

                    Row row = rowIterator.next();
                    if(row.getCell(index).getStringCellValue().equalsIgnoreCase(testCaseName)){
                        //get all cells for this row
                        cellIterator = row.cellIterator();
                        while(cellIterator.hasNext()){

                            Cell eachCell = cellIterator.next();

                            if(eachCell.getCellType()== CellType.STRING) {
                                data.add(eachCell.getStringCellValue());
                            }else if (eachCell.getCellType()== CellType.NUMERIC){
                                data.add(String.valueOf(eachCell.getNumericCellValue()));
                            }else{
                                data.add(String.valueOf(eachCell.getBooleanCellValue()));
                            }

                        }
                    }
                }

            }

        }

        return data;

    }


    public Object[][] getData(String excelFilePath,String sheetName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        int rowCount = xssfSheet.getPhysicalNumberOfRows();
        int columnCount = xssfSheet.getRow(0).getLastCellNum();

        //create multi dim array
        Object[][] data = new Object[rowCount-1][columnCount];


        for(int i = 0; i < rowCount-1;i++){

            for(int j =0; j < columnCount; j++){

                XSSFCell cell = xssfSheet.getRow(i+1).getCell(j);

                if(cell.getCellType() == CellType.STRING){
                    data[i][j] = cell.getStringCellValue();
                }else if(cell.getCellType() == CellType.NUMERIC){
                    data[i][j] = String.valueOf(cell.getNumericCellValue());
                }else{
                    data[i][j] = String.valueOf(cell.getBooleanCellValue());
                }

            }
        }


        return data;

    }




}

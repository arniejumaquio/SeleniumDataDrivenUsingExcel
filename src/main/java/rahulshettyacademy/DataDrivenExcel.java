package rahulshettyacademy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class DataDrivenExcel {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/TestData.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        int numOfSheet = xssfWorkbook.getNumberOfSheets();

        //iterate through the sheet
        for(int i = 0; i < numOfSheet; i++){

           String sheetName = xssfWorkbook.getSheetName(i);
           if(sheetName.equalsIgnoreCase("Sheet1")){
               XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);

             Iterator<Row> rows =  xssfSheet.iterator();
             Row firstRow = rows.next();

             Iterator<Cell> cells = firstRow.cellIterator();
             int count = 0;
             int columnIndex = 0;
             while (cells.hasNext()){

               Cell cell =  cells.next();
               if(cell.getStringCellValue() .equalsIgnoreCase("TestCases")){

                   //correct column
                   columnIndex = count;

               }

                count++;
             }

               System.out.println("Column Index = "+columnIndex);

             //1.From the above we have known the column index/position
             //2.We need to scan the column
             while(rows.hasNext())  {

                 Row row = rows.next();
                 if( row.getCell(columnIndex).getStringCellValue().equalsIgnoreCase("Purchase")){
                     //get all the cell from this row
                     Iterator<Cell> allCells = row.cellIterator();
                     while (allCells.hasNext()){
                         String eachCellValue = allCells.next().getStringCellValue();
                         System.out.println(eachCellValue);
                     }

                 }

             }


           }

        }


    }

}

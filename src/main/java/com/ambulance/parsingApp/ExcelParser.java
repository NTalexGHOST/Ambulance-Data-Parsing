package com.ambulance.parsingApp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelParser {

    public List<AmbulanceEntity> getAmbulanceData(String path) throws IOException {
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new HSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

//        for (Row row : sheet) {
//            Cell cell = row.getCell(0);
//            if(cell != null && cell.getCellType() != CellType.STRING && cell.getDateCellValue() != null){
//
//            }
//        }
        String age = sheet.getRow(9).getCell(16).toString();
        List<AmbulanceEntity> ambulanceEntityList = new ArrayList<>();
        AmbulanceEntity ambulance  = new AmbulanceEntity(
                sheet.getRow(3).getCell(3).toString(),
                sheet.getRow(3).getCell(0).getDateCellValue(),
                Integer.parseInt(age.substring(0, age.length()-4)),
                sheet.getRow(5).getCell(1).toString(),
                sheet.getRow(6).getCell(1).toString(),
                sheet.getRow(4).getCell(1).toString(),
                sheet.getRow(14).getCell(8).getDateCellValue(),
                sheet.getRow(14).getCell(11).getDateCellValue()

        );
        ambulanceEntityList.add(ambulance);
        return ambulanceEntityList;
    }
}

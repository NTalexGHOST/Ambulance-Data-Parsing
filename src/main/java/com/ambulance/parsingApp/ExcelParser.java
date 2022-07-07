package com.ambulance.parsingApp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelParser {

    private void checkDataIntegrity(String id, java.util.Date callDate, Integer age, String cause, String diagnose, String destination , java.util.Date timeResponse, java.util.Date timeArrive) throws Exception {
        Exception emptyOrNullException = new Exception("Can't use empty value");
        if (id==null || id.isEmpty())
            throw emptyOrNullException;
        if (callDate==null)
            throw emptyOrNullException;
        if (age==null)
            throw emptyOrNullException;
        if (cause==null || cause.isEmpty())
            throw emptyOrNullException;
        if (diagnose==null || diagnose.isEmpty())
            throw emptyOrNullException;
        if (destination==null || destination.isEmpty())
            throw emptyOrNullException;
        if (timeResponse==null)
            throw emptyOrNullException;
        if (timeArrive==null)
            throw emptyOrNullException;
    }

    public void getAmbulanceData(String path, AmbulanceEntityRepo repo) throws IOException {
        //Открываем необходимый файл
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //Экземпляр класса, который мы будем использовать для записи данных в лист
        for (int i = 3; i < sheet.getLastRowNum()-10; i++) {
            if (sheet.getRow(i).getCell(0) != null &&
                    sheet.getRow(i).getCell(0).getCellType() != CellType.STRING &&
                    DateUtil.isCellDateFormatted(sheet.getRow(i).getCell(0))) {
                try {
                    String age = sheet.getRow(i).getCell(16).toString();
                    age = age.substring(0, age.length() - 4);
                    //Проверяем данные на отсутствие пустых строк или null
                    checkDataIntegrity(sheet.getRow(i).getCell(3).toString(),
                            sheet.getRow(i).getCell(0).getDateCellValue(),
                            Integer.parseInt(age),
                            sheet.getRow(i + 2).getCell(1).toString(),
                            sheet.getRow(i + 3).getCell(1).toString(),
                            sheet.getRow(i + 1).getCell(1).toString(),
                            sheet.getRow(i + 5).getCell(8).getDateCellValue(),
                            sheet.getRow(i + 5).getCell(11).getDateCellValue());
                    //Вызываем конструктор класса, в который вбиваются все значения
                    AmbulanceEntity ambulance = new AmbulanceEntity(
                            sheet.getRow(i).getCell(3).toString(),
                            sheet.getRow(i).getCell(0).getDateCellValue(),
                            Integer.parseInt(age),
                            sheet.getRow(i + 2).getCell(1).toString(),
                            sheet.getRow(i + 3).getCell(1).toString(),
                            sheet.getRow(i + 1).getCell(1).toString(),
                            sheet.getRow(i + 5).getCell(8).getDateCellValue(),
                            sheet.getRow(i + 5).getCell(11).getDateCellValue()
                    );
                    repo.save(ambulance);
                } catch (Exception ignore) { }
            }
        }
    }
}


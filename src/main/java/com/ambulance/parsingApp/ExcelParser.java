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

    public List<AmbulanceEntity> getAmbulanceData(String path) throws IOException {
        //Открываем необходмиый файл
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //Счетчик ряда
        //Лист со всеми данными
        List<AmbulanceEntity> ambulanceEntityList = new ArrayList<>();
        //Экземпляр класса, который мы будем использовать для записи данных в лист
        AmbulanceEntity ambulance;
        for (int i = 3; i < sheet.getLastRowNum()-10; i++) {
            if (sheet.getRow(i).getCell(0) != null &&
                    sheet.getRow(i).getCell(0).getCellType() != CellType.STRING &&
                    DateUtil.isCellDateFormatted(sheet.getRow(i).getCell(0))) {
                try {
                    String age = sheet.getRow(i).getCell(16).toString();
                    //Вызываем конструктор класса, в который вбиваются все значения
                    ambulance = new AmbulanceEntity(
                            sheet.getRow(i).getCell(3).toString(),
                            sheet.getRow(i).getCell(0).getDateCellValue(),
                            Integer.parseInt(age.substring(0, age.length() - 4)),
                            sheet.getRow(i + 2).getCell(1).toString(),
                            sheet.getRow(i + 3).getCell(1).toString(),
                            sheet.getRow(i + 1).getCell(1).toString(),
                            sheet.getRow(i + 5).getCell(8).getDateCellValue(),
                            sheet.getRow(i + 5).getCell(11).getDateCellValue()
                    );
                    //Проверяем данные на отсутвие пустых строк или null
                    ambulance.checkDataIntegrity();
                    //Добавляем полученную структуру в общий лист
                    ambulanceEntityList.add(ambulance);
                } catch (Exception ignore) {

                }
            }

        }
        return ambulanceEntityList;
    }

    public List<AmbulanceEntity> getAmbulanceData_old(String path) throws IOException {
        //Открываем необходмиый файл
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //Счетчик ряда
        int i = 0;
        //Лист со всеми данными
        List<AmbulanceEntity> ambulanceEntityList = new ArrayList<>();
        //Берем каждый ряд строки
        for (Row row : sheet) {
            //Получаем первую клетку в каждой строке
            Cell cell = row.getCell(0);
            //Если клетка не пустая, не является строкой и из нее корректно вытаскивается дата => это клетка с датой,
            //относительно которой можно вытащить и другие данные, так как они находятся на одном отдалении от даты
            //(а если данные находятся в других клетках, то они вызовут исключение и они автоматически отбросятся)
            if (cell != null && cell.getCellType() != CellType.STRING && cell.getDateCellValue() != null && i > 2) {
                AmbulanceEntity ambulance;
                try {
                    String age = sheet.getRow(i + 6).getCell(16).toString();
                    //Вызываем конструктор класса, в который вбиваются все значения
                    ambulance = new AmbulanceEntity(
                            sheet.getRow(i).getCell(3).toString(),
                            sheet.getRow(i).getCell(0).getDateCellValue(),
                            Integer.parseInt(age.trim()),
                            //Integer.parseInt(age.split(" ")[0]),
                            sheet.getRow(i + 2).getCell(1).toString(),
                            sheet.getRow(i + 3).getCell(1).toString(),
                            sheet.getRow(i + 1).getCell(1).toString(),
                            sheet.getRow(i + 11).getCell(8).getDateCellValue(),
                            sheet.getRow(i + 11).getCell(11).getDateCellValue()
                    );
                } catch (Exception ignore) {
                    i++;
                    continue;
                }
                //Добавляем полученную структуру в общий лист
                ambulanceEntityList.add(ambulance);
            }
            i++;
        }
        return ambulanceEntityList;
    }
}


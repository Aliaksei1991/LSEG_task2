package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomXmlParser {
    public void parseFileAndPrintOutput(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);
            List<String> columnNames = parseAndGetColumnNames(sheet.getRow(5));

            String cellWithDate = sheet.getRow(2).getCell(0).getStringCellValue();
            String date = cellWithDate.substring(cellWithDate.lastIndexOf('-') + 2);

            for (int i = 6; i < 30; i++) {
                Row row = sheet.getRow(i);
                double hour = row.getCell(0).getNumericCellValue();
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    Cell value = row.getCell(j);
                    System.out.println(String.format("%s;%s %02.0f:00; %s", columnNames.get(j), date, hour, value));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> parseAndGetColumnNames(Row row) {
        List<String> columnNames = new ArrayList<>();
        for (Iterator<Cell> iterator = row.cellIterator(); iterator.hasNext(); ) {
            Cell cell = iterator.next();
            String value = cell.getStringCellValue();
            if (value.contains("(")) {
                value = value.substring(0, value.lastIndexOf('(') - 1).trim();
            }
            columnNames.add(value);
        }
        return columnNames;
    }
}

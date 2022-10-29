package com.smart.excel;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.smart.entities.Attendence;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Attendence> listStudent;


    public UserExcelExporter(List<Attendence> listStudent) {
        this.listStudent=listStudent;
        workbook = new XSSFWorkbook();

    }

    private void createCell(Row row,int columnCount, Object value,CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell=row.createCell(columnCount);
        if(value instanceof Long) {
            cell.setCellValue((Long) value);
        }else if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeHeaderLine() {
        sheet=workbook.createSheet("Student");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"Employee Attendence  Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));

        row=sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Attendence Id", style);
        createCell(row, 1, "Employee Name", style);
        createCell(row, 2, "Employee Code", style);
        createCell(row, 3, "Employee DOA", style);
        createCell(row, 4, "Attendence Time", style);

    }

    private void writeDataLines() {
        int rowCount=2;

        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Attendence stu:listStudent) {
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, stu.getAid(), style);
            createCell(row, columnCount++, stu.getName(), style);
            createCell(row, columnCount++, stu.getCode(), style);
            createCell(row, columnCount++, stu.getDoa(), style);
            createCell(row, columnCount++, stu.getTime(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}

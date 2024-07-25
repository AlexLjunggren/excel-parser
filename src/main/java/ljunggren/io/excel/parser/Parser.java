package ljunggren.io.excel.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ljunggren.io.excel.parser.model.SimpleCell;
import ljunggren.io.excel.parser.model.SimpleRow;
import ljunggren.io.excel.parser.model.SimpleSheet;
import ljunggren.io.excel.parser.model.SimpleWorkbook;
import lombok.Getter;

@Getter
public class Parser {

    private char delimiter = ',';
    private boolean firstRowIsHeader;
    
    public Parser delimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }
    
    public Parser firstRowIsHeader() {
        this.firstRowIsHeader = true;
        return this;
    }
    
    public SimpleWorkbook parse(File file) throws Exception {
        List<SimpleSheet> sheets = new ArrayList<>();
        XSSFWorkbook workbook = getWorkbookFromFile(file);
        Iterator<Sheet> sheetIterator =  workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            sheets.add(parseSheet(sheetIterator.next()));
        }
        return new SimpleWorkbook(file.getName(), sheets);
    }
    
    private XSSFWorkbook getWorkbookFromFile(File file) throws Exception {
        FileInputStream is = new FileInputStream(file);
        try {
            return new XSSFWorkbook(new FileInputStream(file)); 
        } finally {
            is.close();
        }
    }
    
    private SimpleSheet parseSheet(Sheet sheet) {
        int columnCount = 0;
        SimpleRow headers = new SimpleRow();
        List<SimpleRow> rows = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            columnCount = sheet.getRow(0).getLastCellNum();
            headers = parseRow(rowIterator.next(), columnCount);
        }
        while (rowIterator.hasNext()) {
            rows.add(parseRow(rowIterator.next(), columnCount));
        }
        return new SimpleSheet(sheet.getSheetName(), headers, rows);
    }
    
    private SimpleRow parseRow(Row row, int columnCount) {
        List<SimpleCell> cells = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            cells.add(parseCell(row.getCell(i)));
        }
        return new SimpleRow(cells);
    }
    
    private SimpleCell parseCell(Cell cell) {
        return new SimpleCell(cell == null ? null : cell.toString());
    }
    
}

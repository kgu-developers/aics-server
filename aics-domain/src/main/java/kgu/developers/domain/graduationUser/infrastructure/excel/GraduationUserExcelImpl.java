package kgu.developers.domain.graduationUser.infrastructure.excel;

import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserExcel;
import kgu.developers.domain.graduationUser.exception.GraduationUserExcelGenerationFailed;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GraduationUserExcelImpl implements GraduationUserExcel {

    private static final int HEADER_ROW_INDEX = 0;
    private static final int DATA_START_ROW_INDEX = 1;
    private static final String SHEET_NAME = "졸업 대상자";

    @Override
    public byte[] generate(List<GraduationUser> graduationUsers) {
        try (Workbook workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
             ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            CellStyle headerStyle = createHeaderStyle(workbook);
            createHeaderRow(sheet, headerStyle);
            populateDataRows(sheet, graduationUsers);

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new GraduationUserExcelGenerationFailed(e);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return headerStyle;
    }

    private void createHeaderRow(Sheet sheet, CellStyle style) {
        Row header = sheet.createRow(HEADER_ROW_INDEX);
        GraduationUserExcelColumn[] columns = GraduationUserExcelColumn.values();

        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i].getHeaderName());
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, columns[i].getWidth());
        }
    }

    private void populateDataRows(Sheet sheet, List<GraduationUser> users) {
        GraduationUserExcelColumn[] columns = GraduationUserExcelColumn.values();
        int rowNum = DATA_START_ROW_INDEX;

        for (GraduationUser user : users) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < columns.length; i++) {
                Object value = columns[i].getValueExtractor().apply(user);
                String cellValue = (value == null) ? "" : String.valueOf(value);
                row.createCell(i).setCellValue(cellValue);
            }
        }
    }
}

package com.hbh.m2emcp.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Markdown 工具
 *
 * @author huangbh
 * @date 2025/05/20
 */
@Slf4j
@Component
public class MarkdownUtils {

    /**
     * Markdown 表格解析
     *
     * @param markdown Markdown
     * @return {@link List }<{@link List }<{@link String }>>
     */
    public List<List<String>> parseMarkdownTable(String markdown) {
        List<List<String>> rows = new ArrayList<>();
        if (markdown == null || markdown.trim().isEmpty()) {
            // 空输入直接返回空列表
            return rows;
        }

        String[] lines = markdown.trim().split("\n");
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.startsWith("|")) {
                // 去除星号并分割
                line = line.replaceAll("\\*", "");
                List<String> cells = Arrays.stream(line.split("\\|"))
                        .map(String::trim)
                        .filter(cell -> !cell.isEmpty())
                        .collect(Collectors.toList());
                rows.add(cells);
            }
        }
        return rows;
    }

    /**
     * 创建 Excel 并返回二进制数组
     *
     * @param tableData 表数据
     * @return {@link byte[]}
     */
    public byte[] createExcel(List<List<String>> tableData) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            fillSheet(sheet, tableData);

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                // 返回二进制数组
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            log.error("生成 Excel 二进制数据失败：{}", e.getMessage(), e);
            // 出现异常时返回空数组
            return new byte[0];
        }
    }

    /**
     * 创建 Excel 文件并保存到指定路径
     *
     * @param fireName  Fire 名称
     * @param dir       迪尔
     * @param tableData 表数据
     */
    public void createExcel(String fireName, String dir, List<List<String>> tableData) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            fillSheet(sheet, tableData);

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);

                Path outputPath = Paths.get(dir, fireName);
                if (!Files.exists(outputPath.getParent())) {
                    Files.createDirectories(outputPath.getParent());
                }

                try (FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
                    fos.write(outputStream.toByteArray());
                }
            }
        } catch (IOException e) {
            log.error("文件转excel失败：{}", e.getMessage(), e);
        }
    }

    /**
     * 填充工作表数据
     *
     * @param sheet     工作表
     * @param tableData 表数据
     * @throws IOException IO 异常
     */
    private void fillSheet(Sheet sheet, List<List<String>> tableData) throws IOException {
        for (int rowIndex = 0; rowIndex < tableData.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex);
            List<String> rowData = tableData.get(rowIndex);
            for (int colIndex = 0; colIndex < rowData.size(); colIndex++) {
                String cellValue = rowData.get(colIndex);
                Cell cell = row.createCell(colIndex);
                cell.setCellValue(cellValue);
            }
        }
    }
}

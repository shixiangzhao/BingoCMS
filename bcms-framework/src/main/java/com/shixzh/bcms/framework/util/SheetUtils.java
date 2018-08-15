package com.shixzh.bcms.framework.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by zhaoshixiang on 2018/8/15.
 */
public class SheetUtils {

    private static Logger log = LoggerFactory.getLogger(SheetUtils.class);

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 注解列表（Object[]{ ExcelField, Field/Method }）
     */
    List<Object[]> annotationList = Lists.newArrayList();

    /**
     * 构造函数
     *
     * @param wb          Excel的工作区
     * @param sheetConfig sheet的相关属性
     */
    public SheetUtils(SXSSFWorkbook wb, SheetConfig sheetConfig) {
        this(wb, sheetConfig.getSheetname(), sheetConfig.getTitle(), sheetConfig.getCls(), 1);
    }

    /**
     * 构造函数
     *
     * @param title  表格标题，传“空值”，表示无标题
     * @param cls    实体对象，通过annotation.ExportField获取标题
     * @param type   导出类型（1:导出数据；2：导出模板）
     * @param groups 导入分组
     */
    public SheetUtils(SXSSFWorkbook wb, String sheetname, String title, Class<?> cls, int type, int... groups) {
        // Get annotation field
        Field[] fs = cls.getDeclaredFields();
        for (Field f : fs) {
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, f});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, f});
                }
            }
        }
        // Get annotation method
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            ExcelField ef = m.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == type)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[]{ef, m});
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[]{ef, m});
                }
            }
        }
        // Field sorting
        Collections.sort(annotationList, (o1, o2) -> new Integer(((ExcelField) o1[0]).sort()).compareTo(
                ((ExcelField) o2[0]).sort()));
        // Initialize
        List<String> headerList = Lists.newArrayList();
        for (Object[] os : annotationList) {
            String t = ((ExcelField) os[0]).title();
            // 如果是导出，则去掉注释
            if (type == 1) {
                String[] ss = StringUtils.split(t, "**", 2);
                if (ss.length == 2) {
                    t = ss[0];
                }
            }
            headerList.add(t);
        }
        initialize(wb, sheetname, title, headerList);
    }

    /**
     * 初始化函数
     *
     * @param title      表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    private void initialize(SXSSFWorkbook wb, String sheetname, String title, List<String> headerList) {
        sheet = wb.createSheet(sheetname);
        this.styles = createStyles(wb);
        // Create title
        if (StringUtils.isNotBlank(title)) {
            Row titleRow = sheet.createRow(rownum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size() - 1));
        }
        // Create header
        if (headerList == null) {
            throw new RuntimeException("headerList not null!");
        }
        Row headerRow = sheet.createRow(rownum++);
        headerRow.setHeightInPoints(16);
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length == 2) {
                cell.setCellValue(ss[0]);
                Comment comment = sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            } else {
                cell.setCellValue(headerList.get(i));
            }
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i) * 2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
        }
        log.debug("Initialize success.");
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * 添加一行
     *
     * @return 行对象
     */
    public Row addRow(Sheet sheet) {
        return sheet.createRow(rownum++);
    }

    /**
     * 添加一个单元格
     *
     * @param row    添加的行
     * @param column 添加列号
     * @param val    添加值
     * @param align  对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格对象
     */
    public Cell addCell(SXSSFWorkbook wb, Row row, int column, Object val, int align, Class<?> fieldType) {
        Cell cell = row.createCell(column);
        CellStyle style = styles.get("data" + (align >= 1 && align <= 3 ? align : ""));
        try {
            wb.createDataFormat();
            if (val == null) {
                cell.setCellValue("");
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
            } else if (val instanceof Integer) {
                cell.setCellValue((Integer) val);
            } else if (val instanceof Long) {
                cell.setCellValue((Long) val);
            } else if (val instanceof Double) {
                cell.setCellValue((Double) val);
            } else if (val instanceof Float) {
                cell.setCellValue((Float) val);
                Calendar cal = Calendar.getInstance();
                cal.setTime((Date) val);
                int hour = cal.get(Calendar.HOUR);//小时
                int minute = cal.get(Calendar.MINUTE);//分
                int second = cal.get(Calendar.SECOND);//秒
                if (hour > 0 || minute > 0 || second > 0) {
                    cell.setCellValue(DateUtils.formatDate((Date) val, "yyyy-MM-dd HH:mm:ss"));
                } else {
                    cell.setCellValue(DateUtils.formatDate((Date) val, "yyyy-MM-dd"));
                }
            } else {
                if (fieldType != Class.class) {
                    cell.setCellValue((String) fieldType.getMethod("setValue", Object.class).invoke(null, val));
                } else {
                    cell.setCellValue((String) Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
                            "fieldtype." + val.getClass().getSimpleName() + "Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
            }
        } catch (Exception ex) {
            log.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        cell.setCellStyle(style);
        return cell;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<Object[]> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<Object[]> annotationList) {
        this.annotationList = annotationList;
    }

}

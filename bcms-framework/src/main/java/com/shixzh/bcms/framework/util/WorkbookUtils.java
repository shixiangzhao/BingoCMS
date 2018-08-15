package com.shixzh.bcms.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoshixiang on 2018/8/15.
 */
public class WorkbookUtils {

    private static Logger log = LoggerFactory.getLogger(WorkbookUtils.class);

    /**
     * 工作薄对象
     */
    private SXSSFWorkbook wb;

    /**
     * 构造函数
     *
     * @param sheetConfig sheet的配置与数据列表
     */
    public WorkbookUtils(List<SheetConfig> sheetConfig) {
        this(sheetConfig, 1);
    }

    /**
     * 构造函数
     *
     * @param scList sheet的配置与数据列表
     * @param type   导出类型（1:导出数据；2：导出模板）
     * @param groups 导入分组
     */
    public WorkbookUtils(List<SheetConfig> scList, int type, int... groups) {
        this.wb = new SXSSFWorkbook(500);
        for (SheetConfig config : scList) {
            SheetUtils sheetUtils = new SheetUtils(this.wb, config);
            setDataList(config.getData(), sheetUtils);
        }
    }

    /**
     * 添加数据（通过annotation.ExportField添加数据）
     *
     * @return list 数据列表
     */
    public <E> WorkbookUtils setDataList(List<E> list, SheetUtils sheetUtils) {
        Sheet sheet = sheetUtils.getSheet();
        for (E e : list) {
            int column = 0;
            Row row = sheetUtils.addRow(sheet);
            StringBuilder sb = new StringBuilder();
            for (Object[] os : sheetUtils.getAnnotationList()) {
                ExcelField ef = (ExcelField) os[0];
                Object val = null;
                // Get entity value
                try {
                    if (StringUtils.isNotBlank(ef.value())) {
                        val = ReflectionUtils.invokeGetter(e, ef.value());
                    } else {
                        if (os[1] instanceof Field) {
                            val = ReflectionUtils.invokeGetter(e, ((Field) os[1]).getName());
                        } else if (os[1] instanceof Method) {
                            val = ReflectionUtils.invokeMethod(e, ((Method) os[1]).getName(), new Class[]{}, new Object[]{});
                        }
                    }
                } catch (Exception ex) {
                    // Failure to ignore
                    log.info(ex.toString());
                    val = "";
                }
                sheetUtils.addCell(this.wb, row, column++, val, ef.align(), ef.fieldType());
                sb.append(val + ", ");
            }
            log.debug("Write success: [" + row.getRowNum() + "] " + sb.toString());
        }
        return this;
    }

    /**
     * 输出数据流
     *
     * @param os 输出数据流
     */
    public WorkbookUtils write(OutputStream os) throws IOException {
        wb.write(os);
        if (os != null) {
            os.close();
        }
        return this;
    }

    /**
     * 输出到客户端
     *
     * @param fileName 输出文件名
     */
    public WorkbookUtils write(HttpServletResponse response, String fileName) throws IOException {
        response.resetBuffer();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + EncodeUtils.urlEncode(fileName));
        write(response.getOutputStream());
        return this;
    }

    /**
     * 输出到文件
     *
     * @param fileName 输出文件名
     */
    public WorkbookUtils writeFile(String fileName) throws FileNotFoundException, IOException {
        FileOutputStream os = new FileOutputStream(fileName);
        this.write(os);
        if (os != null) {
            os.close();
        }
        return this;
    }

    /**
     * 清理临时文件
     */
    public WorkbookUtils dispose() {
        wb.dispose();
        return this;
    }

    /**
     * 导出测试
     */
    public static void main(String[] args) throws Throwable {
        class User {
            @ExcelField(title = "title1")
            private String id;
            @ExcelField(title = "title2")
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        class Person {
            @ExcelField(title = "title01")
            private String id;
            @ExcelField(title = "title02")
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        User u1 = new User();
        u1.setId("1");
        u1.setName("name154");
        User u2 = new User();
        u2.setId("2");
        u2.setName("name223");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        Person p1 = new Person();
        p1.setId("3");
        p1.setName("name365");
        Person p2 = new Person();
        p2.setId("4");
        p2.setName("name445");
        List<Person> list1 = new ArrayList<>();
        list1.add(p1);
        list1.add(p2);
        // 导出多个sheet
        SheetConfig sc1 = new SheetConfig("user", "用户", User.class, list);
        SheetConfig sc2 = new SheetConfig("person", "人员", Person.class, list1);
        List<SheetConfig> sheetConfig = Arrays.asList(sc1, sc2);
        WorkbookUtils exportExcel = new WorkbookUtils(sheetConfig);
        exportExcel.writeFile("D://export.xlsx");

    }

}

package com.jason.system.util;

import com.jason.system.annotation.Excel;
import com.jason.system.annotation.Excels;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>描述:导出Excel帮助类
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/16 11:44
 * @see
 */
public class ExcelUtil<T> {

    /**
     * 列头样式key模板
     */
    private static final String COLUMN_STYLE_KEY_TEMP = "head_{}";

    /**
     * 数据样式key模板
     */
    private static final String DATA_STYLE_KEY_TEMP = "data_{}";

    /**
     * 数据集合
     */
    private List<T> list;

    /**
     * 工作表名
     */
    private String sheetName;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Excel.Type type;

    /**
     * excel标题名
     */
    private String title;

    /**
     * 注解属性列表
     */
    private List<Object[]> fields;


    /**
     * 需要排除的属性
     */
    private Field[] excludeField;

    /**
     * 对象的子列表方法
     */
    private Method subMethod;

    /**
     * 对象的子列表属性
     */
    private HashMap<Field,List<Object[]>> subFields;
    /**
     * 最大高度
     */
    private double maxHeight;

    private Class<?> clazz;

    private Workbook wb;
    private Sheet sheet;
    /**
     * 列表样式
     */
    private Map<String, CellStyle> styles;

    private int rowNum = 0;

    /**
     * 合并后最后行数
     */
    private int subMergedLastRowNum = 0;

    /**
     * 合并后开始行数
     */
    private int subMergedFirstRowNum = 1;
    /**
     * Excel 每个Sheet最大行数
     */
    private int sheetSize = 65536;


    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    private void init(List<T> list, String sheetName, String title, Excel.Type type) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        this.title = title;

        createExcelFiled();
        createWorkBook();
        createTitle(this.title);

        createColumnName();

    }


    private void createExcelFiled() {

        this.subFields = new HashMap<>();
        //所有属性上面的注解
        this.fields = getFiled(this.clazz);
        this.fields = fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());

        this.maxHeight = getRowMaxHeight();
    }


    /**
     * 获取标记注解的属性
     *
     * @return
     */
    private List<Object[]> getFiled(Class<?> clazz) {
        //属性集合
        List<Object[]> filedList = new ArrayList<>();
        List<Field> tempField = new ArrayList<>();
        tempField.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempField.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempField) {
            //排除指定的字段
            if (!ArrayUtil.contain(this.excludeField, field.getName())) {
                //但注解
                if (field.isAnnotationPresent(Excel.class)) {
                    Excel excel = field.getAnnotation(Excel.class);
                    field.setAccessible(true);
                    //判断是否为数组或列表
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        subMethod = getSubMethod(field.getName(), clazz);
                        ParameterizedType pt = (ParameterizedType) field.getGenericType();
                        Class<?> subClass = (Class<?>) pt.getActualTypeArguments()[0];
                        this.subFields.put(field,getFiled(subClass));
                        //this.subFields = FieldUtils.getFieldsListWithAnnotation(subClass, Excel.class);
                        FieldUtils.getFieldsListWithAnnotation(subClass, Excel.class);
                        continue;
                    }

                    if (excel != null && (excel.type() == Excel.Type.ALL || excel.type() == type)) {
                        filedList.add(new Object[]{field, excel});
                    }

                }
                // 多注解
                if (field.isAnnotationPresent(Excels.class)) {
                    Excels excels = field.getAnnotation(Excels.class);
                    if (excels == null || excels.value() == null) {
                        continue;
                    }
                    for (Excel excel : excels.value()) {
                        if (excel != null && (excel.type() == Excel.Type.ALL || excel.type() == type)) {
                            field.setAccessible(true);
                            filedList.add(new Object[]{field, excel});
                        }
                    }
                }
            }
        }
        return filedList;
    }



    /**
     * 创建工作薄
     *
     * @return
     */
    private void createWorkBook() {
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet();
        this.wb.setSheetName(0, sheetName);
        this.styles = createStyle();
    }


    /**
     * 创建单元格样式
     *
     * @return
     */
    private Map<String, CellStyle> createStyle() {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<>();
        //创建表头单元格样式
        CellStyle style = this.wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //单元格对齐方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);

        style.setFont(titleFont);
        styles.put("title", style);

        //创建数据单元格样式
        style = this.wb.createCellStyle();
        //设置单元格对齐方式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置单元格边框样式
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());


        Font dataFont = this.wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setBold(true);
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = this.wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font totalFont = this.wb.createFont();
        totalFont.setFontName("Arial");
        totalFont.setBold(true);
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);

        styles.put("total", style);

        annotationHeadStyle(this.wb, styles);
        annotationDataStyle(this.wb, styles);
        return styles;

    }


    /**
     * 根据注解创建表头样式
     *
     * @param workbook
     * @param styles
     */
    private void annotationHeadStyle(Workbook workbook, Map<String, CellStyle> styles) {
        CellStyle style = null;
        Font font = null;

        for (Object[] object : this.fields) {
            Field field = (Field) object[0];
            Excel excel = (Excel) object[1];
            String key = StringUtils.format(COLUMN_STYLE_KEY_TEMP, field.getName());
            //创建单元格样式
            style = workbook.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));

            style.setAlignment(excel.align());
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFillForegroundColor(excel.headerBackgroundColor().index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);


            font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 10);
            font.setColor(excel.headerColor().index);
            style.setFont(font);
            styles.put(key, style);
        }
    }

    /**
     * 根据注解创建数据样式
     */
    private void annotationDataStyle(Workbook workbook, Map<String, CellStyle> styles) {
        CellStyle style = null;
        Font font = null;
        for (Object[] object : this.fields) {
            Field field = (Field) object[0];
            Excel excel = (Excel) object[1];
            String key = StringUtils.format(DATA_STYLE_KEY_TEMP, field.getName());
            //创建单元格样式
            style = workbook.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));

            style.setAlignment(excel.align());
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFillForegroundColor(excel.backgroundColor().getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 10);
            font.setColor(excel.color().index);
            style.setFont(font);
            styles.put(key, style);
        }
    }


    /**
     * 创建表---表头
     */
    private void createTitle(String title) {
        if (StringUtils.isNotEmpty(title)) {
            subMergedFirstRowNum++;
            subMergedLastRowNum++;
            int titleLastCol = this.fields.size() - 1;
            if (isSubList()) {
                for (Field field: subFields.keySet()) {
                    titleLastCol = titleLastCol + subFields.get(field).size() - 1;
                }
            }
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints((float) maxHeight);
            Cell cell = row.createCell(0);
            cell.setCellStyle(styles.get("title"));
            cell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), row.getRowNum(), titleLastCol));
        }
    }

    /**
     * 创建列名
     */
    private void createColumnName() {
        Row row = this.sheet.createRow(rowNum++);
        row.setHeightInPoints((short) 16);
        Cell cell = null;

        for (int i = 0; i < this.fields.size(); i++) {
            cell = row.createCell(i);
            Field field = (Field) this.fields.get(i)[0];
            Excel excel = (Excel) this.fields.get(i)[1];

            if (subFields.containsKey(field)) {
                // todo 创建子列表 列名
            }
            double width = excel.width();
            this.sheet.setColumnWidth(i, (int) (width*256));
            StringBuilder columnName = new StringBuilder();
            columnName.append(excel.name());
            String prompt = excel.prompt();
            if (StringUtils.isNotEmpty(prompt)) {
                columnName.append("( ");
                columnName.append(prompt);
                columnName.append(" )");
            }
            cell.setCellValue(columnName.toString());
            cell.setCellStyle(styles.get(StringUtils.format(COLUMN_STYLE_KEY_TEMP, field.getName())));

            if (isSubList() && !subFields.containsKey(field)) {
                this.sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum() + 1, i, i));
            }
        }
    }



    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName) {
        exportExcel(response, list, sheetName, "用户信息");
    }

    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, String title) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        this.init(list, sheetName, title, Excel.Type.EXPORT);

        exportExcel(response);
    }

    public void exportExcel(HttpServletResponse response) {
        try {
            writeSheet();
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            System.out.println("导出Excel报错");
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * 写入工作薄
     */
    private void writeSheet() {
        int sheetNo = Math.max(1, (int) Math.ceil(this.list.size() * 1.0 / sheetSize));
        for (int no = 0; no < sheetNo; no++) {
            if (no > 0) {
                this.sheet = this.wb.createSheet(sheetName + no);
                subMergedLastRowNum = 0;
                subMergedFirstRowNum = 1;
                rowNum = 0;
                this.createTitle(this.title);
                this.createColumnName();
            }
            fillExcelData(no * sheetSize, (no + 1) * sheetSize);
        }
    }


    /**
     * 填充表格数据
     *
     * @param starIndex list的开始位置
     * @param endIndex  list的结束位置
     */
    private void fillExcelData(int starIndex, int endIndex) {
        if (starIndex < 0) {
            starIndex = 0;
        }
        if (endIndex < starIndex) {
            endIndex = starIndex;
        }
        if (endIndex > this.list.size()) {
            endIndex = list.size();
        }

        Row row = null;
        for (int i = starIndex; i < endIndex; i++) {

            row = this.sheet.createRow(rowNum++);
            row.setHeightInPoints((float) maxHeight);
            T vo = list.get(i);
            Object ob = null;
            for (int j = 0; j < this.fields.size(); j++) {
                Field field = (Field) this.fields.get(j)[0];
                Excel excel = (Excel) this.fields.get(j)[1];
                try {
                    ob = getFieldValue(field, excel, vo);
                } catch (Exception e) {
                    System.err.println("属性获取失败  " + e.getMessage());
                }
                Cell cell = row.createCell(j);
                cell.setCellStyle(styles.get(StringUtils.format(DATA_STYLE_KEY_TEMP, field.getName())));
                cell.setCellValue(ob.toString());
            }
        }
    }

    /**
     * 获取属性值
     *
     * @param file  属性字段
     * @param excel file字段上面的Excel注解
     * @param vo    实体
     * @return
     * @throws Exception
     */
    private String getFieldValue(Field file, Excel excel, T vo) throws Exception {
        Object ob = file.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr())) {
            String target = excel.targetAttr();
            if (target.contains(".")) {
                String[] targets = target.split("[.]");
                for (String name : targets) {
                    ob = getFieldValue(ob, name);
                }
            } else {
                ob = getFieldValue(ob, target);
            }
        }
        return fieldValuePares(ob, excel);
    }

    /**
     * 获取属性值
     *
     * @param o
     * @param name
     * @return
     * @throws Exception
     */
    private Object getFieldValue(Object o, String name) throws Exception {
        if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name)) {
            Class<?> clazz = o.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(o);
        }
        return o;
    }


    /**
     * 根据Excel注解处理属性值
     *
     * @param ob
     * @param excel
     * @return
     */
    private String fieldValuePares(Object ob, Excel excel) {
        String fieldValue = ob == null ? "" : ob.toString();
        String convert = excel.readConvertExp();
        String dateFormat = excel.dateFormat();
        String dictType = excel.dictType();
        String separator = excel.separator();
        if (StringUtils.isNotEmpty(convert)) {
            return convertByExp(fieldValue, convert);
        } else if (StringUtils.isNotEmpty(dateFormat)) {
            return dataFormat(fieldValue, dateFormat);
        } else if (StringUtils.isNotEmpty(dictType)) {
            return convertDictType(fieldValue, dictType);
        } else {
            return fieldValue;
        }
    }


    /**
     * @param fieldValue
     * @param convertExp
     * @return
     */
    private String convertByExp(String fieldValue, String convertExp) {
        String keyValues[] = convertExp.split(",");
        for (String keyValue : keyValues) {
            String key = keyValue.split("=")[0];
            String value = keyValue.split("=")[1];
            if (key.equals(fieldValue)) {
                return value;
            }
        }
        return "";
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    private String dataFormat(String date, String format) {
        if (StringUtils.isNotEmpty(date)) {
            return "";
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        return dateFormatter.format(date);
    }

    /**
     * 字典类型转换
     *
     * @param value
     * @param dictType
     * @return
     */
    private String convertDictType(String value, String dictType) {
        // todo 字典类型转换未实现
        return value;
    }


    /**
     * 是否有子列表
     *
     * @return
     */
    public boolean isSubList() {
        return StringUtils.isNotEmpty(subFields) && subFields.size() > 0;
    }

    /**
     * 获取对象的子列表方法
     *
     * @param name      名称
     * @param pojoClass 类对象
     * @return 子列表方法
     */
    public Method getSubMethod(String name, Class<?> pojoClass) {
        StringBuffer getMethodName = new StringBuffer("get");
        getMethodName.append(name.substring(0, 1).toUpperCase());
        getMethodName.append(name.substring(1));
        Method method = null;
        try {
            method = pojoClass.getMethod(getMethodName.toString(), new Class[]{});
        } catch (Exception e) {
            System.out.println("获取对象异常{}" + e.getMessage());
        }
        return method;
    }

    /**
     * 获取行最大高度
     *
     * @return
     */
    private double getRowMaxHeight() {
        Object[] object = fields.stream().max(Comparator.comparing(objects -> ((Excel) objects[1]).height())).orElse(null);
        double maxHeight = ((Excel) object[1]).height();
        return maxHeight;
    }

}

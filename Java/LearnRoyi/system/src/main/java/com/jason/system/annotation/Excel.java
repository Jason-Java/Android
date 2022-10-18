package com.jason.system.annotation;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * <p>描述:导入导出Excel注解
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/16 11:05
 * @see
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 导出时在Excel中的排序
     * @return
     */
    int sort() default Integer.MAX_VALUE;

    /**
     * 导出到Excel中列表名
     * @return
     */
    String name() default "";

    /**
     * 日期格式 如：yyyy-MM-dd
     * @return
     */
    String dateFormat() default "";

    /**
     * 如果是字典类型，请设置字典的type值 （如：sys_user_sex）
     *
     * @return
     */
    String dictType() default "";

    /**
     * 读取内容转换表达式 如（0=男,1=女,2=未知）
     * @return
     */
    String readConvertExp() default "";

    /**
     * 分隔符，读取字符串组内容
     * @return
     */
    String separator() default "";

    /**
     * BigDecimal 精度 默认：-1（默认不开启BigDecimal格式化）
     * @return
     */
    int scale() default -1;

    /**
     * BigDecimal 舍入规则 默认：BigDecimal.round_half_even
     *
     * @return
     */
    int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    /**
     * 每行的高度 单位：字符
     *
     * @return
     */
    double height() default 14;

    /**
     * 每列的宽度  单位：字符
     *
     * @return
     */
    double width() default 16;

    /**
     * 文字后缀 如% 90 变成90%
     * @return
     */
    String suffix() default "";

    /**
     *当值为空的时候 字段的默认值
     * @return
     */
    String defaultValue() default "";

    /**
     * 提示信息
     * @return
     */
    String prompt() default "";

    /**
     * 设置只能选择不能输入的列内容.
     */
    public String[] combo() default {};

    /**
     * 是否需要纵向合并单元格,应对需求:含有list集合单元格)
     */
    public boolean needMerge() default false;

    /**
     * 是否导出数据,应对需求:有时我们需要导出一份模板,这是标题需要但内容需要用户手工填写.
     */
    public boolean isExport() default true;
    /**
     * 另一个类中的属性名称,支持多级获取,以小数点隔开
     */
    public String targetAttr() default "";

    /**
     * 是否自动统计数据,在最后追加一行统计数据总和
     */
    public boolean isStatistics() default false;



    /**
     * 导出列头背景色
     */
    public IndexedColors headerBackgroundColor() default IndexedColors.GREY_50_PERCENT;

    /**
     * 导出列头字体颜色
     */
    public IndexedColors headerColor() default IndexedColors.WHITE;

    /**
     * 导出单元格背景色
     */
    public IndexedColors backgroundColor() default IndexedColors.WHITE;

    /**
     * 导出单元格字体颜色
     */
    public IndexedColors color() default IndexedColors.BLACK;

    /**
     * 导出字段对齐方式
     */
    public HorizontalAlignment align() default HorizontalAlignment.CENTER;

    /**
     * 自定义数据处理器
     */
   // public Class<?> handler() default ExcelHandlerAdapter.class;

    /**
     * 自定义数据处理器参数
     */
    public String[] args() default {};

    /**
     * 字段类型（0：导出导入；1：仅导出；2：仅导入）
     */
    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }


}

package com.pongsky.kit.excel.handler.export;

import com.pongsky.kit.excel.annotation.ExcelProperty;
import com.pongsky.kit.excel.entity.ExcelExportInfo;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Month 处理器
 *
 * @author pengsenhao
 **/
public class ExcelIntegerMonthExportHandler implements ExcelExportHandler {

    private static final Map<Integer, String> INTEGER_MONTH_MAP = new ConcurrentHashMap<>(16) {
        {
            put(1, "一月");
            put(2, "二月");
            put(3, "三月");
            put(4, "四月");
            put(5, "五月");
            put(6, "六月");
            put(7, "七月");
            put(8, "八月");
            put(9, "九月");
            put(10, "十月");
            put(11, "十一月");
            put(12, "十二月");
        }
    };

    @Override
    public void exec(Field field, ExcelProperty excelProperty, Object obj, ExcelExportInfo info) {
        String value = obj == null
                ? excelProperty.defaultValue()
                : INTEGER_MONTH_MAP.get((Integer) obj);
        info.getCell().setCellValue(new XSSFRichTextString(value));
        info.setTextWidth(info.getCell().getColumnIndex(), value.length());
    }

}

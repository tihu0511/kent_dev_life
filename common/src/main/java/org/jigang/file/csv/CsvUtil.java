package org.jigang.file.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jigang.reflect.ReflectUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BF100271 on 2016/6/24.
 */
public class CsvUtil {
    /**
     * 解析csv文件
     * 解析大文件慎用，容易内存溢出
     * @param csvFile csv文件路径
     * @param clazz 解析完的类型
     * @param charset 编码格式
     * @return
     * @throws Exception
     */
    public static <T> List<T> parseCSV(String csvFile, Class<T> clazz, String charset) throws Exception {
        List<T> list = new ArrayList<T>();
        Field[] fields = clazz.getDeclaredFields();

        InputStreamReader in = new InputStreamReader(new FileInputStream(csvFile), charset);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            T instance = clazz.newInstance();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CsvHeader.class)) {
                    CsvHeader annotation = field.getAnnotation(CsvHeader.class);
                    String value = annotation.value();
                    field.setAccessible(true);
                    ReflectUtil.setFieldValue(field, instance, record.get(value));
                }
            }
            list.add(instance);
        }
        return list;
    }

    /**
     * 解析csv文件
     *
     * @param csvFile csv文件路径
     * @param charset 编码格式
     * @return 返回Iterable，for循环遍历时是逐行取csv记录
     * @throws IOException
     */
    public static Iterable<CSVRecord> parseCSV(String csvFile, String charset) throws IOException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(csvFile), charset);
        return CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
    }

    /**
     * 解析csv文件
     *
     * @param csvFile csv文件路径
     * @param charset 编码格式
     * @param callback 业务回调接口
     * @throws Exception
     */
    public static <T> void parseCSVAndDeal(String csvFile, String charset, CsvDealCallback callback) throws IOException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(csvFile), charset);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            callback.deal(record);
        }
    }
}

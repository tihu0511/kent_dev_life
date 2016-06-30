package org.jigang.file.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.jigang.reflect.ReflectUtil;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BF100271 on 2016/6/24.
 */
public class CsvUtil {
    private static final Logger logger = Logger.getLogger(CsvUtil.class);

    private static final String DEFAULT_RECORD_SEPRATER = "\n";

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

    /**
     * 写入csv文件
     * @param file 需要写入的csv文件
     * @param header 第一行头名称
     * @param isAppendMode 是否为追加模式
     * @param dataList 需要写入的数据
     * @param writeRecord 写入每行数据的逻辑
     * @param <T>
     * @throws IOException
     */
    public static <T> void writeCsvFile(String file, boolean isAppendMode, String[] header, List<T> dataList,
            CsvWriteRecord<T> writeRecord) throws IOException {
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        try {
            //创建 CSVFormat
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(DEFAULT_RECORD_SEPRATER);
            //初始化FileWriter
            fileWriter = new FileWriter(file, isAppendMode);
            //初始化 CSVPrinter
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //创建CSV文件头,追加模式不需要写入文件头
            if (!isAppendMode) {
                csvFilePrinter.printRecord(header);
            }

            // 用户对象放入List
            if (dataList != null && dataList.size() > 0) {
                for (T data : dataList) {
                    csvFilePrinter.printRecord(writeRecord.generateRecord(data));
                }
            }
            fileWriter.flush();
            logger.info("CSV file written finished.");
        } finally {
            if (null != csvFilePrinter) {
                csvFilePrinter.close();
            }
            if (null != fileWriter) {
                fileWriter.close();
            }
        }
    }
}

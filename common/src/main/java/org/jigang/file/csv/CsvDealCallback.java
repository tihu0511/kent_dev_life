package org.jigang.file.csv;

import org.apache.commons.csv.CSVRecord;

/**
 * 解析CSV数据后的业务回调接口
 *
 * Created by BF100271 on 2016/6/24.
 */
public interface CsvDealCallback {
    /**
     * 业务处理方法
     * @param record
     * @return 返回
     */
    boolean deal(CSVRecord record);
}

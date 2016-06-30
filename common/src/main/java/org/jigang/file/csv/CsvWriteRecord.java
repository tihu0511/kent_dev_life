package org.jigang.file.csv;

import java.io.IOException;
import java.util.List;

/**
 * Created by wujigang on 16/6/30.
 */
public interface CsvWriteRecord<T> {
    /**
     * 生成每行记录
     * @param data
     * @return
     * @throws IOException 可以抛出异常给上游捕捉
     */
    List<String> generateRecord(T data) throws IOException;
}

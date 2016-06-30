package org.jigang.file.csv;

import org.jigang.date.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wujigang on 16/6/30.
 */
public class CsvTest {
    @Test
    public void writeCsvFile() throws IOException {
        String fileName = "/Users/wendy/develop/tmp/csv/FooDemo.csv";
        String[] header = {"ID", "NAME", "DAY"};

        List<Foo> foos = new ArrayList<Foo>();
        foos.add(new Foo(1, "Kent", new Date()));
        foos.add(new Foo(2, "Wendy", DateUtil.addDays(new Date(), -2)));
        foos.add(new Foo(3, "John", DateUtil.addDays(new Date(), -5)));

        CsvUtil.writeCsvFile(fileName, true, header, foos, new CsvWriteRecord<Foo>() {
            public List<String> generateRecord(Foo data) throws IOException {
                List<String> record = new ArrayList<String>();
                record.add(String.valueOf(data.getId()));
                record.add(data.getName());
                record.add(DateUtil.format(data.getDate(), DateUtil.FORMAT_TIME));
                return record;
            }
        });
    }
}

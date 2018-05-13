package com.cmaotai.service.service;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FileService {

    public static boolean isInvoke() throws IOException {
        File file = new File("~/submit/submit.txt");
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        if (Files.isFile().test(file)) {
            String date = Files.readFirstLine(file, Charset.defaultCharset());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            if ((DateTime.parse(now, formatter).toDate().getTime() - DateTime.parse(date, formatter)
                .toDate().getTime()) / 1000 > 60 * 60) {
                FileUtils.writeStringToFile(file, now, Charset.defaultCharset().name());
                return true;
            }
            System.err.println("这次下单操作不执行，原因：上次操作【" + date + "】和这次操作【" + now + "】没到一个小时。");
            return false;
        } else {
            FileUtils.writeStringToFile(file, now, Charset.defaultCharset().name());
            return true;
        }
    }

}

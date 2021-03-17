package com.xwder.summary.file;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * @author xwder
 * @date 2021-03-01 17:15
 */
public class FileTest {

    @Test
    public void fileTest() {
        File file = new File("D:/cstc/overload/download/permitLicense/zip/2021/03/file.txt");
        System.out.println(file.exists());

        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    /**
     * 文件的创建时间 （linux为文件修改时间）
     */
    @Test
    public void fileCreateTimeTest() {
        try {
            Path path = Paths.get("D:\\dartsdk-windows-x64-release.zip");
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            long createTime = attr.creationTime().toMillis();
            Date date = new Date(createTime);
            System.out.println(DateUtil.formatDateTime(date));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

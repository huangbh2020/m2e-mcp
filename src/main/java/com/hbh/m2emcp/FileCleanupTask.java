package com.hbh.m2emcp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件清理任务
 *
 * @author hunagbh
 * @date 2025/05/26
 */
@Component
public class FileCleanupTask {

    private static final String DEFAULT_DIR = "D:\\mcp-doc";

    // 每天凌晨 4 点执行（cron 表达式）
    @Scheduled(cron = "0 0 4 * * ?")
    public void cleanupFiles() {
        String dirPath = System.getProperty("os.name").toLowerCase().contains("win") ? DEFAULT_DIR : "/mcp-doc";
        Path directory = Paths.get(dirPath);
        File[] files = directory.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }
}


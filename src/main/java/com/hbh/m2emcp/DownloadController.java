package com.hbh.m2emcp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 下载 Controller
 *
 * @author huangbh
 * @date 2025/05/27
 */
@RestController
public class DownloadController {



    private final static String DEFAULT_DIR = "D:\\mcp-doc";
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        // 构建文件路径
        String dirPath = System.getProperty("os.name").toLowerCase().contains("win") ? DEFAULT_DIR : "/mcp-doc";
        String fileName = filename+".xlsx";
        Path filePath = Paths.get(dirPath).resolve(fileName).normalize();
        Resource resource;

        try {
            resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("无法读取文件: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("文件读取异常: " + filename, e);
        }
    }
}

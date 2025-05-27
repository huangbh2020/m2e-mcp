package com.hbh.m2emcp.service;

import com.hbh.m2emcp.utils.MarkdownUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * markdown转其他格式服务实现
 *
 * @author huangbh
 * @date 2025/05/20
 */
@Service
public class Markdown2OtherServiceImpl implements Markdown2OtherService {

    @Resource
    private MarkdownUtils markerUtils;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final static String DEFAULT_DIR = "D:\\mcp-doc";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public String mark2Excel(String markdownStr) {
        // 参数校验
        if (markdownStr == null || markdownStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Markdown 内容不能为空");
        }

        // 解析表格
        List<List<String>> lists = markerUtils.parseMarkdownTable(markdownStr);
        if (lists == null || lists.isEmpty()) {
            throw new IllegalArgumentException("解析到的表格数据为空");
        }

        // 构建目录
        String dirPath = System.getProperty("os.name").toLowerCase().contains("win") ? DEFAULT_DIR : "/mcp-doc";
        File dir = new File(dirPath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("无法创建目标目录：" + dirPath);
        }

        // 添加时间戳防止重名
        String timestamp = LocalDateTime.now().format(TIME_FORMATTER);

        // 创建 Excel
        try {
            markerUtils.createExcel(timestamp + ".xlsx", dirPath, lists);
        } catch (Exception e) {
            throw new RuntimeException("生成 Excel 文件失败", e);
        }
        // 安全拼接路径
        return "下载地址："+baseUrl+contextPath+"/download/"+ timestamp;
    }
}

package com.hbh.m2emcp.mcp;

import com.hbh.m2emcp.service.Markdown2OtherService;
import jakarta.annotation.Resource;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * Markdown转Excel
 *
 * @author huangbh
 * @date 2025/05/20
 */
@Service
public class Markdown2OtherMcp {

    @Resource
    private Markdown2OtherService markdown2OtherService;

    /**
     * 更改为 Excel格式
     *
     * @param markdownStr Markdown str
     * @return {@link String }
     */
    @Tool(description = "把markdown表格转换成excel")
    public String changeToExcel(@ToolParam(description="markdown字符串")String markdownStr) {
       return markdown2OtherService.mark2Excel(markdownStr);
    }
}

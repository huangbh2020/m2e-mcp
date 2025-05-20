package com.hbh.m2emcp.service;

/**
 * Markdown转其他格式服务
 *
 * @author huangbh
 * @date 2025/05/20
 */
public interface Markdown2OtherService {

    /**
     * 更改为 Excel
     *
     * @param markdownStr Markdown str
     * @return String
     */
    String mark2Excel(String markdownStr,String fileName);
}

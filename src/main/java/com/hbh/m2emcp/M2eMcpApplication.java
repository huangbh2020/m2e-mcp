package com.hbh.m2emcp;

import com.hbh.m2emcp.mcp.Markdown2OtherMcp;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class M2eMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(M2eMcpApplication.class, args);
    }



    @Bean
    public ToolCallbackProvider weatherTools(Markdown2OtherMcp markdown2OtherMcp) {
        return MethodToolCallbackProvider.builder().toolObjects(markdown2OtherMcp).build();
    }

}

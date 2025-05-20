
---

## 🧩 项目功能描述

1. **Markdown 转 Excel**
    - 支持解析 Markdown 中的表格数据。
    - 将解析后的表格内容导出为 `.xlsx` 格式的 Excel 文件。
    - 提供两种输出方式：
        - 输出到指定目录下的文件。

2. **服务封装**
    - 使用 Spring Boot 构建服务模块。
    - 提供标准的服务接口 [Markdown2OtherService](file://2025\m2e-mcp\src\main\java\com\hbh\m2emcp\service\Markdown2OtherService.java#L8-L17) 和其实现类 [Markdown2OtherServiceImpl](file://D:\workspace\2025\m2e-mcp\src\main\java\com\hbh\m2emcp\service\Markdown2OtherServiceImpl.java#L18-L67)。
    - 可通过 REST API 或其他方式调用该服务实现文档转换。

3. **工具类支持**
    - 工具类 [MarkdownUtils](file://m2e-mcp\src\main\java\com\hbh\m2emcp\utils\MarkdownUtils.java#L26-L129) 实现了核心的解析和导出逻辑。
    - 包含对表格行的识别、单元格处理、Excel 创建等功能。

---

## ⚙️ 启动环境要求

### 1. **Java 环境**
- JDK 17 或更高版本。

### 2. **构建工具**
- Maven 。

### 3. **依赖库**
- Spring Boot 框架（Spring Context, Spring Boot Starter Web 等）。
- Apache POI：用于操作 Excel 文件（`org.apache.poi.xssf.usermodel.XSSFWorkbook`）。
- Lombok：简化 Java Bean 编写（使用 `@Slf4j`, `@Data` 等注解）。

### 4. **运行目录权限**
- 默认输出路径为 `D:\mcp-doc`（Windows）或 `/mcp-doc`（Linux/Unix），需确保程序有读写权限。

---

## 📁 主要目录与文件结构

```
src/
├── main/
│   ├── java/
│   │   └── com/hbh/m2emcp/
│   │       ├── service/               # 服务接口及实现
│   │       │   ├── Markdown2OtherService.java
│   │       │   └── Markdown2OtherServiceImpl.java
│   │       └── utils/
│   │           └── MarkdownUtils.java # 核心工具类
│   └── resources/
│       └── application.properties     # Spring 配置文件
└── test/
    └── java/
        └── com/hbh/m2emcp/
            └── MarkdownUtilsTest.java # 单元测试
```


---

## 🚀 启动方式建议

1. **Maven 启动 Spring Boot 项目**
   ```bash
   mvn spring-boot:run
   ```


2. **打包后运行**
   ```bash
   mvn clean package
   java -jar target/m2e-mcp.jar
   ```




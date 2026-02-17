# 仓库指南

## 项目结构与模块组织
- `src/main/java/com/teriteri/backend`：Spring Boot 后端主代码。
- `controller`：REST 接口层；`service` 与 `service/impl`：业务接口与实现。
- `mapper`：MyBatis-Plus 映射接口；XML 位于 `src/main/resources/com/teriteri/backend/mapper`。
- `pojo`、`pojo/dto`、`pojo/vo`：实体与数据传输对象。
- `config`、`utils`、`component`、`im`：基础设施代码（安全、Redis、启动流程、Netty IM）。
- `src/main/resources/static/esindex`：Elasticsearch 索引模板（用于初始化测试）。
- `src/test/java`：以 Spring 上下文为主的集成测试。
- `database/teriteri.sql`：数据库结构与初始化 SQL。
- `public/`：本地上传与分片存储目录。

## 构建、测试与开发命令
- `mvnw.cmd clean compile`（或 `./mvnw clean compile`）：编译项目并解析依赖。
- `mvnw.cmd test`：运行 JUnit 5 测试。当前测试使用 `@SpringBootTest`，依赖 MySQL、Redis、Elasticsearch 环境。
- `mvnw.cmd spring-boot:run`：启动后端服务（HTTP 默认端口 `7070`）；应用同时启动 Netty IM（`7071`）。
- `mvnw.cmd clean package -DskipTests`：在本地缺少集成依赖时快速打包。

## 代码风格与命名规范
- 使用 Java 8 规范，4 空格缩进，文件编码为 UTF-8。
- 包名全小写（如 `com.teriteri.backend...`）。
- 类名使用 `PascalCase`，方法/字段使用 `camelCase`，常量使用 `UPPER_SNAKE_CASE`。
- 控制器保持轻量，业务逻辑下沉到 `service/impl`。
- `mapper` 接口方法名需与 XML `statement id` 一致。

## 测试规范
- 测试框架：JUnit 5 + Spring Boot Test（`spring-boot-starter-test`）。
- 测试文件放在 `src/test/java` 对应包路径下。
- 命名建议：`*Tests` 或 `Test*`（示例：`BackendApplicationTests`、`TestComment`）。
- 运行完整测试前请先确认外部依赖可用，或提供安全的测试配置。

## 提交与合并请求规范
- 当前工作区不含 `.git` 元数据；提交信息请遵循 Conventional Commits（示例：`fix(comment): handle null fatherId`）。
- 每次提交保持单一目的、原子化变更。
- PR 需说明变更目的、影响范围、关联 issue、测试证据；接口变更请附请求/响应示例。

## 安全与配置建议
- 禁止提交真实账号密码、Access Key、主机私密配置等敏感信息。
- 敏感配置优先使用环境变量或未跟踪的本地覆盖文件。

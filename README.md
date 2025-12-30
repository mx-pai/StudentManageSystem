# 学生信息管理系统 (Student Information Management System)

基于 Spring Boot 和原生前端技术构建的学生信息管理系统，提供基于角色的访问控制（RBAC），支持教师和学生两种角色的不同操作权限。

## 技术栈

### 后端
- **核心框架**: Spring Boot 3.x
- **数据访问**: Spring JDBC Template
- **数据库**: MySQL 8.0
- **工具库**: Lombok

### 前端
- **结构与样式**: HTML5, CSS3, Bootstrap 5.3
- **脚本逻辑**: JavaScript (ES6+)
- **HTTP 客户端**: Axios
- **图标库**: Bootstrap Icons

## 功能特性

### 1. 用户认证与权限
- **登录/注册**: 支持用户注册和登录。
- **角色区分**:
  - **教师**: 用户名以 'T' 开头自动识别为教师。
  - **学生**: 其他用户名默认为学生。
- **权限控制**: 后端接口与前端页面均根据角色进行权限隔离。

### 2. 学生管理 (教师权限)
- **列表展示**: 分页展示所有学生信息。
- **搜索功能**: 支持按学号、姓名、专业进行模糊搜索。
- **数据维护**: 支持新增、编辑和删除学生信息。

### 3. 课程管理
- **教师权限**:
  - 课程的增删改查。
  - 支持按课程号、课程名搜索。
- **学生权限**:
  - 仅可查看课程列表。
  - 支持搜索课程。

### 4. 选课管理
- **教师权限**:
  - 查看所有选课记录。
  - 支持按学号、课程号搜索选课记录。
  - 录入/修改学生成绩。
  - 强制退课操作。
- **学生权限**:
  - 查看个人的选课记录及成绩。
  - 进行选课和退课操作。

### 5. 仪表盘
- 根据用户角色展示不同的统计信息和快捷操作入口。

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置
1. 创建数据库 `student_info`。
2. 导入数据库表结构（请参考 `src/main/resources/sql` 或根据实体类创建表）。
3. 修改 `src/main/resources/application.properties` 中的数据库连接信息：
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student_info?useSSL=false&serverTimezone=UTC
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 运行项目
1. 在项目根目录下运行 Maven 命令构建项目：
   ```bash
   mvn clean package
   ```
2. 运行生成的 JAR 包或直接在 IDE 中运行 `StudentInfoApplication` 主类。
3. 项目启动后，访问：`http://localhost:8080`

## 项目结构

```
src/main
├── java/edu/student/studentinfo
│   ├── common          # 通用工具类与异常处理
│   ├── controller      # 控制器层 (API 接口)
│   ├── dto             # 数据传输对象
│   ├── entity          # 实体类
│   ├── repository      # 数据访问层 (DAO)
│   └── service         # 业务逻辑层
└── resources
    ├── static          # 前端静态资源 (HTML, CSS, JS)
    └── application.properties # 配置文件
```

## API 说明
所有 API 均位于 `/api` 路径下，统一返回 JSON 格式数据。主要模块包括：
- `/api/auth`: 认证相关
- `/api/students`: 学生管理
- `/api/courses`: 课程管理
- `/api/sc`: 选课管理

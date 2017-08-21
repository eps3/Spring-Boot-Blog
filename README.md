## Spring Boot Blog


#### TODO

- 更新spring boot版本
- 完善开发文档
- 修改前端模版框架
- 添加评论表
- 添加多用户支持
- 添加插件式开发
- 添加企业站模版
- 优化前端页面



#### 1. 技术

- Spring Boot

- velocity 模板引擎

- JPA 数据层

- Spring Security 权限控制

- Ehcache 缓存

- Amaze-UI 前端框架(渣前端只能各种套用示例

- Editor.md Markdown编辑器



#### 2. 环境

- jdk 1.8

- IDEA

- Maven

> 本项目使用的lombok插件，如果您使用的是Idea import本项目，请安装lombok插件并设置setting->build->compiler->>annotation processors-->勾选Enable annotation processing


#### 3. 数据库

- 模仿了wordpress简略版



#### 4. 运行

```

maven clean package -Dmaven.test.skip=true

java jar ./target/xxxxx.jar

```



###### 修改密码

> 启动后回自动查找是否有用户，没有即创建一个账号密码均为admin的用户，后台可修改



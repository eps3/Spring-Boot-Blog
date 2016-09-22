##Spring Boot Blog




####技术

- Spring Boot

- velocity 模板引擎

- JPA 数据层

- Spring Security 权限控制

- Ehcache 缓存

- Amaze-UI 前端框架(渣前端只能各种套用示例

- Editor.md Markdown编辑器



####环境

- jdk 1.8

- IDEA

- Gradle 1.14

> 本项目使用的lombok插件，如果您使用的是Idea import本项目，请安装lombok插件并设置setting->build->compiler->>annotation processors-->勾选Enable annotation processing


####数据库

- 模仿了wordpress简略版



####运行

```

gradle runBoot

//或者

gradle clean bulid -x text

java jar ./build/libs/xxxxx.jar

```



######修改密码

> 启动后回自动查找是否有用户，没有即创建一个账号密码均为admin的用户，后台可修改




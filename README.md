## 基于微服务的网盘后端实现

### 分布式文件存储系统：fastdfs

### 服务

#### Spring Cloud Config
    
#### Netflix Zuul

#### Spring Eureka 

#### OAuth2 & JSON Web Token

#### Open Zipkin

#### user-service

#### file-system-service


### 数据库优化
#### 垂直分片
* 用户表：将经常更新字段拉出来，新建一张表；
* 用户表：为经常使用登录的手机号建立索引

## TODO
1. user表不与user_status，user_priority连接，返回响应信息，目前在配置类中实现
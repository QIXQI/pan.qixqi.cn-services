## 基于微服务的网盘后端实现

### 分布式文件存储系统：fastdfs

### 服务

#### Spring Cloud Config
    
#### Netflix Zuul

#### Spring Eureka 

#### OAuth2 & JSON Web Token
* Spring Security5.39 需要对客户端密钥和用户密码加密（辛酸，找了一天Bug）

#### Open Zipkin
* [TODO] Spring Boot 和 Spring Cloud 版本出现问题

#### user-service

#### file-system-service

#### Spring Cloud Stream & Kafka
* 生产者和消费者部署主机，需要配置 /etc/hosts 文件，指向kafka服务器ip


### 数据库优化
#### 垂直分片
* 用户表：将经常更新字段拉出来，新建一张表；
* 用户表：为经常使用登录的手机号建立索引
* file_link表：为了避免表的join，采用反范式编程，通过一些数据冗余，以空间换取时间，提高性能
* [TODO] file_share表：没有采用数据冗余，逻辑上与 file_link 表join
* [TODO] 注册时，~~用户表默认值不起作用，~~ 注册返回的用户对象，不包含默认值，目前策略，注册成功后重新登录
* [TODO] 更新用户表时，密码也必须提供
* [TODO] 登录时，不是提交json，而是使用表单数据 @RequestParam

#### 文件信息使用 mongodb存储
* 使用 json文件，初始化mongodb，两点改动：
    * 键值采用驼峰式
    * 新加 "_class"字段，并且最后mongodb中也保留此字段，据我思考，是因为mongodb是NoSQL，每条文档，都可以有不同的 "_class"类解析，还是很有必要的
    * [TODO] 由于json文件的每个对象，均可以存在关系型数据库中，可以设计自己的解析类，取消 "_class"字段存到mongodb中，节省空间
    * [TODO] FolderLinkService 中 getFolderLinks(String uid) 结果缓存

## TODO
1. user表不与user_status，user_priority连接，返回响应信息，目前在配置类中实现

## 版本控制
### Spring 2.3.10.RELEASE
### Spring Cloud Hoxton.SR11
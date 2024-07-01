## 平台简介


* 采用前后端分离的模式，微服务版本前端(基于 [Vue]
* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，权限认证使用Redis。
* 流量控制框架选型Sentinel，分布式事务选型Seata。



## 系统模块

~~~
com.ruoyi     
├── ruoyi-ui              // 前端框架 [80]
├── ruoyi-gateway         // 网关模块 [8080]
├── ruoyi-auth            // 认证中心 [9200]
├── ruoyi-api             // 接口模块
│       └── ruoyi-api-system                          // 系统接口
├── ruoyi-common          // 通用模块
│       └── ruoyi-common-core                         // 核心模块
│       └── ruoyi-common-datascope                    // 权限范围
│       └── ruoyi-common-datasource                   // 多数据源
│       └── ruoyi-common-log                          // 日志记录
│       └── ruoyi-common-redis                        // 缓存服务
│       └── ruoyi-common-seata                        // 分布式事务
│       └── ruoyi-common-security                     // 安全模块
│       └── ruoyi-common-swagger                      // 系统接口
├── ruoyi-modules         // 业务模块
│       └── ruoyi-system                              // 系统模块 [9201]
│       └── ruoyi-gen                                 // 代码生成 [9202]
│       └── ruoyi-job                                 // 定时任务 [9203]
│       └── ruoyi-asset                               // 资产模块 [9204]
│       └── ruoyi-file                                // 文件服务 [9300]
├── ruoyi-visual          // 图形化管理模块
│       └── ruoyi-visual-monitor                      // 监控中心 [9100]
├──pom.xml                // 公共依赖
~~~

## 架构图

<img src="https://oscimg.oschina.net/oscnet/up-82e9722ecb846786405a904bafcf19f73f3.png"/>

## 内置功能
0.  资产管理：添加资产人以及购买股票





## 演示图

<table>
    <tr>
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/requirement.jpg?raw=true"/></td>
         <td><img src="https://oscimg.oschina.net/oscnet/cd1f90be5f2684f4560c9519c0f2a232ee8.jpg"/></td>
    </tr>
   <tr>
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/asset_list_image.png?raw=true"/></td>   
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/asset_add_image.png?raw=true"/></td>
    </tr>
     <tr>
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/asset_update_image.png?raw=true"/></td>   
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/redis_iamge_code_image.png?raw=true"/></td>
    </tr>
       <tr>
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/webSocker_code_image.png?raw=true"/></td>   
        <td><img src="https://github.com/taohyson/interview/blob/main/md-images/third_docker_image.png?raw=true"/></td>
    </tr>
</table>



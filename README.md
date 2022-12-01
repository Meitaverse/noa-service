
    
### 1 服务端口
    8360

### 2 工程结构
    api  接口  
    service     相对具体的业务逻辑服务层  
    dao         数据访问层，与底层 MySQL、Oracle、Hbase、mongoDB 等进行数据交互  
    dto         数据传输对象，Service 或 Manager 向外传输的对象。 需要重写toString()方法  
    entry       此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。  需要重写toString()方法  
    config      配置  
    aop         切面  
    interceptor 拦截器  
    filter      过滤器  
    scheduler   定时器  
    exception   自定义异常  
    util        工具类  
    manager     通用业务处理层，它有如下特征:  
                    1) 对第三方平台封装的层，预处理返回结果及转化异常信息。(七牛 微信 腾讯云 等)  
                    2) 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理  
                    3) 与 DAO 层交互，对多个 DAO 的组合复用  

    
### 2.4 git 分支管理（参考git flow）  
    master 主分支 生产环境运行的代码
    hotfix-{} 热修复 从master切出 紧急修复线上bug 合并到master后需要合并到dev分支 然后删除    
    dev       开发分支  
    release-{} 预发布(可选) 从dev切出进行测试，不再合并新功能，只修复bug。完成测试和并在此分支上修复后合并master发布，再合并到dev后删除    
    feature-{} 功能分支  从dev分支切出。具体某个功能点开发，一般在这类分支进行开发。完成客服和测试后合并到dev  


# #规范3：部署
### 3.1 运行环境
       local 本地环境  
       dev   开发环境  
       prod  生产环境  
### 3.2 项目启动  
    方式一: 命令行  
        1. 进入项目根目录  
        2. 运行 SPRING_PROFILES_ACTIVE={运行环境} mvn spring-boot:run  
    方式二: 使用idea运行项目(推荐)  
### 3.3 项目部署（参考）  
    1. 打包  
        运行 mvn package  
        成功后在target目录下会生产 jar 包  
    2. build docker  
       运行 sudo mvn dockerfile:build
    3. 启动
        运行 mkdir -p data/db
        运行 docker-compose up -d

        运行 docker run -d noa-service:1.0-SNAPSHOT -p 8360:8360

### 3.4 接口文档
     swagger http://${ip}:8360/swagger-ui.html 或者 https://${base_url}/swagger-ui.html

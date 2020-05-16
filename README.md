# SmartRoom-Spring

# 1，添加jpa、mysql模块

<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--jpa 模块：关系型数据库-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!--数据库类型：mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    配置JPA
打开，/src/main/resources/application.properties

配置以下信息：

# 在项目初始化时，重新创建数据表
spring.jpa.hibernate.ddl-auto=create
# 指定连接的类型为mysql 连接的地址为：localhost 端口为3306 ，数据为springmvc
spring.datasource.url=jdbc:mysql://localhost:3306/springmvc
# 用户名为root
spring.datasource.username=root
# 密码为空
spring.datasource.password=
    
    运行 SpringServerApplication
    在mysql服务启动的前提下，将得到启动成功的提示:
    2017-04-05 15:16:17.776  INFO 4627 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-04-05 15:16:17.782  INFO 4627 --- [           main] com.mengyunzhi.SpringMvcApplication      : Started SpringMvcApplication in 6.929 seconds (JVM running for 7.965)

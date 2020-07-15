# SmartRoom-Spring

## 1，添加jpa、mysql模块

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

## #在项目初始化时，重新创建数据表
spring.jpa.hibernate.ddl-auto=create
## #指定连接的类型为mysql 连接的地址为：localhost 端口为3306 ，数据为springmvc
spring.datasource.url=jdbc:mysql://localhost:3306/springmvc
## #用户名为root
spring.datasource.username=root
## #密码为空
spring.datasource.password=
    
    运行 SpringServerApplication
    在mysql服务启动的前提下，将得到启动成功的提示:
    2017-04-05 15:16:17.776  INFO 4627 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-04-05 15:16:17.782  INFO 4627 --- [           main] com.mengyunzhi.SpringMvcApplication      : Started SpringMvcApplication in 6.929 seconds (JVM running for 7.965)



## 2,创建数据表

SpringMVC中集成了hibernate框架，所以在SrpingMVC,有关关系型数据库的部分，我们完全可以参考hibernate的开发文档。hibernate为我们提供了这样一个功能：将带有相关注解的java类自动与数据表进行关系。从而使我们可以完全的使用java代码来定义数据表。这样的做的优点当然很多，对于我们而言，我们再也不需要为了数据表不统一造成的各种莫名BUG而烦恼了。

JPA全称Java Persistence API.JPA通过JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。http://baike.baidu.com/item/JPA。

新建实体类
新建repository包，并在该包中，新建Picture类。
使用@Entity来说明该类对应一个数据表,数据表的名字与类名相同。
使用@Id来说明：此字段是该表的主键。
使用@GeneratedValue(strategy = GenerationType.AUTO)来说明：该主键的生成策略为自动，对应MySQL的属性为’Auto increment’

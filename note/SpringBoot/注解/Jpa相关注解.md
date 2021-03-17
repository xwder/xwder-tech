# SpringBoot Jpa相关注解



在Springboot应用开发中使用JPA时，通常在主应用程序所在包或者其子包的某个位置定义我们的Entity和Repository，这样基于Springboot的自动配置，无需额外配置，我们定义的Entity和Repository即可被发现和使用。但有时候我们需要定义Entity和Repository**不在应用程序所在包及其子包**，那么这时候就需要使用@EntityScan和@EnableJpaRepositories了。

```java
@Entity
@Table(name = "grade")
public class Grade {
   // 省略具体内容
}

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>, JpaSpecificationExecutor<Grade> {
   // 省略具体内容
}
```

## @EntityScan

@EntityScan用来扫描和发现指定包及其子包中的Entity定义。其用法如下:

```java
@EntityScan(basePackages = {"com.department.entities","come.employee.entities"})
```


如果多处使用@EntityScan，它们的basePackages集合能覆盖所有被Repository使用的Entity即可，**集合有交集也没有关系**。但是如果不能覆盖被Repository使用的Entity，应用程序启动是会出错,比如：

Not a managed type: com.customer.entities.Customer@EnableJpaRepositories

## @EnableJpaRepositories

@EnableJpaRepositories用来扫描和发现指定包及其子包中的`Repository`定义。其用法如下:

```java
@EnableJpaRepositories(basePackages = {"com.department.repositories","come.employee.repositories"})
```

如果多处使用@EnableJpaRepositories，它们的basePackages**集合不能有交集**，并且要能覆盖所有需要的Repository定义。

如果有交集，相应的Repository会被尝试反复注册，从而遇到如下错误:

The bean ‘OrderRepository’, defined in xxx, could not be registered. A bean with that name has already been defined in xxx and overriding is disabled.

如果不能覆盖所有需要的Repository定义，会遇到启动错误：

Parameter 0 of method setCustomerRepository in com.service.CustomerService required a bean of type ‘come.repo.OrderRepository’ that could not be found.
**@EntityScan和@EnableJpaRepositories参考示例**：

```java
@SpringBootApplication(exclude = {
        RedisManager.class,
        MongoManager.class
})
@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseJpaRepositoryFactoryBean.class,basePackages = {"com.cstc", "com.csg.sone.base"})
@ComponentScan(basePackages = {"com.cstc", "com.csg.sone.base"})
@EntityScan(basePackages = {"com.cstc.sonep"})
@EnableJpaAuditing(auditorAwareRef = "sonepAuditorAware")
@EnableScheduling
@EnableAspectJAutoProxy
@ServletComponentScan(basePackages = {"com.cstc", "com.csg.sone.base"})
@EnableConfigurationProperties
public class PortalApplication extends SpringBootServletInitializer{

 @Value("${spring.application.name}")
    String applicationName;


    public static void main(String[] args) {
        System.setProperty("user.timezone", TimeZone.getDefault().getID());
        SpringApplication springApplication = new SpringApplication(PortalApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortalApplication.class);
    }

}
```

参考：[Springboot应用中@EntityScan和@EnableJpaRepositories的用法](https://andyboke.blog.csdn.net/article/details/84099595)
# Maven scope依赖范围详解

## scope简单理解

maven的scope的范围整理。

Maven的生命周期存在**编译**、**测试**、**运行**等不同周期，有些依赖只用于测试，比如junit；

有些依赖编译用不到，只有运行的时候才能用到，比如mysql的驱动包在编译期就用不到（编译期用的是JDBC接口），而是在运行时用到的；

JDBC原理就是统一数据库访问接口，各种不同数据库厂商针对自己的数据库产品实现各自的数据库访问驱动，这样在编码访问数据库的时候编译过程中只需要使用到jdbc接口，然后针对不同的数据库提供不同的jdbc接口实现，以达到屏蔽各种数据库厂商接口差异的屏蔽，参考下面JDBC结构图。

![](https://cdn.xwder.com/image/blog/xwder/1-20210315172016145.jpg)

在运行时用到的；还有些依赖，编译期要用到，而运行期不需要提供，因为有些容器已经提供了，比如servlet-api在tomcat中已经提供了，我们只需要的是编译期提供而已。如果运行时候出现servlet版本依赖冲突，可以从这方面去排查问题。

在POM 4中，<dependency>中还引入了<scope>，它主要管理依赖的部署。大致有compile、provided、runtime、test、system等几个。

- compile：默认的scope，运行期有效，需要打入包中
- provided：编译期有效，运行期不需要提供，不会打入包中
- runtime：编译不需要，在运行期有效，需要导入包中。（接口与实现分离）
- test：测试需要，不会打入包中
- system：非本地仓库引入、存在系统的某个路径下的jar。（一般不使用）

## scope的范围

### compile模式(**编译范围**)

默认就是compile，什么都不配置也就是意味着compile。compile表示被依赖项目需要参与当前项目的编译，当然后续的测试，运行周期也参与其中，是一个比较强的依赖。打包的时候通常需要包含进去.

该依赖需要参与当前项目的编译、测试、运行、打包

### runntime模式(**运行时范围**)

runntime表示被依赖项目无需参与项目的编译，不过后期的测试和运行周期需要其参与。与compile相比，跳过编译而已.

比如，你可能在编译的时候只需要JDBC API JAR，而只有在运行的时候才需要JDBC驱动实现。

编译时该包不参与，运行时参与

###  **test** 模式(测试范围)

test范围依赖 在一般的编译和运行时都不需要，它们只有在测试编译和测试运行阶段可用。

```xml
 <dependency>
 	<groupId>org.springframework.boot</groupId>
 	<artifactId>spring-boot-starter-test</artifactId>
 	<scope>test</scope>
 </dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>
```

###  **provided(已提供范围)**

provided 表明该依赖已经提供，故只在未提供时才被使用，

应用场景是你定义了一个Servlet，此刻得需要Servlet-api.jar 才能编译成功，但是当你达成war 包时，你并不想将 Servlet-api.jar 包进去，因为Tomcat等容器会提供

跟compile 类似，说明JDK、容器或使用者会提供这个依赖，如Servlet.jar

这个依赖只作用在编译和测试，该依赖会由系统组件提供，不需手动添加，只存在编译、运行、测试阶段，打包是不用包进去，打包阶段做了exclude**动作

provided意味着打包的时候可以不用包进去，别的设施(Web Container)会提供。

阿里开发规范文档：如果依赖其它二方库，尽量是 provided 引入，让二方库使用者去依赖具体版本号； 无 log 具体实现，只依赖日志框架

例如 ： 添加<scope>provided</scope>，因为provided表明该包只在编译和测试的时候用，所以，当启动tomcat的时候，就不会冲突了

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>3.0-alpha-1</version>
    <scope>provided</scope>
</dependency>
```

### **system (系统范围)**

被依赖项不会从maven仓库下载，而是从本地系统指定路径下寻找，需要 systemPath 属性

system范围依赖与provided 类似，但是你必须显式的提供一个对于本地系统中JAR 文件的路径，这么做是为了允许基于本地对象编译，而这些对象是系统类库的一部分。这样的构件应该是一直可用的，Maven 也不会在仓库中去寻找它

从参与度来说，与provided相同，不过被依赖项不会从maven仓库抓，而是从本地文件系统拿，一定需要配合systemPath属性使用，例如：

#### 一、system 方式1-dependency 本地jar包

dependency 本地jar包，如果没有建私服，可以在pom.xml中通过文件方式引

```xml
<dependency>
    <!--自定义-->
    <groupId>com.im</groupId> 
    <!--自定义-->
    <artifactId>sdk</artifactId>   
    <!--自定义-->
    <version>1.0</version>
    <!--system，类似provided，需要显式提供依赖的jar以后，Maven就不会在Repository中查找它-->
    <scope>system</scope>
    <!--项目根目录下的lib文件夹下-->
    <systemPath>${basedir}/lib/sdk-1.0.jar</systemPath>
</dependency>
```

#### 二、system 方式2-编译阶段指定外部lib

```xml
<plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>2.3.2</version>
    <configuration>
    <source>1.8</source>
    <target>1.8</target>
    <encoding>UTF-8</encoding>
    <compilerArguments>
    <!--指定外部lib-->
    <extdirs>lib</extdirs>
    </compilerArguments>
    </configuration>
</plugin>
```

#### 三、system 方式3-将外部jar打入本地maven仓库

```shell
mvn install:install-file -Dfile=sdk-1.0.jar -DgroupId=com.im -DartifactId=sdk -Dversion=1.0 -Dpackaging=jar
```

引入jar包

```xml
<dependency>
    <groupId>com.im</groupId>
    <artifactId>sdk</artifactId>
    <version>1.0</version>
</dependency>
```



### **import(导入)**

import仅支持在<dependencyManagement>中的类型依赖项上。它表示要在指定的POM <dependencyManagement>部分中用有效的依赖关系列表替换的依赖关系。该scope类型的依赖项``实际上不会参与限制依赖项的可传递性。

前面说过该类型作用于只在**dependencyManagement**内使用生效，它可以用来管理模块依赖，说白了就是针对包含了一系列子依赖进的模块导入到当前项目中进行管理使用，而不是把需要用到的依赖一个一个的加入到项目中进行管理，可以理解为多继承模式。比如在一些场景中：我们只是想单纯加入springboot模块的依赖，而不想将springboot作为父模块引入项目中，此时就可以使用import来处理。

一般我们会将springboot作为父模块引入到项目中，如下：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.9.RELEASE</version>
    <relativePath/>
</parent>
```

一个项目一般只能有一个父依赖模块，真实开发中，我们都会自定义自己的父模块，这样就会冲突了。所以我们可以使用import来将springboot做为依赖模块导入自己项目

## maven预定义内置属性

```
${basedir}表示项目根目录,即包含pom.xml文件的目录;
${version}表示项目版本;
${project.basedir}同${basedir};
${project.baseUri}表示项目文件地址;
${maven.build.timestamp}表示项目构件开始时间;
${maven.build.timestamp.format}表示属性${maven.build.timestamp}的展示格式,默认值为yyyyMMdd-HHmm,可自定义其格式
```

## scope 的传递依赖

A -> B -> C, 当前项目 A，A依赖于B，B依赖于C，知道B在 A中的scope，怎么知道 C在 A 中的 scope

即，A需不需要 C的问题，本质由 C在B中的scope决定

当 C 在 B 中的scope 是test 或 provided 时，C 直接被丢弃，A不依赖C

否则 A 依赖 C，C的scope 继承与B 的scope

## Maven optional

在maven中经常会使用**<optional>true</optional>**参数，如下：

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
</dependency>
```

此处的<optional>true</optional>的作用是让依赖只被当前项目使用，而不会在模块间进行传递依赖。

学习自：

[Maven scope依赖范围详解](https://www.cnblogs.com/molao-doing/articles/Maven.html)

[maven scope属性说明](https://www.cnblogs.com/kingsonfu/p/10342892.html)


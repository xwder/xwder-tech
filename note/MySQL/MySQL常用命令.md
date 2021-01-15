## MySQL常用命令

![mysql基础知识](https://cdn.xwder.com/image/blog/xwder/1-20210105153054422.jpg)

## 1.数据库、表操作命令

### 1.1数据库、表信息查询

1、获取所有可用的数据库：**SHOW DATABASES**；
2、选择数据库：**USE xwder；**
3、用于显示数据库服务器的状态信息：**SHOW STATUS；**
4、用来显示授权用户的安全权限：**SHOW GRANTS；**
5、用来显示数据库服务器或警告信息：**SHOW ERRORS 或者SHOW WARNINGS；**
6、用于显示创建数据库时的创建语句：**SHOW CREATE DATABASE xwder；**
7、用于显示创建表时的创建语句：**SHOW CREATE TABLE xwder；**
8、获取当前所选的数据库中所有可用的表：**SHOW TABLES；**
9、获取表中所有列的信息：**SHOW COLUMNS FROM tableName；**同时DESCRIBE语句有相同的效果：**DESCRIBE tableName；**

10、查看引擎： **SHOW ENGINES;**

### 1.2数据库、表信息操作命令

- 创建表

```sql
CREATE TABLE `book_info` (
 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `update_status` varchar(255) DEFAULT NULL,
  `word_size` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `latest_chapter` varchar(255) DEFAULT NULL,
  `book_desc` varchar(10000) DEFAULT NULL,
  `book_img_source` varchar(255) DEFAULT NULL,
  `book_site_img_source` varchar(255) DEFAULT NULL COMMENT '数据图片源地址',
  `book_img` varchar(255) DEFAULT NULL,
  `book_url` varchar(255) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name_author` (`book_name`,`author`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95699 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
```

- 删除数据库、表

```sql
DROP DATABASE xwder; -- 删除数据库
DROP TABLE book_info; -- 删除表
```

- 表命名表名 更改多个表名，之间用逗号间隔

```sql
RENAME TABLE book_info TO book_info_new_name, backup_vendors TO vendors; -- 重命名表名称
```

- 修改表结构

```sql
alter table book_info comment '书籍表'; -- 修改表注释
alter table book_info  modify column app_name varchar(20) COMMENT '应用的名称';
alter table book_info  modify column category varchar(20) COMMENT '分类'; #修改字段类型和注释
alter table book_info  modify column category varchar(20) null COMMENT '分类'; #设置字段允许为空
alter table book_info add new_column varchar(255) not null comment '新增字段名注释'; #增加一个字段，设好数据类型，且不为空，添加注释
alter table book_info add id bigint(20) unsigned NOT NULL AUTO_INCREMENT ,add primary key (id);  #添加字段设置为主键自增
alter table book_info drop new_column; #删除字段
```

- 清空表

```sql
delete from book_info;
truncate table book_info;
```

**delete和truncate区别：效率上truncate比delete快，但truncate删除后不记录mysql日志，不可以恢复数据。**

## 2.常用SQL命令

- 基本查询语句：select、distinct、where、order by、in、like、order by having、group by；
- 联结查询：left join，right join，full join；
- 组合查询：union、union all，**UNION**返回的是去重后的结果，如果不需要去重则可以使用**UNION ALL**；
- 使用函数查询：参考MySQL常用函数
- 插入、更新、删除命令

## 3.SQL关键字执行顺序

​		在SQL语句中每个关键字都会按照顺序往下执行，而每一步操作，会生成一个虚拟表，最后产生的虚拟表会作为执行的最终结果返回。下面的是常用的关键字的执行顺序：

```sql
(8)SELECT (9)DISTINCT<select_list>
(1)FROM <left_table>
(3)<join_type> JOIN <right_table>
(2)            ON <join_condition>
(4)WHERE <where_condition>
(5)GROUP BY<group_by_list>
(6)WITH{CUBE|ROLLUP}
(7)HAVING<having_condition>
(10)ORDER BY<order_by_list>
(11)LIMIT<limit_number>
```

1、**FROM**：对FROM左边的表和右边的表计算笛卡尔积，产生虚表VT1；

2、**ON**：对虚拟表VT1进行ON筛选，只有那些符合<join_condition>条件的行才会被记录在虚拟表VT2中；

3、**JOIN**：如果是OUT JOIN，那么将保留表中（如左表或者右表）未匹配的行作为外部行添加到虚拟表VT2中，从而产生虚拟表VT3；

4、**WHERE**：对虚拟表VT3进行WHERE条件过滤，只有符合<where_condition>的记录才会被放入到虚拟表VT4；

5、**GROUP BY**：根据GROUP BY子句中的列，对虚拟表VT4进行分组操作，产生虚拟表VT5；

6、**CUBE|ROLLUP**：对虚拟表VT5进行CUBE或者ROLLUP操作，产生虚拟表VT6；

7、**HAVING**：对虚拟表VT6进行HAVING条件过滤，只有符合<having_condition>的记录才会被插入到虚拟表VT7中；

8、**SELECT**：执行SELECT操作，选择指定的列，插入到虚拟表VT8中；

9、**DISTINCT**：对虚拟表VT8中的记录进行去重，产生虚拟表VT9；

10、**ORDER BY**：将虚拟表VT9中的记录按照<order_by_list>进行排序操作，产生虚拟表VT10；

11、**LIMIT**：取出指定行的记录，产生虚拟表VT11，并将结果返回。

(这一段整理自：[新手MySQL工程师必备命令速查手册]: https://mp.weixin.qq.com/s/uNuzYxsvADD8a3WQeeen7w)

## 4.索引

​		MySQL索引的建立对于MySQL的高效运行是很重要的，索引可以大大提高MySQL的检索速度。

​		**索引分单列索引和组合索引。单列索引，即一个索引只包含单个列，而组合索引，即一个索引包含多个列。**

创建索引有两种方式，一种是直接利用CREATE INDEX进行创建，另外一种则是通过修改表结构来进行添加，则是利用ALTER TABLE语句。

### 4.1.创建索引

**1、使用CREATE INDEX**

语法为：

```sql
CREATE [UNIQUE|FULLTEXT|SPATIAL] INDEX index_name
 [USING index_type]
 ON table_name (index_col_name,...)
```

其中对应的语法变量信息如下：

- [UNIQUE|FULLTEXT|SPATIAL]

其中括号中的这三个关键字表示创建的索引类型，它们分别表示**唯一索引、全文索引、空间索引**三种不同的索引类型。如果我们不指定任何关键字，则默认为普通索引。

- index_name

index_name表示索引的名称，由用户自行定义，以便于以后对该索引进行修改等管理操作。

- index_type

index_type表示索引的具体实现方式，在MySQL中，有两种不同形式的索引——BTREE索引和HASH索引。在存储引擎为MyISAM和InnoDB的表中只能使用BTREE，其默认值就是BTREE；在存储引擎为MEMORY或者HEAP的表中可以使用HASH和BTREE两种类型的索引，其默认值为HASH。

- index_colname

index_col_name表示需要创建索引的字段名称，我们还可以针对多个字段创建复合索引，只需要在多个字段名称之间以英文逗号隔开即可。

此外，对于CHAR或VARCHAR类型的字段，我们还可以只使用字段内容前面的一部分来创建索引，只需要在对应的字段名称后面加上形如(length)的指令即可，表示只需要使用字段内容前面的length个字符来创建索引。在这里，我们以customers表的cust_name字段(类型为VARCHAR(50))为例，使用cust_name字段的6个字符前缀来创建索引。

```sql
CREATE INDEX idx_book_name ON book_info (book_name);
```

**2、使用ALTER TABLE**

语法为：

```sql
ALTER TABLE table_name
ADD [UNIQUE|FULLTEXT|SPATIAL] INDEX index_name
(index_col_name,...) [USING index_type]
```

### 4.2.修改索引

在MySQL中并没有提供修改索引的直接指令，一般情况下，我们需要先删除掉原索引，再根据需要创建一个同名的索引，从而变相地实现修改索引操作。

```sql
--先删除
ALTER TABLE book_info
DROP INDEX idx_book_name;
--再以修改后的内容创建同名索引
CREATE INDEX idx_book_name ON book_info (book_name);
```

### 4.3.删除索引

删除指定表中指定名称的索引，语法为：

```sql
ALTER TABLE table_name DROP INDEX index_name;
```

例如删除索引：idx_book_name

```sql
ALTER TABLE book_info DROP INDEX idx_book_name;
```

### 4.4.查看索引

```sql
--如果查看索引前，没有使用user db_name等命令指定具体的数据库，则必须加上FROM db_name
SHOW INDEX FROM table_name [FROM db_name]
--如果查看索引前，没有使用user db_name等命令指定具体的数据库，则必须加上db_name.前缀
SHOW INDEX FROM [db_name.]table_name
```

参考：[一千行 MySQL 学习笔记](https://mp.weixin.qq.com/s/s9-WB-blmohxFlibsVdFWw)
## 写SQL的好习惯

### 1、写完SQL使用explain查看执行计划，检查索引

写完SQL先explain查看执行计划，检查where,order by,group by后面的列，多表关联的列是否已加索引，优先考虑组合索引（SQL性能优化）（SQL性能优化）

### 2、操作delete或者update语句，加个limit (SQL后悔药）

```sql
delete from user where user_name = 'xwder' order by age desc limit 1;
```

![delete添加limit](https://cdn.xwder.com/image/blog/xwder/1-20210127093644186.png)

- **「降低写错SQL的代价」**, 你在命令行执行这个SQL的时候，如果不加limit，执行的时候一个**「不小心手抖」**，可能数据全删掉了，如果**「删错」**了呢？加了limit 200，就不一样了。删错也只是丢失200条数据，可以通过binlog日志快速恢复的。
- **「SQL效率很可能更高」**，你在SQL行中，加了limit 1，如果第一条就命中目标return， 没有limit的话，还会继续执行扫描表。
- **「避免了长事务」**，delete执行时,如果age加了索引，MySQL会将所有相关的行加写锁和间隙锁，所有执行相关行会被锁住，如果删除数量大，会直接影响相关业务无法使用。
- **「数据量大的话，容易把CPU打满」** ,如果你删除数据量很大时，不加 limit限制一下记录数，容易把cpu打满，导致越删越慢的。

### 3、表设计规范（SQL规范优雅）

**设计表选择合适字段类型、添加(以及及时更新)字段注释、创建时间和修改时间字段**

如果修改字段含义或对字段表示的状态追加时，需要**及时更新字段注释**。（SQL规范优雅）

```sql
CREATE TABLE `user` (
  `id` bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT COMMENT='用户表';
```

### 4、SQL格式规范

SQL书写格式，关键字大小保持一致，使用缩进。（SQL规范优雅）

```sql
SELECT category_id,count(*) count FROM blog_article WHERE user_id = 1 GROUP BY category_id;

SELECT
	category_id,
	count(*) count 
FROM
	blog_article 
WHERE
	user_id = 1 
GROUP BY
	category_id;
```

### 5、INSERT语句标明对应的字段名称（SQL规范优雅）

### 6、 数据更新规范（SQL后悔药）

变更SQL操作先在测试环境执行，写明详细的操作步骤以及回滚方案，并在上生产前review。

- 变更SQL操作先在测试环境测试，避免有语法错误就放到生产上了。
- 变更Sql操作需要写明详细操作步骤，尤其有依赖关系的时候，如：先修改表结构再补充对应的数据。
- 变更Sql操作有回滚方案，并在上生产前，review对应变更SQL。

### 7、<font color='red'> 修改或删除重要数据前，要先备份，先备份，先备份（SQL后悔药）</font>

### 8、where后面的字段，留意其数据类型的隐式转换（SQL性能优化）

```sql
//表中userid 是varchar字符串类型,此时不用使用userid索引导致全表扫描类型转换
select * from user where userid =1;
```

### 9、尽量把所有列定义为NOT NULL（SQL规范优雅）

### 10、禁止使用 select * （SQL性能优化）

- 节省资源、减少网络开销。
- 可能用到覆盖索引，减少回表，提高查询效率。

### 11、所有表必须使用Innodb存储引擎（SQL规范优雅）

Innodb **「支持事务，支持行级锁，更好的恢复性」**，高并发下性能更好，所以呢，没有特殊要求（即Innodb无法满足的功能如：列存储，存储空间数据等）的情况下，所有表必须使用Innodb存储引擎

### 12、数据库和表的字符集尽量统一使用UTF8（utf8mb4）（SQL规范优雅）

尽量统一使用UTF8编码

- 可以避免乱码问题
- 可以避免，不同字符集比较转换，导致的索引失效问题

**「如果需要存储表情，那么选择utf8mb4来进行存储，注意它与utf-8编码的区别。」**

### 13、尽量使用varchar代替 char。（SQL性能优化）

- 因为首先变长字段存储空间小，可以节省存储空间。

### 14、索引命名要规范

主键索引名为 pk_ 字段名；唯一索引名为 uk _字段名 ；普通索引名则为 idx _字段名。（SQL规范优雅）

说明：pk_ 即primary key；uk_ 即unique key；idx_ 即index 的简称。

### 15、WHERE从句中不对列进行函数转换和表达式计算

对列字段进行函数转换或者表达式计算可能会导致索引失效。

### 16、如果修改/更新数据过多，考虑批量进行。

**「反例：」**

```sql
delete from account  limit 100000;
```

**「正例：」**

```sql
for each(200次)
{
 delete from account  limit 500;
}
```

**「理由：」**

- 大批量操作会会造成主从延迟。
- 大批量操作会产生大事务，阻塞。
- 大批量操作，数据量过大，会把cpu打满。
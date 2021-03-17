## MySQL常用函数整理

## CONCAT函数

SQL CONCAT函数用于将两个字符串连接起来，形成一个单一的字符串
SQL CONCAT函数用于将两个字符串连接起来，形成一个单一的字符串。试试下面的例子：

```SQL
SQL> SELECT CONCAT('FIRST ', 'SECOND');
+----------------------------+
| CONCAT('FIRST ', 'SECOND') |
+----------------------------+
| FIRST SECOND               |
+----------------------------+
1 row in set (0.00 sec)
```

## concat_ws函数
MySQL中concat_ws函数 使用方法：  CONCAT_WS(separator,str1,str2,...)
CONCAT_WS() 代表 CONCAT With Separator ，是CONCAT()的特殊形式。第一个参数是其它参数的分隔符。分隔符的位置放在要连接的两个字符串之间。分隔符可以是一个字符串，也可以是其它参数。 注意： 如果分隔符为 NULL，则结果为 NULL。函数会忽略任何分隔符参数后的 NULL 值。

```SQL
mysql> select concat_ws(',','11','22','33');
+-------------------------------+
| concat_ws(',','11','22','33') |
+-------------------------------+
| 11,22,33                      |
+-------------------------------+
1 row in set (0.00 sec)
```

和MySQL中concat函数不同的是, concat_ws函数在执行的时候,不会因为NULL值而返回NULL 

```SQL
mysql>  select concat_ws(',','11','22',NULL);
+-------------------------------+
| concat_ws(',','11','22',NULL) |
+-------------------------------+
| 11,22                         |
+-------------------------------+
1 row in set (0.00 sec)
```

## group_concat函数
MySQL中group_concat函数 完整的语法如下：

group_concat([DISTINCT] 要连接的字段 [Order BY ASC/DESC 排序字段] [Separator '分隔符'])

```SQL
mysql> select * from aa; 
+------+------+ 
| id| name | 
+------+------+
|1 | 10|
|1 | 20|
|1 | 20|
|2 | 20|
|3 | 200   |
|3 | 500   | 
+------+------+
 6 rows in set (0.00 sec)
```

以id分组，把name字段的值打印在一行，逗号分隔(默认)

```SQL
mysql> select id,group_concat(name) from aa group by id; 
+------+--------------------+ 
| id| group_concat(name) | 
+------+--------------------+ 
|1 | 10,20,20|
|2 | 20 | 
|3 | 200,500| 
+------+--------------------+ 
3 rows in set (0.00 sec)
```

以id分组，把name字段的值打印在一行，分号分隔

```SQL
mysql> select id,group_concat(name separator ';') from aa group by id;
 +------+----------------------------------+ 
| id| group_concat(name separator ';') 
| +------+----------------------------------+ 
|1 | 10;20;20 |
|2 | 20|
|3 | 200;500   | 
+------+----------------------------------+ 
3 rows in set (0.00 sec)
```

以id分组，把去冗余的name字段的值打印在一行，逗号分隔

```SQL
mysql> select id,group_concat(distinct name) from aa group by id;
 +------+-----------------------------+ 
| id| group_concat(distinct name) | 
+------+-----------------------------+ 
|1 | 10,20|
|2 | 20   | 
|3 | 200,500 |
 +------+-----------------------------+ 
3 rows in set (0.00 sec)
```

以id分组，把name字段的值打印在一行，逗号分隔，以name排倒序

```SQL
mysql> select id,group_concat(name order by name desc) from aa group by id; 
+------+---------------------------------------+ 
| id| group_concat(name order by name desc) 
| +------+---------------------------------------+
|1 | 20,20,10   | 
|2 | 20| 
|3 | 500,200| 
+------+---------------------------------------+ 
3 rows in set (0.00 sec)
```

## repeat()函数
用来复制字符串,如下'ab'表示要复制的字符串，2表示复制的份数

```SQL
mysql> select repeat('ab',2);
+----------------+ 
| repeat('ab',2) | 
+----------------+
 | abab           | 
+----------------+
```
   1 row in set (0.00 sec)
   又如 mysql> 
```SQL
select repeat('a',2);
+---------------+ 
| repeat('a',2) | 
+---------------+ 
| aa            | 
+---------------+ 
1 row in set (0.00 sec)
```

## CAST函数
- (1).CAST()函数的参数是一个表达式，它包括用AS关键字分隔的源值和目标数据类型。以下例子用于将文本字符串'12'转换为整型:

```SQL
SELECT CAST('12' AS int)
```

## 日期计算 DATE_ADD(d,INTERVAL expr type)

- DATE_ADD(d,INTERVAL expr type)函数返回起始日期d加上一个时间段后的日期。

- expr是一个表达式，用来指定从起始日期添加或减去的时间间隔值。

- expr是一个字符串。对于负值的时间间隔，它可以用一个负号“-”开头。

- expr表达式与后面的间隔类型type对应。

- MySQL中的日期间隔类型如下表所示：

- MySQL计算日期的函数DATE_ADD(d,INTERVAL expr type)

| 类型(type值)  |   含义   |     expr表达式的形式     |
| :-----------: | :------: | :----------------------: |
|     YEAR      |    年    |            YY            |
|     MONTH     |    月    |            MM            |
|      DAY      |    日    |            DD            |
|     HOUR      |    时    |            hh            |
|    MINUTE     |    分    |            mm            |
|    SECOND     |    秒    |            ss            |
|  YEAR_MONTH   |  年和月  | YY和MM之间用任意符号隔开 |
|   DAY_HOUR    | 日和小时 | DD和hh之间用任意符号隔开 |
|  DAY_MINUTE   | 日和分钟 | DD和mm之间用任意符号隔开 |
|  DAY_SECOND   | 日和秒钟 | DD和ss之间用任意符号隔开 |
|  HOUR_MINUTE  |  时和分  | hh和mm之间用任意符号隔开 |
|  HOUR_SECOND  |  时和秒  | hh和ss之间用任意符号隔开 |
| MINUTE_SECOND |  分和秒  | mm和ss之间用任意符号隔开 |

- example
```sql
SELECT DATE_ADD('2014-10-8 23:59:59',INTERVAL 1 SECOND) AS col1

2014-10-09 00:00:00
```

```sql
SELECT DATE_ADD('2014-10-8 23:59:59',INTERVAL '1 1' YEAR_MONTH) AS col2

2015-11-08 23:59:59
```


## INNER、LEFT、RIGHT JOIN
-  [mysql的几	种join](https://blog.csdn.net/u012410733/article/details/63684663?_blank)
- [SQL中INNER、LEFT、RIGHT JOIN的区别和用法详解](https://blog.csdn.net/wangyuchun_799/article/details/49097263?_blank)

- A INNER JOIN B ON……：内联操作，将符合ON条件的A表和B表结果均搜索出来，然后合并为一个结果集。
- A LEFT JOIN B ON……：左联操作，左联顾名思义是，将符合ON条件的B表结果搜索出来，
然后左联到A表上，然后将合并后的A表输出。
- A RIGHT JOIN B ON……：右联操作，右联顾名思义是，将符合ON条件的A表结果搜索出来，
然后右联到B表上，然后将合并后的B表输出


## floor(), round()
- floor：函数只返回整数部分，小数部分舍弃。
  
- round：函数四舍五入，大于0.5的部分进位，不到则舍弃。与floor不同。
ROUND() 函数
ROUND() 函数用于把数值字段舍入为指定的小数位数。

### SQL ROUND() 语法
```sql
SELECT ROUND(column_name,decimals) FROM table_name;
```

参数	描述
column_name	必需。要舍入的字段。
decimals	必需。规定要返回的小数位数。


###  SQL ROUND() 实例
ROUND(X)： 返回参数X的四舍五入的一个整数。
```sql
mysql> select ROUND(-1.23);
        -> -1
mysql> select ROUND(-1.58);
        -> -2
mysql> select ROUND(1.58);
        -> 2
```
ROUND(X,D)： 返回参数X的四舍五入的有 D 位小数的一个数字。如果D为0，结果将没有小数点或小数部分。

```sql
mysql> select ROUND(1.298, 1);
        -> 1.3
mysql> select ROUND(1.298, 0);
        -> 1
```
注意：ROUND 返回值被变换为一个BIGINT!

##  字符串截取函数
MySQL 字符串截取函数：left(), right(), substring(), substring_index()。还有 mid(), substr()。其中，mid(), substr() 等价于 substring() 函数，substring() 的功能非常强大和灵活。  
```sql
1. 字符串截取：left(str, length)  
mysql> select left('sqlstudy.com', 3);  
+-------------------------+  
| left('sqlstudy.com', 3) |  
+-------------------------+  
| sql                     |  
+-------------------------+  
2. 字符串截取：right(str, length)  
mysql> select right('sqlstudy.com', 3);  
+--------------------------+  
| right('sqlstudy.com', 3) |  
+--------------------------+  
| com                      |  
+--------------------------+  
3. 字符串截取：substring(str, pos); substring(str, pos, len)  
3.1 从字符串的第 4 个字符位置开始取，直到结束。  
mysql> select substring('sqlstudy.com', 4);  
+------------------------------+  
| substring('sqlstudy.com', 4) |  
+------------------------------+  
| study.com                    |  
+------------------------------+  
3.2 从字符串的第 4 个字符位置开始取，只取 2 个字符。  
mysql> select substring('sqlstudy.com', 4, 2);  
+---------------------------------+  
| substring('sqlstudy.com', 4, 2) |  
+---------------------------------+  
| st                              |  
+---------------------------------+  
3.3 从字符串的第 4 个字符位置（倒数）开始取，直到结束。  
mysql> select substring('sqlstudy.com', -4);  
+-------------------------------+  
| substring('sqlstudy.com', -4) |  
+-------------------------------+  
| .com                          |  
+-------------------------------+  
3.4 从字符串的第 4 个字符位置（倒数）开始取，只取 2 个字符。  
mysql> select substring('sqlstudy.com', -4, 2);  
+----------------------------------+  
| substring('sqlstudy.com', -4, 2) |  
+----------------------------------+  
| .c                               |  
+----------------------------------+  
我们注意到在函数 substring(str,pos, len)中， pos 可以是负值，但 len 不能取负值。  
4. 字符串截取：substring_index(str,delim,count)  
4.1 截取第二个 '.' 之前的所有字符。  
mysql> select substring_index('www.sqlstudy.com.cn', '.', 2);  
+------------------------------------------------+  
| substring_index('www.sqlstudy.com.cn', '.', 2) |  
+------------------------------------------------+  
| www.sqlstudy                                   |  
+------------------------------------------------+  
4.2 截取第二个 '.' （倒数）之后的所有字符。  
mysql> select substring_index('www.sqlstudy.com.cn', '.', -2);  
+-------------------------------------------------+  
| substring_index('www.sqlstudy.com.cn', '.', -2) |  
+-------------------------------------------------+  
| com.cn                                          |  
+-------------------------------------------------+  
4.3 如果在字符串中找不到 delim 参数指定的值，就返回整个字符串  
mysql> select substring_index('www.sqlstudy.com.cn', '.coc', 1);  
+---------------------------------------------------+  
| substring_index('www.sqlstudy.com.cn', '.coc', 1) |  
+---------------------------------------------------+  
| www.sqlstudy.com.cn                               |  
+---------------------------------------------------+  
 4.4 截取一个表某个字段数据的中间值 如该字段数据为  1,2,3  
mysql> select substring_index(substring_index(该字段, ',', 2) , ',', -1) from 表名;    
+--------------------------------------------------------------+    
| substring_index(substring_index(该字段, ',', 2);  , ',', -1)|    
+--------------------------------------------------------------+    
| 2                                        |    
+--------------------------------------------------------------+
```

##  find_in_set()
FIND_IN_SET(str,strlist)

str 要查询的字符串
strlist 字段名 参数以”,”分隔 如 (1,2,6,8)
查询字段(strlist)中包含(str)的结果，返回结果为null或记录

假如字符串str在由N个子链组成的字符串列表strlist 中，则返回值的范围在 1 到 N 之间。 一个字符串列表就是一个由一些被 ‘,’ 符号分开的子链组成的字符串。如果第一个参数是一个常数字符串，而第二个是type SET列，则FIND_IN_SET() 函数被优化，使用比特计算。 如果str不在strlist 或strlist 为空字符串，则返回值为 0 。如任意一个参数为NULL，则返回值为 NULL。这个函数在第一个参数包含一个逗号(‘,’)时将无法正常运行。

```sql
mysql> SELECT FIND_IN_SET('b','a,b,c,d');
-> 2
```

参考：[mysql中find_in_set()函数的使用](https://www.cnblogs.com/xiaoxi/p/5889486.html)


## replace 函数
UPDATE tb1 SET f1=REPLACE(f1, 'abc', 'def');  
REPLACE(str,from_str,to_str) 
在字符串 str 中所有出现的字符串 from_str 均被 to_str替换，然后返回这个字符串  
把'xx' 替换为 '--'：UPDATE users SET username=REPLACE(username,'xx','--')  WHERE username LIKE '%xx%';

更过函数参考：[MySQL函数及用法示例](https://mp.weixin.qq.com/s/-5GqRasUSwDBBU8fxNdRjA)

## count()函数

不要使用 count(列名)或 count(常量)来替代count(\*)，count(\*)是 SQL92 定义的标

准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关。

说明：**count(*)会统计值为 NULL 的行，而 count(列名)不会统计此列为 NULL 值的行。**

## sum()函数 注意NPE异常

当某一列的值全是 NULL 时，count(col)的返回结果为 0，但 sum(col)的返回结果为NULL，因此使用 sum()时需注意 NPE 问题。

可以使用如下方式来避免 sum 的 NPE 问题：SELECT IFNULL(SUM(column), 0) FROM table;
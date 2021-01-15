## MySQL常用SQL

### 1.count添加条件 case when

```sql
SELECT
	category,
	count(*) count,
	count( CASE WHEN category = '玄幻魔法' THEN 1 END ) 玄幻魔法 
FROM
	book_info 
GROUP BY
	category
```


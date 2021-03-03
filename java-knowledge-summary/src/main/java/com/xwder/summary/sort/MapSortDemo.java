package com.xwder.summary.sort;

import java.util.*;

/**
 * map 排序
 * Map是键值对的集合接口，它的实现类主要包括：HashMap,TreeMap,Hashtable以及LinkedHashMap等。 <p>
 * <p>
 * TreeMap：基于红黑树（Red-Black tree）的 NavigableMap 实现， <p>
 * 该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。 <p>
 *
 * @author xwder
 * @date 2021/3/3 11:26
 */
public class MapSortDemo {


    /**
     * 根据value排序
     */
    public void sortByMapValue() {
        Map<String, Integer> goodsCodeMapPrice = new HashMap<>();
        goodsCodeMapPrice.put("goods1", 3);
        goodsCodeMapPrice.put("goods2", 8);
        goodsCodeMapPrice.put("goods3", 4);
        goodsCodeMapPrice.put("goods4", 10);
        // 将这个map转换成list以便排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(goodsCodeMapPrice.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            // 默认的是从小到大排序，
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 从大到小排序
                // return (o2.getValue() - o1.getValue());
                // 从小到大排序
                return (o1.getValue() - o2.getValue());
            }
        });

        Collections.sort(list, (o1, o2) -> {
            // 从大到小排序
            // return (o2.getValue() - o1.getValue());
            // 从小到大排序
            return (o1.getValue() - o2.getValue());
        });

        // 输出排序后的结果
        for (Map.Entry<String, Integer> t : list) {
            System.out.println(t.getKey() + ":" + t.getValue());
        }

    }

    /**
     * 根据map key 排序
     */
    public void sortByMapKey() {
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });
        map.put("b", "ccccc");
        map.put("d", "aaaaa");
        map.put("c", "bbbbb");
        map.put("a", "ddddd");

        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }
    }

    public static void main(String[] args) {
        MapSortDemo mapSortDemo = new MapSortDemo();
        mapSortDemo.sortByMapKey();
        mapSortDemo.sortByMapValue();
    }
}

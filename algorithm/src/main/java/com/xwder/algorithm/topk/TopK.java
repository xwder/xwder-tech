package com.xwder.algorithm.topk;

import java.util.*;

/**
 * topK 问题
 * 题意：
 * 给一组词，统计出现频率最高的 k 个。
 * 比如说 “I love leetcode, I love coding” 中频率最高的 2 个就是 I 和 love 了。
 *
 * @author wande
 * @version 1.0
 * @date 2020/05/14
 */
class TopK {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer count = map.getOrDefault(word, 0);
            count++;
            map.put(word, count);
        }

        // Step 2
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k + 1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if (e1.getValue().equals(e2.getValue())) {
                    return e2.getKey().compareTo(e1.getKey());
                }
                return e1.getValue().compareTo(e2.getValue());
            }
        });

        // Step 3
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().getKey());
        }
        Collections.reverse(res);
        return res;
    }
}
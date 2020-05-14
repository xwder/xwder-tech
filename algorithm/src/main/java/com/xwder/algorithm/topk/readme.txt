题意：
    给一组词，统计出现频率最高的 k 个。
    比如说 “I love leetcode, I love coding” 中频率最高的 2 个就是 I 和 love 了。

    有同学觉得这题特别简单，但其实这题只是母题，它可以升级到系统设计层面来问：
    在某电商网站上，过去的一小时内卖出的最多的 k 种货物。

思路：
    统计下所有词的频率，然后按频率排序取最高的前 k 个呗。

细节：
    用 HashMap 存放单词的频率，用 minHeap/maxHeap 来取前 k 个。
实现：
    1、建一个 HashMap <key = 单词，value = 出现频率>，遍历整个数组，相应的把这个单词的出现次数 + 1.
    这一步时间复杂度是 O(n).

    2、用 size = k 的 minHeap 来存放结果，定义好题目中规定的比较顺序
    a. 首先按照出现的频率排序；
    b. 频率相同时，按字母顺序。

    3、遍历这个 map，如果
    a. minHeap 里面的单词数还不到 k 个的时候就加进去；
    b. 或者遇到更高频的单词就把它替换掉。

时空复杂度分析：
第一步是 O(n)，第三步是 nlog(k)，所以加在一起时间复杂度是 O(nlogk).
用了一个额外的 heap 和 map，空间复杂度是 O(n).

参考：
    有关 HashMap 面试会问的一切！ https://mp.weixin.qq.com/s/VQ-SpUoLB6PETJeBs88jQA
    PriorityQueue详解            https://www.jianshu.com/p/f1fd9b82cb72
来源：布隆过滤器究竟是什么  https://mp.weixin.qq.com/s/Y7OJ0ntjU0pumWuwFoY8mQ

结论：
    从容器的角度来说：
        如果布隆过滤器判断元素在集合中存在，不一定存在
        如果布隆过滤器判断不存在，一定不存在

    从元素的角度来说：
        如果元素实际存在，布隆过滤器一定判断存在
        如果元素实际不存在，布隆过滤器可能判断存在

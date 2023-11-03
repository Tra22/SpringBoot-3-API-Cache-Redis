package com.tra22.spring.redis.mapper;

import java.util.List;

public interface BaseMapper<T, K> {
    T map(K k);
    K mapReverse(T t);
    List<T> mapList(List<K> k);
    List<K> mapReverseList(List<T> t);
}

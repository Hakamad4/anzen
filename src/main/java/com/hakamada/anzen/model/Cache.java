package com.hakamada.anzen.model;

public interface Cache<T> {

    Cache<T> put(String key, T obj);

    T getByKey(String key);
}

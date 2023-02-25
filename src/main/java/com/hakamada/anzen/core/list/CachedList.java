package com.hakamada.anzen.core.list;

import com.hakamada.anzen.model.Cache;

import java.io.Serializable;

public class CachedList<T> implements Cache<T> {

    private Linked<T> linked;


    @Override
    public Cache<T> put(String key, T obj) {
        return null;
    }

    @Override
    public T getByKey(String key) {
        return null;
    }
}

package com.hakamada.anzen.core.v1;

import com.hakamada.anzen.model.Cache;

import java.util.LinkedList;

public class Anzen<T> implements Cache<T> {

    private final int maxCacheSize;
    private LinkedList<String> keyList;
    private LinkedList<T> linkedList;

    public Anzen(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.linkedList = new LinkedList<>();
        this.keyList = new LinkedList<>();
    }

    @Override
    public Cache<T> put(String key, T obj) {
        validateCacheLimitSize();
        validateDuplicatedKey(key);
        keyList.add(key);
        linkedList.add(obj);
        return this;
    }

    private void validateCacheLimitSize() {
        if (keyList.size() >= maxCacheSize) {
            keyList.removeFirst();
            linkedList.removeFirst();
        }
    }

    private void validateDuplicatedKey(String key) {
        if (keyList.contains(key)) {
            int i = keyList.indexOf(key);
            keyList.remove(i);
            linkedList.remove(i);
        }
    }

    @Override
    public T getByKey(String key) {
        if (!keyList.contains(key)) {
            return null;
        }
        return linkedList.get(indexOf(key));
    }

    public int indexOf(String key) {
        return keyList.indexOf(key);
    }

    public LinkedList<T> getLinkedList() {
        return linkedList;
    }

    public int getTotalCached() {
        return linkedList.size();
    }
}

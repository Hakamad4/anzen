package com.hakamada.anzen.core.v3;

import com.hakamada.anzen.model.Cache;
import com.hakamada.anzen.model.CachedItem;

import java.util.LinkedList;

public class V3CachedHolder<T> implements Cache<T> {
    private final int maxCacheSize;
    private static LinkedList<CachedItem> cachedItems;

    public V3CachedHolder(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        cachedItems = new LinkedList<>();
    }

    @Override
    public Cache<T> put(String key, T obj) {
        validateCacheCapacity();
        validateDuplicatedKey(key);

        cachedItems.add(
                new CachedItem.Builder<T>()
                        .withKey(key)
                        .withItem(obj)
                        .build()
        );
        return this;
    }

    private void validateDuplicatedKey(String key) {
        if (cachedItems.contains(key)) {
            int i = cachedItems.indexOf(key);
            cachedItems.remove(i);
        }
    }

    private void validateCacheCapacity() {
        if (cachedItems.size() >= maxCacheSize) {
            cachedItems.removeFirst();
        }
    }


    @Override
    public T getByKey(String key) {
        var cachedItem = filterCachedItemByKey(key);
        return cachedItem == null ? null : (T) cachedItem.getContent();
    }

    private CachedItem filterCachedItemByKey(String key) {
        return cachedItems.parallelStream()
                .filter(cached -> cached.getKey().equals(key))
                .findFirst()
                .orElse(null);
    }
}

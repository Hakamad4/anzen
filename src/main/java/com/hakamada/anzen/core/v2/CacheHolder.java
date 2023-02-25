package com.hakamada.anzen.core.v2;

import com.hakamada.anzen.model.Cache;
import com.hakamada.anzen.model.CacheLimitSizeCantBeZeroExcetion;
import com.hakamada.anzen.model.Strategy;


public class CacheHolder<T> implements Cache<T> {

    private final Strategy strategy;
    private static int totalCached = 0;
    private static final int KEY_POSITION = 0;
    private static final int VALUE_POSITION = 1;
    private final Class<T> genericClass;
    private int cacheLimitSize;
    private final Object[][] cachedObject;
    private final byte[][][][] cachedBytes;

    public CacheHolder(Strategy strategy, Class<T> genericClass, int cacheLimitSize) {
        this.strategy = strategy;
        checkLimitSize(cacheLimitSize);
        this.genericClass = genericClass;
        this.cachedBytes = new byte[cacheLimitSize][][][];
        this.cachedObject = new Object[cacheLimitSize][];
    }

    public CacheHolder(Class<T> genericClass) {
        this(Strategy.FIFO, genericClass, 1000);
    }

    private void checkLimitSize(int cacheLimitSize) throws CacheLimitSizeCantBeZeroExcetion {
        if (cacheLimitSize <= 0) {
            throw new CacheLimitSizeCantBeZeroExcetion();
        }
    }

    private void removeOneWithAnyPolicy() {

    }

    private void removeNByStrategy(int nRemoval) {
        switch (strategy) {
            case FIFO:
                break;
            case LRU:
                break;
            case LFU:
                break;
            case RANDOM:
                break;
        }
    }

    @Override
    public synchronized Cache<T> put(String key, T obj) {
        if (totalCached == cacheLimitSize) {
            removeOneWithAnyPolicy();
        }
        final int currentKeyPosition = getCurrentKeyPosition(key);
        int cachePosition = currentKeyPosition > -1 ? currentKeyPosition : totalCached;
        if (cachedObject[cachePosition] == null) {
            cachedObject[cachePosition] = new Object[2];
        }
        cachedObject[cachePosition][KEY_POSITION] = key;
        cachedObject[cachePosition][VALUE_POSITION] = obj;
        totalCached++;
        return this;
    }

    @Override
    public T getByKey(String key) {
        T obj;
        int currentKeyPosition = getCurrentKeyPosition(key);
        if (currentKeyPosition < 0) {
            obj = null;
        } else {
            obj = (T) cachedObject[currentKeyPosition][VALUE_POSITION];
        }
        return obj;
    }

    private int getCurrentKeyPosition(String key) {
        int position = -1;
        for (int i = 0; i < cachedObject.length; i++) {
            Object[] objects = cachedObject[i];
            if (objects == null || objects[KEY_POSITION].equals(key)) {
                position = i;
                break;
            }
        }
        return position;
    }

    public Object[][] getCachedObject() {
        return cachedObject;
    }
}

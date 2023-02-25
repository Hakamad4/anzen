package com.hakamada.anzen.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CachedItem<T> {

    private String key;
    private T content;
    private LocalDateTime cachedAt;
    private CachedItem<T> nextItem;

    public CachedItem(String key, T content) {
        this.key = key;
        this.content = content;
        this.cachedAt = LocalDateTime.now();
    }

    public static class Builder<T> {
        private String key;
        private T item;

        public Builder() {
        }

        public Builder<T> withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder<T> withItem(T item) {
            this.item = item;
            return this;
        }

        public CachedItem<T> build() {
            return new CachedItem<>(
                    this.key,
                    this.item
            );
        }

    }

    public String getKey() {
        return key;
    }

    public T getContent() {
        return content;
    }

    public LocalDateTime getCachedAt() {
        return cachedAt;
    }

    public CachedItem<T> getNextItem() {
        return nextItem;
    }

    public void setNextItem(CachedItem<T> nextItem) {
        this.nextItem = nextItem;
    }

    public boolean hasNext() {
        return nextItem != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CachedItem)) return false;
        CachedItem<?> that = (CachedItem<?>) o;
        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

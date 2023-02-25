package com.hakamada.anzen.core.list;

import com.hakamada.anzen.model.CachedItem;

import java.io.Serializable;
import java.util.Objects;

public class Linked<T> implements Serializable {
    private CachedItem<T> first;
    private CachedItem<T> last;
    private int size;

    public Linked() {
        this.size = 0;
    }

    public void add(String key, T content) {
        CachedItem<T> node = new CachedItem<>(key, content);
        if (Objects.isNull(first) && Objects.isNull(last)) {
            this.first = node;
        } else {
            if (contains(key)) {
                System.out.println(" contains key ");
                remove(key);
            }
            this.last.setNextItem(node);
        }
        this.last = node;
        this.size++;
    }

    public boolean contains(String key) {
        boolean contains = first.getKey().equals(key);
        for (CachedItem<T> current = first; current.hasNext() && !contains; current = current.getNextItem()) {
            contains = current.getKey().equals(key);
        }
        return contains;
    }

    public void remove(String key) {
        CachedItem<T> aboveCurrent = first;
        for (CachedItem<T> current = first; current.hasNext(); current = current.getNextItem()) {
            if (current.getKey().equals(key)) {
                aboveCurrent.setNextItem(current.getNextItem());
                size--;
            }
            aboveCurrent = current;
        }
    }

    public CachedItem<T> get(String key) {
        for (CachedItem<T> current = first; current.hasNext(); current = current.getNextItem()) {
            System.out.println("current = " + current.getKey());
            if (current.getKey().equals(key)) {
                return current;
            }
        }
        return null;
    }

    public CachedItem<T> getFirst() {
        return first;
    }

    public void setFirst(CachedItem<T> first) {
        this.first = first;
    }

    public CachedItem<T> getLast() {
        return last;
    }

    public void setLast(CachedItem<T> last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

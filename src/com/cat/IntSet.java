package com.cat;

public interface IntSet {
    void put(int value);

    void remove(int value);

    int size();

    boolean isEmpty();

    void clear();

    boolean containsValue(int value);

    void printSet();
}

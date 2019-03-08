package com.cat;

public interface IntMap {
    void put(int key, int value);

    void remove(int key, int value);

    int size();

    boolean isEmpty();

    void clear();

    boolean containsKey(int key);

    boolean containsValue(int value);

    int get(int key);

    void printMap();

}

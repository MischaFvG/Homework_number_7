package com.cat;


import java.util.ArrayList;
import java.util.List;

public class IntHashMap implements IntMap {

    private class Element {
        private int key;
        private int value;
        private Element nextElement;

        public Element(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private Element[] arrayMap = new Element[16];
    private final double LOAD_FACTOR = 0.75;
    private int newSize = (int) (LOAD_FACTOR * arrayMap.length);

    @Override
    public void put(int key, int value) {
        Element element = new Element(key, value);
        int index = element.key % arrayMap.length;
        if (arrayMap[index] == null) {
            arrayMap[index] = element;
        } else {
            Element currentElement = arrayMap[index];
            while (currentElement != null) {
                if (currentElement.nextElement == null) {
                    currentElement.nextElement = element;
                    currentElement = element;
                }
                currentElement = currentElement.nextElement;
            }
        }
        size++;
        if (size > newSize) {
            resize();
        }
    }


    @Override
    public void remove(int key, int value) {
        int index = key % arrayMap.length;
        Element element = arrayMap[index];
        while (element != null) {
            Element nextElement = element.nextElement;
            if (nextElement != null && element.key == key && element.value == value) {
                element.key = nextElement.key;
                element.value = nextElement.value;
                element.nextElement = nextElement.nextElement;
            }
            if (nextElement == null && element.key == key && element.value == value) {
                arrayMap[index] = null;
            }
            element = element.nextElement;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean containsKey(int key) {
        int index = key % arrayMap.length;
        Element element = arrayMap[index];
        while (element != null) {
            if (element.key == key) {
                return true;
            }
            element = element.nextElement;
        }
        return false;
    }

    @Override
    public boolean containsValue(int value) {
        for (int i = 0; i < arrayMap.length; i++) {
            Element element = arrayMap[i];
            while (element != null) {
                if (element.value == value) {
                    return true;
                }
                element = element.nextElement;
            }
        }
        return false;
    }

    @Override
    public int get(int key) {
        int index = key % arrayMap.length;
        Element element = arrayMap[index];
        while (element != null) {
            if (element.key == key) {
                return element.value;
            }
            element = element.nextElement;
        }
        return 0;
    }

    @Override
    public void printMap() {
        for (int i = 0; i < arrayMap.length; i++) {
            Element element = arrayMap[i];
            while (element != null) {
                System.out.print("[" + element.key + " " + element.value + "]");
                element = element.nextElement;
            }
        }
        System.out.println();
    }

    private void resize() {
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < arrayMap.length; i++) {
            Element element = arrayMap[i];
            while (element != null) {
                list.add(element);
                element = element.nextElement;
            }
        }
        Element[] elements = new Element[list.size()];
        list.toArray(elements);
        int newArrayMapSize = arrayMap.length * 2;
        Element[] newArrayMap = new Element[newArrayMapSize];
        for (int i = 0; i < elements.length; i++) {
            int index = elements[i].key % newArrayMap.length;
            if (newArrayMap[index] == null) {
                newArrayMap[index] = elements[i];
            } else {
                Element currentElement = newArrayMap[index];
                while (currentElement != null) {
                    if (currentElement.nextElement == null) {
                        currentElement.nextElement = elements[i];
                        currentElement = elements[i];
                    }
                    currentElement = currentElement.nextElement;
                }
            }
        }
        arrayMap = newArrayMap;
        newSize = (int) (LOAD_FACTOR * arrayMap.length);
    }
}

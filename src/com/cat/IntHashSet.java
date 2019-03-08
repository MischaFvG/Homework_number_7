package com.cat;

import java.util.ArrayList;
import java.util.List;

public class IntHashSet implements IntSet {
    private class Element {
        private int value;
        private Element nextElement;

        public Element(int value) {
            this.value = value;
        }
    }

    private int size = 0;
    private Element[] arraySet = new Element[16];
    private final double LOAD_FACTOR = 0.75;
    private int newSize = (int) (LOAD_FACTOR * arraySet.length);

    @Override
    public void put(int value) {
        Element element = new Element(value);
        int index = value % arraySet.length;
        if (arraySet[index] == null) {
            arraySet[index] = element;
        } else {
            Element currentElement = arraySet[index];
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
    public void remove(int value) {
        int index = value % arraySet.length;
        Element element = arraySet[index];
        while (element != null) {
            Element nextElement = element.nextElement;
            if (nextElement != null && element.value == value) {
                element.value = nextElement.value;
                element.nextElement = nextElement.nextElement;
            }
            if (nextElement == null && element.value == value) {
                arraySet[index] = null;
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
    public boolean containsValue(int value) {
        int index = value % arraySet.length;
        Element element = arraySet[index];
        while (element != null) {
            if (element.value == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printSet() {
        for (int i = 0; i < arraySet.length; i++) {
            Element element = arraySet[i];
            while (element != null) {
                System.out.print("[" + element.value + "]");
                element = element.nextElement;
            }
        }
        System.out.println();
    }

    private void resize() {
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < arraySet.length; i++) {
            Element element = arraySet[i];
            while (element != null) {
                list.add(element);
                element = element.nextElement;
            }
        }
        Element[] elements = new Element[list.size()];
        list.toArray(elements);
        int newArraySetSize = arraySet.length * 2;
        Element[] newArraySet = new Element[newArraySetSize];
        for (int i = 0; i < elements.length; i++) {
            int index = elements[i].value % newArraySet.length;
            if (newArraySet[index] == null) {
                newArraySet[index] = elements[i];
            } else {
                Element currentElement = newArraySet[index];
                while (currentElement != null) {
                    if (currentElement.nextElement == null) {
                        currentElement.nextElement = elements[i];
                        currentElement = elements[i];
                    }
                    currentElement = currentElement.nextElement;
                }
            }
        }
        arraySet = newArraySet;
        newSize = (int) (LOAD_FACTOR * arraySet.length);
    }
}

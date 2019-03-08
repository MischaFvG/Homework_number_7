package com.cat;

public class Main {

    public static void main(String[] args) {
        System.out.println("Homework_number_7");
        System.out.println("IntHashMap");
        IntMap intHashMap = new IntHashMap();
        intHashMap.put(0, 2);
        intHashMap.put(1, 2);
        intHashMap.put(3, 24);
        intHashMap.put(34, 234);
        System.out.println(intHashMap.containsKey(1));
        System.out.println(intHashMap.containsValue(24));
        intHashMap.printMap();
        System.out.println(intHashMap.containsKey(34));
        intHashMap.put(55, 21);
        intHashMap.printMap();
        System.out.println(intHashMap.containsValue(222));
        System.out.println(intHashMap.get(34));
        intHashMap.put(19, 23456789);
        intHashMap.printMap();
        intHashMap.put(35, 3452);
        intHashMap.printMap();
        intHashMap.remove(3, 24);
        intHashMap.printMap();
        intHashMap.remove(0, 2);
        intHashMap.printMap();
        System.out.println(intHashMap.size());
        intHashMap.clear();
        System.out.println(intHashMap.size());
        System.out.println(intHashMap.isEmpty());
        System.out.println("IntHashSet");
        IntSet intHashSet = new IntHashSet();
        intHashSet.put(4);
        intHashSet.put(45);
        intHashSet.put(23);
        intHashSet.printSet();
        System.out.println(intHashSet.containsValue(4));
        intHashSet.remove(4);
        intHashSet.printSet();
        intHashSet.clear();
        System.out.println(intHashSet.size());
        System.out.println(intHashSet.isEmpty());
    }
}

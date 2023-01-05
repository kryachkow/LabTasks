package com.task3;

import com.task3.model.ArticleHashLength;
import com.task3.model.ArticleHashSum;
import com.task3.model.SimpleItem;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class IterationResearch {

    static HashMap<String, SimpleItem> defaultHashMap = new HashMap<>();
    static HashMap<ArticleHashLength, SimpleItem> hashLengthMap = new HashMap<>();
    static HashMap<ArticleHashSum, SimpleItem> hashSumMap = new HashMap<>();
    static LinkedHashMap<String, SimpleItem>  defaultLinkedHashMap = new LinkedHashMap<>();
    static LinkedHashMap<ArticleHashLength, SimpleItem> hashLengthLinkedMap = new LinkedHashMap<>();
    static LinkedHashMap<ArticleHashSum, SimpleItem> hashSumLinkedMap = new LinkedHashMap<>();
    static {
        defaultHashMap.put("ABSC", new SimpleItem(1, "first"));
        defaultHashMap.put("UIEK", new SimpleItem(2, "second"));
        defaultHashMap.put("JKME", new SimpleItem(3, "third"));
        defaultHashMap.put("LK02", new SimpleItem(4, "fourth"));
        defaultHashMap.put("NILP56", new SimpleItem(5, "fifth"));
        defaultHashMap.put("ILET391", new SimpleItem(6,"sixth"));
        defaultHashMap.put("HT", new SimpleItem(7,"seventh"));

        hashLengthMap.put(new ArticleHashLength("ABSC"), new SimpleItem(1, "first"));
        hashLengthMap.put(new ArticleHashLength("UIEK"), new SimpleItem(2, "second"));
        hashLengthMap.put(new ArticleHashLength("JKME"), new SimpleItem(3, "third"));
        hashLengthMap.put(new ArticleHashLength("LK02"), new SimpleItem(4, "fourth"));
        hashLengthMap.put(new ArticleHashLength("NILP56"), new SimpleItem(5, "fifth"));
        hashLengthMap.put(new ArticleHashLength("ILET391"), new SimpleItem(6,"sixth"));
        hashLengthMap.put(new ArticleHashLength("HT"), new SimpleItem(7,"seventh"));

        hashSumMap.put(new ArticleHashSum("ABSC"), new SimpleItem(1, "first"));
        hashSumMap.put(new ArticleHashSum("UIEK"), new SimpleItem(2, "second"));
        hashSumMap.put(new ArticleHashSum("JKME"), new SimpleItem(3, "third"));
        hashSumMap.put(new ArticleHashSum("LK02"), new SimpleItem(4, "fourth"));
        hashSumMap.put(new ArticleHashSum("NILP56"), new SimpleItem(5, "fifth"));
        hashSumMap.put(new ArticleHashSum("ILET391"), new SimpleItem(6,"sixth"));
        hashSumMap.put(new ArticleHashSum("HT"), new SimpleItem(7,"seventh"));

        defaultLinkedHashMap.put("ABSC", new SimpleItem(1, "first"));
        defaultLinkedHashMap.put("UIEK", new SimpleItem(2, "second"));
        defaultLinkedHashMap.put("JKME", new SimpleItem(3, "third"));
        defaultLinkedHashMap.put("LK02", new SimpleItem(4, "fourth"));
        defaultLinkedHashMap.put("NILP56", new SimpleItem(5, "fifth"));
        defaultLinkedHashMap.put("ILET391", new SimpleItem(6,"sixth"));
        defaultLinkedHashMap.put("HT", new SimpleItem(7,"seventh"));

        hashLengthLinkedMap.put(new ArticleHashLength("ABSC"), new SimpleItem(1, "first"));
        hashLengthLinkedMap.put(new ArticleHashLength("UIEK"), new SimpleItem(2, "second"));
        hashLengthLinkedMap.put(new ArticleHashLength("JKME"), new SimpleItem(3, "third"));
        hashLengthLinkedMap.put(new ArticleHashLength("LK02"), new SimpleItem(4, "fourth"));
        hashLengthLinkedMap.put(new ArticleHashLength("NILP56"), new SimpleItem(5, "fifth"));
        hashLengthLinkedMap.put(new ArticleHashLength("ILET391"), new SimpleItem(6,"sixth"));
        hashLengthLinkedMap.put(new ArticleHashLength("HT"), new SimpleItem(7,"seventh"));

        hashSumLinkedMap.put(new ArticleHashSum("ABSC"), new SimpleItem(1, "first"));
        hashSumLinkedMap.put(new ArticleHashSum("UIEK"), new SimpleItem(2, "second"));
        hashSumLinkedMap.put(new ArticleHashSum("JKME"), new SimpleItem(3, "third"));
        hashSumLinkedMap.put(new ArticleHashSum("LK02"), new SimpleItem(4, "fourth"));
        hashSumLinkedMap.put(new ArticleHashSum("NILP56"), new SimpleItem(5, "fifth"));
        hashSumLinkedMap.put(new ArticleHashSum("ILET391"), new SimpleItem(6,"sixth"));
        hashSumLinkedMap.put(new ArticleHashSum("HT"), new SimpleItem(7,"seventh"));

    }




    static void makeIteration(){
        System.out.println("\n defaultHashMap iteration \n");
        defaultHashMap.forEach((str, simpleItem) -> System.out.println(str + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

        System.out.println("~~~~~~~~~~~~~");

        System.out.println("\n hashLengthMap iteration \n");
        hashLengthMap.forEach((art, simpleItem) -> System.out.println(art + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

        System.out.println("~~~~~~~~~~~~~");

        System.out.println("\n hashSumMap iteration \n");
        hashSumMap.forEach((art, simpleItem) -> System.out.println(art + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

        System.out.println("~~~~~~~~~~~~~");

        System.out.println("\n defaultLinkedHashMap iteration \n");
        defaultLinkedHashMap.forEach((str, simpleItem) -> System.out.println(str + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

        System.out.println("~~~~~~~~~~~~~");

        System.out.println("\n hashLengthLinkedMap iteration \n");
        hashLengthLinkedMap.forEach((art, simpleItem) -> System.out.println(art + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

        System.out.println("~~~~~~~~~~~~~");

        System.out.println("\n hashSumLinkedMap iteration \n");
        hashSumLinkedMap.forEach((art, simpleItem) -> System.out.println(art + " -> " + simpleItem.getName() + " " + simpleItem.getWeight()));

    }

    public static void main(String[] args) {
        makeIteration();
    }
}


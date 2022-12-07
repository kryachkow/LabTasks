package com.task4.storage;

import com.task4.model.CartPart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderStorage {
    private static final TreeMap<LocalDateTime, List<CartPart>> orderTreeMap = new TreeMap<>();

    public static void addOrder(LocalDateTime dateTime, List<CartPart> cart){
        orderTreeMap.put(dateTime, cart);
    }

    public static Map.Entry<LocalDateTime, List<CartPart>> getClosestEntry(LocalDateTime dateTime){
        if(orderTreeMap.isEmpty()){
            return null;
        }
        Map.Entry<LocalDateTime, List<CartPart>> lowerEntry = orderTreeMap.lowerEntry(dateTime);
        Map.Entry<LocalDateTime, List<CartPart>> higherEntry = orderTreeMap.higherEntry(dateTime);
        if(lowerEntry == null){
            return higherEntry;
        }
        if(higherEntry == null){
            return lowerEntry;
        }
        int diffLower = dateTime.compareTo(lowerEntry.getKey());
        int diffHigher = dateTime.compareTo(higherEntry.getKey());

        if( diffLower + diffHigher > 0){
            return higherEntry;
        }
        return lowerEntry;
    }

    public static List<Map.Entry<LocalDateTime, List<CartPart>>> getOrders(LocalDateTime firstDate, LocalDateTime secondDate){
        return orderTreeMap
                .entrySet()
                .stream()
                .filter( e-> e.getKey().isBefore(secondDate) && e.getKey().isAfter(firstDate))
                .collect(Collectors.toList());
    }
}

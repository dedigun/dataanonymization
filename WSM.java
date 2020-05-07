package wsmimplement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dedigun
 */
public class WSM {

    public static void main(String[] args) {

        //double alternative[][] = {{3, -2, 0}};
        int altItem[] = {1, 2, 4, 5, 6, 9, 10, 11};
        double[] weightCriteria = {0.3, 0.4, 0.3};
        double alternative[][] = {{2, 0, -1}, {3, -2, 0}, {1, -2, 1}, {2, -2, 1}, {1, -1, 1}, {1, 0, -1}, {2, -1, -2}, {3, 0, 0}};
        double score = 0;
        double totalScore = 0;
        List<Double> listScore = new ArrayList<>();
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        for (int x = 0; x < alternative.length; x++) {
            for (int i = 0; i < weightCriteria.length; i++) {
                score = weightCriteria[i] * alternative[x][i];
                totalScore += score;
            }
            listScore.add(totalScore);
            totalScore = 0;//reset total score to zero
        }

        for (int j = 0; j < altItem.length; j++) {
            for (int i = 0; i < listScore.size(); i++) {
                map.put(altItem[j], listScore.get(j));
            }
        }
       Map<Integer, Double> sortedMap = sortByValue(map);
        printMap(sortedMap);
    }

    public static <K, V> void printMap(Map<K, V> sortedMap) {
        for (Map.Entry<K, V> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey()
				+ " : "+ entry.getValue());
        }
    }
    
    //this block code is based on the example in mkyong.com
    private static Map<Integer, Double> sortByValue(Map<Integer, Double> map) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Integer, Double>> list =
                new LinkedList<Map.Entry<Integer, Double>>(map.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o2,
                               Map.Entry<Integer, Double> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
}

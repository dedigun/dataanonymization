/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsmimplement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author dedigun
 */
public class CountDiff {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "D:\\dataset\\sampledata_p1.txt";//"D:\\dataset\\kosarakhost.txt"
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<String, Integer> countByWords = new HashMap<String, Integer>();
        Map<Integer, Integer> mapItemSurgCount = new HashMap<Integer, Integer>();
        Map<Integer, Integer> mapItemTargCount = new HashMap<Integer, Integer>();
        Scanner s = new Scanner(new File(path));
        String item = null;
        String line;
        int[][] targetItem = {{2, 8}};
        int[][] surogateItem = {{1, 5}, {3, 9}};
        int countTargItem = 0;
        int countSurgItem = 0;
        int reduce = 0;
        int violate = 0;
        int diff = 0;
        List<List<Integer>> listOnList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int transNum = 0;
        System.out.println("=============================");
        while ((line = br.readLine()) != null) {
            List<Integer> listRecord = new ArrayList<>();
            transNum++;
            line = line.trim();
            System.out.println(line);
        }
        System.out.println("=============================");
        while (s.hasNext()) {
            item = s.next();
            String[] words = item.toLowerCase().split("\\s+");
            for (String word : words) {
                Integer count = countByWords.get(word);
                if (count == null) {
                    count = 0;
                }
                countByWords.put(word, count + 1);
            }
        }
        Iterator it = countByWords.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String keyMap = String.valueOf(pair.getKey());
            String valueMap = String.valueOf(pair.getValue());
            mapItemTargCount.put(Integer.valueOf(keyMap), Integer.valueOf(valueMap));
            mapItemSurgCount.put(Integer.valueOf(keyMap), Integer.valueOf(valueMap));
        }
        //System.out.println(mapItemTargCount.keySet());
        //System.out.println(mapItemTargCount.values());
        
        //this block code works if the number of target item is more than one set
        for (int i = 0; i < targetItem.length; i++) {
            for (int j = 0; j < targetItem[i].length; j++) {
                countTargItem = mapItemTargCount.get(targetItem[i][j]);
                System.out.println(targetItem[i][j] + " : " + countTargItem);
            }
        }
        System.out.println("----------------");

        for (int i = 0; i < surogateItem.length; i++) {
            for (int j = 0; j < surogateItem[i].length; j++) {
                if (mapItemSurgCount.get(surogateItem[i][j]) == null) {
                    countSurgItem = 0;
                } else {
                    countSurgItem = mapItemSurgCount.get(surogateItem[i][j]);
                }
                //System.out.println(surogateItem[i][j] + " : " + countSurgItem);
                diff = countTargItem - countSurgItem;
                System.out.println("diff of  " + surogateItem[i][j] + ":" + diff);
            }
        }
    }

    /*public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
           
                 System.out.println(entry.getKey()+ " : " + entry.getValue());
           
           
        }
    }*/
}

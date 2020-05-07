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
public class CountReduceItem {

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
        String keyMap = null;
        String valueMap = null;
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
        System.out.println("Target-->Surogate : #reduced");
        for (int i = 0; i < targetItem.length; i++) {
            for (int j = 0; j < surogateItem.length; j++) {
                for (int k = 0; k < surogateItem[j].length; k++) {
                    //System.out.println(targetItem[i][j] + "-->" + (surogateItem[j][k]));
                    Iterator it = countByWords.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        keyMap = String.valueOf(pair.getKey());
                        valueMap = String.valueOf(pair.getValue());
                        if (Integer.valueOf(keyMap) == Integer.valueOf(surogateItem[j][k])) {
                            System.out.println(targetItem[i][j] + "-->" + (surogateItem[j][k]) + ":" + valueMap);
                        }
                        if(Integer.valueOf(keyMap) != Integer.valueOf(surogateItem[j][k])){
                            //System.out.println("a");
                        }
                        
                    }
                }
                //System.out.println(String.valueOf(surogateItem[i][j])+":"+valueMap);
            }
        }
    }
}

/*reduce is equal to the number of surrogate item in the database before modification
    Iterator it = countByWords.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        keyMap = String.valueOf(pair.getKey());
                        valueMap = String.valueOf(pair.getValue());
                        
                        if(Integer.valueOf(keyMap)==Integer.valueOf(targetItem[i][j])){
                            a = Integer.valueOf(valueMap);
                            System.out.println(keyMap +" : "+a);
                        }
                        if(Integer.valueOf(keyMap)==Integer.valueOf(surogateItem[j][k])){
                            b = Integer.valueOf(valueMap);
                            System.out.println(keyMap +" : "+b);
                        }
                        reduce=b;
                        System.out.println(reduce);
                    }
                }
 */

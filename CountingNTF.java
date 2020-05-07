/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
algorithm
1. Read dataset and count the item frequency in dataset
2. Read adversary knowledge in AK
3. count ntf value for each item in AK -> ntf(i)= 0.4+(1-0.4)X (freq of item i / max.freq of item i in dataset)
4. determine Sb value by suming up ntf value of item in AK and count the average
5. select item i in AK that has ntf value higher than Sb value.
6. end

 */
package normalizedterm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author dedigun
 */
public class CountingNTF {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "D:\\dataset\\sampledata.txt";
        Hashtable map = new Hashtable();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int[][] advKnow = {{2, 8}, {5}};
        int numbItem = 0;
        int transNum = 0;
        int totalItem = 0;
        int akLenght=0;
        double min_sup = 0;
        int maxFreq=0;
        String[] sItem = null;
        List<Integer> listTransctions = new ArrayList<>();
        List<List<Integer>> listOnList = new ArrayList<>();
        List<String> freqItem = new ArrayList<>();
        List<Integer> frequency = new ArrayList<>();
        List<Double> ntfList = new ArrayList<>();
        HashMap hm = new HashMap();
        double sup_item;
        double ntf=0;
        double totalNtf=0;
        double sb;
        while ((line = br.readLine()) != null) {
            //List<Integer> listTransctions = new ArrayList<>();
            processLine(line, map);
            transNum++;
            line = line.trim();
            sItem = line.split(" ");
            for (int i = 0; i < sItem.length; i++) {
                listTransctions.add(Integer.valueOf(sItem[i]));
            }
        }
        System.out.println(listOnList);
        //end of load and read dataset

        //frequent item mapping
        Enumeration e = map.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            numbItem = (int) map.get(key);
            sup_item = ((double) numbItem / (double) transNum);
            if (sup_item >= min_sup) {
                //System.out.println(key + ":" + numbItem);//+ " --> " + "Support : " + sup_item
                freqItem.add(key + ":" + numbItem);
                Collections.sort(freqItem);//
                frequency.add(numbItem);
                Collections.sort(frequency);
            }
        }
        System.out.println(freqItem);
        System.out.println(frequency);
        
        //fetching max-frequency of item
        for(int i=0;i<frequency.size();i++){
            maxFreq = frequency.get(frequency.size()-1);
        }
        System.out.println("max_tf = "+ maxFreq);
        System.out.println("-------------------");
        //end of fetching max-freq
        
        //counting tnf for each ak items
        for (int j = 0; j < advKnow.length; j++) {
            for (int k = 0; k < advKnow[j].length; k++) {
                akLenght++;
                for (int i = 0; i < freqItem.size(); i++) {
                    if (Integer.valueOf(freqItem.get(i).substring(0, freqItem.get(i).indexOf(":"))) == advKnow[j][k]) {
                        //System.out.println(freqItem.get(i));
                        //System.out.println(Integer.valueOf(freqItem.get(i).substring(freqItem.get(i).indexOf(":")+1)));
                        ntf = 0.4+((1-0.4)* ((Integer.valueOf(freqItem.get(i).substring(freqItem.get(i).indexOf(":")+1))))/maxFreq);
                        ntfList.add(ntf);
                        totalNtf+=ntf;
                        System.out.println("ntf : "+Integer.valueOf(freqItem.get(i).substring(0, freqItem.get(i).indexOf(":")))+"=>"+ntf);
                        hm.put(advKnow[j][k], ntf);
                    } 
                }
            }
        }
        //end of tnf counting
        DecimalFormat df = new DecimalFormat("##.####");
        System.out.println("Total ntf "+df.format(totalNtf));
        sb=totalNtf/akLenght;
        System.out.println("Sb : "+df.format(sb));
        double sbv= Double.valueOf(df.format(sb));//sbv : Sensitive bound value
        Set sethm = hm.entrySet();
        Iterator iter = sethm.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            //System.out.println(entry.getKey() +":"+ entry.getValue());
            //lanjut besok, retrieve item with ntf value < sb
            if(Double.valueOf((double) entry.getValue())<=sbv){
                System.out.println("Victim Item : " + entry.getKey());
            }
        }        
    }

    static void processLine(String line, Map map) {
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            addWord(map, st.nextToken());
        }
    }

    static void addWord(Map map, String word) {
        Object obj = map.get(word);
        if (obj == null) {
            map.put(word, 1);
        } else {
            int i = ((Integer) obj).intValue() + 1;
            map.put(word, new Integer(i));
        }
    }
}

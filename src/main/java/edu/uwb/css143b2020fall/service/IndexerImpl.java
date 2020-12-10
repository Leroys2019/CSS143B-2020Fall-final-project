package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        /*implemented some code from https://leetcode.com/problems/implement-magic-dictionary/discuss/107446/easy-14-lines-java-solution-hashmap
        //to help use the hashmap although some code had to be changed to match up with the problem im facing.
         */
        int size = docs.size();
        for (int i = 0; i<size; i++) {
            int firstWord = 0;//Hold a place for the first word found on the document
            String[] word = docs.get(i).trim().split("\\s+");
            for(String words: word){
                //Checks if doc has any empty words and if so it continues on to the next word
                if(docs.get(i).isEmpty()){
                    continue;
                }
                /*Creates and add in a key for each word that hasn't
                 already been given a key so it can added onto the map
                 */
             if(!indexes.containsKey(words)){
                 List<List<Integer>> val = new ArrayList<>();
                 for(int j = 0; j< size; j++){
                     List<Integer> atIndex = new ArrayList<>();
                     val.add(atIndex);
                 }
                 indexes.put(words,val);
             }
             List<List<Integer>> wordList = indexes.get(words);//grabs the list for the words

                List<Integer> docList = wordList.get(i);
                docList.add(firstWord); //adds the index of thr word to the doc list
                wordList.add(i, docList);
                wordList.remove(i); //removes the old list
                indexes.replace(words, wordList); //replace old with new list
                firstWord++;
            }
            }
        return indexes;
    }
}
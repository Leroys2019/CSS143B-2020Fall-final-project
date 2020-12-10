package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();
        /*used https://stackabuse.com/search-algorithms-in-java/
        and https://www.baeldung.com/java-check-if-list-sorted slightly to help
         */
        List<List<Integer>> val = index.get(keyPhrase);
        String[] words = keyPhrase.trim().split("\\s+");
        if (!index.containsKey(words[0])) {
            return result;
        }
        if (words.length == 1) {
            for (int i = 0; i < val.size(); i++) {
                if (val.get(i).size() != 0) {
                    result.add(i);
                }
            }
        }
        if (words.length > 1) {
            for (int j = 0; j < index.get(words[0]).size(); j++) {
                List<List<Integer>> wordPosition = new ArrayList<>(words.length);
                for (String word : words) {
                    wordPosition.add(index.get(word).get(j));
                }
                if (inOrder(wordPosition)) {
                    result.add(j);
                }
            }
        }
        return result;
    }
    private boolean inOrder(List<List<Integer>> wordPosition) {//Sorts before returning
        for (int k = 0; k < wordPosition.get(0).size(); k++) {
            return isSorted(wordPosition, 1, wordPosition.get(0).get(k));
        }
        return false;
    }
    private boolean isSorted(List<List<Integer>> wordPosition, int wordIndex, int position) {
        if (wordPosition.size() <= wordIndex) {
            return true;
        } else for (Integer place : wordPosition.get(wordIndex))  {
                if (place == position + 1) {
                    return isSorted(wordPosition, wordIndex + 1, position + 1);
                } else {
                    continue;
                   }
            }
            return false;
        }
    }
    /*Just here for my own usage to understand what the output needs to look like
    {
            better=[[], [], [], [], [1], [], []],
            hallo=[[], [2], [], [], [], [], []],
            world=[[0, 1, 3], [0, 1], [0, 4], [2], [], [], []],
            abc=[[], [], [3], [0], [], [], []],
            hello=[[2], [], [2], [1], [], [], []],
            seattle=[[], [], [1], [], [], [], []],
            sunny=[[], [], [], [], [0], [], [1]],
            day=[[], [], [5], [], [], [1], [0]],
            fun=[[], [], [], [3], [], [0, 2], [2]]
            }

     */
import java.util.*;

public class WordFrequencyGame {
    public String getWordFrequency(String sentence) {
        if (sentence.split("\\s+").length == 1) {//constant
            return sentence + " 1";
        } else {//unuse else
            try {//do to many
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");
                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {//stream
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map = getListMap(wordFrequencies);//param rename
                List<WordFrequency> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {//stream
                    WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
                    list.add(input);//unneed temp value
                }
                wordFrequencies = list;
                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());//param rename
                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency wordFrequency : wordFrequencies) {//stream;for loop ->function
                    String s = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> inputList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency input : inputList) {//stream
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }


}

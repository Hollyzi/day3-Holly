import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {//constant
            return sentence + " 1";
        } else {//unuse else
            try {//do to many
                //split the input string with 1 to n pieces of spaces
                List<WordFrequency> wordFrequencies = getInitiateWordFrequencies(sentence);

                //get the map for the next step of sizing the same word
                wordFrequencies = getWordFrequenciesMap(wordFrequencies);

                return getStringJoiner(wordFrequencies);

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private static String getStringJoiner(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getWordFrequenciesMap(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> wordToWordfrequencies = getListMap(wordFrequencies);
        List<WordFrequency> templeWordfrequencies = new ArrayList<>();

        for (Map.Entry<String, List<WordFrequency>> entry : wordToWordfrequencies.entrySet()) {
            WordFrequency input = new WordFrequency(entry.getKey(), entry.getValue().size());
            templeWordfrequencies.add(input);//unneed temp value
        }
        wordFrequencies = templeWordfrequencies;
        wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
        return wordFrequencies;
    }

    private static List<WordFrequency> getInitiateWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        List<WordFrequency> wordFrequencies= Arrays.stream(words)
                .map(word->new WordFrequency(word,1))
                .collect(Collectors.toList());

        return wordFrequencies;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencies) {//stream
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            } else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return map;
    }


}

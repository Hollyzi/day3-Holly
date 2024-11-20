import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String LINE_BREAK = "\n";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {//unuse else
            try {
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

        return wordToWordfrequencies.entrySet().stream()
                .map(entry-> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((current, next) -> next.getWordCount() - current.getWordCount())
                .toList();
    }

    private static List<WordFrequency> getInitiateWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE);

        List<WordFrequency> wordFrequencies= Arrays.stream(words)
                .map(word->new WordFrequency(word,1))
                .collect(Collectors.toList());

        return wordFrequencies;
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }


}

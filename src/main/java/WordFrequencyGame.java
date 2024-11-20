import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr) {//rename
        if (inputStr.split("\\s+").length == 1) {//constant
            return inputStr + " 1";
        } else {//unuse else
            try {//do to many
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split("\\s+");
                List<Input> inputList = new ArrayList<>();
                for (String s : arr) {//stream
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }
                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);//param rename
                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {//stream
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);//unneed temp value
                }
                inputList = list;
                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());//param rename
                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {//stream;for loop ->function
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {//stream
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}

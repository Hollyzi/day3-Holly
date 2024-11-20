public class WordFrequency {
    private String word;
    private int count;

    public WordFrequency(String word, int count){//parameter rename
        this.word =word;
        this.count =count;
    }


    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }


}

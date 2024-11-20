public class Input {
    private String value;
    private int count;

    public Input(String w, int i){//parameter rename
        this.value =w;
        this.count =i;
    }


    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }


}

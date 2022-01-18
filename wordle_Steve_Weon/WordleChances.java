public class WordleChances {
    private int numWrongGuesses;
    
    public WordleChances() {
        numWrongGuesses = 0;
    }
    
    public boolean isntDone() {
        return numWrongGuesses < 6;
    }
    
    public void wrongAgain() {
        numWrongGuesses++;
    }
    
    public int getWrongGuesses() {
        return numWrongGuesses;
    }
}
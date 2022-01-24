import java.util.*; 

public class WordleGame {
    public static void main(String[] args) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Puzzle puzzle = new Puzzle();
        WordleChances chances = new WordleChances();
        
        while (puzzle.isUnsolved() && chances.isntDone()) {
            System.out.println("Guess the WORDLE in 6 tries");
            System.out.println("After each guess, letters will change to show how close your guess was to the word");
            System.out.println("Only 5-letter existing words are valid");
            System.out.println("* Words with repetitive letters (ex. spoon, balls) are not in the list, but you can give it a try as long as it is a word");
            System.out.println();
            System.out.println("lower case = letter not in the word");
            System.out.println("? = letter in the word but not at correct spot");
            System.out.println("UPPER CASE = letter in the word & at correct spot");
            System.out.println();
            puzzle.show();
            String guess = scanner.nextLine();
            if (!puzzle.makeGuess(guess)) {
                chances.wrongAgain();
            }
            clearScreen();
        }
        
        if (chances.isntDone()) {
            System.out.print("You win! The word was " + puzzle.getWord() + "!");
        } else {
            System.out.print("You lost! The word was " + puzzle.getWord() + "!");
        }
    }
    
    public static void clearScreen() {
        System.out.println("\f");
    }
}
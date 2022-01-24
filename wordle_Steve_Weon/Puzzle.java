import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

public class Puzzle {
    private String temp, word, guess;
    private int index, correct;
    private boolean noInput, symbols, notFiveLetter, notWord;
    private String[][] grid;
    private ArrayList<String> words, possibleWords;
    
    public Puzzle() {
        temp = guess = "";
        noInput = symbols = notFiveLetter = notWord = false;
        index = correct = 0;
        grid = new String[6][5];
        words = new ArrayList<String>();
        possibleWords = new ArrayList<String>();
        loadWords("words.txt");
        word = words.get((int)(Math.random() * (words.size()+1)));
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = "_";
            }
        }
    }
    
    public void show() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
        if (noInput) {
            System.out.println("*No input");
        }
        if (notFiveLetter || symbols) {
            System.out.println("*Invalid input: " + temp);
        }
        if (notWord) {
            System.out.println(temp + " is not a word");
        }
    }
    
    public boolean makeGuess(String a) {
        noInput = symbols = notFiveLetter = notWord = false;
        temp = a.toLowerCase();
        if (temp.length() <= 0) { //filters empty inputs
            noInput = true;
            return true;
        }
        if (temp.length() != 5) { //filters inputs less/greater than 5 words
            notFiveLetter = true;
            return true;
        }
        if (hasSign(temp)) { //filters inputs with signs
            symbols = true;
            return true;
        }
        if (!isExistingWord(temp)) { //Checks if it is an actual word
            notWord = true;
            return true;
        }
        guess = temp ; //input is valid to be a guess
        correct = 0;
        for (int col = 0; col < grid[0].length; col++) {
            grid[index][col] = guess.substring(col, col+1);
            if (grid[index][col].equals(word.substring(col,col+1))) {
                grid[index][col] = word.substring(col, col+1).toUpperCase();
                correct++;
            }
            else if (word.contains(grid[index][col])) {
                grid[index][col] += "?";
            }
        }
        index++;
        if (correct == 5) return true;
        return false;
    }
    
    public boolean isUnsolved() {
        return correct != 5;
    }
    
    public boolean hasSign(String x) {
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) < 'a' || x.charAt(i) > 'z') {
                return true;
            }
        }
        return false;
    }
    
    public String getWord() {
        return word;
    }
    
    public boolean isExistingWord(String temp) {
        for (int i = 0; i < possibleWords.size(); i++) {
            if (possibleWords.get(i).equals(temp)) return true;
        }
        return false;
    }
    
    public void loadWords(String filename) {
        File wordfile = new File(filename);
        try {
            Scanner fileScanner = new Scanner(wordfile);
            boolean repeated = false;
            while (fileScanner.hasNext()) {
                String w = fileScanner.nextLine();
                if (w.length() == 5 && !Character.isUpperCase(w.charAt(0))) {
                    possibleWords.add(w);
                    for (int i = 0; i < w.length(); i++) {
                        if (w.substring(i+1).indexOf(w.substring(i,i+1)) != -1) {
                            repeated = true;
                        }
                    }
                    if (!repeated) {
                        words.add(w);
                    }
                    repeated = false;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.print(e);
        }
    }
}
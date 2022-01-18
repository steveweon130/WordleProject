import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class Puzzle {
    private String temp, word, guess;
    private int index, correct;
    private boolean noInput, symbols, notFiveLetter;
    private String[][] grid;
    
    public Puzzle() {
        temp = guess = "";
        noInput = symbols = notFiveLetter = false;
        index = correct = 0;
        grid = new String[6][5];
        word = "plank";
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
    }
    
    public boolean makeGuess(String a) {
        noInput = symbols = notFiveLetter = false;
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
}
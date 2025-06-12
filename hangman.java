import arc.*;
import java.util.*;
import java.io.*;

public class Game {

    public void play(Console con, ThemeManager themeManager, LeaderboardManager leaderboard) {
        con.clear();
        con.println("Enter your name:");
        String name = con.readLine();
        boolean isCheater = name.equalsIgnoreCase("statitan");
        int score = 0;

        ArrayList<String> themes = themeManager.getThemes();
        con.println("Available themes:");
        for (String theme : themes) {
            con.println("- " + theme);
        }

        con.println("Enter the theme name:");
        String chosenTheme = con.readLine();

        ArrayList<String> words = themeManager.loadWords(chosenTheme);
        boolean[] used = new boolean[words.size()];
        int remaining = words.size();

        while (remaining > 0) {
            int index;
            do {
                index = (int) (Math.random() * words.size());
            } while (used[index]);

            used[index] = true;
            String word = words.get(index).toUpperCase();

            boolean won = runGame(con, word, isCheater);
            if (won) {
                score++;
            }

            remaining--;
            con.println("Play next word? (Y/N)");
            if (!con.readLine().equalsIgnoreCase("Y")) break;
        }

        con.println(name + " saved the hangman " + score + " times!");
        leaderboard.saveScore(name, score);
        con.readLine(); // Pause
    }

    public boolean runGame(Console con, String word, boolean isCheater) {
        int wrongGuesses = 0;
        boolean[] revealed = new boolean[word.length()];
        int maxFails = 6;
        if (isCheater) maxFails = 7;

        while (wrongGuesses < maxFails) {
            Graphics.drawHangman(con, wrongGuesses, word, revealed);
            con.println("Guess the word:");
            String guess = con.readLine().toUpperCase();
            System.out.println("DEBUG: Guess was " + guess);

            if (guess.equals(word)) {
                return true;
            } else {
                wrongGuesses++;
                int revealIndex;
                do {
                    revealIndex = (int) (Math.random() * word.length());
                } while (revealed[revealIndex]);
                revealed[revealIndex] = true;
            }
        }

        Graphics.drawHangman(con, maxFails, word, revealed);
        con.println("You failed! Word was: " + word);
        return false;
    }
}

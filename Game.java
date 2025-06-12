import arc.*;

public class Game {

    public void play(Console con, ThemeManager themeManager, LeaderboardManager leaderboard) {
        con.clear();
        con.println("Enter your name:");
        String name = con.readLine();
        boolean isCheater = name.equalsIgnoreCase("statitan");
        int intScore = 0;

        String[] themes = themeManager.getThemes();
        con.println("Available themes:");
        for (int i = 0; i < themes.length; i++) {
            con.println("- " + themes[i]);
        }

        con.println("Enter the theme name:");
        String chosenTheme = con.readLine();

        String[] words = themeManager.loadWords(chosenTheme);
        boolean[] used = new boolean[words.length];
        int intRemaining = words.length;

        while (intRemaining > 0) {
            int intIndex;
            do {
                intIndex = (int) (Math.random() * words.length);
            } while (used[intIndex]);

            used[intIndex] = true;
            String strWord = words[intIndex].toUpperCase();

            boolean won = runGame(con, strWord, isCheater);
            if (won) {
                intScore++;
            }

            intRemaining--;
            con.println("Play next word? (Y/N)");
            if (!con.readLine().equalsIgnoreCase("Y")) break;
            con.clear();
        }

        con.println(name + " saved the hangman " + intScore + " times!");
        leaderboard.saveScore(name, intScore);
        con.readLine(); // Pause
    }

    public boolean runGame(Console con, String word, boolean isCheater) {
        int intWrongGuesses = 0;
        boolean[] revealed = new boolean[word.length()];
        int intMaxFails = isCheater ? 7 : 6;

        while (intWrongGuesses < intMaxFails) {
            Graphics.drawHangman(con, intWrongGuesses, word, revealed);
            con.println("Guess the word:");
            String guess = con.readLine().toUpperCase();

            if (guess.equals(word)) return true;

            intWrongGuesses++;
            int intRevealIndex;
            do {
                intRevealIndex = (int) (Math.random() * word.length());
            } while (revealed[intRevealIndex]);

            revealed[intRevealIndex] = true;
        }

        Graphics.drawHangman(con, intMaxFails, word, revealed);
        con.println("You failed! Word was: " + word);
        return false;
    }
}

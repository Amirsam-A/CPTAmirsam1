import arc.*;

public class Game {

    public void play(Console con, ThemeManager themeManager, LeaderboardManager leaderboard) {
        con.clear();
        con.println("Enter your name:");
        String name = con.readLine();
        boolean isCheater = name.equalsIgnoreCase("statitan");
        int score = 0;

        String[] themes = themeManager.getThemes();
        con.println("Available themes:");
        for (int i = 0; i < themes.length; i++) {
            con.println("- " + themes[i]);
        }

        con.println("Enter the theme name:");
        String chosenTheme = con.readLine();

        String[] words = themeManager.loadWords(chosenTheme);
        boolean[] used = new boolean[words.length];
        int remaining = words.length;

        while (remaining > 0) {
            int index;
            do {
                index = (int) (Math.random() * words.length);
            } while (used[index]);

            used[index] = true;
            String word = words[index].toUpperCase();

            boolean won = runGame(con, word, isCheater);
            if (won) {
                score++;
            }

            remaining--;
            con.println("Play next word? (Y/N)");
            if (!con.readLine().equalsIgnoreCase("Y")) break;
            con.clear();
        }

        con.println(name + " saved the hangman " + score + " times!");
        leaderboard.saveScore(name, score);
        con.readLine(); // Pause
    }

    public boolean runGame(Console con, String word, boolean isCheater) {
        int wrongGuesses = 0;
        boolean[] revealed = new boolean[word.length()];
        int maxFails = isCheater ? 7 : 6;

        while (wrongGuesses < maxFails) {
            Graphics.drawHangman(con, wrongGuesses, word, revealed);
            con.println("Guess the word:");
            String guess = con.readLine().toUpperCase();

            if (guess.equals(word)) return true;

            wrongGuesses++;
            int revealIndex;
            do {
                revealIndex = (int) (Math.random() * word.length());
            } while (revealed[revealIndex]);

            revealed[revealIndex] = true;
        }

        Graphics.drawHangman(con, maxFails, word, revealed);
        con.println("You failed! Word was: " + word);
        return false;
    }
}

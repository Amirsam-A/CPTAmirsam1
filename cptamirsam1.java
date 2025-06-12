import arc.*;

public class cptamirsam1 {
    public static void main(String[] args) {
        Console con = new Console("Hangman");
        ThemeManager themeManager = new ThemeManager();
        LeaderboardManager leaderboard = new LeaderboardManager();
        Game game = new Game();
        String strChoice;

        while (true) {
            con.clear();
            con.println("=== Hangman ===");
            con.println("(P)lay Game");
            con.println("(L)eaderboard");
            con.println("(A)dd Theme");
            con.println("(H)elp");
            con.println("(Q)uit");

            strChoice = con.getChar() + "";
            strChoice = strChoice.toUpperCase();

            if (strChoice.equals("P")) {
                game.play(con, themeManager, leaderboard);
            } else if (strChoice.equals("L")) {
                leaderboard.displayLeaderboard(con);
            } else if (strChoice.equals("A")) {
                themeManager.addTheme(con);
            } else if (strChoice.equals("H")) {
                con.println("Help: Choose 'Play Game' to start, then guess the full word.");
                con.readLine(); // pause
            } else if (strChoice.equals("S")) {
                con.println("Secret Joke: Why do programmers hate nature? It has too many bugs!");
                con.readLine(); // pause
            } else if (strChoice.equals("Q")) {
                con.println("Thanks for playing!");
                break;
            }
        }
    }
}

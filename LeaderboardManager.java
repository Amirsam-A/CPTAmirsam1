import arc.*;

public class LeaderboardManager {

    public void saveScore(String name, int score) {
        TextOutputFile out = new TextOutputFile("Leaderboard.txt", true);
        out.println(name + "," + score);
        out.close();
    }

    public void displayLeaderboard(Console con) {
        String[] names = new String[100];
        int[] scores = new int[100];
        int count = 0;

        try {
            TextInputFile in = new TextInputFile("Leaderboard.txt");
            while (!in.eof() && count < 100) {
                String line = in.readLine();
                String[] parts = line.split(",");
                names[count] = parts[0];
                scores[count] = Integer.parseInt(parts[1]);
                count++;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("DEBUG: Error reading leaderboard.");
        }

        // Bubble sort
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (scores[j] < scores[j + 1]) {
                    int tempScore = scores[j];
                    String tempName = names[j];
                    scores[j] = scores[j + 1];
                    names[j] = names[j + 1];
                    scores[j + 1] = tempScore;
                    names[j + 1] = tempName;
                }
            }
        }

        con.println("=== Leaderboard ===");
        for (int i = 0; i < count; i++) {
            con.println(names[i] + ": " + scores[i]);
        }
        con.readLine(); // pause
    }
}

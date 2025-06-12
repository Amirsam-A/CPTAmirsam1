import arc.*;

public class ThemeManager {

    public String[] getThemes() {
        String[] themes = new String[100];
        TextInputFile file = new TextInputFile("Themes.txt");
        int intCount = 0;

        while (!file.eof() && intCount < 100) {
            themes[intCount] = file.readLine();
            intCount++;
        }
        file.close();

        // Resize array to remove unused slots
        String[] trimmed = new String[intCount];
        for (int i = 0; i < intCount; i++) trimmed[i] = themes[i];
        return trimmed;
    }

    public String[] loadWords(String filename) {
        String[] words = new String[100];
        TextInputFile file = new TextInputFile(filename);
        int count = 0;

        while (!file.eof() && count < 100) {
            String word = file.readLine().trim();
            if (word.length() >= 7) {
                words[count] = word;
                count++;
            }
        }
        file.close();

        String[] trimmed = new String[count];
        for (int i = 0; i < count; i++) trimmed[i] = words[i];
        return trimmed;
    }

    public void addTheme(Console con) {
        con.println("Enter new theme name (e.g. StarWars.txt):");
        String filename = con.readLine();

        TextOutputFile themeFile = new TextOutputFile(filename, false);
        TextOutputFile master = new TextOutputFile("Themes.txt", true);

        while (true) {
            con.println("Add a word (min 7 letters) or 'stop':");
            String word = con.readLine();
            if (word.equalsIgnoreCase("stop")) break;
            if (word.length() >= 7) {
                themeFile.println(word);
            }
        }

        themeFile.close();
        master.println(filename);
        master.close();
    }
}

import arc.*;

public class Graphics {

    public static void drawHangman(Console con, int intStage, String strWord, boolean[] blnRevealed) {
        con.clear();
        con.drawLine(100, 500, 300, 500); // base
        con.drawLine(200, 500, 200, 200); // pole
        con.drawLine(200, 200, 350, 200); // top
        con.drawLine(350, 200, 350, 250); // rope

        if (intStage > 0) con.drawOval(325, 250, 50, 50); // head
        if (intStage > 1) con.drawLine(350, 300, 350, 400); // body
        if (intStage > 2) con.drawLine(350, 325, 300, 370); // left arm
        if (intStage > 3) con.drawLine(350, 325, 400, 370); // right arm
        if (intStage > 4) con.drawLine(350, 400, 300, 450); // left leg
        if (intStage > 5) con.drawLine(350, 400, 400, 450); // right leg

        String strDisplay = "";
        for (int i = 0; i < strWord.length(); i++) {
            strDisplay += blnRevealed[i] ? strWord.charAt(i) + " " : "_ ";
        }
        con.println("Word: " + strDisplay.trim());
    }
}

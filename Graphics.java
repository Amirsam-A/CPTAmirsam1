import arc.*;

public class Graphics {

    public static void drawHangman(Console con, int stage, String word, boolean[] revealed) {
        con.clear();
        con.drawLine(100, 500, 300, 500); // base
        con.drawLine(200, 500, 200, 200); // pole
        con.drawLine(200, 200, 350, 200); // top
        con.drawLine(350, 200, 350, 250); // rope

        if (stage > 0) con.drawOval(325, 250, 50, 50); // head
        if (stage > 1) con.drawLine(350, 300, 350, 400); // body
        if (stage > 2) con.drawLine(350, 325, 300, 370); // left arm
        if (stage > 3) con.drawLine(350, 325, 400, 370); // right arm
        if (stage > 4) con.drawLine(350, 400, 300, 450); // left leg
        if (stage > 5) con.drawLine(350, 400, 400, 450); // right leg

        String display = "";
        for (int i = 0; i < word.length(); i++) {
            display += revealed[i] ? word.charAt(i) + " " : "_ ";
        }
        con.println("Word: " + display.trim());
    }
}

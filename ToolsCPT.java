import arc.*;

public class ToolsCPT{
	
	public static int menuprint (Console con){
		con.println("press 1 to play");
		con.println("press 2 for leaderboard");
		con.println("press 3 to add a theme");
		con.println("press 4 to quit");
		int choice = con.readInt();
		return choice;
		
	}
	
	public static void playprint (Console con){
		con.println("*----Hang Man----*");
	}
	
	public static void leaderboardprint (Console con){
		con.println("*----Leaderboards---*");
	}
	
	public static void themeprint (Console con){
		con.println("*----Add Themes----*");
	}
	
	public static void secretprint (Console con){
		con.println("You found the secret!");
		con.println("what did the other plate say to the other plate?");
		con.println("dinner's on me!");
	}
	
	
	
}


import arc.*;

public class CPTAmirsam1{
	public static void main(String[] args) {
		Console con = new Console();
				
		while(true) {
			int choice = ToolsCPT.menuprint(con);
			
			if (choice == 1){
				ToolsCPT.playprint(con);
			} else if (choice == 2) {
				ToolsCPT.leaderboardprint(con);
			} else if (choice == 3) {
				ToolsCPT.themeprint(con);
			} else if (choice == 4 ) {
				System.exit(0);
			} else {
				con.println("Ivalid choice. Try again.");
			}
		}	
	}
}

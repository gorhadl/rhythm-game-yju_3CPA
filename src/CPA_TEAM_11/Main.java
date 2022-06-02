package CPA_TEAM_11;

import java.io.IOException;

public class Main {
	public static final String aws = "127.0.0.1";
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5;
	public static final int SLEEP_TIME = 3; // 조정하면 렉 사라질것같음
	public static final int REACH_TIME = 1;

	public static void main(String[] args) {
		
		try {
			new DynamicBeat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
//if (str[1].equals("D")) 
//	GameFrame.game.noteButtonOtherD = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//else if(str[1].equals("F"))
//	GameFrame.game.noteButtonOtherF = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//else if(str[1].equals("J"))
//	GameFrame.game.noteButtonOtherJ = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//else if(str[1].equals("K"))
//	GameFrame.game.noteButtonOtherK = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//if (str[1].equals("D")) 
//	GameFrame.game.noteButtonOtherD = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//else if(str[1].equals("F"))
//	GameFrame.game.noteButtonOtherF = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//else if(str[1].equals("J"))
//	GameFrame.game.noteButtonOtherJ = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//else if(str[1].equals("K"))
//	GameFrame.game.noteButtonOtherK = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();


//else if(d1 == false) {
//noteButtonOtherD = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//} else if(f1) {
//noteButtonOtherF = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//} else if(f1 == false) {
//noteButtonOtherF = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//} else if(j1) {
//noteButtonOtherJ = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//} else if(j1 == false) {
//noteButtonOtherJ = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//} else if(k1) {
//noteButtonOtherK = new ImageIcon(Main.class.getResource("../Images/notebuttonown2.png")).getImage();
//} else if(k1 == false) {
//noteButtonOtherK = new ImageIcon(Main.class.getResource("../Images/notebuttonown.png")).getImage();
//}

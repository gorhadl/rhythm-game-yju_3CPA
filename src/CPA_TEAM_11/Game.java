package CPA_TEAM_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Beat;

public class Game extends Thread {
	private Image scoreBox = new ImageIcon(Main.class.getResource("../Images2/score.png")).getImage();
	private Image noteButtonD = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
	private Image noteButtonF = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
	private Image noteButtonJ = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
	private Image noteButtonK = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
	
	private Image Score = new ImageIcon(Main.class.getResource("../Images2/Score1.png")).getImage();
	private Image judgeImage;
	
	private String titleName;
	private String musicTitle;
	private Music gameMusic;
	private int score;
	private String sc = Integer.toString(score);
	private boolean isResult = false;
	static boolean back = false;
	private int count=0;
	private String c;

	ArrayList<Note> noteList = new ArrayList<>();
	
	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteButtonD,343,600,null);
		g.drawImage(noteButtonF,493,600,null);
		g.drawImage(noteButtonJ,643,600,null);
		g.drawImage(noteButtonK,793,600,null);
		//판정라인 그릴꺼면 여기다가 노트보다 위쪽에 
		
		for(int i=0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(note.getY() > 660 ) {
				judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge5.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.drawImage(scoreBox,0,30,null);
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Elephant", Font.BOLD,50));
		
		g.drawString(sc, 30, 95);
		if(!isResult)
			g.drawImage(judgeImage,485,200, null); //468
//		g.drawString(titleName, 20, 702); 이거는 게임화면에 곡제목띄우는거
		
		if(isResult) {
			g.drawImage(Score, 390, 140,null);
			this.sc = scorePrint();
			g.setColor(Color.white);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("Elephant", Font.BOLD,100));
			g.drawString(sc, 450, 200);
			
		}
//		if(!(count ==0) && count < 10) {
//			this.c = countPrint();
//			g.setColor(Color.white);
//			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//			g.setFont(new Font("Arial", Font.BOLD,50));
//			g.drawString(c, 625, 340);
//		} else if (!(count == 0) && count <100) {
//			this.c = countPrint();
//			g.setColor(Color.white);
//			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//			g.setFont(new Font("Arial", Font.BOLD,50));
//			g.drawString(c, 610, 340);
//		} else if (!(count == 0) && count < 1000) {
//			g.setColor(Color.white);
//			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//			g.setFont(new Font("Arial", Font.BOLD,50));
//			this.c = countPrint();
//			g.drawString(c, 595, 340);
//		}
	}
	
	public void pressD() {
		judge("D");
		noteButtonD = new ImageIcon(Main.class.getResource("../Images2/notebutton2.png")).getImage();
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseD() {
		noteButtonD = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
		
	}
	public void pressF() {
		judge("F");
		noteButtonF = new ImageIcon(Main.class.getResource("../Images2/notebutton2.png")).getImage();
		new Music("drumBig1.mp3",false).start();
	}
	public void releaseF() {
		noteButtonF = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
		
	}
	public void pressJ() {
		judge("J");
		noteButtonJ = new ImageIcon(Main.class.getResource("../Images2/notebutton2.png")).getImage();
		new Music("drumSmall2.mp3",false).start();
	}
	public void releaseJ() {
		noteButtonJ = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
		
	}
	public void pressK() {
		judge("K");
		noteButtonK = new ImageIcon(Main.class.getResource("../Images2/notebutton2.png")).getImage();
		new Music("drumBig2.mp3",false).start();
	}
	public void releaseK() {
		noteButtonK = new ImageIcon(Main.class.getResource("../Images2/notebutton1.png")).getImage();
		
	}
	@Override
	public void run() {
		dropNotes(this.titleName);
		try {
			Thread.sleep(7000);
			isResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			back = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close() {
		this.gameMusic.close();
		this.interrupt();
	}
	public void dropNotes(String titlename) {
		Beat[] beats = null;
		if(titleName.equals("Song1")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] {
					 new Beat(startTime, "D"),
		               new Beat(startTime + gap*1 , "D"),
		               new Beat(startTime + gap*2 , "F"),
		               new Beat(startTime + gap*3 , "D"),
		               new Beat(startTime + gap*4 , "F"),
		               new Beat(startTime + gap*5 , "D"),
		               new Beat(startTime + gap*6 , "F"),
		               new Beat(startTime + gap*10 , "J"),
		               new Beat(startTime + gap*11 , "J"),
		               new Beat(startTime + gap*12 , "K"),
		               new Beat(startTime + gap*13 , "J"),
		               new Beat(startTime + gap*14 , "K"),
		               new Beat(startTime + gap*15 , "J"),
		               new Beat(startTime + gap*16 , "K"),
		               
		               new Beat(startTime + gap*20 , "D"),
		               new Beat(startTime + gap*22 , "F"),
		               new Beat(startTime + gap*24 , "J"),
		               new Beat(startTime + gap*26 , "K"),
		               new Beat(startTime + gap*28 , "J"),
		               new Beat(startTime + gap*30 , "F"),
		               new Beat(startTime + gap*32 , "D"),
		               
		               new Beat(startTime + gap*34 , "K"),
		               new Beat(startTime + gap*36 , "J"),
		               new Beat(startTime + gap*38 , "F"),
		               new Beat(startTime + gap*40 , "D"),
		               new Beat(startTime + gap*42 , "F"),
		               new Beat(startTime + gap*44 , "J"),
		               new Beat(startTime + gap*46 , "K"),
		               
		               new Beat(startTime + gap*48 , "K"),
		               new Beat(startTime + gap*50 , "K"),
		               new Beat(startTime + gap*52 , "F"),
		               new Beat(startTime + gap*54 , "K"),
		               new Beat(startTime + gap*56 , "D"),
		               new Beat(startTime + gap*58 , "J"),
		               new Beat(startTime + gap*60 , "J"),
		               new Beat(startTime + gap*62 , "J"),
		               new Beat(startTime + gap*64 , "D"),
		               new Beat(startTime + gap*66 , "K"),
		               new Beat(startTime + gap*68 , "K"),
		               new Beat(startTime + gap*70 , "K"),
		               
		               new Beat(startTime + gap*72 , "K"),
		               new Beat(startTime + gap*73 , "D"),
		               new Beat(startTime + gap*74 , "J"),
		               new Beat(startTime + gap*75 , "F"),
		               new Beat(startTime + gap*76 , "K"),
		               new Beat(startTime + gap*77 , "D"),
		               new Beat(startTime + gap*78 , "J"),
		               new Beat(startTime + gap*79 , "F"),
		               new Beat(startTime + gap*80 , "K"),
		               new Beat(startTime + gap*81 , "D"),
		               new Beat(startTime + gap*82 , "J"),
		               new Beat(startTime + gap*83 , "F"),
		               
		               new Beat(startTime + gap*85 , "F"),
		               new Beat(startTime + gap*87 , "F"),
		               new Beat(startTime + gap*88 , "D"),
		               new Beat(startTime + gap*90 , "K"),
		               new Beat(startTime + gap*93 , "J"),
		               new Beat(startTime + gap*94 , "K"),
		               new Beat(startTime + gap*96 , "D"),
		               new Beat(startTime + gap*98 , "F"),
		               new Beat(startTime + gap*100 , "F"),
		               new Beat(startTime + gap*102 , "D"),
		               new Beat(startTime + gap*104 , "J"),
		               new Beat(startTime + gap*106 , "K"),
		               new Beat(startTime + gap*108 , "D"),
		               new Beat(startTime + gap*110 , "J"),
		               new Beat(startTime + gap*112 , "F"),
		               new Beat(startTime + gap*114 , "K"),
		               
		               // 아래로, 메인 훅 등장부분
		               
		               new Beat(startTime + gap*116 , "F"), //전 +2, 후 +2
		               new Beat(startTime + gap*118 , "F"), //후+1
		               new Beat(startTime + gap*119 , "D"), //후+2
		               new Beat(startTime + gap*121 , "K"), //후+3
		               new Beat(startTime + gap*124 , "J"), //후+1
		               new Beat(startTime + gap*125 , "K"), //후+2
		               new Beat(startTime + gap*127 , "D"), //후+2
		               new Beat(startTime + gap*129 , "F"), //후+2
		               new Beat(startTime + gap*131 , "F"), //후+2
		               new Beat(startTime + gap*133 , "D"), //후+2
		               new Beat(startTime + gap*135 , "J"), //후+2
		               new Beat(startTime + gap*137 , "K"), //후+2
		               new Beat(startTime + gap*139 , "D"), //후+2
		               new Beat(startTime + gap*141 , "J"), //후+2
		               new Beat(startTime + gap*143 , "F"), //후+2
		               new Beat(startTime + gap*145 , "K"), //후+2

		               new Beat(startTime + gap*147 , "D"), //전 +2, 후 +2
		               new Beat(startTime + gap*149 , "D"), //후+1
		               new Beat(startTime + gap*150 , "F"), //후+2
		               new Beat(startTime + gap*152 , "J"), //후+3
		               new Beat(startTime + gap*155 , "K"), //후+1
		               new Beat(startTime + gap*156 , "J"), //후+2
		               new Beat(startTime + gap*158 , "F"), //후+2
		               new Beat(startTime + gap*160 , "D"), //후+2
		               new Beat(startTime + gap*162 , "D"), //후+2
		               new Beat(startTime + gap*164 , "F"), //후+2
		               new Beat(startTime + gap*166 , "K"), //후+2
		               new Beat(startTime + gap*168 , "J"), //후+2
		               new Beat(startTime + gap*170 , "F"), //후+2
		               new Beat(startTime + gap*172 , "K"), //후+2
		               new Beat(startTime + gap*174 , "D"), //후+2
		               new Beat(startTime + gap*176 , "J"), //후+2
		               
		               new Beat(startTime + gap*178 , "F"), //전 +2, 후 +2
		               new Beat(startTime + gap*180 , "F"), //후+1
		               new Beat(startTime + gap*181 , "D"), //후+2
		               new Beat(startTime + gap*183 , "K"), //후+3
		               new Beat(startTime + gap*186 , "J"), //후+1
		               new Beat(startTime + gap*187 , "K"), //후+2
		               new Beat(startTime + gap*189 , "D"), //후+2
		               new Beat(startTime + gap*191 , "F"), //후+2
		               new Beat(startTime + gap*193 , "F"), //후+2
		               new Beat(startTime + gap*195 , "D"), //후+2
		               new Beat(startTime + gap*197 , "J"), //후+2
		               new Beat(startTime + gap*199 , "K"), //후+2
		               new Beat(startTime + gap*201 , "D"), //후+2
		               new Beat(startTime + gap*203 , "J"), //후+2
		               new Beat(startTime + gap*205 , "F"), //후+2
		               new Beat(startTime + gap*207 , "K"), //후+2
		               
		               new Beat(startTime + gap*209 , "D"), //전 +2, 후 +2
		               new Beat(startTime + gap*211 , "D"), //후+1
		               new Beat(startTime + gap*212 , "F"), //후+2
		               new Beat(startTime + gap*214 , "J"), //후+3
		               new Beat(startTime + gap*217 , "K"), //후+1
		               new Beat(startTime + gap*218 , "J"), //후+2
		               new Beat(startTime + gap*220 , "F"), //후+2
		               new Beat(startTime + gap*222 , "D"), //후+2
		               new Beat(startTime + gap*224 , "D"), //후+2
		               new Beat(startTime + gap*226 , "F"), //후+2
		               new Beat(startTime + gap*228 , "K"), //후+2
		               new Beat(startTime + gap*230 , "J"), //후+2
		               new Beat(startTime + gap*232 , "F"), //후+2
		               new Beat(startTime + gap*234 , "K"), //후+2
		               new Beat(startTime + gap*236 , "D"), //후+2
		               new Beat(startTime + gap*238 , "J"), //후+2
		               
		               new Beat(startTime + gap*240 , "F"), //전 +2, 후 +2
		               new Beat(startTime + gap*242 , "F"), //후+1
		               new Beat(startTime + gap*243 , "D"), //후+2
		               new Beat(startTime + gap*245 , "K"), //후+3
		               new Beat(startTime + gap*248 , "J"), //후+1
		               new Beat(startTime + gap*249 , "K"), //후+2
		               new Beat(startTime + gap*251 , "D"), //후+2
		               new Beat(startTime + gap*253 , "F"), //후+2
		               new Beat(startTime + gap*255 , "F"), //후+2
		               new Beat(startTime + gap*257 , "D"), //후+2
		               new Beat(startTime + gap*259 , "J"), //후+2
		               new Beat(startTime + gap*261 , "K"), //후+2
		               new Beat(startTime + gap*263 , "D"), //후+2
		               new Beat(startTime + gap*265 , "J"), //후+2
		               new Beat(startTime + gap*267 , "F"), //후+2
		               new Beat(startTime + gap*269 , "K"), //후+2
		               
		               new Beat(startTime + gap*271 , "D"), //전 +2, 후 +2
		               new Beat(startTime + gap*273 , "D"), //후+1
		               new Beat(startTime + gap*274 , "F"), //후+2
		               new Beat(startTime + gap*276 , "J"), //후+3
		               new Beat(startTime + gap*279 , "K"), //후+1
		               new Beat(startTime + gap*280 , "J"), //후+2
		               new Beat(startTime + gap*282 , "F"), //후+2
		               new Beat(startTime + gap*284 , "D"), //후+2
		               new Beat(startTime + gap*286 , "D"), //후+2
		               new Beat(startTime + gap*288 , "F"), //후+2
		               new Beat(startTime + gap*290 , "K"), //후+2
		               new Beat(startTime + gap*292 , "J"), //후+2
		               new Beat(startTime + gap*294 , "F"), //후+2
		               new Beat(startTime + gap*296 , "K"), //후+2
		               new Beat(startTime + gap*298 , "D"), //후+2
		               new Beat(startTime + gap*300 , "J"), //후+2
		               
		               new Beat(startTime + gap*302 , "F"), //전 +2, 후 +2
		               new Beat(startTime + gap*304 , "F"), //후+1
		               new Beat(startTime + gap*305 , "D"), //후+2
		               new Beat(startTime + gap*307 , "K"), //후+3
		               new Beat(startTime + gap*310 , "J"), //후+1
		               new Beat(startTime + gap*311 , "K"), //후+2
		               new Beat(startTime + gap*313 , "D"), //후+2
		               new Beat(startTime + gap*315 , "F"), //후+2
		               new Beat(startTime + gap*317 , "F"), //후+2
		               new Beat(startTime + gap*319 , "D"), //후+2
		               new Beat(startTime + gap*321 , "J"), //후+2
		               new Beat(startTime + gap*323 , "K"), //후+2
		               new Beat(startTime + gap*325 , "D"), //후+2
		               new Beat(startTime + gap*327 , "J"), //후+2
		               new Beat(startTime + gap*329 , "F"), //후+2
		               new Beat(startTime + gap*331 , "K"), //후+2
		               
		               new Beat(startTime + gap*342 , "D"),
		               new Beat(startTime + gap*342 , "F"),
		               new Beat(startTime + gap*342 , "K"),
		               new Beat(startTime + gap*342 , "J"),
		               
		              

		         };
		}
		else if (titleName.equals("Song2")) {
			int startTime = -300;
			int gap = 125;
			beats = new Beat[] {
					new Beat(startTime, "D"),
					new Beat(startTime + gap *3, "F"),
					new Beat(startTime + gap *5, "D"),
					new Beat(startTime + gap *8, "F"),
					new Beat(startTime + gap *12, "J"),
					new Beat(startTime + gap *15, "D"),
					new Beat(startTime + gap *15, "J"),
					new Beat(startTime + gap *25, "K"),
					new Beat(startTime + gap *27, "F"),
					new Beat(startTime + gap *29, "J"),
					new Beat(startTime + gap *31, "D"),
					new Beat(startTime + gap *34, "F"),
					new Beat(startTime + gap *36, "D"),
					new Beat(startTime + gap *39, "J"),
					new Beat(startTime + gap *42, "K"),
					new Beat(startTime + gap *45, "D"),
					new Beat(startTime + gap *47, "F"),
					new Beat(startTime + gap *51, "J"),
					new Beat(startTime + gap *54, "K"),
					new Beat(startTime + gap *56, "J"),
					new Beat(startTime + gap *58, "K"),
					new Beat(startTime + gap *61, "J"),
					new Beat(startTime + gap *64, "D"),
//					new Beat(startTime, "D"),
					new Beat(startTime + gap *68, "F"),
					new Beat(startTime + gap *71, "D"),
					new Beat(startTime + gap *73, "J"),
					new Beat(startTime + gap *75, "K"),
					new Beat(startTime + gap *78, "D"),
					new Beat(startTime + gap *80, "J"),
					new Beat(startTime + gap *84, "K"),
					new Beat(startTime + gap *87, "F"),
					new Beat(startTime + gap *89, "J"),
					new Beat(startTime + gap *92, "D"),
					new Beat(startTime + gap *94, "F"),
					new Beat(startTime + gap *97, "D"),
					new Beat(startTime + gap *101, "J"),
					new Beat(startTime + gap *102, "K"),
					new Beat(startTime + gap *103, "D"),
					new Beat(startTime + gap *107, "F"),
					new Beat(startTime + gap *110, "J"),
					new Beat(startTime + gap *113, "K"),
					new Beat(startTime + gap *115, "J"),
					new Beat(startTime + gap *118, "K"),
					new Beat(startTime + gap *120, "J"),
					new Beat(startTime + gap *122, "D"),
					new Beat(startTime + gap *127, "D"),
					new Beat(startTime + gap *127, "K"),
					new Beat(startTime + gap *130, "D"),
					new Beat(startTime + gap *130, "K"),
					new Beat(startTime + gap *133, "D"),
					new Beat(startTime + gap *133, "K"),
					new Beat(startTime + gap *136, "D"),
					new Beat(startTime + gap *136, "K"),
					new Beat(startTime + gap *140, "D"),
					new Beat(startTime + gap *140, "K"),
					new Beat(startTime + gap *143, "D"),
					new Beat(startTime + gap *143, "K"),
					new Beat(startTime + gap *146, "D"),
					new Beat(startTime + gap *146, "K"),
					
					new Beat(startTime + gap *150, "K"),
					new Beat(startTime + gap *153, "D"),
					new Beat(startTime + gap *155, "J"),
					new Beat(startTime + gap *157, "K"),
					new Beat(startTime + gap *161, "F"),
					new Beat(startTime + gap *166, "J"),
					new Beat(startTime + gap *168, "D"),
					new Beat(startTime + gap *170, "F"),
					new Beat(startTime + gap *172, "D"),
					new Beat(startTime + gap *174, "J"),
					new Beat(startTime + gap *176, "K"),
					new Beat(startTime + gap *180, "D"),
					new Beat(startTime + gap *182, "F"),
					new Beat(startTime + gap *185, "J"),
					new Beat(startTime + gap *187, "K"),
					new Beat(startTime + gap *190, "J"),
					new Beat(startTime + gap *192, "K"),
					new Beat(startTime + gap *194, "J"),
					new Beat(startTime + gap *197, "D"),
					new Beat(startTime + gap *200, "J"),
					new Beat(startTime + gap *201, "K"),
					new Beat(startTime + gap *203, "F"),
					new Beat(startTime + gap *204, "J"),
					new Beat(startTime + gap *206, "D"),
					new Beat(startTime + gap *208, "F"),
					new Beat(startTime + gap *211, "D"),
					new Beat(startTime + gap *213, "J"),
					new Beat(startTime + gap *216, "K"),
					new Beat(startTime + gap *219, "D"),
					new Beat(startTime + gap *221, "F"),
					new Beat(startTime + gap *224, "J"),
					new Beat(startTime + gap *225, "K"),
					new Beat(startTime + gap *227, "J"),
					new Beat(startTime + gap *230, "K"),
					new Beat(startTime + gap *232, "J"),
					new Beat(startTime + gap *234, "D"),
					new Beat(startTime + gap *237, "J"),
					new Beat(startTime + gap *239, "K"),
					new Beat(startTime + gap *241, "J"),
					new Beat(startTime + gap *243, "D"),
					
					new Beat(startTime + gap *253, "J"),
					new Beat(startTime + gap *253, "D"),
					new Beat(startTime + gap *256, "F"),
					new Beat(startTime + gap *256, "K"),
					new Beat(startTime + gap *259, "J"),
					new Beat(startTime + gap *259, "D"),
					new Beat(startTime + gap *262, "F"),
					new Beat(startTime + gap *262, "K"),
					new Beat(startTime + gap *265, "J"),
					new Beat(startTime + gap *265, "D"),
					new Beat(startTime + gap *268, "F"),
					new Beat(startTime + gap *268, "K"),
					
					new Beat(startTime + gap *270, "D"),
					new Beat(startTime + gap *272, "J"),
					new Beat(startTime + gap *274, "F"),
					new Beat(startTime + gap *276, "K"),
					
					new Beat(startTime + gap *280, "J"),
					new Beat(startTime + gap *280, "D"),
					new Beat(startTime + gap *283, "F"),
					new Beat(startTime + gap *283, "K"),
					new Beat(startTime + gap *286, "J"),
					new Beat(startTime + gap *286, "D"),
					new Beat(startTime + gap *289, "F"),
					new Beat(startTime + gap *289, "K"),
					new Beat(startTime + gap *292, "J"),
					new Beat(startTime + gap *292, "D"),
					new Beat(startTime + gap *295, "F"),
					new Beat(startTime + gap *295, "K"),
					
					new Beat(startTime + gap *297, "D"),
					new Beat(startTime + gap *299, "J"),
					new Beat(startTime + gap *301, "F"),
					new Beat(startTime + gap *303, "K"),
					
					new Beat(startTime + gap *309, "J"),
					new Beat(startTime + gap *309, "D"),
					new Beat(startTime + gap *312, "F"),
					new Beat(startTime + gap *312, "K"),
					new Beat(startTime + gap *315, "J"),
					new Beat(startTime + gap *315, "D"),
					new Beat(startTime + gap *318, "F"),
					new Beat(startTime + gap *318, "K"),
					new Beat(startTime + gap *321, "J"),
					new Beat(startTime + gap *321, "D"),
					new Beat(startTime + gap *323, "F"),
					new Beat(startTime + gap *323, "K"),
					
					new Beat(startTime + gap *326, "D"),
					new Beat(startTime + gap *328, "J"),
					new Beat(startTime + gap *329, "F"),
					new Beat(startTime + gap *331, "K"),
					new Beat(startTime + gap *333, "F"),
					new Beat(startTime + gap *334, "K"),
					new Beat(startTime + gap *335, "J"),
					new Beat(startTime + gap *337, "D"),
					
					new Beat(startTime + gap *349, "D"),
					new Beat(startTime + gap *353, "J"),
					new Beat(startTime + gap *354, "F"),
					new Beat(startTime + gap *356, "K"),
					new Beat(startTime + gap *358, "F"),
					new Beat(startTime + gap *359, "K"),
					new Beat(startTime + gap *361, "D"),
					new Beat(startTime + gap *362, "J"),
					new Beat(startTime + gap *364, "K"),
					new Beat(startTime + gap *365, "D"),
					
					new Beat(startTime + gap *381, "F"),
					new Beat(startTime + gap *383, "D"),
					new Beat(startTime + gap *384, "J"),
					new Beat(startTime + gap *386, "K"),
					new Beat(startTime + gap *389, "D"),
					new Beat(startTime + gap *391, "J"),
					new Beat(startTime + gap *393, "K"),
					new Beat(startTime + gap *396, "F"),
					new Beat(startTime + gap *398, "J"),
					new Beat(startTime + gap *401, "D"),
					new Beat(startTime + gap *403, "F"),
					new Beat(startTime + gap *406, "D"),
					new Beat(startTime + gap *410, "J"),
					new Beat(startTime + gap *411, "K"),
					new Beat(startTime + gap *413, "D"),
					new Beat(startTime + gap *417, "F"),
					new Beat(startTime + gap *420, "J"),
					new Beat(startTime + gap *424, "K"),
					new Beat(startTime + gap *426, "J"),
					new Beat(startTime + gap *429, "K"),
					new Beat(startTime + gap *431, "J"),
					new Beat(startTime + gap *433, "D"),
					new Beat(startTime + gap *435, "D"),
					new Beat(startTime + gap *438, "K"),
					new Beat(startTime + gap *441, "D"),
					new Beat(startTime + gap *441, "K"),
					new Beat(startTime + gap *444, "D"),
					new Beat(startTime + gap *444, "K"),
					
					new Beat(startTime + gap *447, "F"),
					
					new Beat(startTime + gap *451, "K"),
					
					new Beat(startTime + gap *454, "J"),
					
					new Beat(startTime + gap *457, "K"),
					
					new Beat(startTime + gap *461, "D"),
					new Beat(startTime + gap *463, "J"),
					new Beat(startTime + gap *157 + gap*310, "K"),
					new Beat(startTime + gap *161+ gap*310, "F"),
					new Beat(startTime + gap *166+ gap*310, "J"),
					new Beat(startTime + gap *168+ gap*310, "D"),
					new Beat(startTime + gap *170+ gap*310, "F"),
					new Beat(startTime + gap *172+ gap*310, "D"),
					new Beat(startTime + gap *174+ gap*310, "J"),
					new Beat(startTime + gap *176+ gap*310, "K"),
					new Beat(startTime + gap *180+ gap*310, "D"),
					new Beat(startTime + gap *182+ gap*310, "F"),
					new Beat(startTime + gap *185+ gap*310, "J"),
					new Beat(startTime + gap *187+ gap*310, "K"),
					new Beat(startTime + gap *190+ gap*310, "J"),
					new Beat(startTime + gap *192+ gap*310, "K"),
					new Beat(startTime + gap *194+ gap*310, "J"),
					new Beat(startTime + gap *197+ gap*310, "D"),
					new Beat(startTime + gap *200+ gap*310, "J"),
					new Beat(startTime + gap *201+ gap*310, "K"),
					new Beat(startTime + gap *203+ gap*310, "F"),
					new Beat(startTime + gap *204+ gap*310, "J"),
					new Beat(startTime + gap *206+ gap*310, "D"),
					new Beat(startTime + gap *208+ gap*310, "F"),
					new Beat(startTime + gap *211+ gap*310, "D"),
					new Beat(startTime + gap *213+ gap*310, "J"),
					new Beat(startTime + gap *216+ gap*310, "K"),
					new Beat(startTime + gap *219+ gap*310, "D"),
					new Beat(startTime + gap *221+ gap*310, "F"),
					new Beat(startTime + gap *224+ gap*310, "J"),
					new Beat(startTime + gap *225+ gap*310, "K"),
					new Beat(startTime + gap *227+ gap*310, "J"),
					new Beat(startTime + gap *230+ gap*310, "K"),
					new Beat(startTime + gap *232+ gap*310, "J"),
					new Beat(startTime + gap *234+ gap*310, "D"),
					new Beat(startTime + gap *237+ gap*310, "J"),
					new Beat(startTime + gap *239+ gap*310, "K"),
					new Beat(startTime + gap *241+ gap*310, "J"),
					new Beat(startTime + gap *243+ gap*310, "D"),
					new Beat(startTime + gap *554, "D"),
					
					new Beat(startTime + gap *557, "D"),
					new Beat(startTime + gap *558, "J"),
					new Beat(startTime + gap *559, "F"),
					new Beat(startTime + gap *561, "K"),
					
					new Beat(startTime + gap *3 + gap * 561, "F"),
					new Beat(startTime + gap *5 + gap * 561, "D"),
					new Beat(startTime + gap *8 + gap * 561, "F"),
					new Beat(startTime + gap *12 + gap * 561, "J"),
					new Beat(startTime + gap *15 + gap * 561, "D"),
					new Beat(startTime + gap *15 + gap * 561, "J"),
					new Beat(startTime + gap *25 + gap * 561, "K"),
					new Beat(startTime + gap *27 + gap * 561, "F"),
					new Beat(startTime + gap *29 + gap * 561, "J"),
					new Beat(startTime + gap *31 + gap * 561, "D"),
					new Beat(startTime + gap *34 + gap * 561, "F"),
					new Beat(startTime + gap *36 + gap * 561, "D"),
					new Beat(startTime + gap *39 + gap * 561, "J"),
					new Beat(startTime + gap *42 + gap * 561, "K"),
					new Beat(startTime + gap *45 + gap * 561, "D"),
					new Beat(startTime + gap *47 + gap * 561, "F"),
					new Beat(startTime + gap *51 + gap * 561, "J"),
					new Beat(startTime + gap *54 + gap * 561, "K"),
					new Beat(startTime + gap *56 + gap * 561, "J"),
					new Beat(startTime + gap *58 + gap * 561, "K"),
					new Beat(startTime + gap *61 + gap * 561, "J"),
					new Beat(startTime + gap *64 + gap * 561, "D"),
//					new Beat(startTime, "D"),
					new Beat(startTime + gap *68 + gap * 561, "F"),
					new Beat(startTime + gap *71 + gap * 561, "D"),
					new Beat(startTime + gap *73 + gap * 561, "J"),
					new Beat(startTime + gap *75 + gap * 561, "K"),
					new Beat(startTime + gap *78 + gap * 561, "D"),
					new Beat(startTime + gap *80 + gap * 561, "J"),
					new Beat(startTime + gap *84 + gap * 561, "K"),
					new Beat(startTime + gap *87 + gap * 561, "F"),
					new Beat(startTime + gap *89 + gap * 561, "J"),
					new Beat(startTime + gap *92 + gap * 561, "D"),
					new Beat(startTime + gap *94 + gap * 561, "F"),
					new Beat(startTime + gap *97 + gap * 561, "D"),
					new Beat(startTime + gap *101 + gap * 561, "J"),
					new Beat(startTime + gap *102 + gap * 561, "K"),
					new Beat(startTime + gap *103 + gap * 561, "D"),
					new Beat(startTime + gap *107 + gap * 561, "F"),
					new Beat(startTime + gap *110 + gap * 561, "J"),
					new Beat(startTime + gap *113 + gap * 561, "K"),
					new Beat(startTime + gap *115 + gap * 561, "J"),
					new Beat(startTime + gap *118 + gap * 561, "K"),
					new Beat(startTime + gap *120 + gap * 561, "J"),
					new Beat(startTime + gap *122 + gap * 561, "D"),
					
					new Beat(startTime + gap *127 + gap * 561, "K"),
					new Beat(startTime + gap *130 + gap * 561, "D"),
					new Beat(startTime + gap *131 + gap * 561, "K"),
					new Beat(startTime + gap *133 + gap * 561, "D"),
				
					new Beat(startTime + gap *136 + gap * 561, "D"),
					new Beat(startTime + gap *138 + gap * 561, "K"),
					new Beat(startTime + gap *140 + gap * 561, "D"),
					
					new Beat(startTime + gap *143 + gap * 561, "D"),
					
					new Beat(startTime + gap *146 + gap * 561, "D"),
					new Beat(startTime + gap *148 + gap * 561, "K"),
					
					new Beat(startTime + gap *150 + gap * 561, "K"),
					new Beat(startTime + gap *153 + gap * 561, "D"),
					new Beat(startTime + gap *155 + gap * 561, "J"),
					new Beat(startTime + gap *157 + gap * 561, "K"),
					new Beat(startTime + gap *161 + gap * 561, "F"),
					new Beat(startTime + gap *166 + gap * 561, "J"),
					new Beat(startTime + gap *168 + gap * 561, "D"),
					new Beat(startTime + gap *170 + gap * 561, "F"),
					new Beat(startTime + gap *172 + gap * 561, "D"),
					new Beat(startTime + gap *174 + gap * 561, "J"),
					new Beat(startTime + gap *176 + gap * 561, "K"),
					new Beat(startTime + gap *180 + gap * 561, "D"),
					new Beat(startTime + gap *182 + gap * 561, "F"),
					new Beat(startTime + gap *185 + gap * 561, "J"),
					new Beat(startTime + gap *187 + gap * 561, "K"),
					new Beat(startTime + gap *190 + gap * 561, "J"),
					new Beat(startTime + gap *192 + gap * 561, "K"),
					new Beat(startTime + gap *194 + gap * 561, "J"),
					new Beat(startTime + gap *197 + gap * 561, "D"),
					
					new Beat(startTime + gap *758 + gap * 10, "D"),
					new Beat(startTime + gap *758+ gap * 10, "J"),
					new Beat(startTime + gap *761+ gap * 10, "F"),
					new Beat(startTime + gap *761+ gap * 10, "K"),
					new Beat(startTime + gap *763+ gap * 10, "D"),
					new Beat(startTime + gap *763+ gap * 10, "J"),
					new Beat(startTime + gap *766+ gap * 10, "F"),
					new Beat(startTime + gap *766+ gap * 10, "K"),
					new Beat(startTime + gap *768+ gap * 10, "D"),
					new Beat(startTime + gap *768+ gap * 10, "J"),
					
					new Beat(startTime + gap *771+ gap * 10, "F"),
					new Beat(startTime + gap *772+ gap * 10, "K"),
					new Beat(startTime + gap *774+ gap * 10, "D"),
					new Beat(startTime + gap *777+ gap * 10, "J"),
					//--
					new Beat(startTime + gap *758 + gap * 33, "D"),
					new Beat(startTime + gap *758+ gap * 33, "J"),
					new Beat(startTime + gap *761+ gap * 33, "F"),
					new Beat(startTime + gap *761+ gap * 33, "K"),
					new Beat(startTime + gap *763+ gap * 33, "D"),
					new Beat(startTime + gap *763+ gap * 33, "J"),
					new Beat(startTime + gap *766+ gap * 33, "F"),
					new Beat(startTime + gap *766+ gap * 33, "K"),
					new Beat(startTime + gap *768+ gap * 33, "D"),
					new Beat(startTime + gap *768+ gap * 33, "J"),
					
					new Beat(startTime + gap *769+ gap * 33, "D"),
					new Beat(startTime + gap *771+ gap * 35, "F"),
					new Beat(startTime + gap *772+ gap * 35, "K"),
					new Beat(startTime + gap *774+ gap * 35, "D"),
					new Beat(startTime + gap *777+ gap * 35, "J"),
					new Beat(startTime + gap *779+ gap * 35, "F"),
//--
					new Beat(startTime + gap *758 + gap * 60, "D"),
					new Beat(startTime + gap *758+ gap * 60, "J"),
					new Beat(startTime + gap *761+ gap * 60, "F"),
					new Beat(startTime + gap *761+ gap * 60, "K"),
					new Beat(startTime + gap *763+ gap * 60, "D"),
					new Beat(startTime + gap *763+ gap * 60, "J"),
					new Beat(startTime + gap *766+ gap * 60, "F"),
					new Beat(startTime + gap *766+ gap * 60, "K"),
					new Beat(startTime + gap *768+ gap * 60, "D"),
					new Beat(startTime + gap *768+ gap * 60, "J"),
					
					new Beat(startTime + gap *769+ gap * 65, "D"),
					new Beat(startTime + gap *772+ gap * 65, "F"),
					new Beat(startTime + gap *774+ gap * 65, "K"),
					new Beat(startTime + gap *777+ gap * 65, "D"),
					new Beat(startTime + gap *778+ gap * 65, "J"),
					new Beat(startTime + gap *780+ gap * 65, "F"),
					
					new Beat(startTime + gap *758 + gap * 90, "D"),
					new Beat(startTime + gap *758+ gap * 90, "J"),
					new Beat(startTime + gap *761+ gap * 90, "F"),
					new Beat(startTime + gap *761+ gap * 90, "K"),
					new Beat(startTime + gap *763+ gap * 90, "D"),
					new Beat(startTime + gap *763+ gap * 90, "J"),
					new Beat(startTime + gap *766+ gap * 90, "F"),
					new Beat(startTime + gap *766+ gap * 90, "K"),
					new Beat(startTime + gap *768+ gap * 90, "D"),
					new Beat(startTime + gap *768+ gap * 90, "J"),
					
					new Beat(startTime + gap *769+ gap * 94, "D"),
					new Beat(startTime + gap *771+ gap * 94, "F"),
					new Beat(startTime + gap *772+ gap * 94, "K"),
					new Beat(startTime + gap *774+ gap * 94, "D"),
					new Beat(startTime + gap *777+ gap * 94, "J"),
					new Beat(startTime + gap *779+ gap * 94, "F"),
					
//					new Beat(startTime + gap *200 + gap * 561, "J"),
//					new Beat(startTime + gap *201 + gap * 561, "K"),
//					new Beat(startTime + gap *203 + gap * 561, "F"),
//					new Beat(startTime + gap *204 + gap * 561, "J"),
//					new Beat(startTime + gap *206 + gap * 561, "D"),
//					new Beat(startTime + gap *208 + gap * 561, "F"),
//					new Beat(startTime + gap *211 + gap * 561, "D"),
//					new Beat(startTime + gap *213 + gap * 561, "J"),
//					new Beat(startTime + gap *216 + gap * 561, "K"),
//					new Beat(startTime + gap *219 + gap * 561, "D"),
//					new Beat(startTime + gap *221 + gap * 561, "F"),
//					new Beat(startTime + gap *224 + gap * 561, "J"),
//					new Beat(startTime + gap *225 + gap * 561, "K"),
//					new Beat(startTime + gap *227 + gap * 561, "J"),
//					new Beat(startTime + gap *230 + gap * 561, "K"),
//					new Beat(startTime + gap *232 + gap * 561, "J"),
//					new Beat(startTime + gap *234 + gap * 561, "D"),
//					new Beat(startTime + gap *237 + gap * 561, "J"),
//					new Beat(startTime + gap *239 + gap * 561, "K"),
//					new Beat(startTime + gap *241 + gap * 561, "J"),
//					new Beat(startTime + gap *243 + gap * 561, "D"),
					
					
			};
			
			
			
		}
		else if (titleName.equals("Song3")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		int i=0;
		try {
			Thread.sleep(2000);
			gameMusic.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void judge(String input) {
		for (int i=0; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {

		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge5.png")).getImage();
		} else if(judge.equals("Not bad")) {
			score += 30;
//			count ++;
			sc = Integer.toString(score);
//			c = Integer.toString(count);
			judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge4.png")).getImage();
		} else if(judge.equals("Good")) {
			score += 50;
//			count ++;
			sc = Integer.toString(score);
//			c = Integer.toString(count);
			judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge3.png")).getImage();
		} else if(judge.equals("Great")) {
			score += 70;
//			count ++;
			sc = Integer.toString(score);
//			c = Integer.toString(count);
			judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge2.png")).getImage();
		} else if(judge.equals("Perfect")) {
			score += 100;
//			count ++;
			sc = Integer.toString(score);
//			c = Integer.toString(count);
			judgeImage = new ImageIcon(Main.class.getResource("../Images2/judge1.png")).getImage();
		}
//		else {
			// 여기에 count 초기화 썻음 앞에 if절업셍고
//		}
	}
	public String scorePrint() {
		return this.sc;
	}
	public String countPrint() {
		return this.c;
	}
}

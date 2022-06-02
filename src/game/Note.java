package game;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import CPA_TEAM_11.Main;
import client.ClientUI;

public class Note extends Thread{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../Images2/note3.png")).getImage();
	private Image noteBasicImage2 = new ImageIcon(Main.class.getResource("../Images2/note2.png")).getImage();
	private int x , y = 660 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	static public boolean Otherp;
   private boolean proceeded =true;
   
   public boolean isProceeded() {
      return proceeded;
   }
   public void close() {
      proceeded=false;
   }
   public String getNoteType() {
      return noteType;
   }
   public Note(String noteType) {
	   if(noteType.equals("D")) {
			x=343;
		} else if(noteType.equals("F")) {
			x=493;
		} else if(noteType.equals("J")) {
			x=643;
		} else if(noteType.equals("K")) {
			x=793;
		} else if (noteType.equals("E")) {
			x=2000;
		}
		this.noteType = noteType;
   
      
   }
   public void screenDraw(Graphics2D g) {
	   if(noteType.equals("D") || noteType.equals("J")) {
			g.drawImage(noteBasicImage,x,y,null);
		} else {
			g.drawImage(noteBasicImage2,x,y,null);
		}
   }
   @Override
   public void run() {
      try {
         while(true) {
            drop();
            if(proceeded) {
               Thread.sleep(ClientUI.NOTE_SLEEP);
            }else {
               interrupt();
               break;
            }
            Thread.sleep(Main.SLEEP_TIME);
         }
      }catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }
   
   public void drop() { //좀삐리함
	   y += Main.NOTE_SPEED;
		if(y > 660) {
			close();
		}
   }
   public String judge() {
	      if(y>=613) {
	         System.out.println("Perfect");
	         ClientUI.net.sendNoteJudge("Perfect");
	         ClientUI.net.sendScoreRequest(GameFrame.game.score);
	         close();
	         if(Otherp) {
	        	 GameFrame.game.score += 1000;
	        	 return "Perfect";
	         } else {
	        	 return "Bad";
	         }
	         
	      }else if(y >= 590) {
	    	  System.out.println("Perfect");
		         ClientUI.net.sendNoteJudge("Perfect");
		         ClientUI.net.sendScoreRequest(GameFrame.game.score);
		         close();
		         if(Otherp) {
		        	 GameFrame.game.score += 1000;
		        	 return "Perfect";
		         } else {
		        	 return "Bad";
		         }
	      }else if( y >= 578) {
	    	  System.out.println("Perfect");
		         ClientUI.net.sendNoteJudge("Perfect");
		         ClientUI.net.sendScoreRequest(GameFrame.game.score);
		         close();
		         if(Otherp) {
		        	 GameFrame.game.score += 1000;
		        	 return "Perfect";
		         } else {
		        	 return "Bad";
		         }
	      } else {
	         System.out.println("None");
	         return "None";
	      }
	   }
   public int getY() {
      return y;
   }
   
}